package com.epam.bp.autobase.gwt.util;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

// GWT-analogue of com.epam.bp.autobase.util.DateParser, uses [GWT] DateTimeFormat instead of [JRE] SimpleDateFormat
public class DateParser {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static Date dateFromString(String formattedString) {
        if (formattedString == null) return null;
        DateTimeFormat dtf = new DateTimeFormat(DATE_PATTERN) {
        };
        return dtf.parse(formattedString);
    }

    public static String stringFromDate(Date date) {
        if (date == null) return null;
        DateTimeFormat dtf = new DateTimeFormat(DATE_PATTERN) {
        };
        return dtf.format(date);
    }

    public static String stringFromDateTime(Date dateWithTime) {
        if (dateWithTime == null) return null;
        DateTimeFormat dtf = new DateTimeFormat(TIMESTAMP_PATTERN) {
        };
        return dtf.format(dateWithTime);
    }

    public static Date dateTimeFromString(String dateWithTime) {
        if (dateWithTime == null) return null;
        DateTimeFormat dtf = new DateTimeFormat(TIMESTAMP_PATTERN) {
        };
        return dtf.parse(dateWithTime);
    }

}
