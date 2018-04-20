package com.benclaus.koperasi.model.usm;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class User extends Role{
	protected String userCode;
	protected String password;
	protected String createdBy;
	protected String sessionTimeOut;
	protected String activeStatus;
	protected String roleCode;
	protected String companyCode;
	protected String companyName;
	protected String mobile;
	protected String emailAddress;
	protected String firstName;
	protected String lastName;
	protected String roleName;
	protected String fullName;
	protected Date lastLogon;
	protected String statusLabel;
	protected List userRoleHas;
	protected List userRoleNoHas;
	protected String passwordConf;
	protected String sessionId;
	protected String initials;
	protected String newPassword;

	public User() {
		super();
		super.tableName = "ms_user";
		super.fields = "userCode";
	}
	
	public User(String userCode) {
		super();
		this.userCode = userCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setUserCode(String userCode) { 
		this.userCode = userCode; 
		super.contents = userCode; 
	}
	public String getUserCode() { return userCode; }

	
	public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
	public String getRoleCode() { return roleCode; }
	
	public void setUserRoleHas(List userRoleHas) { this.userRoleHas = userRoleHas; }
	public List getUserRoleHas() { return userRoleHas; }
	
	public void setUserRoleNoHas(List userRoleNoHas) { this.userRoleNoHas = userRoleNoHas; }
	public List getUserRoleNoHas() { return userRoleNoHas; }
	
	public void setLastLogon(Date lastLogon) { this.lastLogon = lastLogon; }
	public Date getLastLogon() {return lastLogon;	}

	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return password; }
	
	public String getCompanyCode() { return companyCode; }
	public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }

	public void setSessionTimeOut(String sessionTimeOut) { this.sessionTimeOut = sessionTimeOut; }
	public String getSessionTimeOut() { return sessionTimeOut;	}
	
	public String getActiveStatus() { return activeStatus; }
	public void setActiveStatus(String activeStatus) { this.activeStatus = activeStatus; }
	
	public String getPasswordConf() { return passwordConf; }
	public void setPasswordConf(String passwordConf) { this.passwordConf = passwordConf; }
	
	public void setSessionId(String sessionId) { this.sessionId = sessionId; }
	public String getSessionId() { return sessionId; }
	
	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT USM_USER]>>>>> ");        
            try{
                Field[] fields = User. class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
	/**
	 * Returns the initial.
	 * @return String
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * Sets the initial.
	 * @param initial The initial to set
	 */
	public void setInitials(String initial) {
		this.initials = initial;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}