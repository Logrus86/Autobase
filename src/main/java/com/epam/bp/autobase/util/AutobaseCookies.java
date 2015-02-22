package com.epam.bp.autobase.util;

import java.util.Calendar;
import java.util.Date;

public class AutobaseCookies {
    public static final String NAME_UUID = "uuid";
    public static final String NAME_LANG = "language";
    public static final Integer MAX_AGE_UUID = 60 * 60 * 24 * 30;
    public static final Integer MAX_AGE_LANG = MAX_AGE_UUID;

    public static Date getMaxAgeUuidDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, MAX_AGE_UUID / (60 * 60 * 24));
        return c.getTime();
    }

}
