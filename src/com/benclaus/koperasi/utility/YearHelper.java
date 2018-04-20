package com.benclaus.koperasi.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Lambok
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class YearHelper {
	private static YearHelper yearList = null;
	private ArrayList years;
	private ArrayList<LabelValueBean> months;
	private String currentYear = "0";
	
	private YearHelper() {
		years = new ArrayList();
		months = new ArrayList<>();
	}
	
	public static synchronized YearHelper getInstance() {
		if (yearList==null) yearList = new YearHelper();
		return yearList;
	}
	
	//public Hashtable getYearList() {
	public ArrayList getYearList(int minus, int adder) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int tahun = cal.get(cal.YEAR);	
		currentYear = ""+tahun;
		int tahun1 = tahun-minus;
		int tahun2 = tahun+adder;
		years = new ArrayList();
		for( int i=tahun2; i>=tahun1; i--)
			//years.put(""+i,""+i);
			years.add(new LabelValueBean(""+i, ""+i));
		return years;
	}
	public ArrayList<LabelValueBean> getMonthList() {
		months = new ArrayList<>();
		months.add(new LabelValueBean("January", "1"));
		months.add(new LabelValueBean("February", "2"));
		months.add(new LabelValueBean("March", "3"));
		months.add(new LabelValueBean("April", "4"));
		months.add(new LabelValueBean("May", "5"));
		months.add(new LabelValueBean("June", "6"));
		months.add(new LabelValueBean("July", "7"));
		months.add(new LabelValueBean("August", "8"));
		months.add(new LabelValueBean("September", "9"));
		months.add(new LabelValueBean("October", "10"));
		months.add(new LabelValueBean("November", "11"));
		months.add(new LabelValueBean("December", "12"));
		return months;
	}
	
	public String getMonthName(int month) {
		switch (month) {
		case 1:
			return "Januari";
		case 2:
			return "Februari";
		case 3:
			return "Maret";
		case 4:
			return "April";
		case 5:
			return "Mei";
		case 6:
			return "Juni";
		case 7:
			return "Juli";
		case 8:
			return "Agustus";
		case 9:
			return "September";
		case 10:
			return "Oktober";
		case 11:
			return "November";
		case 12:
			return "Desember";
		default:
			break;
		}
		return "";
	}
	
	/**
	 * Returns the currentYear.
	 * @return String
	 */
	public String getCurrentYear() {
		return currentYear;
	}

}
