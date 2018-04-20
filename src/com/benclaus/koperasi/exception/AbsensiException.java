package com.benclaus.koperasi.exception;

import java.sql.SQLException;

public class AbsensiException extends Exception {
	private static final long serialVersionUID = 8183550158559909899L;
	
	public final static int DUPLICATE_ENTRY = 1062;
	
	private int errorCode = -1;

	public AbsensiException() {
		super();
	}
	
	public AbsensiException(String message) {
		super(message);
	}
	
	public AbsensiException(String message, Exception e) {
		super(message, e);
		
		if (e instanceof SQLException) {
			errorCode = ((SQLException) e).getErrorCode();
		}
	}	
	
	public AbsensiException(Exception e) {
		super(e);
		
		if (e instanceof SQLException) {
			errorCode = ((SQLException) e).getErrorCode();
		}
	}
	
	public String toString() {
		StackTraceElement[] elements = super.getStackTrace();
		if (elements.length> 0) {
			return  super.toString() + "<br>"+elements[0].toString();
		}else{
			return  super.toString();	
		}
	} 
	
	public String getMessage() {
		return super.getMessage();
	}

	public int getErrorCode() { return errorCode; }
	
	
}
