/**
 * 
 */
package com.benclaus.koperasi.report;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.action.trx.AjuAction;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

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
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer kreditId = Integer.parseInt(request.getParameter("id"));
		byte[] doc = null;//getAjuDocument(kreditId); // Method that generate and return PDF as byte[]

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline;filename=document.pdf"); // Change from "attachment" to
																						// "inline" for opening in
																						// browser (attachment should
																						// download directly)
		response.setContentLength(doc.length);

		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(doc);
			out.flush();
		} catch (IOException e) {
		} finally {
			if (out != null)
				out.close();
		}
	}

	private void getAjuDocument(Integer kreditId){
		Map<String, Object> param = new HashMap<>();
		param.put("pt", "PT.GS");
		param.put("aplikasi", "1. Ijazah SMP\n2. Jamsostek + BPJS\n3. ATM + Buku tabungan\n4. Parklaring PT. GS");
		param.put("nik", "GS.1235");
		param.put("nama", "SHOFYAH RAHMAN");
		param.put("bagian", "AQ");
		param.put("bank", "HANA BANK");
		param.put("supervisor", "SAMY");
		param.put("status", "RO");
		param.put("surveyor", "ACEP");
		param.put("ref_trf", "0");
		param.put("hp", "0812-2345-8979 / 0813-8765-9876");
		param.put("note", "0");
		param.put("status_kerja", 0);
		param.put("pokok", new Double(4000000));
		param.put("cicilan", new Double(450000));
		param.put("tenor", 10);
		param.put("tgl_lunas", new Date());
		param.put("no_kredit", "110.32459");
		param.put("deadline", "9");
		param.put("tgl_aju", new Date());

		byte[] bytes = null;

		try {
//			JasperReport jasperReport =  (JasperReport)JRLoader.loadObject(
//					AjuAction.class.getResourceAsStream("/com/benclaus/koperasi/resources/jrxml/Aju.jasper"));
			
			JasperReport jasperReport = JasperCompileManager.compileReport(
					AjuAction.class.getResourceAsStream("/com/benclaus/koperasi/resources/jrxml/Aju.jrxml"));
			JasperPrint printer = JasperFillManager.fillReport(jasperReport, param);
//			bytes = JasperExportManager.exportReportToPdf(printer);
			JasperExportManager.exportReportToPdfFile(printer, "d://test.pdf");
			
//			bytes = JasperRunManager.runReportToPdf(jasperReport, param);
			
		} catch (JRException e) {
			e.printStackTrace();
		}
//		return bytes;
	}
	public static void main(String[] args) {
		new ReportViewer().getAjuDocument(1);
	}
}
