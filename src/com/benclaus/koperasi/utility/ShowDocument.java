/**
 * 
 */
package com.benclaus.koperasi.utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David J W
 *
 */
public class ShowDocument extends HttpServlet {

	/**
	 * 
	 */
	public ShowDocument() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException {
		doPost(httpservletrequest, httpservletresponse);
	}

	protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException {
		processRequest(httpservletrequest, httpservletresponse);
	}

	protected void processRequest(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException {
	    String s = httpservletrequest.getParameter("fileName");
	    int i = s.lastIndexOf(".");
	    String s1 = s.substring(i + 1, s.length());
	    if(s1.equals("pdf"))
	        httpservletresponse.setContentType("application/pdf");
	    else
	    if(s1.equals("doc"))
	        httpservletresponse.setContentType("application/msword");
	    else
	    if(s1.equals("xls"))
	        httpservletresponse.setContentType("application/vnd.ms-excel");
	    else
	    if(s1.equals("gif"))
	        httpservletresponse.setContentType("image/gif");
	    else
	    if(s1.equals("jpg"))
	        httpservletresponse.setContentType("image/jpeg");
	    else
	        httpservletresponse.setContentType("mime/type");
	    
	    javax.servlet.ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
	    ShowUtil showutil = new ShowUtil();
	    showutil.showDoc(s, servletoutputstream, httpservletresponse);
	}


}
