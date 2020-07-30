package com.imshhui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * User: liyulin
 */
public class Utils {

    public static final String PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    public static String formatModify(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = new Date(time);
        return sdf.format(d);
    }

    public static boolean isUnModified(String modifiedTime, long lastModified) {
        if (Objects.isNull(modifiedTime)) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.parse(modifiedTime).getTime() / 1000 == lastModified / 1000;
        } catch (ParseException e) {
            return false;
        }
    }
}
