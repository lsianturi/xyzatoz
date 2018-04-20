package com.benclaus.koperasi.model;

import java.text.DecimalFormat;

public class Data {
	private int id;
	private Company company;
	private BookItem bookItem;
	private Integer month;
	private Integer year;
	
	private Double plan1=0.0;
	private Double plan2=0.0;
	private Double plan3=0.0;
	private Double plan4=0.0;
	private Double plan5=0.0;
	private Double plan6=0.0;
	private Double plan7=0.0;
	private Double plan8=0.0;
	private Double plan9=0.0;
	private Double plan10=0.0;
	private Double plan11=0.0;
	private Double plan12=0.0;
	
	/*private String plan1Str;
	private String plan2Str;
	private String plan3Str;
	private String plan4Str;
	private String plan5Str;
	private String plan6Str;
	private String plan7Str;
	private String plan8Str;
	private String plan9Str;
	private String plan10Str;
	private String plan11Str;
	private String plan12Str;*/
	
	private Double actual1;
	private Double actual2;
	private Double actual3;
	private Double actual4;
	private Double actual5;
	private Double actual6;
	private Double actual7;
	private Double actual8;
	private Double actual9;
	private Double actual10;
	private Double actual11;
	private Double actual12;
	
	private final DecimalFormat format = new DecimalFormat("###,###.00");
	
	public Data() {
		super();
		format.setMaximumFractionDigits(11);
	}
	public Data(int id) {
		super();
		this.id = id;
	}
	public Data(BookItem bookItem, Company company) {
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getPlan1() {
		return plan1;
	}
	public void setPlan1(Double plan1) {
		this.plan1 = plan1;
	}
	public Double getPlan2() {
		return plan2;
	}
	public void setPlan2(Double plan2) {
		this.plan2 = plan2;
	}
	public Double getPlan3() {
		return plan3;
	}
	public void setPlan3(Double plan3) {
		this.plan3 = plan3;
	}
	public Double getPlan4() {
		return plan4;
	}
	public void setPlan4(Double plan4) {
		this.plan4 = plan4;
	}
	public Double getPlan5() {
		return plan5;
	}
	public void setPlan5(Double plan5) {
		this.plan5 = plan5;
	}
	public Double getPlan6() {
		return plan6;
	}
	public void setPlan6(Double plan6) {
		this.plan6 = plan6;
	}
	public Double getPlan7() {
		return plan7;
	}
	public void setPlan7(Double plan7) {
		this.plan7 = plan7;
	}
	public Double getPlan8() {
		return plan8;
	}
	public void setPlan8(Double plan8) {
		this.plan8 = plan8;
	}
	public Double getPlan9() {
		return plan9;
	}
	public void setPlan9(Double plan9) {
		this.plan9 = plan9;
	}
	public Double getPlan10() {
		return plan10;
	}
	public void setPlan10(Double plan10) {
		this.plan10 = plan10;
	}
	public Double getPlan11() {
		return plan11;
	}
	public void setPlan11(Double plan11) {
		this.plan11 = plan11;
	}
	public Double getPlan12() {
		return plan12;
	}
	public void setPlan12(Double plan12) {
		this.plan12 = plan12;
	}
	public Double getActual1() {
		return actual1;
	}
	public void setActual1(Double actual1) {
		this.actual1 = actual1;
	}
	public Double getActual2() {
		return actual2;
	}
	public void setActual2(Double actual2) {
		this.actual2 = actual2;
	}
	public Double getActual3() {
		return actual3;
	}
	public void setActual3(Double actual3) {
		this.actual3 = actual3;
	}
	public Double getActual4() {
		return actual4;
	}
	public void setActual4(Double actual4) {
		this.actual4 = actual4;
	}
	public Double getActual5() {
		return actual5;
	}
	public void setActual5(Double actual5) {
		this.actual5 = actual5;
	}
	public Double getActual6() {
		return actual6;
	}
	public void setActual6(Double actual6) {
		this.actual6 = actual6;
	}
	public Double getActual7() {
		return actual7;
	}
	public void setActual7(Double actual7) {
		this.actual7 = actual7;
	}
	public Double getActual8() {
		return actual8;
	}
	public void setActual8(Double actual8) {
		this.actual8 = actual8;
	}
	public Double getActual9() {
		return actual9;
	}
	public void setActual9(Double actual9) {
		this.actual9 = actual9;
	}
	public Double getActual10() {
		return actual10;
	}
	public void setActual10(Double actual10) {
		this.actual10 = actual10;
	}
	public Double getActual11() {
		return actual11;
	}
	public void setActual11(Double actual11) {
		this.actual11 = actual11;
	}
	public Double getActual12() {
		return actual12;
	}
	public void setActual12(Double actual12) {
		this.actual12 = actual12;
	}
	
	public String getPlan1Str() {
		return format.format(plan1);
	}
	public String getPlan2Str() {
		return format.format(plan2);
	}
	public String getPlan3Str() {
		return format.format(plan3);
	}
	public String getPlan4Str() {
		return format.format(plan4);
	}
	public String getPlan5Str() {
		return format.format(plan6);
	}
	public String getPlan6Str() {
		return format.format(plan6);
	}
	public String getPlan7Str() {
		return format.format(plan7);
	}
	public String getPlan8Str() {
		return format.format(plan8);
	}
	public String getPlan9Str() {
		return format.format(plan9);
	}
	public String getPlan10Str() {
		return format.format(plan10);
	}
	public String getPlan11Str() {
		return format.format(plan11);
	}
	public String getPlan12Str() {
		return format.format(plan12);
	}
}
