package com.benclaus.koperasi.model.usm;

import java.lang.reflect.Field;

import com.benclaus.koperasi.model.system.Audit;

public class Menu extends Audit{
	
	private String menuCode;
	private String parentMenuCode;
	private String name;
	private String description;
	private String menuType;
	private String systemMenu;
	private String menuOrder;
	private String menuLevel;
	private String command;
	private Integer child;
	private Integer hadChild;	
	
	public Menu() {
		super();
		super.tableName = "ms_menu";
		super.fields = "menuCode";
	}
	
	public void setMenuCode(String menuCode) { 
		this.menuCode = menuCode; 
		super.contents = menuCode;
	}
	public String getMenuCode() { return menuCode; }

	public void setParentMenuCode(String parentMenuCode) { this.parentMenuCode = parentMenuCode; }
	public String getParentMenuCode() { return parentMenuCode; }
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return description; }
	
	public void setMenuType(String menuType) { this.menuType = menuType;}
	public String getMenuType() { return menuType; }
	
	public void setSystemMenu(String systemMenu) { this.systemMenu = systemMenu; }
	public String getSystemMenu() { return systemMenu; }
	
	public void setMenuOrder(String menuOrder) { this.menuOrder = menuOrder; }
	public String getMenuOrder() { return menuOrder; }
	
	public void setMenuLevel(String menuLevel) { this.menuLevel = menuLevel; }
	public String getMenuLevel() { return menuLevel; }
	
	public void setCommand(String command) { this.command = command; }
	public String getCommand() { return command; }

	public void setChild(Integer child) { this.child = child; }
	public Integer getChild() { return child; }
	
	public void setHadChild(Integer hadChild) { this.hadChild = hadChild; }
	public Integer getHadChild() { return hadChild; }
	
	public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("[SOUT USM_MENU]>>>>> ");        
            try{
                Field[] fields = Menu.class.getDeclaredFields();
                for (int i = 1; i < fields.length; i++) {
                    strBuff.append(fields[i].getName()).append(" = ").append(fields[i].get(this)).append(", ");
                }
            }catch(IllegalAccessException iae) {}

        return strBuff.toString();
    }
}