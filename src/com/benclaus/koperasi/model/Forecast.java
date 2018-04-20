package com.benclaus.koperasi.model;

import java.text.DecimalFormat;

public class Forecast {
	private int id;
	private Company company;
	private BookItem bookItem;
	private Integer year;
	
	private Double amount=0.0;
	
	private final DecimalFormat df = new DecimalFormat("###,###.00");
	
	public Forecast() {
		super();
		df.setMaximumFractionDigits(11);
	}
	public Forecast(int id) {
		super();
		this.id = id;
	}
	public Forecast(BookItem bookItem, Company company) {
		super();
		this.bookItem = bookItem;
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getAmountStr(){
		return df.format(amount);
	}
}
