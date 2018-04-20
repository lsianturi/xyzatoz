package com.benclaus.koperasi.model;

public class CompanyBook {
	private int id;
	private Company company;
	private Book book;
	private int fullAmount;
	private int inThousand;
	private int inMio;
	private int inBio;
	private int periodic;
	private int ytd;
	
	public CompanyBook() {
		super();
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getFullAmount() {
		return fullAmount;
	}
	public void setFullAmount(int fullAmount) {
		this.fullAmount = fullAmount;
	}
	public boolean isFullAmt() {
		return fullAmount == 1 ? true : false;
	}
	public int getInThousand() {
		return inThousand;
	}
	public void setInThousand(int inThousand) {
		this.inThousand = inThousand;
	}
	public boolean isThousand() {
		return inThousand == 1 ? true : false;
	}
	public int getInMio() {
		return inMio;
	}
	public void setInMio(int inMio) {
		this.inMio = inMio;
	}
	public boolean isMio() {
		return inMio == 1 ? true : false;
	}
	public int getInBio() {
		return inBio;
	}
	public void setInBio(int inBio) {
		this.inBio = inBio;
	}
	public boolean isBio() {
		return inBio == 1 ? true : false;
	}
	public int getPeriodic() {
		return periodic;
	}
	public void setPeriodic(int periodic) {
		this.periodic = periodic;
	}
	public boolean isMonhtly() {
		return periodic == 1 ? true : false;
	}
	public int getYtd() {
		return ytd;
	}
	public void setYtd(int ytd) {
		this.ytd = ytd;
	}
	public boolean isYearly() {
		return ytd == 1 ? true : false;
	}
}
