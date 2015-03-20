package com.epam.bp.autobase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("NonJREEmulationClassesInClientCode")
public class DateParser {
    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String DATE_DB_PATTERN = "yyyy-MM-dd";

    public static Date dateFromString(String formattedString) {
        if (formattedString == null) return null;
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            result = sdf.parse(formattedString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String stringFromDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(date);
    }

    public static String stringFromDateTime(Date dateWithTime) {
        if (dateWithTime == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return sdf.format(dateWithTime);
    }

    public static Date dateTimeFromString(String dateWithTime) {
        if (dateWithTime == null) return null;
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
        try {
            result = sdf.parse(dateWithTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date dateWithOffset(Date startDate, Integer offsetDaysCount) {
        Calendar result = Calendar.getInstance();
        result.setTime(startDate);
        result.add(Calendar.DATE, offsetDaysCount);
        return result.getTime();
    }
}
