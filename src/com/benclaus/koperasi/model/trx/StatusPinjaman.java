package com.benclaus.koperasi.model.trx;

import java.util.ArrayList;
import java.util.List;

import com.benclaus.koperasi.utility.LabelValueBean;

public class StatusPinjaman {
	public static Integer UNREAL = 0;
	public static Integer REALIZED = 1;
	public static Integer BELUM_LUNAS = 0;
	public static Integer LUNAS = 1;
	
	public static String getStatusAju(Integer id) {
		switch (id) {
		case 0:
			return "Belum Realisasi";
		case 1:
			return "Sudah Realisasi";
		default:
			return null;
		}
	}
	public static String getStatusReal(Integer id) {
		switch (id) {
		case 0:
			return "Belum Lunas";
		case 1:
			return "Lunas";
		default:
			return null;
		}
	}
	
	public static List<LabelValueBean> getStatusAju(){
		List<LabelValueBean> list = new ArrayList<>();
		list.add(new LabelValueBean("Belum Realisasi", "1"));
		list.add(new LabelValueBean("Sudah Realisasi", "2"));
		return list;
	}
	
	public static List<LabelValueBean> getStatusReal(){
		List<LabelValueBean> list = new ArrayList<>();
		list.add(new LabelValueBean("Belum Lunas", "1"));
		list.add(new LabelValueBean("Lunas", "2"));
		return list;
	}
}
