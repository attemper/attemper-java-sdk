package com.github.attemper.java.sdk.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date util
 */
public class DateUtil {

    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * format date string to date of yyyy-MM-dd
     *
     * @param dateStr like 2019-09-09
     * @return date
     */
    public static synchronized Date parseDateStrToYYYYMMDD(String dateStr) {
        return getDate(dateStr, DATE_FORMAT_YYYYMMDD);
    }

    /**
     * format date string to date of yyyy-MM-dd HH:mm:ss SSS
     *
     * @param dateStr like 2019-09-09 12:59:01 012
     * @return date
     */
    public static synchronized Date parseDateStrToYYYYMMDDHHMMSSSSS(String dateStr) {
        return getDate(dateStr, DATE_FORMAT_YYYYMMDDHHMMSSSSS);
    }

    private static Date getDate(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }
}
