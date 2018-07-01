package com.benclaus.koperasi.model.master;

import java.lang.reflect.Field;
import java.util.Date;

import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.model.usm.Menu;

public class Pegawai extends Audit{
	
	private Integer id;
	private String nama;
	private String alamat;
	private String domisili;
	private String telepon;
	private StatusPK statusPegawai;
	private StatusPK statusSipil;
	private Date tglMasuk ;
	private Cabang cabang;
	private Unit unit;
	private String keterangan;

	public Pegawai() {
		super();
		super.tableName = "ms_pegawai";
		super.fields = "id";
	}
	public Pegawai(Integer id) {
		super();
		super.tableName = "ms_pegawai";
		super.fields = "id";
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

	public String getDomisili() {
		return domisili;
	}

	public void setDomisili(String domisili) {
		this.domisili = domisili;
	}

	public StatusPK getStatusPegawai() {
		return statusPegawai;
	}

	public void setStatusPegawai(StatusPK statusPegawai) {
		this.statusPegawai = statusPegawai;
	}

	public StatusPK getStatusSipil() {
		return statusSipil;
	}

	public void setStatusSipil(StatusPK statusSipil) {
		this.statusSipil = statusSipil;
	}

	public Date getTglMasuk() {
		return tglMasuk;
	}

	public void setTglMasuk(Date tglMasuk) {
		this.tglMasuk = tglMasuk;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public Cabang getCabang() {
		return cabang;
	}

	public void setCabang(Cabang cabang) {
		this.cabang = cabang;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT MS_PEGAWAI]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
}
