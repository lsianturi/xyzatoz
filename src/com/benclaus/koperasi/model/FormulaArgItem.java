package com.benclaus.koperasi.model;

public class FormulaArgItem {
	private FormulaArg formulaArg;
	private BookItem bookItem;
	private String formula;
	private String formulaArgs;
	private double value;
	
	public FormulaArg getFormulaArg() {
		return formulaArg;
	}
	public void setFormulaArg(FormulaArg formulaArg) {
		this.formulaArg = formulaArg;
	}
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getFormulaArgs() {
		return formulaArgs;
	}
	public void setFormulaArgs(String formulaArgs) {
		this.formulaArgs = formulaArgs;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
