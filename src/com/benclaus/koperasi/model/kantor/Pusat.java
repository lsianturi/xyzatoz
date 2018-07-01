package com.benclaus.koperasi.model.kantor;

import com.benclaus.koperasi.model.system.Audit;

public class Pusat extends Audit {
	private Integer id;
	private String nama;
	private String alamat;
	private String telepon;
	private String fax;
	private String badanHukum;
	private String npwp;
	private String siup;
	private String tdp;
	private String logoKoperasi;
	private String logoPerusahaan;
	
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
	public String getBadanHukum() {
		return badanHukum;
	}
	public void setBadanHukum(String badanHukum) {
		this.badanHukum = badanHukum;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getSiup() {
		return siup;
	}
	public void setSiup(String siup) {
		this.siup = siup;
	}
	public String getTdp() {
		return tdp;
	}
	public void setTdp(String tdp) {
		this.tdp = tdp;
	}
	public String getLogoKoperasi() {
		return logoKoperasi;
	}
	public void setLogoKoperasi(String logoKoperasi) {
		this.logoKoperasi = logoKoperasi;
	}
	public String getLogoPerusahaan() {
		return logoPerusahaan;
	}
	public void setLogoPerusahaan(String logoPerusahaan) {
		this.logoPerusahaan = logoPerusahaan;
	}
	
}
