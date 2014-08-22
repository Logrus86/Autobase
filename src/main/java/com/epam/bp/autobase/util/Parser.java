package com.epam.bp.autobase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Date StringToDate(String stringDate) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            result = sdf.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO add own exception and logging (in it)
        }
        return result;
    }
}
