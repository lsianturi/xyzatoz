package com.benclaus.koperasi.action.app.master;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.dao.app.DataService;
import com.benclaus.koperasi.dao.master.NasabahService;
import com.benclaus.koperasi.dao.master.StatusPKService;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

public class NasabahAction extends SecurityAction {
	private static Logger log = Logger.getLogger(NasabahAction.class);
	private String MENU_NSB_QUERY = "MST_NSB_search";
	private String MENU_NSB_ADD= "MST_NSB_add";
	private String MENU_NSB_UPD = "MST_NSB_upd";
	private String MENU_NSB_DEL= "MST_NSB_del";

	private StatusPKService stService = StatusPKService.getInstance();
	private NasabahService nService = NasabahService.getInstance();
	
	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {

		try {
			request.setAttribute("PerusahaanList", stService.getPerusahaan());
			request.setAttribute("BankList", stService.getBank());
			request.setAttribute("JnsAgtList", stService.getJenisAnggota());
			request.setAttribute("StsAgtList", stService.getStatusAnggota());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		DynaActionForm actualForm = (DynaActionForm) form;

		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		session.removeAttribute(MENU_NSB_QUERY);

//		Integer year = Calendar.getInstance().get(Calendar.YEAR);
//
//		actualForm.set("fromYear", year);
//		actualForm.set("toYear", year);

		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_NSB_QUERY) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_NSB_QUERY);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm mapForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);

			// Set owner
			Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

			PaginatedList mapList = (PaginatedList) nService.searchNasabah(mapForm.getMap());
			int totalSize = nService.searchNasabahSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList, totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_NSB_QUERY, sessionForm);
			session.setAttribute("Data", mapList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
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

		if (session.getAttribute(MENU_NSB_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_NSB_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Add");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_NSB_ADD);
		if (forward != null)
			return forward;

		try {
			prepareSearch(request);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		planForm.set("dispatch", Constant.ADDFIRSTSTEP);
		return mapping.findForward("first");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_NSB_UPD);
		if (forward != null)
			return forward;

		try {
			prepareSearch(request);
			Integer companyId = request.getParameter("companyId").equals("") ? 0
					: Integer.parseInt(request.getParameter("companyId"));
			if (companyId == 0) {
				errors.add(Constant.GLOBALERROR,
						new ActionError("error.exception", "Please choose only one company only to edit at once."));
			}
			Integer bookId = request.getParameter("bookId").equals("") ? 0
					: Integer.parseInt(request.getParameter("bookId"));
			Integer bookItemId = request.getParameter("bookItemId").equals("") ? 0
					: Integer.parseInt(request.getParameter("bookItemId"));
			Integer fromYear = request.getParameter("fromYear").equals("") ? 0
					: Integer.parseInt(request.getParameter("fromYear"));
			Integer toYear = request.getParameter("toYear").equals("") ? 0
					: Integer.parseInt(request.getParameter("toYear"));
			if (fromYear.intValue() != toYear.intValue()) {
				errors.add(Constant.GLOBALERROR,
						new ActionError("error.exception", "\nPlease choose only one year only to edit at once."));
			}
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			Map<String, Object> map = new HashMap<>();
			map.put("companyId", companyId);
			map.put("bookId", bookId);
			map.put("bookItemId", bookItemId);
			map.put("fromYear", fromYear);
			map.put("toYear", toYear);

			List<Data> data = service.getActual(map);
			Integer size = data.size();
			planForm.set("year", fromYear);
			planForm.set("companyId", companyId);
			planForm.set("bookId", bookId);
			request.setAttribute("DataList", new Page(data, size));
			// request.setAttribute("plan", dat);
			// session.setAttribute("DataList", new Page(data, size));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("success");

	}

	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_NSB_UPD);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm planForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidLogin"));
		}

		try {
			prepareSearch(request);
			if (isTokenValid(request)) {
				saveToken(request);

			} else {
				errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		return mapping.findForward("success");
	}

	public void export2Excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Workbook wb = new HSSFWorkbook();
		Sheet personSheet = wb.createSheet("Actual");
		Row headerRow = personSheet.createRow(0);
		headerRow.createCell(0).setCellValue("Company");
		headerRow.createCell(1).setCellValue("Book");
		headerRow.createCell(2).setCellValue("Book Item");
		headerRow.createCell(3).setCellValue("Year");
		headerRow.createCell(4).setCellValue("Jan");
		headerRow.createCell(5).setCellValue("Feb");
		headerRow.createCell(6).setCellValue("Mar");
		headerRow.createCell(7).setCellValue("Apr");
		headerRow.createCell(8).setCellValue("May");
		headerRow.createCell(9).setCellValue("Jun");
		headerRow.createCell(10).setCellValue("Jul");
		headerRow.createCell(11).setCellValue("Aug");
		headerRow.createCell(12).setCellValue("Sep");
		headerRow.createCell(13).setCellValue("Oct");
		headerRow.createCell(14).setCellValue("Nov");
		headerRow.createCell(15).setCellValue("Dec");

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("#.###########"));

		PaginatedList list = (PaginatedList) session.getAttribute("Data");

		int row = 1;
		Row dataRow = null;
		list.gotoPage(0);
		String comp = "";
		Integer year = 0;
		do {
			for (int i = 0; i < list.size(); i++) {
				Data data = (Data) list.get(i);

				dataRow = personSheet.createRow(row);

				Cell compCell = dataRow.createCell(0);
				compCell.setCellValue(data.getCompany().getName());
				if (comp.equals(""))
					comp = data.getCompany().getPrefix();

				Cell bookCell = dataRow.createCell(1);
				bookCell.setCellValue(data.getBookItem().getBook().getName());

				Cell bookItemCell = dataRow.createCell(2);
				bookItemCell.setCellValue(data.getBookItem().getName());

				Cell yearCell = dataRow.createCell(3);
				yearCell.setCellValue(data.getYear());
				if (year.intValue() == 0)
					year = data.getYear();

				Cell janCell = dataRow.createCell(4);
				if (data.getActual1() != null) {
					janCell.setCellValue(data.getActual1());
					janCell.setCellStyle(cellStyle);
				}

				Cell febCell = dataRow.createCell(5);
				if (data.getActual2() != null) {
					febCell.setCellValue(data.getActual2());
					febCell.setCellStyle(cellStyle);
				}

				Cell marCell = dataRow.createCell(6);
				if (data.getActual3() != null) {
					marCell.setCellValue(data.getActual3());
					marCell.setCellStyle(cellStyle);
				}

				Cell aprCell = dataRow.createCell(7);
				if (data.getActual4() != null) {
					aprCell.setCellValue(data.getActual4());
					aprCell.setCellStyle(cellStyle);
				}

				Cell mayCell = dataRow.createCell(8);
				if (data.getActual5() != null) {
					mayCell.setCellValue(data.getActual5());
					mayCell.setCellStyle(cellStyle);
				}

				Cell junCell = dataRow.createCell(9);
				if (data.getActual6() != null) {
					junCell.setCellValue(data.getActual6());
					junCell.setCellStyle(cellStyle);
				}

				Cell julCell = dataRow.createCell(10);
				if (data.getActual7() != null) {
					julCell.setCellValue(data.getActual7());
					julCell.setCellStyle(cellStyle);
				}

				Cell augCell = dataRow.createCell(11);
				if (data.getActual8() != null) {
					augCell.setCellValue(data.getActual8());
					augCell.setCellStyle(cellStyle);
				}

				Cell sepCell = dataRow.createCell(12);
				if (data.getActual9() != null) {
					sepCell.setCellValue(data.getActual9());
					sepCell.setCellStyle(cellStyle);
				}

				Cell octCell = dataRow.createCell(13);
				if (data.getActual10() != null) {
					octCell.setCellValue(data.getActual10());
					octCell.setCellStyle(cellStyle);
				}

				Cell novCell = dataRow.createCell(14);
				if (data.getActual11() != null) {
					novCell.setCellValue(data.getActual11());
					novCell.setCellStyle(cellStyle);
				}

				Cell decCell = dataRow.createCell(15);
				if (data.getActual12() != null) {
					decCell.setCellValue(data.getActual12());
					decCell.setCellStyle(cellStyle);
				}

				row = row + 1;
			}
		} while (list.nextPage());
		session.removeAttribute("Data");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Actual-" + comp + "-" + year + ".xls");
		OutputStream fileOut = response.getOutputStream();
		wb.write(fileOut);
		fileOut.close();

	}

}