package id.co.ggpc.absensi.action.app;

import java.awt.Color;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import id.co.ggpc.absensi.action.SecurityAction;
import id.co.ggpc.absensi.dao.app.AbsensiService;
import id.co.ggpc.absensi.model.Employee;
import id.co.ggpc.absensi.model.EmployeeAbsensi;
import id.co.ggpc.absensi.model.MonthlySummary;
import id.co.ggpc.absensi.utility.Constant;
import id.co.ggpc.absensi.utility.DAFContainer;
import id.co.ggpc.absensi.utility.DateHelper;
import id.co.ggpc.absensi.utility.LabelValueBean;
import id.co.ggpc.absensi.utility.YearHelper;
/**
 * @author Lambok
 *
 */
public final class MonthlySummaryAction extends SecurityAction{
	private static Logger log = Logger.getLogger(MonthlySummaryAction.class);
	private String MENU_RPT_QUERY = "summary_abs";
	
	private static Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
	private static Font subTitleFont = new Font(Font.HELVETICA, 10, Font.BOLD);

	private static Font tableHeader = new Font(Font.HELVETICA, 8, Font.BOLD);
	private static Font tableRow = new Font(Font.HELVETICA, 8, Font.NORMAL);
	private static Font tableRowRed = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.RED);
	private static Font tableRowGreen = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.BLUE);
	
	private AbsensiService service = AbsensiService.getInstance();
	
	private void prepareSearch (HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			request.setAttribute("monthList", YearHelper.getInstance().getMonthList());
			request.setAttribute("deptList", service.getDeptList(Integer.parseInt(session.getAttribute(Constant.COMPANY).toString())));
			request.setAttribute("yearList",  (service.getYearList() != null ||service.getYearList().size()==0)? service.getYearList() :
				new ArrayList().add(new LabelValueBean(""+Calendar.getInstance().get(Calendar.YEAR), ""+Calendar.getInstance().get(Calendar.YEAR))));
				request.setAttribute("empList", service.getEmployeeList(null));
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("prepare");
		
		ActionForward forward = new ActionForward();
		
		DynaActionForm rptForm = (DynaActionForm) form;
		
		forward = hasMenuAccess(mapping, request, MENU_RPT_QUERY);
		if (forward != null) { return forward; }
		prepareSearch(request);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int month = cal.get(Calendar.MONTH) +1;
		int year = cal.get(Calendar.YEAR);
		rptForm.set("month", month<10 ? "0"+month : ""+month);
		rptForm.set("year", ""+year);
		saveToken(request);
		
		rptForm.set("dispatch", "generate");
		forward = mapping.findForward("main");
		return forward;
	}
	
	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(MENU_RPT_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_RPT_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return mapping.findForward("main");
	}
	
	public ActionForward generate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("generate");
		
		prepareSearch(request);
		ActionErrors errors = new ActionErrors();			
		HttpSession session = request.getSession();
		DynaActionForm rptForm = (DynaActionForm) form;
		
		List<EmployeeAbsensi> leaveData = null;
		Integer empId = 0;
		Integer workdays=0;
		Integer hols=0;
		Integer annualLeave = 0;
		Integer businessTravel = 0;
		Integer annualLeaveHalfDay = 0;
		Integer sickLeave = 0;
		Integer specialLeave = 0;
		Integer unpaidLeave = 0;
		Integer leaveHours = 0;
		StringBuilder sb = new StringBuilder();
		
		try{
			String dept = rptForm.getString("department");
			try {
				empId = Integer.parseInt(rptForm.getString("empId"));
				
			} catch (Exception e) {empId=0;}
			Integer month = new Integer(rptForm.get("month").toString());
			Integer year = new Integer(rptForm.get("year").toString());
			
//			Integer wd = 0;
			Map<String, Object> param = new HashMap<>();
			param = rptForm.getMap();
//			if (empId != 0) param.put("empId", empId);
//			if (dept != null && !dept.equals("")) param.put("department", dept);
			param.put("firstDate", year +"-"+ param.get("month") + "-" + "01");
			param.put("company", session.getAttribute(Constant.COMPANY));
			
			hols = service.getHolidayNumber(param); //this number already exclude holiday in weekend
			workdays = service.getNumberOfWorkingDayInMonth(year +"-"+ month + "-" + "01");
			workdays -= hols;
			List<MonthlySummary> summaryList =null;
			summaryList = service.getSummary(param);
			
			if (summaryList != null && summaryList.size() > 0) {
				for (MonthlySummary sum : summaryList) {
					param.put("employeeNo", sum.getEmployeeNo());
					leaveData = service.getLeaveDateByMonth(param);
					annualLeave = 0;
					businessTravel = 0;
					annualLeaveHalfDay = 0;
					sickLeave = 0;
					specialLeave = 0;
					unpaidLeave = 0;
					leaveHours = 0;
					sb.replace(0, sb.length(), "");
					if (leaveData !=null ) {
						for(EmployeeAbsensi leave : leaveData) {
							try {
								service.insertEmployeeLeave(leave);
							}catch (Exception e){
								try {
									service.updateEmployeeLeave(leave);
								} catch (Exception e2) {
									log.debug(e2);
								}
							}
							
							if (leave.getLeaveType().startsWith("Annual Leave")) {
								annualLeave++;
								leaveHours += 8;
							} else if (leave.getLeaveType().startsWith("Business Travel")) {
								businessTravel++;
								leaveHours += 8;
							} else if (leave.getLeaveType().startsWith("Annual Leave Half Day")) {
								annualLeaveHalfDay ++;
								leaveHours += 4;
							} else if (leave.getLeaveType().startsWith("Sick Leave")) {
								sickLeave++;
								leaveHours += 8;
							} else if (leave.getLeaveType().startsWith("Special Leave")) {
								specialLeave++;
								leaveHours += 8;
							} else if (leave.getLeaveType().startsWith("Unpaid Leave")) {
								unpaidLeave++;
								leaveHours += 8;
							}
						}
						if (annualLeave > 0) {
							sb.append("AL: " + annualLeave + ". ");
						}
						if (businessTravel > 0) {
							sb.append("BT: " + businessTravel+ ". ");
						}
						if (annualLeaveHalfDay > 0) {
							sb.append("ALH: " + annualLeaveHalfDay+ ". ");
						}
						if (sickLeave > 0) {
							sb.append("SiL: " + sickLeave + ". ");
						}
						if (specialLeave > 0) {
							sb.append("SpL: " + specialLeave+ ". ");
						}
						if (unpaidLeave > 0) {
							sb.append("UL: " + unpaidLeave+ ". ");
						}
						if (sb.length()>1) {
							sb.append("\nTotal: "+leaveHours +"h.");
						}
					}
					sum.setNote(sb.toString());
				}
				
				try {
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "attachment; filename=" + year + "-"+param.get("month") + "-MonthlySummary.pdf");
					OutputStream fileOut = response.getOutputStream();
					
					Document document = new Document(new Rectangle(792, 612));
					PdfWriter.getInstance(document, fileOut);
					document.open();
					addMetaData(document);
					addContent(document, month, year, summaryList, workdays);
					document.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "There is no data to summarize."));
				saveErrors(request, errors);
//				forward = mapping.findForward("invalidPage");
			}
			
		}catch(Exception e) { 						
			log.error(e.getMessage(), e);			
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
			saveErrors(request, errors);
		}
		return mapping.findForward("main");
	} 
	
	private static void addMetaData(Document document) {
		document.addTitle("Attendance summary");
		document.addSubject("Attendance summary");
		document.addKeywords("attendance,summary");
		document.addAuthor("Lambok Sianturi");
		document.addCreator("Lambok Sianturi");
	}
	
	private static void addContent(Document document, Integer month, Integer year, List<MonthlySummary> summary, Integer workdays) throws DocumentException {
		Paragraph report = new Paragraph();
		Paragraph title = new Paragraph();
		title.add(new Paragraph("Attendance Monthly Summary Report", titleFont));
		title.add(new Paragraph("In Time: 08:00 WIB               Out time: 17:15 / 17:30 WIB (Friday)", subTitleFont));
		title.add(new Paragraph("Daily work hours: 8hr (excl. break)", subTitleFont));
		title.add(new Paragraph("Month: " + YearHelper.getInstance().getMonthName(month)+", Year " + year, subTitleFont));
		title.add(new Paragraph("Workday(s): "+ workdays + " days = " + workdays * 8 + " hours", subTitleFont));
		
		title.add(new Paragraph("AL: Annual Leave, BT: Business Travel, ANL: Annual Leave Half day, SiL: Sick Leave, SpL: Special Leave, UL: Unpaid Leave", tableRow));

		report.add(title);

		// add a table
		createTable(report, summary, workdays * 8);
		
		// now add all this to the document
		document.add(report);

	}

private static void createTable(Paragraph content, List<MonthlySummary> summary, Integer workhour) throws BadElementException, DocumentException  {
		PdfPTable table = new PdfPTable(15);
		table.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
		table.setWidths(new float[] { 25, 55, 100, 95, 50, 35, 45,40, 40, 40, 40, 40, 40, 40, 120 });
		table.setWidthPercentage(100);
		table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        
		
		PdfPCell c1 = new PdfPCell(new Phrase("No", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Employee No", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Name", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Job Title", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Job Level", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Work days", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Work hours", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Early In", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Late In", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Early Out", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Late Out", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Under time", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Over time", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Over - Under", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Leave", tableHeader));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		table.setHeaderRows(1);
		int i = 1;
		Integer hour =0;
		for (MonthlySummary sum : summary) {
			if (sum.getWorkdays() != null && sum.getWorkdays() != 0) {
				hour = Integer.parseInt(sum.getTotalWork().substring(0, sum.getTotalWork().indexOf("h") ));
				table.addCell(new Phrase(""+ i++, tableRow));
				table.addCell(new Phrase(sum.getEmployeeNo().toString(), tableRow));
				table.addCell(new Phrase(WordUtils.capitalizeFully(sum.getFirstName() + ( sum.getLastName() != null ? " " +sum.getLastName() : "")), tableRow));
				table.addCell(new Phrase(WordUtils.capitalizeFully(sum.getJobTitle()), tableRow));
				table.addCell(new Phrase(WordUtils.capitalizeFully(sum.getJobLevel()), tableRow));
				table.addCell(new Phrase(""+sum.getWorkdays(), tableRow));
				if (hour == workhour ) {
					table.addCell(new Phrase(sum.getTotalWork(), tableRow));
				} else if (hour > workhour) { 
					table.addCell(new Phrase(sum.getTotalWork(), tableRowGreen));
				} else {
					table.addCell(new Phrase(sum.getTotalWork(), tableRowRed));
				}
				table.addCell(new Phrase(sum.getEarlyIn(), tableRow));
				table.addCell(new Phrase(sum.getLateIn(), tableRow));
				table.addCell(new Phrase(sum.getEarlyOut(), tableRow));
				table.addCell(new Phrase(sum.getLateOut(), tableRow));
				table.addCell(new Phrase(sum.getUnderTime(), tableRow));
				table.addCell(new Phrase(sum.getOverTime(), tableRow));
				if (sum.getOverMinUnder().startsWith("-")) {
					table.addCell(new Phrase(sum.getOverMinUnder(), tableRowRed));
				} else {
					table.addCell(new Phrase(sum.getOverMinUnder(), tableRow));
				}
				table.addCell(new Phrase(sum.getNote(), tableRow));
			}
		}

		content.add(table);

	}
	
}