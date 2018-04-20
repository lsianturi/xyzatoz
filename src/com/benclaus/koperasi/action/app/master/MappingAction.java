package com.benclaus.koperasi.action.app.master;

import java.util.List;

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
import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 * 
 */
public final class MappingAction extends SecurityAction {
	private static Logger log = Logger.getLogger(MappingAction.class);
	private String MENU_MAPPING_VIEW = "cfg_mapping_edit";
	private String MENU_MAPPING_EDIT = "cfg_mapping_view";

	private ConfigService service = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", service.getBookList());
			request.setAttribute("companyList", service.getCompanyList());
			request.setAttribute("bookItemList", service.getBookItemList());
			request.setAttribute("spmList", service.getSPMList());
		} catch (Exception e) {
		}
	}
	private void prepareData(HttpServletRequest request) {
		try {
			request.setAttribute("bookList", service.getBookList());
			request.setAttribute("companyList", service.getCompanyList());
			request.setAttribute("bookItemList", service.getBookItemList());
			request.setAttribute("spmList", service.getSPMList());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		session.removeAttribute(MENU_MAPPING_VIEW);
		/*DynaActionForm absForm = (DynaActionForm) form;
		int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_MAPPING_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		
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
		if (session.getAttribute(MENU_MAPPING_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_MAPPING_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_MAPPING_VIEW);
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

			// Search
			/*Map<String, Object> map = new HashMap<>();
			map.put("companyId", compBookForm.get("companyId"));
			map.put("book", compBookForm.get("book"));*/
			PaginatedList mapList = (PaginatedList) service.searchMapping(mapForm.getMap());
			int totalSize = service.searchMappingSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_MAPPING_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_MAPPING_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_MAPPING_VIEW);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
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
		DynaActionForm mappForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_MAPPING_EDIT);
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
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("fail");
			}
			
			List<Mapping> data = service.getMapping(mappForm.getMap());
			Integer size = data.size();

			mappForm.set("companyId", companyId);
			mappForm.set("bookId", bookId);
			mappForm.set("bookItemId", bookItemId);
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

		
		mappForm.set("dispatch", Constant.UPDATESAVE);
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
		forward = hasMenuAccess(mapping, request, MENU_MAPPING_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm mappForm = (DynaActionForm) form;

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
				
				// Update
				Mapping mapp = new Mapping();
				mapp.setCompany(new Company((Integer)mappForm.get("companyId")));
				
				Integer[] ids = (Integer[]) mappForm.get("id");
				String[] cellFy =  mappForm.getStrings("cellFy");
				String[] cellYtdPrevActual = mappForm.getStrings("cellYtdPrevActual");
				String[] cellYtdCurrentActual= mappForm.getStrings("cellYtdCurrentActual");
				String[] cellYtdCurrentPlan = mappForm.getStrings("cellYtdCurrentPlan");
 				String[] formula = mappForm.getStrings("formula");
				
				int size = ids.length;
				for (int i=0; i<size; i++) {
					for(Integer id: ids) {
						mapp.setId(id);
						mapp.setCellFy(cellFy[i]);
						mapp.setCellYtdPrevActual(cellYtdPrevActual[i]);
						mapp.setCellYtdCurrentActual(cellYtdCurrentActual[i]);
						mapp.setCellYtdCurrentPlan(cellYtdCurrentPlan[i]);
						mapp.setFormula(formula[i]);
						i++;
						int affectedRow = service.updateMapping(mapp);
						/*if (affectedRow < 1)
							errors.add(Constant.GLOBALERROR,
									new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));*/
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
		
		return mapping.findForward("continue");
	}
	
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Delete");
		
		// Check Menu Access

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_MAPPING_EDIT);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		if (userLogin == null) {
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			// do delete
			//int affectedRow = companyService.deleteCompany(companyForm.getMap(), userLogin.getUser(), "delete");
			Integer id = Integer.parseInt(request.getParameter("id"));
			int affectedRow =
				service.deleteCompany(id);
			if (affectedRow == 0)
				errors.add(Constant.GLOBALERROR,
					new ActionError("error.deleteFail", getMessage(request, "error.noRowUpdated")));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
		}

		// Return to Search
		return mapping.findForward("success");
	}

}