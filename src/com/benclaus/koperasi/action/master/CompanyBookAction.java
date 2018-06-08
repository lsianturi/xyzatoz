package com.benclaus.koperasi.action.master;

import java.util.HashMap;
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
import com.benclaus.koperasi.model.Book;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.CompanyBook;
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
public final class CompanyBookAction extends SecurityAction {
	private static Logger log = Logger.getLogger(CompanyBookAction.class);
	private String MENU_BOOK_VIEW = "cfg_book_view";
	private String MENU_BOOK_EDIT = "cfg_book_edit";

	private ConfigService service = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", service.getBookList());
			request.setAttribute("companyList", service.getCompanyList());
			request.setAttribute("spmList", service.getSPMList());
		} catch (Exception e) {
		}
	}
	private void prepareData(HttpServletRequest request) {
		try {
			request.setAttribute("bookList", service.getBookList());
			request.setAttribute("companyList", service.getCompanyList());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		session.removeAttribute(MENU_BOOK_VIEW);
		/*DynaActionForm absForm = (DynaActionForm) form;
		int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_BOOK_VIEW);
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
		if (session.getAttribute(MENU_BOOK_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_BOOK_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_VIEW);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compBookForm = (DynaActionForm) form;
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
			PaginatedList bookList = (PaginatedList) service.searchBook(compBookForm.getMap());
			int totalSize = service.searchBookSize(compBookForm.getMap());
			int page = Integer.parseInt((String) compBookForm.get("pageIndex"));

			if (page * bookList.getPageSize() > totalSize)
				page = ((totalSize - 1) / bookList.getPageSize()) + 1;
			if (page * bookList.getPageSize() < 1)
				page = 1;

			bookList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(bookList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(compBookForm.getMap());
			session.setAttribute(MENU_BOOK_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_BOOK_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_BOOK_VIEW);
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
		DynaActionForm compForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_BOOK_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Map<String, Object> map = new HashMap<>();
			map.put("companyId", 0);
			map.put("book", 0);
			map.put("id", Integer.parseInt(request.getParameter("id")));
			CompanyBook compBook = service.getBook(map);
			compForm.set("companyId", compBook.getCompany().getId());
			compForm.set("bookId", compBook.getBook().getId());
			/*compForm.set("fullAmount", comp.getFullAmount());
			compForm.set("inThousand", comp.getInThousand());
			compForm.set("inMio", comp.getFullAmount());
			compForm.set("inBio", comp.getInThousand());
			compForm.set("periodic", comp.getFullAmount());
			compForm.set("ytd", comp.getInThousand());*/
			
			request.setAttribute("book", compBook);
			
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
		
		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_BOOK_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm bookForm = (DynaActionForm) form;

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
				CompanyBook book= new CompanyBook();
				book.setId((Integer)bookForm.get("id"));
				book.setBook(new Book((Integer)bookForm.get("bookId")));
				book.setCompany(new Company((Integer)bookForm.get("companyId")));
				book.setFullAmount(bookForm.get("theValue").equals("fullAmount") ? 1:0);
				book.setInThousand(bookForm.get("theValue").equals("inThousand") ? 1:0);
				book.setInBio(bookForm.get("theValue").equals("inBio") ? 1:0);
				book.setInMio(bookForm.get("theValue").equals("inMio") ? 1:0);
				book.setPeriodic(bookForm.get("valuePeriod").equals("periodic") ? 1:0);
				book.setYtd(bookForm.get("valuePeriod").equals("ytd") ? 1:0);
				
				//comp.setPillar(new Pillar((Integer)compForm.get("pilar")));
				int affectedRow = service.updateBook(book);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_EDIT);
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