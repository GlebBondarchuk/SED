package com.bsu.sed.dto;

/**
 * @author gbondarchuk
 */
public class PieChartStatisticsDto {
    private String country;
    private long amount;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
