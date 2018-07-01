package com.benclaus.koperasi.model.kantor;

public class Unit {
	private Integer id;
	private Cabang cabang;
	private String nama;
	private String alamat;
	private String telepon;
	private String fax;
	private String kepalaUnit;
	
	public Unit() {}
	
	public Unit(Integer id) {
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
	public Cabang getCabang() {
		return cabang;
	}
	public void setCabang(Cabang cabang) {
		this.cabang = cabang;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getTelepon() {
		return telepon;
	}
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getKepalaUnit() {
		return kepalaUnit;
	}

	public void setKepalaUnit(String kepalaUnit) {
		this.kepalaUnit = kepalaUnit;
	}
}
