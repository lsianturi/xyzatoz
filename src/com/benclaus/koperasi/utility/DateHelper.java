package com.benclaus.koperasi.utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class DateHelper {
	private int day;
	private int month;
	private int year;
	private static final String DATE_PATTERN = "MM/yy";

	public DateHelper(String date) {
		try {
			day = Integer.parseInt(date.substring(0, 2));
			month = Integer.parseInt(date.substring(3, 5));
			year = Integer.parseInt(date.substring(6, 10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static int getLastDayOfMonth(String dateString) {
	    DateTimeFormatter pattern = DateTimeFormatter.ofPattern(DATE_PATTERN);
	    YearMonth yearMonth = YearMonth.parse(dateString, pattern);
	    LocalDate date = yearMonth.atEndOfMonth();
	    return date.lengthOfMonth();
	}

	public static String TwoNumberCurrentYear() {
		String str = new java.sql.Date(new java.util.Date().getTime()).toString();
		return str.substring(2, 4);
	}

	public static String TwoMonthCurrentYear() {
		String str = new java.sql.Date(new java.util.Date().getTime()).toString();
		return str.substring(5, 7);
	}

	public static String TwoDateCurrentYear() {
		String str = new java.sql.Date(new java.util.Date().getTime()).toString();
		return str.substring(8, 10);
	}

	public static Date SqlDate(String date) {
		return new DateHelper(date).getDate();
	}

	public Date getDate() {

		Date date = Date.valueOf(String.valueOf(year) + "-" + StringMonth(month) + "-" + StringDay(day));
		return date;
	}

	public String sqlDate() {
		return String.valueOf(year) + "-" + StringMonth(month) + "-" + StringDay(day);
	}

	/**
	 * Returns the day.
	 * 
	 * @return int
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns the month.
	 * 
	 * @return int
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the year.
	 * 
	 * @return int
	 */
	public int getYear() {
		return year;
	}

	public String StringMonth(int m) {
		String r = "";
		if (m < 10) {
			switch (m) {
			case 1:
				r = "01";
				break;
			case 2:
				r = "02";
				break;
			case 3:
				r = "03";
				break;
			case 4:
				r = "04";
				break;
			case 5:
				r = "05";
				break;
			case 6:
				r = "06";
				break;
			case 7:
				r = "07";
				break;
			case 8:
				r = "08";
				break;
			case 9:
				r = "09";
				break;
			default:
				r = "01";
			}
		} else {
			r = String.valueOf(m);
		}
		return r;
	}

	public String StringDay(int d) {
		String r = "";
		if (d <= 10) {
			switch (d) {
			case 1:
				r = "01";
				break;
			case 2:
				r = "02";
				break;
			case 3:
				r = "03";
				break;
			case 4:
				r = "04";
				break;
			case 5:
				r = "05";
				break;
			case 6:
				r = "06";
				break;
			case 7:
				r = "07";
				break;
			case 8:
				r = "08";
				break;
			case 9:
				r = "09";
				break;
			default:
				r = "01";
			}
		} else {
			r = String.valueOf(d);
		}
		return r;
	}

	public static boolean isWeekend(java.util.Date date) {
		boolean result = false;
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || 
				cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) 
			return true;
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DateHelper.getLastDayOfMonth("1/16"));
	}
}
