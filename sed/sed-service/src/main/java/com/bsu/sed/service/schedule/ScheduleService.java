package com.bsu.sed.service.schedule;

import com.bsu.sed.model.BackgroundProcessKey;
import com.bsu.sed.model.dto.BackgroundProcessDto;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface ScheduleService {
    void init() throws SchedulerException, NoSuchMethodException, ParseException, ClassNotFoundException;

    void disableProcess(BackgroundProcessKey processKey) throws SchedulerException;

    void enableProcess(BackgroundProcessKey processKey) throws SchedulerException;

    void rescheduleProcess(BackgroundProcessKey processKey, String cron) throws ParseException, SchedulerException;

    void startNow(BackgroundProcessKey processKey) throws SchedulerException;

    List<BackgroundProcessDto> find() throws ParseException, SchedulerException;
}
