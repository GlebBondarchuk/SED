package com.bsu.sed.dao.generic;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author gbondarchuk
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < 31; ++i) {
            Date date = new Date();
            Date newDate = DateUtils.addDays(date, i - 1);
            newDate = DateUtils.setHours(newDate, 0);
            newDate = DateUtils.setMinutes(newDate, 0);
            newDate = DateUtils.setSeconds(newDate, 0);
            System.out.println("[" + newDate.getTime() + "," + (int)(100 * (Math.random())) + "],");
        }
    }
}
