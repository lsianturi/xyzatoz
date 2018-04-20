package com.benclaus.koperasi.model;

public class Config {
	private String excelSrcDir;
	private String excelBakDir;
	private String A3TemplateDir;
	private Integer processInterval;
	
	public String getExcelSrcDir() {
		return excelSrcDir;
	}
	public void setExcelSrcDir(String excelSrcDir) {
		this.excelSrcDir = excelSrcDir;
	}
	public String getExcelBakDir() {
		return excelBakDir;
	}
	public void setExcelBakDir(String excelBakDir) {
		this.excelBakDir = excelBakDir;
	}
	public String getA3TemplateDir() {
		return A3TemplateDir;
	}
	public void setA3TemplateDir(String a3TemplateDir) {
		A3TemplateDir = a3TemplateDir;
	}
	public Integer getProcessInterval() {
		return processInterval;
	}
	public void setProcessInterval(Integer processInterval) {
		this.processInterval = processInterval;
	}
}
