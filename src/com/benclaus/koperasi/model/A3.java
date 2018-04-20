package com.benclaus.koperasi.model;

public class A3 {
//	private Company company;
	private Mapping mapping;
	private Integer year;
	private Double amountActual=0.00000000000;
	private Double amountForecast=0.00000000000;
	private Double amountPlan=0.00000000000;
	
	/*public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}*/
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getAmountActual() {
		return amountActual;
	}
	public void setAmountActual(Double amountActual) {
		this.amountActual = amountActual;
	}
	public Double getAmountForecast() {
		return amountForecast;
	}
	public void setAmountForecast(Double amountForecast) {
		this.amountForecast = amountForecast;
	}
	public Double getAmountPlan() {
		return amountPlan;
	}
	public void setAmountPlan(Double amountPlan) {
		this.amountPlan = amountPlan;
	}
	public Mapping getMapping() {
		return mapping;
	}
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}
}
