package com.bsu.sed.service.process;

import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

/**
 * @author gbondarchuk
 */
public abstract class ProcessExecutor {
    protected abstract void execute() throws InterruptedException;
}
