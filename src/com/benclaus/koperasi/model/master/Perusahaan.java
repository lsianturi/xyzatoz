package com.benclaus.koperasi.model.master;

import java.lang.reflect.Field;

import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.model.usm.Menu;

public class Perusahaan extends Audit {
	private Integer id;
	private String nama;
	private String alamat;
	private Industri industri;
	private Cabang cabang;
	private Unit unit;

	public Perusahaan() {
		super();
		super.tableName = "ms_perusahaan";
		super.fields = "id";
	}
	
	public Perusahaan(Integer id) {
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

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Industri getIndustri() {
		return industri;
	}

	public void setIndustri(Industri industri) {
		this.industri = industri;
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
        strBuff.append("[SOUT MS_PERUSAHAAN]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }
}
