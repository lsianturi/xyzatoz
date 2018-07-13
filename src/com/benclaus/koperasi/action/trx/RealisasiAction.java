package com.benclaus.koperasi.action.trx;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.master.NasabahService;
import com.benclaus.koperasi.dao.master.StatusPKService;
import com.benclaus.koperasi.dao.trx.AjuService;
import com.benclaus.koperasi.dao.trx.RealisasiService;
import com.benclaus.koperasi.model.master.Nasabah;
import com.benclaus.koperasi.model.master.Pegawai;
import com.benclaus.koperasi.model.trx.Aju;
import com.benclaus.koperasi.model.trx.JenisPinjam;
import com.benclaus.koperasi.model.trx.Simulasi;
import com.benclaus.koperasi.model.trx.TipeKredit;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

public class RealisasiAction extends SecurityAction {
	private static Logger log = Logger.getLogger(RealisasiAction.class);
	private String TRX_REAL_QUERY = "TRX_REAL_search";
	private String TRX_REAL_ADD = "TRX_REAL_add";
	private String TRX_REAL_UPD = "TRX_REAL_upd";
	private String TRX_REAL_DEL= "TRX_REAL_del";
	private String TRX_REAL_VIEW = "TRX_REAL_view";

	private StatusPKService stService = StatusPKService.getInstance();
	private NasabahService nService = NasabahService.getInstance();
	private RealisasiService service = RealisasiService.getInstance();
	private AjuService ajuService = AjuService.getInstance();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdfMysql = new SimpleDateFormat("yyyy-MM-dd");
	private Locale locale = new Locale("us", "US");
	private NumberFormat nf = NumberFormat.getInstance(locale);
	
	private void prepareSearch(HttpServletRequest request) {
		try {
			request.setAttribute("PerusahaanList", stService.listPerusahaan());
			request.setAttribute("TipeKreditList", TipeKredit.getTipeKredit());
			request.setAttribute("JenisPinjamList", JenisPinjam.getJenisPinjam());
			request.setAttribute("AgentList", stService.listAgent());
			request.setAttribute("MarketingList", stService.listPegawai());
			request.setAttribute("StatusList", stService.listStatusKredit());
		} catch (Exception e) {
		}
	}
	
	private void prepareData(HttpServletRequest request) {
		try {
			request.setAttribute("KreditList", TipeKredit.getTipeKredit());
			request.setAttribute("JenisPinjam", JenisPinjam.getJenisPinjam());
			request.setAttribute("SukuBunga", StatusPKService.getInstance().listSukuBunga());
			request.setAttribute("AgentList", stService.listAgent());
			request.setAttribute("MarketingList", stService.listPegawai());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("prepare");

		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(true);
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		DynaActionForm myForm = (DynaActionForm) form;

		forward = hasMenuAccess(mapping, request, TRX_REAL_QUERY);
		if (forward != null) {
			return forward;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONDAY, -2);
		cal.set(Calendar.DATE, 1);
		myForm.set("tglAjuFrom", sdf.format(cal.getTime()));
		cal.setTime(new Date());
		myForm.set("tglAjuTo", sdf.format(cal.getTime()));
		prepareSearch(request);
		session.removeAttribute(TRX_REAL_QUERY);

		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}

	public ActionForward hitungCicilan(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("hitungCicilan");
		String interestRate = request.getParameter("sukuBunga");
		String jmlAju = request.getParameter("jmlAju");
		String tenorS = request.getParameter("tenor");
		
		StringBuilder sb = new StringBuilder();
		Double ir = null;
		try {
			ir = Double.valueOf(interestRate);
		}catch (Exception e) {
			sb.append("Format angka suku bunga salah.");
		}
        Integer tenor=null;
		try {
			tenor = Integer.parseInt(tenorS);
		}catch (Exception e) {
			sb.append("Format angka tenor salah.");
		}
        Double pokok = null;
        try {
        	pokok = Double.valueOf(nf.parse(jmlAju).doubleValue());
        }catch (Exception e){
        	sb.append("Format angka jumlah aju salah.");
        }
        
        PrintWriter pw = response.getWriter();
        if (sb.length() > 1) {
        	pw.write(sb.toString());
        	pw.flush();
        	return null;
        }
        
        double angsuran = Simulasi.getCicilan(pokok, ir, tenor);
        sb.append(nf.format(angsuran));
        
		pw.write(sb.toString());
		pw.flush();
		
		return null;
	}
	
	public ActionForward simulasiKredit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("simulasiKredit");
		String interestRate = request.getParameter("sukuBunga");
		String jmlAju = request.getParameter("jmlAju");
		String tenorS = request.getParameter("tenor");
		String payroll = request.getParameter("tglPayroll");
		String aju = request.getParameter("tglAju");
		
		StringBuilder sb = new StringBuilder();
		Double ir = null;
		try {
			ir = Double.valueOf(nf.parse(interestRate).doubleValue());
		}catch (Exception e) {
			sb.append("Format angka suku bunga salah.");
		}
        Integer tenor=null;
		try {
			tenor = Integer.parseInt(tenorS);
		}catch (Exception e) {
			sb.append("Format angka tenor salah.");
		}
        Date tglPayroll= null;
        try {
        	tglPayroll = sdf.parse(payroll);
        }catch ( Exception e) {
        	sb.append("Jatuh tempo tidak bisa dihitung, cek tanggal payroll anggota.");
        }
        
        Date tglAju= null;
        try {
        	tglAju= sdf.parse(aju);
        }catch ( Exception e) {
        	sb.append("Jatuh tempo tidak bisa dihitung, cek tanggal aju.");
        }
        Double pokok = null;
        try {
        	pokok = Double.valueOf(nf.parse(jmlAju).doubleValue());
        }catch (Exception e){
        	sb.append("Format angka jumlah aju salah.");
        }
        
        PrintWriter pw = response.getWriter();
        if (sb.length() > 1) {
        	pw.write(sb.toString());
        	pw.flush();
        	return null;
        }
        
        Calendar calAju = Calendar.getInstance();
        calAju.setTime(tglAju);
        Calendar calPayroll = Calendar.getInstance();
        calPayroll.setTime(tglPayroll);
        Integer tglToday = calAju.get(Calendar.DATE);
        Integer tglGajian= calPayroll.get(Calendar.DATE);
        
        System.out.println(tglToday + " & " + tglGajian);
        
        calAju.set(Calendar.DATE, tglGajian);
        if ( tglGajian > tglToday) {
        	calAju.add(Calendar.MONTH, tenor-1);
        }
        
        sb.append(sdf.format(calAju.getTime()));
        sb.append(";");
        
        List<Simulasi> simulasi = Simulasi.getSimulasi(pokok, ir, tenor, tglAju, tglPayroll);
        
        int i =0;
        if (simulasi != null) {
        	sb.append("<tbody id=\"barisSimulasi\" >");
        	for (Simulasi sim : simulasi) {
	        	if( i % 2 == 0) {
	        		sb.append("<tr class=\"evenRow\" tbody=\"barisSimulasi\">");
	        	} else {
	        		sb.append("<tr class=\"oddRow\" tbody=\"barisSimulasi\">");
	        	}
		        sb.append("<td class=\"celBorder\">"+ i++ +"</td>");
		        sb.append("<td class=\"celBorder\">"+ sdf.format(sim.getTglCicilan()) +"</td>");
		        sb.append("<td class=\"celBorder\">"+ nf.format(sim.getSaldo()) +"</td>");
		        sb.append("<td class=\"celBorder\">"+ nf.format(sim.getPokok()) +"</td>");
		        sb.append("<td class=\"celBorder\">"+ nf.format(sim.getBunga()) +"</td>");
		        sb.append("<td class=\"celBorder\">"+ nf.format(sim.getAngsuran()) +"");
		        sb.append("</tr>");
        	}
        }
		
		pw.write(sb.toString());
		pw.flush();
		
		return null;
	}
	
	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(TRX_REAL_QUERY) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(TRX_REAL_QUERY);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, TRX_REAL_QUERY);
		if (forward != null)
			return forward;

		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm mapForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);
			
			Map<String, Object> param = mapForm.getMap();
			try {
				param.put("tglAjuFrom", sdfMysql.format(sdf.parse(param.get("tglAjuFrom").toString())));
				param.put("tglAjuTo", sdfMysql.format(sdf.parse(param.get("tglAjuTo").toString())));
			} catch (Exception e){}
			PaginatedList mapList = (PaginatedList) service.searchReal(param);
			int totalSize = service.searchRealSize(param);
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList, totalSize));
			
			try {
				param.put("tglAjuFrom", sdf.format(sdfMysql.parse(param.get("tglAjuFrom").toString())));
				param.put("tglAjuTo", sdf.format(sdfMysql.parse(param.get("tglAjuTo").toString())));
			}catch (Exception e){}
			
			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(TRX_REAL_QUERY, sessionForm);
			session.setAttribute("Data", mapList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}

		return mapping.findForward("continue");
	}

	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(TRX_REAL_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(TRX_REAL_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward real(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Realisasi");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, TRX_REAL_UPD);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer ajuId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			Aju aju = service.getReal(ajuId);
			BeanUtils.copyProperties(planForm, aju);
			planForm.set("tglAju", sdf.format(aju.getTglAju()));
			planForm.set("nsbhId", aju.getNasabah().getId());
			planForm.set("nsbhNama", aju.getNasabah().getNama());
			planForm.set("bunga", nf.format(aju.getInterestRate()).replace(",", "."));
			planForm.set("jumlahAju", nf.format(aju.getJumlahAju()));
			planForm.set("angsuranAju", nf.format(aju.getAngsuranAju()));
			planForm.set("nsbhAlamat", aju.getNasabah().getAlamat()); 
			planForm.set("nsbhDomisili", aju.getNasabah().getDomisili());
			planForm.set("nsbhJnsKelamin", aju.getNasabah().getJenisKelamin().getStatus());
			planForm.set("nsbhNik", aju.getNasabah().getNik());
			planForm.set("nsbhBagian", aju.getNasabah().getBagian());
			planForm.set("nsbhPt", aju.getNasabah().getPt().getNama());
			planForm.set("nsbhBank", aju.getNasabah().getBank().getNama());
			planForm.set("nsbhTelepon", aju.getNasabah().getTelepon());
			planForm.set("nsbhNoRekening", aju.getNasabah().getNoRekening());
			planForm.set("nsbhTglPayrol", sdf.format(aju.getNasabah().getTglPayroll()));
			planForm.set("nsbhAplikasi", aju.getNasabah().getAplikasi());
			planForm.set("jatuhTempo", sdf.format(aju.getJatuhTempo()));
			if (aju.getSponsor() != null) planForm.set("sponsor", aju.getSponsor().getId());
			if (aju.getMarketing() != null) planForm.set("marketing", aju.getMarketing().getId());
			
			request.setAttribute("aju", aju);
			planForm.set("tglReal", sdf.format(new Date()));
			
			Double biaya = (2.5/100) * aju.getJumlahAju();
			System.out.println("biaya: " + biaya);
			planForm.set("jumlahReal", nf.format(aju.getJumlahAju()));
			planForm.set("biayaAdmin", nf.format(biaya));
			planForm.set("biayaProvisi", nf.format(biaya));
			planForm.set("biayaLain", "0.00");
			planForm.set("diterima", nf.format(aju.getJumlahAju() - (biaya * 2)));
			
			List<Aju> ajus = ajuService.getDueAju(aju.getNasabah().getId());
			if (ajus != null) {
				List<Simulasi> sims = null;
				for (Aju a : ajus) {
					sims = ajuService.getDueSimulasi(a.getId());
					a.setSimulasi(sims);
				}
				request.setAttribute("DueList", ajus);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("continue");


	}
	

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, TRX_REAL_UPD);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer nsbhId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			Aju aju = service.getAju(nsbhId);
			BeanUtils.copyProperties(planForm, aju);
			planForm.set("tglAju", sdf.format(aju.getTglAju()));
			planForm.set("nsbhId", aju.getNasabah().getId());
			planForm.set("nsbhNama", aju.getNasabah().getNama());
			planForm.set("bunga", nf.format(aju.getInterestRate()).replace(",", "."));
			planForm.set("jumlahAju", nf.format(aju.getJumlahAju()));
			planForm.set("angsuranAju", nf.format(aju.getAngsuranAju()));
			planForm.set("nsbhAlamat", aju.getNasabah().getAlamat()); 
			planForm.set("nsbhDomisili", aju.getNasabah().getDomisili());
			planForm.set("nsbhJnsKelamin", aju.getNasabah().getJenisKelamin().getStatus());
			planForm.set("nsbhNik", aju.getNasabah().getNik());
			planForm.set("nsbhBagian", aju.getNasabah().getBagian());
			planForm.set("nsbhPt", aju.getNasabah().getPt().getNama());
			planForm.set("nsbhBank", aju.getNasabah().getBank().getNama());
			planForm.set("nsbhTelepon", aju.getNasabah().getTelepon());
			planForm.set("nsbhNoRekening", aju.getNasabah().getNoRekening());
			planForm.set("nsbhTglPayrol", sdf.format(aju.getNasabah().getTglPayroll()));
			planForm.set("nsbhAplikasi", aju.getNasabah().getAplikasi());
			planForm.set("jatuhTempo", sdf.format(aju.getJatuhTempo()));
			if (aju.getSponsor() != null) planForm.set("sponsor", aju.getSponsor().getId());
			if (aju.getMarketing() != null) planForm.set("marketing", aju.getMarketing().getId());
			
			request.setAttribute("aju", aju);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("continue");

	}

	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, TRX_REAL_UPD);
		if (forward != null)
			return forward;
		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm myForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidLogin"));
		}

		try {
			
			if (isTokenValid(request)) {
				saveToken(request);
				Aju aju = new Aju();
				aju.setId((Integer)myForm.get("id"));
				aju.setTipeKredit(1); // tipe kredit harus melihat history pinjaman sebelumnya.
				aju.setNoKredit(myForm.getString("noKredit"));
				aju.setTglAju(sdf.parse(myForm.getString("tglAju")));
				aju.setNasabah(new Nasabah((Integer)myForm.get("nsbhId")));
				aju.setPenjamin(myForm.getString("penjamin"));
				aju.setAgunan(myForm.getString("agunan"));
				aju.setJenisPinjam((Integer)myForm.get("jenisPinjam"));
				aju.setJumlahAju(nf.parse(myForm.getString("jumlahAju")).doubleValue());
				aju.setTenor((Integer)myForm.get("tenor"));
				aju.setInterestRate(nf.parse(myForm.getString("interestRate")).doubleValue());
				aju.setAngsuranAju(nf.parse(myForm.getString("angsuranAju")).doubleValue());
				aju.setJatuhTempo(sdf.parse(myForm.getString("jatuhTempo")));
				try {
					aju.setSponsor(new Nasabah((Integer)myForm.get("sponsor")));
				}catch (Exception e){}
				try {
					aju.setMarketing(new Pegawai((Integer)myForm.get("marketing")));
				}catch (Exception e) {}
				
				service.updateAju(aju);
				aju.setCreatedBy(userLogin.getUser().getUserCode());

			} else {
				errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		return mapping.findForward("success");
	}
	
	/*private void generateSimulasi(HttpServletResponse response) {
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

	}*/


}