package com.bsu.sed.listener;

import com.bsu.sed.service.schedule.ScheduleService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.text.ParseException;

/**
 * @author gbondarchuk
 */
public class SedApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static boolean INITIALIZED;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!INITIALIZED) {
            scheduleProcesses();
        }
        INITIALIZED = true;
    }

    private void scheduleProcesses() {
        try {
            scheduleService.init();
        } catch (ClassNotFoundException | NoSuchMethodException | ParseException | SchedulerException e) {
            e.printStackTrace();
        }
    }
}
