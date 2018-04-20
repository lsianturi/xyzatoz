package com.benclaus.koperasi.action.app.master;

import java.util.HashMap;
import java.util.List;
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
import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.model.Book;
import com.benclaus.koperasi.model.BookItem;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 * 
 */
public final class CompanyAction extends SecurityAction {
	private static Logger log = Logger.getLogger(CompanyAction.class);
	private String MENU_COMP_VIEW = "cfg_comp_view";
	private String MENU_COMP_EDIT = "cfg_comp_edit";

	private ConfigService service = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("spmList", service.getSPMList());
			
		} catch (Exception e) {
		}
	}
	private void prepareData(HttpServletRequest request) {
		try {
			//request.setAttribute("pilarList", service.getPillars());
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
		session.removeAttribute(MENU_COMP_VIEW);
		/*DynaActionForm absForm = (DynaActionForm) form;
		int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_COMP_VIEW);
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
		if (session.getAttribute(MENU_COMP_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_COMP_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_COMP_VIEW);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);

			// Set owner
			Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

			// Search
			/*Map<String, Object> map = new HashMap<>();
			map.put("user", compForm.get("user"));
			map.put("name", compForm.get("name"));*/
			PaginatedList companyCodeList = (PaginatedList) service.searchCompany(compForm.getMap());
			int totalSize = service.searchCompanySize(compForm.getMap());
			int page = Integer.parseInt((String) compForm.get("pageIndex"));

			if (page * companyCodeList.getPageSize() > totalSize)
				page = ((totalSize - 1) / companyCodeList.getPageSize()) + 1;
			if (page * companyCodeList.getPageSize() < 1)
				page = 1;

			companyCodeList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(companyCodeList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(compForm.getMap());
			session.setAttribute(MENU_COMP_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_COMP_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_COMP_VIEW);
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

		log.debug("Add");

		ActionErrors errors = new ActionErrors();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm compForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_COMP_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer id = Integer.parseInt(request.getParameter("id"));
			Map<String, Object> map = new HashMap<>();
			map.put("compId", Integer.parseInt(request.getParameter("id")));
			Company comp = service.getCompany(map);
			compForm.set("companyId", comp.getId());
			compForm.set("name", comp.getName());
			compForm.set("pilar", comp.getPillar().getId());
			compForm.set("user", comp.getUser().getUserCode());
			compForm.set("prefix", comp.getPrefix());
			
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

		
		compForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("success");

	}

	public ActionForward updateSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		log.debug("Add Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_COMP_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;

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
				Company comp = new Company();
				comp.setId((Integer)compForm.get("companyId"));
				comp.setName((String)compForm.get("name"));
				comp.setPrefix((String)compForm.get("prefix"));
				//comp.setPillar(new Pillar((Integer)compForm.get("pilar")));
				comp.setPillar(new Pillar(1));
				comp.setUser(new User((String)compForm.get("user")));
				Integer affectedRow = service.updateCompany(comp);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.updateFail", "company prefix may already used."));
				
				List<Book> books = service.getUnavailableBook(comp.getId());
				CompanyBook cBook = new CompanyBook();
				if (books != null) {
					for (Book book : books) {
						cBook.setCompany(comp);
						cBook.setBook(book);
						cBook.setFullAmount(1);
						cBook.setPeriodic(1);
						service.insertBook(cBook);
					}
				}
				List<Mapping> maps = service.getUnavailableMappingByCompany(comp.getId());
				if (maps != null) {
					for (Mapping mapp : maps) {
						service.insertMapping(mapp);
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
		forward = hasMenuAccess(mapping, request, MENU_COMP_EDIT);
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

		DynaActionForm compForm = (DynaActionForm) form;
		compForm.set("dispatch", Constant.ADDSAVE);
		return mapping.findForward("success");

	}

	public ActionForward addSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		log.debug("Add Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_COMP_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;

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
				Company comp = new Company();
				comp.setName((String)compForm.get("name"));
				comp.setPrefix((String)compForm.get("prefix"));
				//comp.setPillar(new Pillar((Integer)compForm.get("pilar")));
				comp.setPillar(new Pillar(1));
				comp.setUser(new User((String)compForm.get("user")));
				int affectedRow = service.insertCompany(comp);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
				
				//add company_books
				Map<String, Object> map = new HashMap<>();
				map.put("companyName", comp.getName());
				map.put("spmCode", comp.getUser().getUserCode());
				comp = service.getCompanyByName(map);
				
				List<Book> books = service.getUnavailableBook(comp.getId());
				CompanyBook cBook = new CompanyBook();
				if (books != null) {
					for (Book book : books) {
						cBook.setCompany(comp);
						cBook.setBook(book);
						cBook.setFullAmount(1);
						cBook.setPeriodic(1);
						service.insertBook(cBook);
					}
				}
				List<Mapping> maps = service.getUnavailableMappingByCompany(comp.getId());
				if (maps != null) {
					for (Mapping mapp : maps) {
						service.insertMapping(mapp);
					}
				} else {
					errors.add( Constant.GLOBALERROR, new ActionError("error.exception", "Error while inserting the mapping template records"));
					saveErrors(request, errors);
					return mapping.findForward("invalidPage");
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
		forward = hasMenuAccess(mapping, request, MENU_COMP_EDIT);
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
		return mapping.findForward("continue");
	}

}