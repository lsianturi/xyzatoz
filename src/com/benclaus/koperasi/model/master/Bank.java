package com.benclaus.koperasi.model.master;

public class Bank {
	private Integer id;
	private String nama;
	private String areaAtm;
	
	public Bank() {
		super();
	}
	public Bank(Integer id) {
		super();
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
	public String getAreaAtm() {
		return areaAtm;
	}
	public void setAreaAtm(String areaAtm) {
		this.areaAtm = areaAtm;
	}
	
}
