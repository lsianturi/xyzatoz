package com.benclaus.koperasi.model;

import com.benclaus.koperasi.model.usm.User;

public class Company {
	private int id;
	private Pillar pillar;
	private String name;
	private User user;
	private String prefix;
	
	public Company() {
		super();
	}
	public Company(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Pillar getPillar() {
		return pillar;
	}
	public void setPillar(Pillar pillar) {
		this.pillar = pillar;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
