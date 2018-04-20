package com.benclaus.koperasi.model.system;

import java.lang.reflect.Field;
import java.util.Date;

import com.benclaus.koperasi.model.usm.User;

public class Audit {
	protected String tableName;
	protected String fields;
	protected String contents = "";
	protected Date createDtm;
	protected String createdBy;
	protected Date lastUpdDtm;
	protected String lastUpdBy;
	protected String lastUpdProcess;
	protected Integer deleted;
	

	public String getCreatedBy() { return createdBy; }
	public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
		
	public String getLastUpdBy() { return lastUpdBy; }
	public void setLastUpdBy(String lastUpdBy) { this.lastUpdBy = lastUpdBy; }
	
	public Date getCreateDtm() { return createDtm; }
	public void setCreateDtm(Date createDtm) { this.createDtm = createDtm; }	
	
	public void setLastUpdDtm(Date lastUpdDtm) { this.lastUpdDtm = lastUpdDtm; }
	public Date getLastUpdDtm() { return lastUpdDtm; }

	public String getLastUpdProcess() { return lastUpdProcess; }
	public void setLastUpdProcess(String lastUpdProcess) { this.lastUpdProcess = lastUpdProcess; }

	public Integer getDeleted() { return deleted; }
	public void setDeleted(Integer deleted) { this.deleted = deleted; }

	public String getContents() { return contents; }
	public void setContents(String contents) { this.contents = contents; }
	
	public String getFields() { return fields; }
	public void setFields(String fields) { this.fields = fields; }
	
	public String getTableName() { return tableName; }
	public void setTableName(String tableName) { this.tableName = tableName; }
	
	public void setAuditTrail(User user, String process) { 
		setLastUpdBy(user.getUserCode());
		setCreatedBy(user.getUserCode());
		setLastUpdProcess(process);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		try {
			Field[] fields = Audit.class.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
					sb.append(",").append(fields[i].getName()).append("=").append(fields[i].get(this));
			}
		} catch (IllegalAccessException iae) {}
		return sb.toString();
	}
}