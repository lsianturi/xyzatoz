package com.benclaus.koperasi.model;

public class Employee {
	private Integer companyId;
	private Integer employeeNo;
	private String firstName;
	private String lastName;
	private String companyName;
	private String department;
	private String email;
	private String jobLevel;
	private String positionCode;
	private String superiorPositionCode;
	private Double leaveBalance;
	private Double endYearLeave;
	private Integer skipForEmptyTapCheck;
	private Integer skipAsNotificationSubject;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public Double getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(Double leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public Double getEndYearLeave() {
		return endYearLeave;
	}
	public void setEndYearLeave(Double endYearLeave) {
		this.endYearLeave = endYearLeave;
	}
	public Integer getSkipForEmptyTapCheck() {
		return skipForEmptyTapCheck;
	}
	public void setSkipForEmptyTapCheck(Integer skipForEmptyTapCheck) {
		this.skipForEmptyTapCheck = skipForEmptyTapCheck;
	}
	public Integer getSkipAsNotificationSubject() {
		return skipAsNotificationSubject;
	}
	public void setSkipAsNotificationSubject(Integer skipAsNotificationSubject) {
		this.skipAsNotificationSubject = skipAsNotificationSubject;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(Integer employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getSuperiorPositionCode() {
		return superiorPositionCode;
	}
	public void setSuperiorPositionCode(String superiorPositionCode) {
		this.superiorPositionCode = superiorPositionCode;
	}
	

}
