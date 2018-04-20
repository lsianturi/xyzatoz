package com.benclaus.koperasi.model.usm;

import java.lang.reflect.Field;


public class Role extends Menu{
	private String roleCode;
	private String name;
	private String description;
    
    public Role() {
    	super();
    	super.tableName = "ms_role";
    	super.fields = "roleCode";
    }
	
	public void setRoleCode(String roleCode) { 
		this.roleCode = roleCode;
		super.contents = roleCode;
	}	
	public String getRoleCode() { return roleCode; }
	
	public void setName(String name) { this.name = name;}	
	public String getName() { return name; }
	
	public void setDescription(String description) { this.description = description;}	
	public String getDescription() { return description; }
	
	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT MS_ROLE]>>>>> ");        
            try{
                Field[] fields = Role. class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }	
}