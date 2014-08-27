package com.epam.bp.autobase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date StringToDate(String stringDate) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            result = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return sdf.format(date);
    }
}
