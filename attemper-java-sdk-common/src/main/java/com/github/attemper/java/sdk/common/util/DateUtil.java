package com.github.attemper.java.sdk.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil{

	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS="yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * 将日期转换为yyyy-MM-dd HH:mm:ss SSS的格式<br> 比如 2018-01-01 13:45:23 123
	 * @return 若date为空，默认返回当前日期的字符串
	 */
	public static String formatDateToYYYYMMDDHHMMSSSSS() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSSSSS);
		return sdf.format(new Date());
	}

	/**
	 * 将日期字符串转换为yyyy-MM-dd HH:mm:ss SSS的格式的日期
	 * @param dateStr
	 * @return
	 */
	public static synchronized Date parseDateStrToYYYYMMDDHHMMSSSSS(String dateStr) {
		if(dateStr == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSSSSS);
		Date date;
		try{
			date = sdf.parse(dateStr);
		}catch (Exception e){
			date = null;
		}
		return date;
	}
}
