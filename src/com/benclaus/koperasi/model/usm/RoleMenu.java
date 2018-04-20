package com.benclaus.koperasi.model.usm;

import com.benclaus.koperasi.model.system.Audit;

public class RoleMenu extends Audit{
	
	private String roleCode;
	private String description;
	private String menuCode;
	private String parentMenuCode;
	private Integer child;
	private Integer hadChild;	
	
	
	public RoleMenu() {
		super();
		super.tableName = "ms_rolemenu";
		super.fields = "menuCode, roleMenu";
	}
	
	public void setRoleCode(String roleCode) { 
		this.roleCode = roleCode; 
		super.contents = super.contents + " "+ roleCode;
	}
	public String getRoleCode() { return roleCode; }
	
	public void setMenuCode(String menuCode) { 
		this.menuCode = menuCode;	
		super.contents = super.contents + " "+ menuCode;
	}	
	public String getMenuCode() { return menuCode; }	
		
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public void setParentMenuCode(String parentMenuCode) { this.parentMenuCode = parentMenuCode;	}	
	public String getParentMenuCode() { return parentMenuCode; }	
	
	public void setChild(Integer child) { this.child = child; }
	public Integer getChild() { return child; }
	
	public void setHadChild(Integer hadChild) { this.hadChild = hadChild; }
	public Integer getHadChild() { return hadChild; }	
}