package com.benclaus.koperasi.model;

import java.util.Date;
import java.util.List;

public class FormulaArg {
	private Integer id;
	private Company company;
	private String companyPrefix;
	private Integer year;
	private Integer month;
	private String fileName;
	private Date processTime;
	private List<FormulaArgItem> formulaItems;
	
	public FormulaArg() {
		super();
	}
	
	public FormulaArg(Integer id) {
		super();
		this.id = id;
	}
	
	public FormulaArg(Company company, Integer year, Integer month) {
		super();
		this.company = company;
		this.year = year;
		this.month = month;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<FormulaArgItem> getFormulaItems() {
		return formulaItems;
	}
	public void setFormulaItems(List<FormulaArgItem> formulaItems) {
		this.formulaItems = formulaItems;
	}
	public String getCompanyPrefix() {
		return companyPrefix;
	}
	public void setCompanyPrefix(String companyPrefix) {
		this.companyPrefix = companyPrefix;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	
}
