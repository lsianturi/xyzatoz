package com.benclaus.koperasi.model.master;

public class Industri {
	private Integer id;
	private String nama;
	
	public Industri() {}
	public Industri(Integer id) {
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
