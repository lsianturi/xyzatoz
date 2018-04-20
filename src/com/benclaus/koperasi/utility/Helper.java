package com.benclaus.koperasi.utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts.util.MessageResources;

import com.benclaus.koperasi.model.usm.Menu;


public class Helper {
	private static final double DAY_MILLIS = 1000 * 60 * 60 * 24.0015;
	private static final double WEEK_MILLIS = DAY_MILLIS * 7;
	private static final double MONTH_MILLIS = DAY_MILLIS * 30.43675;
	private static final double YEAR_MILLIS = WEEK_MILLIS * 52.2;
	private static final long ONE_MINUTE = 60 * 1000L;

	public static final int DEFAULT_DATE = 1;
	public static final int CURRENT_DATE = 2;
	public static final int NULL_DATE = 3;

	private static SimpleDateFormat formatter = new SimpleDateFormat(Constant.GLOBALDATEFORMAT);
	private static SimpleDateFormat formattime = new SimpleDateFormat(Constant.GLOBALTIMESTAMP);
	private static SimpleDateFormat dbFormatter = new SimpleDateFormat(Constant.DB_GLOBALDATEFORMAT);
	private static SimpleDateFormat dbFormattime = new SimpleDateFormat(Constant.DB_GLOBALTIMESTAMP);
	private static SimpleDateFormat dbFormathour = new SimpleDateFormat(Constant.DB_GLOBALTIME);
	private static DecimalFormat decimalFormat = new DecimalFormat("###.##");
	
	protected static MessageResources messages;// = MessageResources.getMessageResources("com/alita/scm/resources/ApplicationResources");
	
	static {
		try {
			messages = MessageResources.getMessageResources("com/alita/scm/resources/ApplicationResources");
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public static Map convertMenuList2Map(List list) {
		HashMap map = new HashMap();

		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			Menu menu = (Menu) itr.next();
			map.put(menu.getMenuCode(), menu);
		}
		return map;
	}
	
	public static int getDateDiff(int calUnit, Date d1, Date d2) {
		// swap if d1 later than d2
		boolean neg = false;
		if (d1.after(d2)) {
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			neg = true;
		}

		// estimate the diff.  d1 is now guaranteed <= d2
		int estimate = (int) getEstDiff(calUnit, d1, d2);

		// convert the Dates to GregorianCalendars
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(d2);

		// add 2 units less than the estimate to 1st date,
		//  then serially add units till we exceed 2nd date
		c1.add(calUnit, (int) estimate - 2);
		for (int i = estimate - 1;; i++) {
			c1.add(calUnit, 1);
			if (c1.after(c2))
				return neg ? 1 - i : i - 1;
		}
	}

	private static int getEstDiff(int calUnit, Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		switch (calUnit) {
			case Calendar.DAY_OF_WEEK_IN_MONTH :
			case Calendar.DAY_OF_MONTH :
				//			case Calendar.DATE :      // codes to same int as DAY_OF_MONTH
				return (int) (diff / DAY_MILLIS + .5);
			case Calendar.WEEK_OF_YEAR :
				return (int) (diff / WEEK_MILLIS + .5);
			case Calendar.MONTH :
				return (int) (diff / MONTH_MILLIS + .5);
			case Calendar.YEAR :
				return (int) (diff / YEAR_MILLIS + .5);
			default :
				return 0;
		} /* endswitch */
	}

	public static void convertDate(Map map, String key) throws ParseException {
		String dateString = (String) map.get(key);
		if (dateString != null && (dateString.trim().length() > 0)) {
			Date dateObject = formatter.parse(dateString);
			map.put(key, dateObject);
		}
	} 
	
	public static String convertDateToOpposite(String unformattedDate, boolean isDDMMYYYY) throws ParseException{
		Date date = isDDMMYYYY ? formatter.parse(unformattedDate) : dbFormatter.parse(unformattedDate);
		return isDDMMYYYY ? dbFormatter.format(date) : formatter.format(date);
	}

	public static String convertDateToString(Date date, boolean toDDMMYYYY) {
		if (toDDMMYYYY) {
			return dbFormatter.format(date);						
		} else {
			return formatter.format(date);			
		}
	}
	
	public static String convertTimeStampToOpposite(String unformattedDate, boolean isDDMMYYYY) throws ParseException{
		Date date = isDDMMYYYY ? formattime.parse(unformattedDate) : dbFormattime.parse(unformattedDate);
		return isDDMMYYYY ? dbFormattime.format(date) : formattime.format(date);
	}
	
	public static String convertTimeStampToString(Date date, boolean toDDMMYYYY) {
		if (toDDMMYYYY) {
			return dbFormattime.format(date);
		} else {
			return formattime.format(date);
		}
	}
	
	public static Date convertStringDate(String unformattedDate, boolean toDDMMYY) throws ParseException{
		if (toDDMMYY) {
			return formatter.parse(unformattedDate);
		}else{
			return dbFormatter.parse(unformattedDate);
		}
	}

	public static String getTodayDate() {
		return dbFormatter.format(Calendar.getInstance().getTime());
	}
	
	public static String getTodayInputDate() {
		return formatter.format(Calendar.getInstance().getTime());
	}
	
	public static String getTodayTime() {
		return dbFormathour.format(Calendar.getInstance().getTime());
	}
	
	public static String getInputTodayDate() {
		return formatter.format(Calendar.getInstance().getTime());
	}

	public static String prefixPadder(String source, int length, String padder) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < (length - source.length()); i++) {
			result.append(padder);
		}
		return result.append(source).toString();
	}

	public static String prefixPadder(long source, int length, String padder) {
		return prefixPadder(String.valueOf(source), length, padder);
	}

	public static String prefixZero(String source, int length) {
		return prefixPadder(source, length, "0");
	}

	public static String prefixZero(long source, int length) {
		return prefixZero(String.valueOf(source), length);
	}

	public static String getFractionValue(BigDecimal bd) {
		if (bd.doubleValue()==0) return "";
		String bs = bd.toString();
		int index = bs.indexOf('.');
		if (index>=0) {
			return bs.substring(index,bs.length());
		}
		return "";
	}

	private static String satuan(int value) {
		return messages.getMessage(String.valueOf(value));
		/*	0=
			1=Satu
			2=Dua
			3=Tiga
			4=Empat
			5=Lima
			6=Enam
			7=Tujuh
			8=Delapan
			9=Sembilan
		*/
	}
	
	private static String fractionSatuan(int value) {
		return fractionSatuan(String.valueOf(value));
	}
	
	private static String fractionSatuan(String value) {
		if ("0".equals(value)) return messages.getMessage("nol");
		return messages.getMessage(value);
		/*	0=Nol
			1=Satu
			2=Dua
			3=Tiga
			4=Empat
			5=Lima
			6=Enam
			7=Tujuh
			8=Delapan
			9=Sembilan
		*/
	}


	private static String puluhan(int value) {
		String msg = messages.getMessage(String.valueOf(value));
		if (msg == null) {
			// get puluhan
			String puluhan = messages.getMessage(String.valueOf((((int) (value / 10)) * 10)));
			if (puluhan!=null && puluhan.length()>0) {
				msg = puluhan + " " + satuan(value % 10);
			} else {
				msg = satuan(value % 10);
			}
			
		}
		return msg;
		/*
		10=Sepuluh
		11=Sebelas
		12=Dua Belas
		13=Tiga Belas
		14=Empat Belas
		15=Lima Belas
		16=Enam Belas
		17=Tujuh Belas
		18=Delapan Belas
		19=Sembilan Belas
		20=Dua Puluh
		30=Tiga Puluh
		40=Empat Puluh
		50=Lima Puluh
		60=Enam Puluh
		70=Tujuh Puluh
		80=Delapan Puluh
		90=Sembilan Puluh
		*/
	}

	private static String ratusan(int value) {
		String msg = messages.getMessage(String.valueOf(value));
		if (msg == null) {
			// get ratusan
			String ratusan = messages.getMessage(String.valueOf((((int) (value / 100)) * 100)));
			if (ratusan!=null && ratusan.length()>0) {
				msg = ratusan + " " + puluhan(value % 100);
			} else {
				msg = puluhan(value % 100);
			}
		}
		return msg;
		// 100=Seratus
		// check in message, if found return it
		// else get in satuan plus ratusan
	}

	public static String terbilang(long value) {
		StringBuffer sb = new StringBuffer();
		
		if (value==0) return messages.getMessage("nol");

		// check for triliun, 1000000000000
		int temp = (int) (value / 1000000000000l);
		if (temp > 0) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(ratusan(temp)).append(" ").append(messages.getMessage("0000000000000"));
		}
		value = value % 1000000000000l;

		// check for milyar, 1000000000
		temp = (int) (value / 1000000000l);
		if (temp > 0) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(ratusan(temp)).append(" ").append(messages.getMessage("0000000000"));
		}
		value = value % 1000000000l;

		// check for juta, 1000000
		temp = (int) (value / 1000000l);
		if (temp > 0) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(ratusan(temp)).append(" ").append(messages.getMessage("0000000"));
		}
		value = value % 1000000l;

		// check for ribu, 1000
		temp = (int) (value / 1000l);
		if (temp == 1) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(messages.getMessage("1000"));
		} else if (temp > 0) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(ratusan(temp)).append(" ").append(messages.getMessage("0000"));
		}
		value = value % 1000l;

		// check for satuan, 1
		temp = (int) value;
		if (temp > 0) {
			if (sb.length() > 1)
				sb.append(" ");
			sb.append(ratusan(temp));
		}
		return sb.toString();
	}
	
	public static String fractionTerbilang(BigDecimal bdvalue) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(terbilang(bdvalue));
		// fraction
		String fraction = getFractionValue(bdvalue);
		if (!"".equals(fraction)) {
			// remove all trailing zero
			while (fraction.endsWith("0")) {
				fraction = fraction.substring(0,fraction.length()-1);
			}
			if (".".equals(fraction)) fraction = ".00";
			for (int i=0; i<fraction.length(); i++) {
				sb.append(" ").append(fractionSatuan(fraction.substring(i,i+1)));
			}
		}
		
		
		return sb.toString();
	}

	public static String terbilang(double value) {
		return terbilang(Math.round(Math.abs(value)));
	}

	public static String terbilang(BigDecimal value) {
		return terbilang(value.abs().longValue());
	}

	public static String terbilang(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuffer sb = new StringBuffer();
		sb
			.append(terbilang(cal.get(Calendar.DATE)))
			.append(" ")
			.append(getMonthOfYear(date))
			.append(" ")
			.append(terbilang(cal.get(Calendar.YEAR)));
		return sb.toString();
	}

	public static String getLocaleDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuffer sb = new StringBuffer();
		sb
			.append(cal.get(Calendar.DATE))
			.append(" ")
			.append(getMonthOfYear(date))
			.append(" ")
			.append(cal.get(Calendar.YEAR));
		return sb.toString();
	}

	public static String getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		String result = "";
		switch (dow) {
			case Calendar.SUNDAY :
				result = messages.getMessage("dow.sunday");
				break;
			case Calendar.MONDAY :
				result = messages.getMessage("dow.monday");
				break;
			case Calendar.TUESDAY :
				result = messages.getMessage("dow.tuesday");
				break;
			case Calendar.WEDNESDAY :
				result = messages.getMessage("dow.wednesday");
				break;
			case Calendar.THURSDAY :
				result = messages.getMessage("dow.thursday");
				break;
			case Calendar.FRIDAY :
				result = messages.getMessage("dow.friday");
				break;
			case Calendar.SATURDAY :
				result = messages.getMessage("dow.saturday");
				break;
		}
		return result;
	}

	public static int getDayOfMonth(Map map, String key) throws ParseException {
		String dateString = (String) map.get(key);
		Calendar cal = Calendar.getInstance();
		int dom = 0;
		if(dateString != null && (dateString.trim().length() > 0)) {
			Date dateObject = formatter.parse(dateString);
			cal.setTime(dateObject);
			dom = cal.get(Calendar.DAY_OF_MONTH);
		} else {
			if (!dateString.equals("")) {
				cal.getTime();
				dom = cal.get(Calendar.DAY_OF_MONTH);
			}			
		}
		return dom;
	}

	public String getMonthOfYear(Locale l) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM", l);
		return sdf.format(c.getTime());		
	}
	
	public static String getMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int moy = cal.get(Calendar.MONTH);
		String result = "";
		switch (moy) {
			case Calendar.JANUARY :
				result = messages.getMessage("moy.january");
				break;
			case Calendar.FEBRUARY :
				result = messages.getMessage("moy.february");
				break;
			case Calendar.MARCH :
				result = messages.getMessage("moy.march");
				break;
			case Calendar.APRIL :
				result = messages.getMessage("moy.april");
				break;
			case Calendar.MAY :
				result = messages.getMessage("moy.may");
				break;
			case Calendar.JUNE :
				result = messages.getMessage("moy.june");
				break;
			case Calendar.JULY :
				result = messages.getMessage("moy.july");
				break;
			case Calendar.AUGUST :
				result = messages.getMessage("moy.august");
				break;
			case Calendar.SEPTEMBER :
				result = messages.getMessage("moy.september");
				break;
			case Calendar.OCTOBER :
				result = messages.getMessage("moy.october");
				break;
			case Calendar.NOVEMBER :
				result = messages.getMessage("moy.november");
				break;
			case Calendar.DECEMBER :
				result = messages.getMessage("moy.december");
				break;
		}
		return result;
	}
	
	public static String getMonthOfYearForReport(String month) {
		if (month.equals("01")) { return "Januari"; }
		if (month.equals("02")) { return "Febuari"; }
		if (month.equals("03")) { return "Maret"; }
		if (month.equals("04")) { return "April"; }
		if (month.equals("05")) { return "Mei"; }
		if (month.equals("06")) { return "Juni"; }
		if (month.equals("07")) { return "Juli"; }
		if (month.equals("08")) { return "Agustus"; }
		if (month.equals("09")) { return "September"; }
		if (month.equals("10")) { return "Oktober"; }
		if (month.equals("11")) { return "Novembar"; }																				
		if (month.equals("12")) { return "Desember"; }			
		return "fake month";																			
	}
	public static String getMonthInRoman(int month) {
		if (month==1) { return "I"; }
		if (month==2) { return "II"; }
		if (month==3) { return "III"; }
		if (month==4) { return "IV"; }
		if (month==5) { return "V"; }
		if (month==6) { return "VI"; }
		if (month==7) { return "VII"; }
		if (month==8) { return "VIII"; }
		if (month==9) { return "IX"; }
		if (month==10) { return "X"; }
		if (month==11) { return "XI"; }																				
		if (month==12) { return "XII"; }			
		return "fake month";																			
	}
	
	/*
	 * oldStringDate must be a String represent some Date in format: dd/MM/yyyy
	 * if all goes right, the result will be in format: yyyy-MM-dd
	 * otherwise it will return the oldStringDate 
	 */
	public static String convertStringDateForInserting(String oldStringDate) {
		String resultString = oldStringDate;
		try {
			oldStringDate = oldStringDate.trim();
			resultString = (oldStringDate.substring(6,10) + "-" + oldStringDate.substring(3,5) + "-" + oldStringDate.substring(0, 2));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}

	
	/*
	 * oldStringDate must be a String represent some Date in format: yyyy-MM-dd
	 * if all goes right, the result will be in format: dd/MM/yyyy
	 * otherwise it will return the oldStringDate
	*/ 
	public static String convertStringDateAfterSelect(String oldStringDate) {
		String resultString = oldStringDate;
		try {
			oldStringDate = oldStringDate.trim();
			resultString = (oldStringDate.substring(8,10) + "/" + oldStringDate.substring(5,7) + "/" + oldStringDate.substring(0, 4));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}

	
	/*
	 * convert the bigDecimal to another BigDecimal object with pattern given (###.##)
	 */
	public static BigDecimal doDecimalFormat(BigDecimal bigDecimal) {
		if(bigDecimal != null) {
			decimalFormat.setMaximumFractionDigits(2);
			decimalFormat.setMinimumFractionDigits(2);	
			return new BigDecimal(""+decimalFormat.format(bigDecimal.doubleValue()));
		}
		else {
			return new BigDecimal(0);	
		}
	}
	
	public static void main(String[] args) {
		Locale locale = new Locale("in", "IN");
		
		SimpleDateFormat formatToDate = new SimpleDateFormat(Constant.GLOBALDATEFORMAT);
		SimpleDateFormat formatToString = new SimpleDateFormat(Constant.LONGDATEFORMAT, locale);

		try {
			Date date = formatToDate.parse("14/07/2004");
			String strDate = formatToString.format(date);
			System.out.println("strDate = " + strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	public static long getHourDifference( String firstHour, String secondHour) {
		
		int hour1 = new Integer(firstHour.substring(0,2)).intValue();
		int hour2 = new Integer(secondHour.substring(0,2)).intValue();
		int minute1 = new Integer(firstHour.substring(3,5)).intValue();
		int minute2 = new Integer(secondHour.substring(3,5)).intValue();
		
		System.out.println("hour1: " + hour1 + " hour2: " + hour2 + " minute1: " + minute1 + " minute2: " + minute2);
		
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		
		c1.setTime(new Date());
		c2.setTime(new Date());
		c1.set(Calendar.HOUR_OF_DAY,hour1);
		c1.set(Calendar.MINUTE,minute1);
		
		c2.set(Calendar.HOUR_OF_DAY,hour2);
		c2.set(Calendar.MINUTE,minute2);
		
		System.out.println((c2.getTime().getTime() - c1.getTime().getTime()) / ONE_MINUTE);
		
		return ( (c2.getTime().getTime() - c1.getTime().getTime()) / ONE_MINUTE);
		
	}

	/**
	 * Utility method to get Roman-numbering of month.
	 * @param month
	 * @return Month number, formatted in Roman numbering
	 */
	public static String getRomanMonth( int month ) {
		switch( month ) {
		case Calendar.JANUARY : return "I";
		case Calendar.FEBRUARY: return "II";
		case Calendar.MARCH : return "III";
		case Calendar.APRIL : return "IV";
		case Calendar.MAY : return "V";
		case Calendar.JUNE : return "VI";
		case Calendar.JULY : return "VII";
		case Calendar.AUGUST : return "VIII";
		case Calendar.SEPTEMBER : return "IX";
		case Calendar.OCTOBER : return "X";
		case Calendar.NOVEMBER : return "XI";
		case Calendar.DECEMBER : return "XII";
		}
		return "";
	}
	
	/**
	 * Stripping the timestamp info from entered {@java.util.Date} input parameter.
	 * 
	 * @param date
	 * @return the input object with stripped timestamp, or if failed, the input no changes.
	 */
	public static Date stripTimestamp( Date date ) {
		DateFormat df = new SimpleDateFormat( "ddMMyyyy" );
		String sDate = df.format( date );
		Date retDate;
		try { 
			retDate = df.parse( sDate ); 
		} catch ( ParseException pe ) {
			System.err.println( "stripTimestamp(): something wrong when parsing date string " + sDate );
			pe.printStackTrace();
			retDate = date;
		}
		return retDate;
	}
}
