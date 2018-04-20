package com.benclaus.koperasi.model;

public class Mapping {
	private int id;
	private BookItem bookItem;
	private String cellFy;
	private String cellYtdPrevActual;
	private String cellYtdCurrentActual;
	private String cellYtdCurrentPlan;
	private Company company;
	private String formula;
	
	public Mapping() {
		super();
	}
	public Mapping(int id) {
		super();
		this.id = id;
	}
	public Mapping(BookItem bookItem, Company company) {
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
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	public String getCellFy() {
		return cellFy;
	}
	public void setCellFy(String cellFy) {
		this.cellFy = cellFy;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getCellYtdPrevActual() {
		return cellYtdPrevActual;
	}
	public void setCellYtdPrevActual(String cellYtdPrevActual) {
		this.cellYtdPrevActual = cellYtdPrevActual;
	}
	public String getCellYtdCurrentActual() {
		return cellYtdCurrentActual;
	}
	public void setCellYtdCurrentActual(String cellYtdCurrentActual) {
		this.cellYtdCurrentActual = cellYtdCurrentActual;
	}
	public String getCellYtdCurrentPlan() {
		return cellYtdCurrentPlan;
	}
	public void setCellYtdCurrentPlan(String cellYtdCurrentPlan) {
		this.cellYtdCurrentPlan = cellYtdCurrentPlan;
	}
}
