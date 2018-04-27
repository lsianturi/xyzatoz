package com.benclaus.koperasi.exception;

import org.apache.struts.util.MessageResources;
/**
 * @author Lambok
 *
 */
public class DaoException extends com.ibatis.dao.client.DaoException {
	static final long serialVersionUID=1L;

	protected static MessageResources messages = MessageResources.getMessageResources("com/benclaus/koperasi/resources/ApplicationResources");
	public DaoException() {
		super();
	}	

	public DaoException(String arg0) {
		super(arg0);
	}
	
	public DaoException(Throwable arg0) {
		super(arg0);
	}
	
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	/*public String getMessage() {
		String message = super.getMessage();
		if (getCause() instanceof SQLException) {
			SQLException sqle = (SQLException) getCause();
			String sqlState = sqle.getSQLState();
//			System.out.println("NIA "+sqlState);
			String msg = messages.getMessage(sqlState);
//			System.out.println("NIA "+msg);			
			if (msg!=null) message = msg;
		}
		return message;
	}*/
}
