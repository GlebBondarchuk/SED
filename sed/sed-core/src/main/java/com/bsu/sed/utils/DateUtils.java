package com.bsu.sed.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author gbondarchuk
 */
public class DateUtils {
    private static final String SYSTEM_DATE_FORMAT = "dd/MM/yyyy HH:mm";

    public static final String TIME_ZONE = "CST";

    private static SimpleDateFormat getDateTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return dateFormat;
    }

    public static Date parse(String date) {
        try {
            SimpleDateFormat format = getDateTimeFormat();
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
