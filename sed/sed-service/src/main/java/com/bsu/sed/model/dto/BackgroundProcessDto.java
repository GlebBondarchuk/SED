package com.bsu.sed.model.dto;

import com.bsu.sed.model.BackgroundProcessKey;

import java.util.Date;

/**
 * @author gbondarchuk
 */
public class BackgroundProcessDto {
    private BackgroundProcessKey processKey;
    private String displayName;
    private String description;
    private String cron;
    private Date nextExecutionTime;
    private boolean executing;
    private boolean disabled;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Date getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void setNextExecutionTime(Date nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    public BackgroundProcessKey getProcessKey() {
        return processKey;
    }

    public void setProcessKey(BackgroundProcessKey processKey) {
        this.processKey = processKey;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isExecuting() {
        return executing;
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }
}
