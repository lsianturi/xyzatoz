package com.benclaus.koperasi.model.trx;

import java.util.ArrayList;
import java.util.List;

import com.benclaus.koperasi.utility.LabelValueBean;

public class StatusPinjaman {
	public static Integer UNREAL = 1;
	public static Integer REALIZED = 2;
	public static Integer LUNAS = 3;
	
	public static String getStatusPinjaman(Integer id) {
		switch (id) {
		case 1:
			return "Belum Realisasi";
		case 2:
			return "Sudah Realisasi";
		case 3: 
			return "Lunas";
		default:
			return null;
		}
	}
	
	public static List<LabelValueBean> getStatusPinjaman(){
		List<LabelValueBean> list = new ArrayList<>();
		list.add(new LabelValueBean("Belum Realisasi", "1"));
		list.add(new LabelValueBean("Sudah Realisasi", "2"));
		list.add(new LabelValueBean("Lunas", "3"));
		return list;
	}
}
