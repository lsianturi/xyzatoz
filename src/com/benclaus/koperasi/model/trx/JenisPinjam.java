package com.benclaus.koperasi.model.trx;

import java.util.ArrayList;
import java.util.List;

import com.benclaus.koperasi.utility.LabelValueBean;

public class JenisPinjam {
	public static Integer BULANAN = 1;
	public static Integer MINGGUAN = 2;
	public static Integer HARIAN = 3;
	
	public static Integer getJenisPinjaman(Integer id) {
		switch (id) {
		case 1:
			return JenisPinjam.BULANAN;
		case 2:
			return JenisPinjam.MINGGUAN;
		case 3: 
			return JenisPinjam.HARIAN;
		default:
			return null;
		}
	}
	
	public static List<LabelValueBean> getJenisPinjam(){
		List<LabelValueBean> list = new ArrayList<>();
		list.add(new LabelValueBean("Bulanan", "1"));
		return list;
	}
}
