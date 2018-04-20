package com.benclaus.koperasi.model.usm;


import java.util.Date;

import com.benclaus.koperasi.model.system.Audit;


public class LoginTrail extends Audit{
	protected String userCode;
	protected String sessionId;
	protected Date lastUpdDtm;
	protected String lastUpdProcess;

	public LoginTrail() {
		super();
		this.tableName = "absen_logintrail";
		this.fields = "userCode";
	}
	
	public String getUserCode() { return userCode; }
	public void setUserCode(String userCode) { 
		this.userCode = userCode; 
		setContents(userCode);
		setLastUpdBy(userCode);
	}

	public Date getLastUpdDtm() { return lastUpdDtm; }
	public void setLastUpdDtm(Date lastUpdDtm) { this.lastUpdDtm = lastUpdDtm; }
	
	public String getLastUpdProcess() { return lastUpdProcess; }
	public void setLastUpdProcess(String lastUpdProcess) { this.lastUpdProcess = lastUpdProcess; }
	
	public String getSessionId() { return sessionId; }
	public void setSessionId(String sessionId) { this.sessionId = sessionId; }
	
}