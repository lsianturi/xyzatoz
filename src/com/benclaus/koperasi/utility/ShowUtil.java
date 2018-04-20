package com.benclaus.koperasi.utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 
 *
 */
public class ShowUtil {

	/**
	 * 
	 */
	public ShowUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public void showDoc(String s, OutputStream outputstream, HttpServletResponse httpservletresponse) throws IOException {
	    
		try {
	        File file = new File(s);
	        FileInputStream fileinputstream = new FileInputStream(file);
	        DataInputStream datainputstream = new DataInputStream(fileinputstream);
	        int i;
	        while((i = datainputstream.read()) != -1) 
	            outputstream.write(i);
	        datainputstream.close();
	        outputstream.flush();
	    }
	    catch(IOException ioexception) {
	        httpservletresponse.setContentType("text/html");
	        httpservletresponse.getOutputStream().print("Error");
	        httpservletresponse.getOutputStream().print(ioexception.toString());
	    }
	}
}
