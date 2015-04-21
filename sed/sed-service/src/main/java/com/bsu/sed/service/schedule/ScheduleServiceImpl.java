package com.bsu.sed.service.schedule;

import com.bsu.sed.dao.BackgroundProcessDao;
import com.bsu.sed.model.BackgroundProcessKey;
import com.bsu.sed.model.dto.BackgroundProcessDto;
import com.bsu.sed.model.persistent.BackgroundProcess;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private static final String JOB_EXECUTION_METHOD = "execute";

    @Autowired
    private BackgroundProcessDao backgroundProcessDao;
    @Autowired
    private ProcessDynamicMapper processDynamicMapper;

    @Qualifier("scheduler")
    @Autowired
    private Scheduler scheduler;

    @Override
    public void init() throws SchedulerException, NoSuchMethodException, ParseException, ClassNotFoundException {
        List<BackgroundProcess> processes = backgroundProcessDao.getAll();
        for (BackgroundProcess process : processes) {
            String processName = process.getProcess().name();
            String cron = process.getCron();
            Object targetClass = processDynamicMapper.getTargetClass(process.getProcess());
            scheduleProcess(processName, targetClass, cron);
            if (process.isDisabled()) {
                pauseProcess(processName);
            }
        }
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
    }

    @Override
    public void disableProcess(BackgroundProcessKey processKey) throws SchedulerException {
        pauseProcess(processKey.name());
        backgroundProcessDao.disableProcess(processKey);
    }

    @Override
    public void enableProcess(BackgroundProcessKey processKey) throws SchedulerException {
        resumeProcess(processKey.name());
        backgroundProcessDao.enableProcess(processKey);
    }

    @Override
    public void rescheduleProcess(BackgroundProcessKey processKey, String cron) throws ParseException, SchedulerException {
        rescheduleProcess(processKey.name(), cron);
        backgroundProcessDao.rescheduleProcess(processKey, cron);
    }

    @Override
    public void startNow(BackgroundProcessKey processKey) throws SchedulerException {
        scheduler.triggerJob(JobKey.jobKey(processKey.name(), Scheduler.DEFAULT_GROUP));
    }

    private boolean isProcessExecuting(List<JobExecutionContext> executionProcesses, String processName) {
        for (JobExecutionContext context : executionProcesses) {
            if (context.getJobDetail().getKey().getName().equals(processName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BackgroundProcessDto> find() throws ParseException, SchedulerException {
        List<BackgroundProcess> processes = backgroundProcessDao.getAll();
        List<JobExecutionContext> executionJobs = scheduler.getCurrentlyExecutingJobs();
        List<BackgroundProcessDto> values = new ArrayList<>();
        for (BackgroundProcess process : processes) {
            BackgroundProcessDto dto = new BackgroundProcessDto();
            dto.setCron(process.getCron());
            dto.setDisplayName(process.getDisplayName());
            dto.setDescription(process.getDescription());
            dto.setProcessKey(process.getProcess());
            dto.setDisabled(process.isDisabled());
            CronExpression cronExpression = new CronExpression(process.getCron());
            dto.setNextExecutionTime(cronExpression.getNextValidTimeAfter(new Date()));
            dto.setExecuting(isProcessExecuting(executionJobs, process.getProcess().name()));
            values.add(dto);
        }
        return values;
    }

    private void scheduleProcess(String processName, Object targetClass, String cron) throws NoSuchMethodException, ClassNotFoundException, ParseException, SchedulerException {
        MethodInvokingJobDetailFactoryBean process = createDefaultProcess();
        process.setName(processName);
        process.setTargetObject(targetClass);
        process.afterPropertiesSet();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .withIdentity(processName + "Trigger")
                .build();
        scheduler.scheduleJob(process.getObject(), cronTrigger);
    }

    private void pauseProcess(String processName) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(processName + "Trigger", Scheduler.DEFAULT_GROUP));
//        scheduler.pauseJob(JobKey.jobKey(processName, Scheduler.DEFAULT_GROUP));
    }

    private void resumeProcess(String processName) throws SchedulerException {
        scheduler.resumeTrigger(TriggerKey.triggerKey(processName + "Trigger", Scheduler.DEFAULT_GROUP));
//        scheduler.resumeJob(JobKey.jobKey(processName, Scheduler.DEFAULT_GROUP));
    }

    private void rescheduleProcess(String processName, String cron) throws ParseException, SchedulerException {
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .withIdentity(processName + "Trigger")
                .build();
        scheduler.rescheduleJob(TriggerKey.triggerKey(processName + "Trigger", Scheduler.DEFAULT_GROUP), cronTrigger);
        if(backgroundProcessDao.isDisabled(BackgroundProcessKey.valueOf(processName))) {
            pauseProcess(processName);
        }
    }

    private MethodInvokingJobDetailFactoryBean createDefaultProcess() {
        MethodInvokingJobDetailFactoryBean job = new MethodInvokingJobDetailFactoryBean();
        job.setConcurrent(false);
        job.setTargetMethod(JOB_EXECUTION_METHOD);
        return job;
    }

    @PreDestroy
    private void destroy() throws SchedulerException {
        scheduler.shutdown(false);
    }
}
