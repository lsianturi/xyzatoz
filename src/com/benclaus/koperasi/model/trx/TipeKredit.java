package com.benclaus.koperasi.model.trx;

import java.util.ArrayList;
import java.util.List;

import com.benclaus.koperasi.utility.LabelValueBean;

public class TipeKredit {
	public static Integer BARU = 1;
	public static Integer REPEAT_ORDER = 2;
	public static Integer TOP_UP = 3;
	public static Integer TAKE_OVER = 4;
	
	public static String getTipeKredit(Integer id) {
		switch (id) {
		case 1:
			return "Baru";
		case 2:
			return "Repeat Order";
		case 3: 
			return "Top Up";
		case 4: 
			return "Take Over";
		default:
			return null;
		}
	}
	public static String getPrefix(Integer id) {
		switch (id) {
		case 1:
			return "110.";
		case 2:
			return "120.";
		case 3: 
			return "130.";
		case 4: 
			return "140.";
		default:
			return null;
		}
	}
	
	public static List<LabelValueBean> getTipeKredit(){
		List<LabelValueBean> list = new ArrayList<>();
		list.add(new LabelValueBean("Baru", "1"));
		list.add(new LabelValueBean("Repeat Order (RO)", "2"));
		list.add(new LabelValueBean("Top Up", "3"));
		list.add(new LabelValueBean("Take Over", "4"));
		return list;
	}
}
