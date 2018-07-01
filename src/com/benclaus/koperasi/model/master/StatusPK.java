package com.benclaus.koperasi.model.master;

public class StatusPK {
	private Integer id;
	private String status;
	
	public StatusPK() {
		super();
	}
	public StatusPK(Integer id) {
		super();
		this.id = id;
	}
	public StatusPK(Integer id, String status) {
		super();
		this.id = id;
		this.status= status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
