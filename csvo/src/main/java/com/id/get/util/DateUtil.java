/**
 * <br>项目名: idget
 * <br>文件名: Constant.java
 * <br>Copyright 
 */
package com.id.get.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * <br>类 名: Constant 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2015年12月14日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public final class DateUtil {
	
	/**
	 * Slf4j logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String DATE_DASH_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";
	
	/**
	 * Date Format without time: yyyyMMdd
     */
	public static final String DATE_SIMPLE_FORMAT= "yyyyMMdd";
	
	/**
	 * Date Format with time: HH:mm
	 */
	public static final String TIME_MINITE_FORMAT = "HH:mm";
	
	public static final String TIME_SECOND_FORMAT = "HH:mm:ss";
	
	public static final String DATE_TIME_DASH_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Date Format with time: yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_TIME_SLASH_SECOND_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * Date Format with time: yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String DATE_TIME_DASH_MILLI_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Date Format with time: yyyyMMddHHmmss
	 */
	public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

	/**
	 * Date Format of weekday: E
	 */
	public static final String FORMAT_WEERK_DAY = "E";

	/**
	 * Time separator: ':'
	 */
	public static final String TIME_SEPARATOR = ":";

	/**
	 * 类名: WeekDay<br>
	 */
	public enum WeekDay {
		SUN(0, "星期天"), MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4,
				"星期四"), FRI(5, "星期五"), SAT(6, "星期六");

		private int index;
		private String value;

		private WeekDay(int index, String value) {
			this.index = index;
			this.value = value;
		}

		/**
		 * 获取星期枚举的序号
		 * 
		 * @return int
		 */
		public int getIndex() {
			return this.index;
		}

		/**
		 * 获取星期枚举的描述（中文）
		 * 
		 * @return String
		 */
		public String getValue() {
			return this.value;
		}

		/**
		 * 重写toString方法
		 */
		public String toString() {
			return this.value;
		}
	}

	/**
	 * 将Date对象按指定的时间格式解析成String
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		if (date == null || StringUtils.isBlank(pattern)) {
			return null;
		}

		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 将Date对象按指定的时间格式解析成String
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @param local
	 *            Local, 地区格式
	 * @return String
	 */
	public static String format(Date date, String pattern, Locale locale) {
		if (date == null || StringUtils.isBlank(pattern) || locale == null) {
			return null;
		}

		return new SimpleDateFormat(pattern, locale).format(date);
	}

	/**
	 * 将Date对象按指定的时间格式解析成String，若Date对象为空，则返回当前时间
	 * 
	 * @param date
	 *            Date
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return String
	 */
	public static String formatAdvance(Date date, String pattern) {
		if (date == null) {
			date = new Date();
		}

		return format(date, pattern);
	}

	/**
	 * 将String按指定的时间格式转化成Date对象
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return Date
	 */
	public static Date parse(String str, String pattern) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(pattern)) {
			return null;
		}

		Date date = null;
		try {
			date = new SimpleDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 将String按指定的时间格式转化成Date对象，若String为空，则返回当前时间
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return Date
	 */
	public static Date parseAdvance(String str, String pattern) {
		if (StringUtils.isBlank(str)) {
			return new Date();
		}

		return parse(str, pattern);
	}

	/**
	 * 获取两个时间点的间隔时长（秒），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            Date
	 * @param after
	 *            Date
	 * @return long 时间间隔（秒）
	 */
	public static long compareSec(Date before, Date after) {
		if (before == null || after == null) {
			return 0l;
		}
		long dif = after.getTime() - before.getTime();
		dif = Math.abs(dif);
		return dif / 1000;
	}

	/**
	 * 获取两个时间点的间隔时长（秒），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            String
	 * @param after
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return long 时间间隔（秒）
	 */
	public static long compareSec(String before, String after, String pattern) {
		if (StringUtils.isBlank(before) || StringUtils.isBlank(after)
				|| StringUtils.isBlank(pattern)) {
			return 0l;
		}

		Date beforeDate = parse(before, pattern);
		Date afterDate = parse(after, pattern);
		return compareSec(beforeDate, afterDate);
	}

	/**
	 * 获取两个时间点的间隔时长（分钟），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            Date
	 * @param after
	 *            Date
	 * @return long 时间间隔（分钟）
	 */
	public static long compareMin(Date before, Date after) {
		return compareSec(before, after) / 60;
	}

	/**
	 * 获取两个时间点的间隔时长（分钟），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            String
	 * @param after
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return long 时间间隔（分钟）
	 */
	public static long compareMin(String before, String after, String pattern) {
		return compareSec(before, after, pattern) / 60;
	}

	/**
	 * 获取两个时间点的间隔时长（小时），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            Date
	 * @param after
	 *            Date
	 * @return long 时间间隔（小时）
	 */
	public static long compareHour(Date before, Date after) {
		return compareMin(before, after) / 60;
	}

	/**
	 * 获取两个时间点的间隔时长（小时），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            String
	 * @param after
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return long 时间间隔（小时）
	 */
	public static long compareHour(String before, String after, String pattern) {
		return compareMin(before, after, pattern) / 60;
	}

	/**
	 * 获取两个时间点的间隔时长（天），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            Date
	 * @param after
	 *            Date
	 * @return long 时间间隔（天）
	 */
	public static long compareDay(Date before, Date after) {
		return compareHour(before, after) / 24;
	}

	/**
	 * 获取两个时间点的间隔时长（天），不区分先后顺序，即不会返回负值
	 * 
	 * @param before
	 *            String
	 * @param after
	 *            String
	 * @param pattern
	 *            String, 时间格式, 如yyyy/MM/dd HH:mm:ss
	 * @return long 时间间隔（天）
	 */
	public static long compareDay(String before, String after, String pattern) {
		return compareHour(before, after, pattern) / 24;
	}

	/**
	 * 获取入参日期是星期几
	 * 
	 * @param date
	 *            Date
	 * @return WeekDay 星期枚举
	 */
	public static WeekDay convertWeek(Date date) {
		if (date == null) {
			return null;
		}

		String eDate = new SimpleDateFormat("E").format(date);
		for (WeekDay weekday : WeekDay.values()) {
			if (weekday.getValue().equals(eDate)) {
				return weekday;
			}
		}
		return null;
	}

	/**
	 * 获取指定时间间隔分钟后的时间
	 * 
	 * @param date
	 *            Date
	 * @param min
	 *            int
	 * @return Date
	 */
	public static Date addMinutes(Date date, int min) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	/**
	 * 获取指定时间间隔秒后的时间
	 * 
	 * @param date
	 *            Date
	 * @param min
	 *            int
	 * @return Date
	 */
	public static Date addSecond(Date date, int sec) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, sec);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间间隔分钟后的时间
	 * 
	 * @param min
	 *            int
	 * @return Date
	 */
	public static Date addMinutes(int min) {
		return addMinutes(new Date(), min);
	}

	/**
	 * 获取当前时间间隔秒后的时间
	 * 
	 * @param min
	 *            int
	 * @return Date
	 */
	public static Date addSecond(int sec) {
		return addSecond(new Date(), sec);
	}

	/**
	 * 获取当前时间对应的月份
	 * 
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int month = now.get(Calendar.MONTH);
		return month;
	}

	/**
	 * 判断两个时间是否处于同年同周
	 * 
	 * @param first
	 *            Date
	 * @param second
	 *            Date
	 * @return boolean
	 */
	public static boolean inSameWeek(Date first, Date second) {
		if (first == null || second == null) {
			return false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(first);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(second);
		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.WEEK_OF_YEAR) == c2
						.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 判断两个时间是否处于同年同月同日
	 * 
	 * @param first
	 *            Date
	 * @param second
	 *            Date
	 * @return boolean
	 */
	public static boolean isSameDay(Date first, Date second) {
		return ((format(first, DATE_SIMPLE_FORMAT).substring(0, 8)).equals(format(
				second, DATE_SIMPLE_FORMAT).substring(0, 8)));
	}

	/**
	 * 获取当日零点时间
	 * 
	 * @return long
	 */
	public static long getInitTimeToday() {
		return getInitTime(new Date());
	}

	/**
	 * 获取指定日前的零点时间
	 * 
	 * @param date
	 *            Date
	 * @return long
	 */
	public static long getInitTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}


	/**
	 * 描 述：将当前时间转换成指定的格式字符串<br/>
	 * 作 者：王天一<br/>
	 * 历 史: (版本) 作者 时间 注释 <br/>
	 * 
	 * @param pattern
	 * @return String
	 */
	public static String getCurrentTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 
	 * 描 述：根据传入的时间获取某一天的开始时间<br/>
	 * 历 史: (版本) 作者 时间 注释 <br/>
	 * @param date
	 * 			传入的时间
	 * @param day
	 * 			传入时间的某一天
	 * @return
	 */
	public static Date getSomeDayStartTime(Date date, int day) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.add(Calendar.DAY_OF_MONTH, day);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();	
	}
	
	/**
	 * 
	 * 描 述：根据传入的时间获取某一天的结束时间<br/>
	 * 历 史: (版本) 作者 时间 注释 <br/>
	 * @param date
	 * 			传入的时间
	 * @param day
	 * 			传入时间的某一天
	 * @return
	 */
	public static Date getSomeDayEndTime(Date date, int day) {
		Calendar dateEnd = Calendar.getInstance();
		dateEnd.setTime(date);
		dateEnd.add(Calendar.DAY_OF_MONTH, day);
		dateEnd.set(Calendar.HOUR_OF_DAY, 23);
		dateEnd.set(Calendar.MINUTE, 59);
		dateEnd.set(Calendar.SECOND, 59);
		dateEnd.set(Calendar.MILLISECOND, 0);
		return dateEnd.getTime();
	}


	/**
	 * 
	 * 描 述： 返回昨天的开始时间 例如：2013-06-24 00:00:00<br/>
	 * @return
	 */
	public static Date getYesterdayStartTime(){
		return getSomeDayStartTime(new Date(), -1);
	}
	
	/**
	 * 
	 * 描 述： 返回昨天的结束时间 例如：2013-06-24 23:59:59<br/>
	 * @return
	 */
	public static Date getYesterdayzEndTime(){
		return getSomeDayEndTime(new Date(), -1);
	}
	
	public static Date formateStringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setLenient(false);
		sdf.applyPattern(DATE_DASH_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			try {
				sdf.applyPattern(DATE_SIMPLE_FORMAT);
				date = sdf.parse(dateString);
			} catch (ParseException e1) {
				try {
					sdf.applyPattern(DATE_TIME_DASH_SECOND_FORMAT);
					date = sdf.parse(dateString);
				} catch (ParseException e2) {
					LOGGER.error("不支持格式化日期：{}", new Object[]{dateString});
				}
			}
		}
		return date;
	}
}
