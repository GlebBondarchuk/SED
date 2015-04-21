package com.bsu.sed.dto;

import java.util.Date;

/**
 * @author gbondarchuk
 */
public class LineChartStatisticsDto {
    private Date date;
    private long amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
