package com.benclaus.koperasi.action.usm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
import com.benclaus.koperasi.dao.usm.MenuService;
import com.benclaus.koperasi.dao.usm.RoleService;
import com.benclaus.koperasi.dao.usm.UserService;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public final class UserAction extends SecurityAction{
	private static Logger log = Logger.getLogger(UserAction.class);
	private String MENU_USER_QUERY = "SET_USR_search";
	private String MENU_USER_UPDATE = "SET_USR_upd";
	
	private UserService service = UserService.getInstance();
	private RoleService roleService = RoleService.getInstance();

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("prepare");
		
		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();				
		
		forward = hasMenuAccess(mapping, request, MENU_USER_QUERY);
		if (forward != null) { return forward; }

		if (session.getAttribute(MENU_USER_QUERY) != null) {
			forward = search(mapping, form, request, response);
		}else{
			forward = mapping.findForward("main");
		}
		return forward;
	}
	
	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(MENU_USER_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_USER_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Search");
		
		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();		
		DynaActionForm dynaForm = (DynaActionForm) form;				
		
		forward = hasMenuAccess(mapping, request, MENU_USER_QUERY);
		if (forward != null) { return forward; }

		if (session.getAttribute(MENU_USER_QUERY) != null && !dynaForm.get("dispatch").equals(Constant.SEARCH)) {
			Object pageIndex = dynaForm.get("pageIndex");
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_USER_QUERY);
			dafProxy.populate((DynaActionForm) form);
			((DynaActionForm) form).set("pageIndex", pageIndex);
		}	
		
		Map map = new HashMap<>();
		map.put("searchColumn", dynaForm.get("searchColumn"));
		map.put("searchValue", dynaForm.get("searchValue"));
		PaginatedList page = service.searchUser(map);
		int intNavi = Integer.parseInt(dynaForm.get("pageIndex").toString());

		request.getSession().setAttribute("pageIndex", dynaForm.get("pageIndex"));

		int totalSize = service.searchUserSize(map);
		int totalPage = totalSize / Constant.PAGESIZE;
		if ((totalSize % Constant.PAGESIZE) != 0) { totalPage++; }

		dynaForm.set("totalSize", String.valueOf(totalSize));
		dynaForm.set("totalPage", String.valueOf(totalPage));
		
		if (intNavi <= 1) { page.gotoPage(intNavi-1); }
		else if (intNavi >= totalPage) { page.gotoPage(intNavi-1); }
		else { page.gotoPage(intNavi-1); }		

		request.setAttribute("menuForm", dynaForm);
		request.setAttribute("DataList", new Page(page, totalSize));

		DAFContainer sessionForm = new DAFContainer(dynaForm.getMap());
		session.setAttribute(MENU_USER_QUERY, sessionForm);
		
		return mapping.findForward("main");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Add");
				
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		forward = hasMenuAccess(mapping, request, MENU_USER_UPDATE);
		if (forward != null) { return forward; }
		
		saveToken(request);
		
		return mapping.findForward("add");
	}
	
	public ActionForward addSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AddSave");

		ActionForward forward = new ActionForward();		
		ActionErrors errors = new ActionErrors();			
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_USER_UPDATE);
		if (forward != null) { return forward; }
		
		int result = -1;
		try{
			if (isTokenValid(request)) {
				saveToken(request);
				User user = new User();
				BeanUtils.copyProperties(user, dynaForm);
				Login userLogin = (Login) session.getAttribute("UserLogin");
				result = service.insertUser(user, userLogin.getUser(), "insert");
				if (result < 0) {
					errors.add(Constant.GLOBALERROR ,new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
					saveErrors(request, errors);
					forward = add(mapping, form, request, response);
				}		
			}else{
				errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				forward = mapping.findForward("invalidPage");					
			}
		}catch(DaoException e) { 						
			log.error(e.getMessage(), e);			
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
			saveErrors(request, errors);
			forward = add(mapping, form, request, response);						
		}		
		return forward;
	} 
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("delete");
		
		ActionForward forward = new ActionForward();		
		ActionErrors errors = new ActionErrors();

		Vector vector = new Vector();		
		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();			
		
		forward = hasMenuAccess(mapping, request, MENU_USER_UPDATE);
		if (forward != null) { return forward; }
		
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));		
		
		Login userLogin = (Login) session.getAttribute("UserLogin");
		
		int result = -1;
		
		if (result > 0) {
			forward = returned(mapping, form, request, response);
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("error.deleteFail", getMessage(request, "error.noRowUpdated")));
			saveErrors(request, errors);
			forward = returned(mapping, form, request, response);		}
		return forward;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("update");

		ActionForward forward = new ActionForward();
		DynaActionForm dynaForm = (DynaActionForm) form;	
		
		forward = hasMenuAccess(mapping, request, MENU_USER_UPDATE);
		if (forward != null) { return forward; }
		
		String code = dynaForm.getString("userCode");
		
		User user = service.getUser(code);
		
		BeanUtils.copyProperties(dynaForm, user);
		request.setAttribute("RoleList", roleService.getRoles());

		saveToken(request);
		
		return mapping.findForward("update");
	}
	
	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("UpdateSave");

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_USER_UPDATE);
		if (forward != null) { return forward; }

		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));

		int result = -1;
		if (isTokenValid(request)) {
			saveToken(request);
			
			try{
				Login userLogin = (Login) session.getAttribute("UserLogin");
				User user = new User();
				BeanUtils.copyProperties(user, dynaForm);
				result = service.updateUser(user, userLogin.getUser(), "update");
			}catch(DaoException e) { log.error(e.getMessage(), e); }
			if (result < 0) {
				errors.add(Constant.GLOBALERROR, new ActionError("error.updateFail", getMessage(request, "error.noRowUpdated")));
				saveErrors(request, errors);
				forward = mapping.findForward("success");
			} else {
				forward = mapping.findForward("main");
			}
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("err.invalidToken"));
			saveErrors(request, errors);
			forward = mapping.findForward("invalidPage");
		}
		
		return forward;
	}

	
	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_USER_QUERY) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_USER_QUERY);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}
	
	
}