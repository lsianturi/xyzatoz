package com.benclaus.koperasi.utility;

public interface Constant {
	// Constant for First Menu
	String FIRST_MENU = "KOP";
	
	//Login WebServices
	String SSO_WS_URL="SSO_WS_URL";
	
	// Constant for Dispatch Action
	String DEFAULT = "DEFAULT";
	String ADD = "add";
	String ADDFIRSTSTEP = "addFirstStep";
	String ADDSAVE = "addSave";
		
	String ADDCHILDSAVE = "addChildSave";
	String UPDATE = "update";
	String UPDATESAVE = "updateSave";
	String VIEW = "view";
	String DELETE = "delete";
	String REMOVE = "remove";
	String SEARCH = "search";
	String DATA = "DATA";
	String PAGE = "changePage";
	String RETURNED = "returned";
	String PREPARE = "prepare";
	String NEXT = "next";
	
	
	// Constant for SupplySlip Pages
	String SS_ENTRY="entry";
	String SS_PRINTED="printed"; 
	
	// Constant for Page Size
	int PAGESIZE = 18;
	
	String SES_USERLOGIN = "UserLogin";
	String RPT_REPORT = "REPORT";
	String EMPTY_STRING = "";
	Integer VALUE_ZERO=0;

	String TRUE = "1";
	String FALSE = "0";
	String GLOBALERROR = "GlobalError";
	String GLOBALMESSAGE = "GlobalMessage";
	String GLOBALDATEFORMAT = "dd/MM/yyyy";
	String DB_GLOBALDATEFORMAT = "yyyy-MM-dd";
	String GLOBALTIMESTAMP = "dd/MM/yyyy hh:mm:ss";
	String DB_GLOBALTIMESTAMP = "yyyy-MM-dd hh:mm:ss";
	String DB_GLOBALTIME = "hh:mm:ss";
	String LONGDATEFORMAT = "dd MMMM yyyy";
	String HANDLEDERROR = "HandledError";
	
	String CAT_ACTIVESTATUS_ACTIVE = "1";
	String CAT_ACTIVESTATUS_NONACTIVE = "0";
}
