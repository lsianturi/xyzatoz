package com.benclaus.koperasi.model;

public class Book {
	private int id;
	private String name;
	
	public Book() {
		super();
	}
	public Book(int id) {
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
}
