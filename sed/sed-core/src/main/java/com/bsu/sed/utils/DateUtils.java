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

    private static final String DOCUMENT_DATE_FORMAT = "yyyy/MM/dd";

    public static final String TIME_ZONE = "CST";

    private static SimpleDateFormat getDateTimeFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return dateFormat;
    }

    private static SimpleDateFormat getDocumentDateFormat() {
        return new SimpleDateFormat(DOCUMENT_DATE_FORMAT);
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

    public static String getDocumentDate() {
        Date now = new Date();
        return getDocumentDateFormat().format(now);
    }
}
