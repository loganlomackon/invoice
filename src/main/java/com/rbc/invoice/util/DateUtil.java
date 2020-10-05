package com.rbc.invoice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	public static String DEFAULT_ZONE_ID = "Asia/Taipei";
	public static String UTC_ZONE_ID = "UTC";
	
	public static String convertDateToStringUntilSeconds(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	public static String convertDateToUtcStringUntilSeconds(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(UTC_ZONE_ID));
		return format.format(date);
	}
	public static String convertDateToDayString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	
	public static Date convertDayStringToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date convertDayStringUntilSecondsToDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date convertDayStringUntilSecondsToDateWithTimeZone(String dateString) {
		if (dateString == null) {
			return null;
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		try {
			return format.parse(dateString);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String convertDateToStringUntilSecondsWithTimeZone(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		return format.format(date);
	}
	
	public static Date getFirstDayOfMonthUTC(Date input) {
		Calendar date = Calendar.getInstance();
		date.setTimeZone(TimeZone.getTimeZone(UTC_ZONE_ID));
		date.setTime(input);
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date.getTime();
	}
	
	public static Date getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(UTC_ZONE_ID));
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal.getTime();
	}
	public static Date getDateFromDate(Date date, int field, int diff) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, diff);
		return cal.getTime();
	}
	
	public static Date setDate(Integer year, Integer mon, Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE_ID));
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, mon);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
}
