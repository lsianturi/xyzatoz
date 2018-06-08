package com.benclaus.koperasi.action.master;

import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.benclaus.koperasi.dao.trx.ConfigService;
import com.benclaus.koperasi.dao.trx.DataService;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 * 
 */
public final class BusinessPlanAction extends SecurityAction {
	private static Logger log = Logger.getLogger(BusinessPlanAction.class);
	private String MENU_BUDGET_VIEW = "data_budg_view";
	private String MENU_BUDGET_EDIT = "data_budg_edit";

	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", cfgService.getBookList());
			request.setAttribute("companyList", cfgService.getCompanyList());
			request.setAttribute("bookItemList", cfgService.getBookItemList());
			request.setAttribute("spmList", cfgService.getSPMList());
			request.setAttribute("yearList", service.getDataYearList());
		} catch (Exception e) {
		}
	}
	private void prepareData(HttpServletRequest request) {
		try {
			request.setAttribute("spmList", cfgService.getSPMList());
			request.setAttribute("companyList", cfgService.getCompanyList());
			request.setAttribute("bookList", cfgService.getBookList());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		DynaActionForm planForm = (DynaActionForm) form;
		session.removeAttribute(MENU_BUDGET_VIEW);

		forward = hasMenuAccess(mapping, request, MENU_BUDGET_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		
		planForm.set("fromYear", year);
		planForm.set("toYear", year);
		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_BUDGET_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_BUDGET_VIEW);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Search");
		

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_BUDGET_VIEW);
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

			PaginatedList planList = (PaginatedList) service.searchPlan(mapForm.getMap());
			int totalSize = service.searchPlanSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * planList.getPageSize() > totalSize)
				page = ((totalSize - 1) / planList.getPageSize()) + 1;
			if (page * planList.getPageSize() < 1)
				page = 1;

			planList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(planList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_BUDGET_VIEW, sessionForm);
			session.setAttribute("Data", planList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception",
					e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}

		return mapping.findForward("continue");
	}
	
	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(MENU_BUDGET_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_BUDGET_VIEW);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}
	
	public ActionForward add(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Add");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_BUDGET_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);
		
		planForm.set("dispatch", Constant.ADDFIRSTSTEP);
		return mapping.findForward("first");

	}
	public ActionForward addFirstStep(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Add First Step");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_BUDGET_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Map<String, Object> map = new HashMap<>();
			map.put("company", (Integer) planForm.get("companyId"));
			map.put("bookItem",(Integer) planForm.get("bookId"));
			map.put("year", (Integer) planForm.get("year"));
			
			int affectedRow = service.insertPlan(map);
			if (affectedRow < 1)
				errors.add(Constant.GLOBALERROR,
						new ActionError("error.insertFail", getMessage(request, "error.noRowInserted")));
			
//			request.setAttribute("DataList", service.getNewInsertedPlan(map));
			List<Data> data = service.getNewInsertedPlan(map);
			Integer size = data.size();
			request.setAttribute("DataList", new Page(data, size));
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		
		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("success");

	}

	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Update");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_BUDGET_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer companyId = request.getParameter("companyId").equals("") ? 0: Integer.parseInt(request.getParameter("companyId"));
			if (companyId == 0) {
				errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "Please choose only one company only to edit at once."));
			}
			Integer bookId = request.getParameter("bookId").equals("") ? 0 : Integer.parseInt(request.getParameter("bookId"));
			Integer bookItemId = request.getParameter("bookItemId").equals("") ? 0 : Integer.parseInt(request.getParameter("bookItemId"));
			Integer fromYear = request.getParameter("fromYear").equals("") ? 0 : Integer.parseInt(request.getParameter("fromYear"));
			Integer toYear = request.getParameter("toYear").equals("") ? 0 : Integer.parseInt(request.getParameter("toYear"));
			if (fromYear.intValue() != toYear.intValue()) {
				errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease choose only one year only to edit at once."));
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
			
			List<Data> data = service.getPlan(map);
			Integer size = data.size();
			planForm.set("year", fromYear);
			planForm.set("companyId", companyId);
			planForm.set("bookId", bookId);
			request.setAttribute("DataList", new Page(data, size));
//			request.setAttribute("plan", dat);
			//session.setAttribute("DataList", new Page(data, size));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		
		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("success");

	}

	public ActionForward updateSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_BUDGET_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm planForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			prepareData(request);
			if (isTokenValid(request)) {
				saveToken(request);
				
				NumberFormat format = NumberFormat.getInstance(Locale.US);
				
				Data data = new Data();
				data.setCompany(new Company((Integer)planForm.get("companyId")));
				
				Integer[] ids = (Integer[])planForm.get("id");
				String[] plan1s = planForm.getStrings("plan1Str");
				String[] plan2s = planForm.getStrings("plan2Str");
				String[] plan3s = planForm.getStrings("plan3Str");
				String[] plan4s = planForm.getStrings("plan4Str");
				String[] plan5s = planForm.getStrings("plan5Str");
				String[] plan6s = planForm.getStrings("plan6Str");
				String[] plan7s = planForm.getStrings("plan7Str");
				String[] plan8s = planForm.getStrings("plan8Str");
				String[] plan9s = planForm.getStrings("plan9Str");
				String[] plan10s = planForm.getStrings("plan10Str");
				String[] plan11s = planForm.getStrings("plan11Str");
				String[] plan12s = planForm.getStrings("plan12Str");
				
				int size = ids.length;
				for (int i=0; i<size; i++) {
					for(Integer id: ids) {
						data.setId(id);
						try {
							data.setPlan1(format.parse(plan1s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for January"));
						}
						try {
							data.setPlan2(format.parse(plan2s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for February"));
						}
						try {
							data.setPlan3(format.parse(plan3s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for March"));
						}
						try {
							data.setPlan4(format.parse(plan4s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for April"));
						}
						try {
							data.setPlan5(format.parse(plan5s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for May"));
						}
						try {
							data.setPlan6(format.parse(plan6s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for June"));
						}
						try {
							data.setPlan7(format.parse(plan7s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for July"));
						}
						try {
							data.setPlan8(format.parse(plan8s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for August"));
						}
						try {
							data.setPlan9(format.parse(plan9s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for September"));
						}
						try {
							data.setPlan10(format.parse(plan10s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for October"));
						}
						try {
							data.setPlan11(format.parse(plan11s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for November"));
						}
						try {
							data.setPlan12(format.parse(plan12s[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for December"));
						}
						if (errors.size() > 0) {
							saveErrors(request, errors);
							return mapping.findForward("fail");
						}
						i++;
						// Update
						int affectedRow = service.updatePlan(data);
						/*if (affectedRow < 1)
							errors.add(Constant.GLOBALERROR, new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));*/
					}
				}
			} else {
				errors.add( Constant.GLOBALERROR, new ActionError("error.invalidToken"));
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
	
	public void export2Excel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
		cellStyle.setDataFormat(
		    wb.getCreationHelper().createDataFormat().getFormat("#.###########"));
		
		PaginatedList list = (PaginatedList) session.getAttribute("Data");   

		int row = 1;
	    Row dataRow = null;
	    list.gotoPage(0);
	    String comp = "";
	    Integer year = 0;
	    do {
			for (int i=0; i < list.size(); i++) {
				Data data= (Data) list.get(i);
				
			    dataRow = personSheet.createRow(row);
	
			    Cell compCell = dataRow.createCell(0);
			    compCell.setCellValue(data.getCompany().getName());
			    if (comp.equals("")) comp = data.getCompany().getPrefix();
			    
			    Cell bookCell = dataRow.createCell(1);
			    bookCell.setCellValue(data.getBookItem().getBook().getName());
			    
			    Cell bookItemCell = dataRow.createCell(2);
			    bookItemCell.setCellValue(data.getBookItem().getName());
			    
			    Cell yearCell = dataRow.createCell(3);
			    yearCell.setCellValue(data.getYear());
			    if (year.intValue() == 0) year = data.getYear();
	
			    Cell janCell = dataRow.createCell(4);
				if (data.getPlan1() != null) {
					janCell.setCellValue(data.getPlan1());
					janCell.setCellStyle(cellStyle);
				}

				Cell febCell = dataRow.createCell(5);
				if (data.getPlan2() != null) {
					febCell.setCellValue(data.getPlan2());
					febCell.setCellStyle(cellStyle);
				}

				Cell marCell = dataRow.createCell(6);
				if (data.getPlan3() != null) {
					marCell.setCellValue(data.getPlan3());
					marCell.setCellStyle(cellStyle);
				}

				Cell aprCell = dataRow.createCell(7);
				if (data.getPlan4() != null) {
					aprCell.setCellValue(data.getPlan4());
					aprCell.setCellStyle(cellStyle);
				}

				Cell mayCell = dataRow.createCell(8);
				if (data.getPlan5() != null) {
					mayCell.setCellValue(data.getPlan5());
					mayCell.setCellStyle(cellStyle);
				}

				Cell junCell = dataRow.createCell(9);
				if (data.getPlan6() != null) {
					junCell.setCellValue(data.getPlan6());
					junCell.setCellStyle(cellStyle);
				}

				Cell julCell = dataRow.createCell(10);
				if (data.getPlan7() != null) {
					julCell.setCellValue(data.getPlan7());
					julCell.setCellStyle(cellStyle);
				}

				Cell augCell = dataRow.createCell(11);
				if (data.getPlan8() != null) {
					augCell.setCellValue(data.getPlan8());
					augCell.setCellStyle(cellStyle);
				}

				Cell sepCell = dataRow.createCell(12);
				if (data.getPlan9() != null) {
					sepCell.setCellValue(data.getPlan9());
					sepCell.setCellStyle(cellStyle);
				}

				Cell octCell = dataRow.createCell(13);
				if (data.getPlan10() != null) {
					octCell.setCellValue(data.getPlan10());
					octCell.setCellStyle(cellStyle);
				}

				Cell novCell = dataRow.createCell(14);
				if (data.getPlan11() != null) {
					novCell.setCellValue(data.getPlan11());
					novCell.setCellStyle(cellStyle);
				}

				Cell decCell = dataRow.createCell(15);
				if (data.getPlan12() != null) {
					decCell.setCellValue(data.getPlan12());
					decCell.setCellStyle(cellStyle);
				}
	
			    row = row + 1;
			}
	    } while (list.nextPage());
		
	    session.removeAttribute("Data");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Plan-"+comp+"-"+year+".xls");
		OutputStream fileOut = response.getOutputStream();
		wb.write(fileOut);
		fileOut.close();
	}

}