package com.bsu.sed.service.process;

/**
 * @author gbondarchuk
 */
public abstract class ProcessExecutor {
    protected abstract void execute() throws InterruptedException;
}
