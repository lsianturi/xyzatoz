package com.benclaus.koperasi.model.trx;

import java.util.Date;

public class Realisasi extends Aju {

	private Date tglReal;
	private Double jmlReal;
	private Double biayaAdmin;
	private Double biayaProvisi;
	private Double biayaLain;
	private Double sisaAngsuran;
	private Double diterima;
	private String terbilang;
	private String keterangan;
	private Integer lunas;
	
	public Realisasi() {
	}

	public Date getTglReal() {
		return tglReal;
	}

	public void setTglReal(Date tglReal) {
		this.tglReal = tglReal;
	}

	public Double getJmlReal() {
		return jmlReal;
	}

	public void setJmlReal(Double jmlReal) {
		this.jmlReal = jmlReal;
	}

	public Double getBiayaAdmin() {
		return biayaAdmin;
	}

	public void setBiayaAdmin(Double biayaAdmin) {
		this.biayaAdmin = biayaAdmin;
	}

	public Double getBiayaProvisi() {
		return biayaProvisi;
	}

	public void setBiayaProvisi(Double biayaProvisi) {
		this.biayaProvisi = biayaProvisi;
	}

	public Double getBiayaLain() {
		return biayaLain;
	}

	public void setBiayaLain(Double biayaLain) {
		this.biayaLain = biayaLain;
	}
	public Double getSisaAngsuran() {
		return sisaAngsuran;
	}

	public void setSisaAngsuran(Double sisaAngsuran) {
		this.sisaAngsuran = sisaAngsuran;
	}

	public Double getDiterima() {
		return diterima;
	}

	public void setDiterima(Double diterima) {
		this.diterima = diterima;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Integer getLunas() {
		return lunas;
	}

	public void setLunas(Integer lunas) {
		this.lunas = lunas;
	}

	public String getTerbilang() {
		return terbilang;
	}

	public void setTerbilang(String terbilang) {
		this.terbilang = terbilang;
	}
	
}

