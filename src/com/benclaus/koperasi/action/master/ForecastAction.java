package com.benclaus.koperasi.action.master;

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
import com.benclaus.koperasi.model.Forecast;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 * 
 */
public final class ForecastAction extends SecurityAction {
	private static Logger log = Logger.getLogger(ForecastAction.class);
	private String MENU_FORECAST_VIEW = "data_fc_view";
	private String MENU_FORECAST_EDIT = "data_fc_edit";

	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", cfgService.getBookList());
			request.setAttribute("companyList", cfgService.getCompanyList());
			request.setAttribute("bookItemList", cfgService.getBookItemList());
			request.setAttribute("spmList", cfgService.getSPMList());
			request.setAttribute("yearList", service.getForecastYearList());
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

		forward = hasMenuAccess(mapping, request, MENU_FORECAST_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		session.removeAttribute(MENU_FORECAST_VIEW);
		
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		
		planForm.set("fromYear", year+1);
		planForm.set("toYear", year+1);
		
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
		if (session.getAttribute(MENU_FORECAST_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_FORECAST_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_FORECAST_VIEW);
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

			PaginatedList mapList = (PaginatedList) service.searchForecast(mapForm.getMap());
			int totalSize = service.searchForecastSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_FORECAST_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_FORECAST_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_FORECAST_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_FORECAST_EDIT);
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
		forward = hasMenuAccess(mapping, request, MENU_FORECAST_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Map<String, Object> map = new HashMap<>();
			map.put("company", (Integer) planForm.get("companyId"));
			map.put("bookItem",(Integer) planForm.get("bookId"));
			map.put("year", (Integer) planForm.get("year"));
			
			int affectedRow = service.insertForecast(map);
			if (affectedRow < 1)
				errors.add(Constant.GLOBALERROR,
						new ActionError("error.insertFail", getMessage(request, "error.noRowInserted")));
			
//			request.setAttribute("DataList", service.getNewInsertedPlan(map));
			List<Forecast> data = service.getNewInsertedForecast(map);
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
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		HttpSession session = request.getSession();
		forward = hasMenuAccess(mapping, request, MENU_FORECAST_EDIT);
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
			
			List<Forecast> data = service.getForecast(map);
			Integer size = data.size();
			planForm.set("year", fromYear);
			planForm.set("companyId", companyId);
			planForm.set("bookId", bookId);
			request.setAttribute("DataList", new Page(data, size));
//			request.setAttribute("plan", dat);
			
//			session.setAttribute("DataList", new Page(data, size));
			
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
		forward = hasMenuAccess(mapping, request, MENU_FORECAST_EDIT);
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
				
				Forecast data = new Forecast();
				data.setCompany(new Company((Integer)planForm.get("companyId")));
				
				Integer[] ids = (Integer[])planForm.get("id");
				String[] amounts = planForm.getStrings("amountStr");
				
				int size = ids.length;
				for (int i=0; i<size; i++) {
					for(Integer id: ids) {
						data.setId(id);
						try {
							data.setAmount(format.parse(amounts[i]).doubleValue());
						} catch(ParseException | NumberFormatException nfe) {
							errors.add(Constant.GLOBALERROR, new ActionError("error.exception", "\nPlease fix number format for amount"));
						}
						
						if (errors.size() > 0) {
							saveErrors(request, errors);
							return mapping.findForward("fail");
						}
						i++;
						// Update
						int affectedRow = service.updateForecast(data);
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
	
}