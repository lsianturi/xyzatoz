package com.benclaus.koperasi.model.master;

public class Area {
	private Integer id;
	private String nama;
	
	public Area() {}
	public Area(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
}
