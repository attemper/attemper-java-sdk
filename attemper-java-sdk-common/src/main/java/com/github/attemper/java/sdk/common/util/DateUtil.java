package com.github.attemper.java.sdk.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * format date string to date of yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static synchronized Date parseDateStrToYYYYMMDD(String dateStr) {
        return getDate(dateStr, DATE_FORMAT_YYYYMMDD);
    }

    /**
     * format date string to date of yyyy-MM-dd HH:mm:ss SSS
     *
     * @param dateStr
     * @return
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
