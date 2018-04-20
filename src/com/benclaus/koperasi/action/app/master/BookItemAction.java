package com.benclaus.koperasi.action.app.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
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
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 * 
 */
public final class BookItemAction extends SecurityAction {
	private static Logger log = Logger.getLogger(BookItemAction.class);
	private String MENU_BOOK_ITEM_VIEW = "book_item_view";
	private String MENU_BOOK_ITEM_EDIT = "book_item_edit";

	private ConfigService service = ConfigService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", service.getBookList());
			
		} catch (Exception e) {
		}
	}
	private void prepareData(HttpServletRequest request) {
		try {
			//request.setAttribute("pilarList", service.getPillars());
			request.setAttribute("bookList", service.getBookList());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		session.removeAttribute(MENU_BOOK_ITEM_VIEW);
		/*DynaActionForm absForm = (DynaActionForm) form;
		int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_VIEW);
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
		if (session.getAttribute(MENU_BOOK_ITEM_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_BOOK_ITEM_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_VIEW);
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
			PaginatedList bookItemList = (PaginatedList) service.searchBookItem(compForm.getMap());
			int totalSize = service.searchBookItemSize(compForm.getMap());
			int page = Integer.parseInt((String) compForm.get("pageIndex"));

			if (page * bookItemList.getPageSize() > totalSize)
				page = ((totalSize - 1) / bookItemList.getPageSize()) + 1;
			if (page * bookItemList.getPageSize() < 1)
				page = 1;

			bookItemList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(bookItemList,
					totalSize));

			DAFContainer sessionForm = new DAFContainer(compForm.getMap());
			session.setAttribute(MENU_BOOK_ITEM_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_BOOK_ITEM_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_BOOK_ITEM_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_EDIT);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			BookItem item= service.getBookItem(Integer.parseInt(request.getParameter("id")));
			BeanUtils.copyProperties(compForm, item);
			compForm.set("bookId", item.getBook().getId());
			
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm itemForm = (DynaActionForm) form;

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
				BookItem item = new BookItem();
				BeanUtils.copyProperties(item, itemForm);
				item.setBook(new Book((Integer)itemForm.get("bookId")));
				int affectedRow = service.updateBookItem(item);
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_EDIT);
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm itemForm = (DynaActionForm) form;

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
				BookItem item = new BookItem();
				item.setBook(new Book((Integer)itemForm.get("bookId")));
				BeanUtils.copyProperties(item, itemForm);
				int affectedRow = service.insertBookItem(item);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
				
				List<Mapping> maps = service.getUnavailableMapping();
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
		forward = hasMenuAccess(mapping, request, MENU_BOOK_ITEM_EDIT);
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
				service.deleteBookItem(id);
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