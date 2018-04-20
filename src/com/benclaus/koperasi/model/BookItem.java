package com.benclaus.koperasi.model;

public class BookItem {
	private int id;
	private Book book;
	private String name;
	
	public BookItem() {
		super();
	}
	public BookItem(int id) {
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
