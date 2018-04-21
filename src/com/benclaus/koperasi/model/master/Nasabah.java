package com.benclaus.koperasi.model.master;

import java.lang.reflect.Field;

import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.model.usm.Menu;

public class Nasabah extends Audit{
	
	private Integer id;
	private String nik;
	private String pt;
	private String nama;
	private String bagian;
	private String jabatan;
	private String statusKaryawan;
	private Integer statusSipil;
	private String alamatKtp;

	public Nasabah() {
		super();
		super.tableName = "ms_nasabah";
		super.fields = "id";
	}
	
	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT MS_NASABAH]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
}
