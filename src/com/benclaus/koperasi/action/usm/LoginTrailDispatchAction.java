package com.benclaus.koperasi.action.usm;

import java.util.ArrayList;
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
import org.apache.struts.util.LabelValueBean;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.usm.UsmService;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

/**
 * @author Lambok
 *
 */
public class LoginTrailDispatchAction extends SecurityAction {
	private static Logger log = Logger.getLogger(LoginTrailDispatchAction.class);
	private static final String MENU_LOGINTRAIL_QUERY = "logintrail";
	private UsmService usmService = UsmService.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		List searchList = new ArrayList();
		searchList.add(new LabelValueBean(getMessage(request, "form.userCode"), "userCode"));
		searchList.add(new LabelValueBean(getMessage(request, "form.sessionId"), "sessionId"));
		searchList.add(new LabelValueBean(getMessage(request, "form.lastUpdProcess"), "lastUpdProcess"));
		request.setAttribute("SearchList", searchList);
	}

	public ActionForward prepare(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("Prepare");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_LOGINTRAIL_QUERY);
		if (forward != null)
			return forward;

		HttpSession session = request.getSession();
		if (session.getAttribute(MENU_LOGINTRAIL_QUERY) != null) {
			// Fetch last search
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_LOGINTRAIL_QUERY);
			dafProxy.populate((DynaActionForm) form);
			forward = search(mapping, form, request, response);
		} else {
			// Prepare
			prepareData(request);
			prepareSearch(request);
			forward = mapping.findForward("continue");
		}
		return forward;
	}

	public ActionForward returned(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();

		// Replace new form with old form
		if (session.getAttribute(MENU_LOGINTRAIL_QUERY) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_LOGINTRAIL_QUERY);
			dafProxy.populate((DynaActionForm) form);
			//			form = (ActionForm) session.getAttribute(MENU_LOGINTRAIL_QUERY);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward changePage(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("ChangePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		// Replace new form with old form
		if (session.getAttribute(MENU_LOGINTRAIL_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_LOGINTRAIL_QUERY);
			dafProxy.populate((DynaActionForm) form);
			//			form = (ActionForm) session.getAttribute(MENU_LOGINTRAIL_QUERY);

			// Replace old form page index with new form 
			 ((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_LOGINTRAIL_QUERY);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm ltForm = (DynaActionForm) form;

		// Revalidate form if needed

		try {
			// Prepare
			prepareData(request);
			prepareSearch(request);

			// Search
			PaginatedList codeList = (PaginatedList) usmService.selectLoginTrail(ltForm.getMap());
			int totalSize = usmService.selectLoginTrailSize(ltForm.getMap());
			int page = Integer.parseInt((String) ltForm.get("pageIndex"));

			if (page * codeList.getPageSize() > totalSize)
				page = ((totalSize - 1) / codeList.getPageSize()) + 1;
			if (page * codeList.getPageSize() < 1)
				page = 1;

			codeList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(codeList, totalSize));

			DAFContainer sessionForm = new DAFContainer(ltForm.getMap());
			session.setAttribute(MENU_LOGINTRAIL_QUERY, sessionForm);
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

	private void prepareData(HttpServletRequest request) throws DaoException {
		// Prepare data before Add or Update
		//request.setAttribute("CategoryList", usmService.selectCategory());
	}
}
