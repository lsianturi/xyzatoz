/**
 * 
 */
package com.benclaus.koperasi.report;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok 
 *
 */
public class ReportViewer extends HttpServlet {
	private static Logger log = Logger.getLogger(ReportViewer.class);
	/**
	 * 
	 */
	public ReportViewer() {
		// TODO Auto-generated constructor stub
	}
	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("Report Viewer");
		int BUFSIZE = 4096;
		ActionErrors errors = new ActionErrors();
//		HttpSession session = request.getSession();
//		RequestDispatcher rd = request.getRequestDispatcher("/invalidPage.jsp");

//		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

		// check user logged in
//		if (userLogin == null || !userLogin.isLoggedIn()) {
//			log.debug("Invalid Login");
//			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidLogin"));
//			request.setAttribute(Globals.ERROR_KEY, errors);
//			rd.forward(request, response);
//			return;
//		}
		
		try{
			ConfigService service = ConfigService.getInstance();
			String type = request.getParameter("type");
			Integer year = new Integer(request.getParameter("year"));
			
			/*AbsenSummary rpt = null;
			if (type.equals("report")) {
				Integer company = Integer.parseInt(request.getParameter("company"));
				Integer empId = Integer.parseInt(request.getParameter("empId"));
				Integer month = new Integer(request.getParameter("month"));
				
				rpt = service.getAttendanceReport(company, empId, month, year);
			} else {
				rpt = service.getSummaryReport(year);
			}
			
			
			OutputStream servletOutStream = response.getOutputStream();
			log.debug("Write the report "+rpt.getFile()+" to browser..");
			File file = new File(rpt.getFile());
			if (file.isFile()) {
				int length = 0;
				ServletContext context  = getServletConfig().getServletContext();
			    String mimetype = context.getMimeType( rpt.getFile() );
				
				response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
				response.setContentLength( (int)file.length() );
				response.setHeader( "Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" );
				
				byte[] bbuf = new byte[BUFSIZE];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				
				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					servletOutStream.write(bbuf,0,length);
				}
				
				in.close();
				servletOutStream.flush();
				servletOutStream.close();
			} else {
				errors.add(
						Constant.GLOBALERROR,
						new ActionError("error.downloadMsg"));
			}*/
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
					Constant.GLOBALERROR,
					new ActionError("error.exception", e.getMessage()));
		}

//		if (errors.size() > 0) {
//			request.setAttribute(Globals.ERROR_KEY, errors);
//			rd.forward(request, response);
//			return;
//		}
	}
}
