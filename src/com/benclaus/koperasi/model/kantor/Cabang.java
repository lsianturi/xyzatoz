package com.benclaus.koperasi.model.kantor;

import com.benclaus.koperasi.model.system.Audit;

public class Cabang extends Audit {
	
	private Integer id;
	private String nama;

	public Cabang() {}
	
	public Cabang(Integer id) {
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
