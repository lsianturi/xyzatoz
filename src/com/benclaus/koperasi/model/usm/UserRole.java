package com.benclaus.koperasi.model.usm;

import com.benclaus.koperasi.model.system.Audit;

public class UserRole extends Audit{
	private String userCode;
	private String roleCode;
	private String name;
	
	public UserRole() {
		super();
		super.tableName = "ms_userrole";
		super.fields = "userCode, roleCode";
	}
	
	public void setUserCode(String userCode){ 
		this.userCode = userCode; 
		super.contents = super.contents +" "+ userCode;
	}
	public String getUserCode(){ return userCode; }
	
	public void setRoleCode(String roleCode){ 
		this.roleCode = roleCode; 
		super.contents = super.contents +" "+ roleCode;
	}
	public String getRoleCode(){ return roleCode; }
	
	public void setName(String name){ this.name = name; }
	public String getName(){ return name; }
}