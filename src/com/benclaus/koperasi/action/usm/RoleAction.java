package com.benclaus.koperasi.action.usm;

import java.util.Iterator;
import java.util.List;
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
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.model.usm.Role;
import com.benclaus.koperasi.model.usm.RoleMenu;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public final class RoleAction extends SecurityAction{
	private static Logger log = Logger.getLogger(MenuAction.class);
	private static final String MENU_ROLE_QUERY = "role";
	private static final String MENU_ROLE_UPDATE = "roleupd";
	private static final String MENU_ROLE_ADD = "roleadd";
	private static final String MENU_ROLE_DELETE = "roledel";	
	private static final String MENU_ROLE_ASSIGN = "assignmenu";
	private static final String MENU_ROLE_ASSIGN_DELETE = "deleteassignmenu";
 
 	private MenuService menuService = MenuService.getInstance();
	private RoleService service = RoleService.getInstance();;		 	
 	
	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();		
		HttpSession session = request.getSession();		

		forward = hasMenuAccess(mapping, request, MENU_ROLE_QUERY);
		if (forward != null) { return forward; }

		if (session.getAttribute(MENU_ROLE_QUERY) != null) {
			forward = search(mapping, form, request, response);
		}else{
			forward = mapping.findForward("main");
		}
		return forward;
	}
	
	public ActionForward returned(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.debug("returned");

		return search(mapping, form, request, response);
	}
	
	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(MENU_ROLE_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_ROLE_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("search");
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();				
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_QUERY);
		if (forward != null) { return forward; }
		
		if (session.getAttribute(MENU_ROLE_QUERY) != null && !dynaForm.get("dispatch").equals(Constant.SEARCH)) {
			Object pageIndex = dynaForm.get("pageIndex");
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_ROLE_QUERY);
			dafProxy.populate((DynaActionForm) form);
			((DynaActionForm) form).set("pageIndex", pageIndex);
		}	
		
		PaginatedList page = service.getPaging(dynaForm.getMap());

		int intNavi = 1;
		try{
			intNavi = Integer.parseInt(dynaForm.get("pageIndex").toString());
		}catch(Exception e) { 
			log.error(e.getMessage(), e);
			dynaForm.set("pageIndex", String.valueOf(intNavi)); 
		}
		request.getSession().setAttribute("pageIndex", dynaForm.get("pageIndex"));

		int totalSize = service.getCountRow(dynaForm.getMap());
		int	totalPage = totalSize / Constant.PAGESIZE;
		if ((totalSize % Constant.PAGESIZE) != 0) { totalPage++; }

		dynaForm.set("totalSize", String.valueOf(totalSize));
		dynaForm.set("totalPage", String.valueOf(totalPage));
		
		if (intNavi <= 1) { page.gotoPage(intNavi-1); }
		else if (intNavi >= totalPage) { page.gotoPage(intNavi-1); }
		else { page.gotoPage(intNavi-1); }		

		request.setAttribute("roleForm", dynaForm);
		request.setAttribute("DataList", new Page(page, totalSize));
		
		DAFContainer sessionForm = new DAFContainer(dynaForm.getMap());
		session.setAttribute(MENU_ROLE_QUERY, sessionForm);
		
		return mapping.findForward("main");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("add");
		
		ActionForward forward = new ActionForward();		
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_ADD);
		if (forward != null) { return forward; }
		
		saveToken(request);
		return mapping.findForward("add");
	}
	
	public ActionForward addSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("addsave");
		
		ActionForward forward = new ActionForward();	
		ActionErrors errors = new ActionErrors();	

		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();			
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_ADD);
		if (forward != null) { return forward; }
		
		int result = -1;
		try{
			if (isTokenValid(request)) {
				saveToken(request);

				Login userLogin = (Login) session.getAttribute("UserLogin");
				Role role = new Role();
				BeanUtils.copyProperties(role, dynaForm);
				result = service.insertRole(role, userLogin.getUser(), "insert");
				if (result > 0) {
					forward = search(mapping, form, request, response);
				}else{				
					errors.add(Constant.GLOBALERROR, new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
					saveErrors(request, errors);
					forward = add(mapping, form, request, response);			
				}
			}else{
				errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				forward = mapping.findForward("invalidPage");
			}
		}catch(Exception e) { 						
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
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_DELETE);
		if (forward != null) { return forward; }
		
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));		
		
		int result = -1;
		try{
			Login userLogin = (Login) session.getAttribute("UserLogin");
			Role role = new Role();
			BeanUtils.copyProperties(role, dynaForm);			
			result = service.deleteRole(role, userLogin.getUser(), "delete");
		}catch(Exception e) { log.error(e.getMessage(), e); }
		
		if (result > 0) {
			forward = search(mapping, form, request, response);
		}else{			
			errors.add(Constant.GLOBALERROR, new ActionError("error.deleteFail", getMessage(request, "error.noRowUpdated")));
			saveErrors(request, errors);
			forward = search(mapping, form, request, response);
		}
		return forward;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("update");
		
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_UPDATE);
		if (forward != null) { return forward; }
		
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));
		
		String roleCode = dynaForm.get("roleCode").toString();
		
		Object userMenuList = service.getRole(roleCode);
		BeanUtils.copyProperties(dynaForm, userMenuList);

		request.setAttribute("roleForm", dynaForm);
		saveToken(request);
		
		return mapping.findForward("update");
	}
	
	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("updateSave");
		
		ActionForward forward = new ActionForward();
		ActionErrors errors = new ActionErrors();

		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();			
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_UPDATE);
		if (forward != null) { return forward; }

		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));

		int result = -1;
		if (isTokenValid(request)) {
		saveToken(request);

			try{
				Login userLogin = (Login) session.getAttribute("UserLogin");
				Role role = new Role();
				BeanUtils.copyProperties(role, dynaForm);
				result = service.updateRole(role, userLogin.getUser(), "update");
			}catch(Exception e) { log.error(e.getMessage(), e);	}
			
			if (result > 0) {
				forward = search(mapping, form, request, response);
			}else{
				errors.add(Constant.GLOBALERROR, new ActionError("error.updateFail", getMessage(request, "error.noRowUpdated")));
				saveErrors(request, errors);
				forward = update(mapping, form, request, response);
			}		
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
			saveErrors(request, errors);
			forward = update(mapping, form, request, response);
		}
		
		return forward;
	} 
	
	public ActionForward addRoleMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("addRoleMenu");		
		
		ActionForward forward = new ActionForward();
		ActionErrors errors = new ActionErrors();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();			
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_ASSIGN);
		if (forward != null) { return forward; }

		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));

		int result = -1;
		try{
			Login userLogin = (Login) session.getAttribute("UserLogin");
			Role role = new Role();
			BeanUtils.copyProperties(role, dynaForm);
			result = service.updateRole(role, userLogin.getUser(), "update");
		}catch(Exception e) { log.error(e.getMessage(), e); }
		
		if (result > 0) {
			forward = search(mapping, form, request, response);
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("error.updateFail", getMessage(request, "error.noRowUpdated")));
			saveErrors(request, errors);
			forward = update(mapping, form, request, response);
		}		
		return forward;
	}
	
	public ActionForward assign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("assign");
		
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		if(Constant.EMPTY_STRING.equals(dynaForm.get("parentMenuCode")))	
			dynaForm.set("parentMenuCode", Constant.FIRST_MENU);

		forward = hasMenuAccess(mapping, request, MENU_ROLE_ASSIGN);
		if (forward != null) { return forward; }
	
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));
		RoleMenu roleMenuPass = new RoleMenu();
		BeanUtils.copyProperties(roleMenuPass, dynaForm);
		List listHas = service.getRoleMenuHas(roleMenuPass);
		Iterator itrHas = listHas.iterator();
		while(itrHas.hasNext()) {
			RoleMenu roleMenu = (RoleMenu)itrHas.next();
			roleMenu.setRoleCode(dynaForm.get("roleCode").toString());
			roleMenu.setParentMenuCode(dynaForm.get("parentMenuCode").toString());
		}

		BeanUtils.copyProperties(roleMenuPass, dynaForm);
		List listNoHas = service.getRoleMenuNoHas(roleMenuPass);
		Iterator itrNoHas = listNoHas.iterator();
		while(itrNoHas.hasNext()) {
			RoleMenu roleMenu = (RoleMenu)itrNoHas.next();
			roleMenu.setRoleCode(dynaForm.get("roleCode").toString());
			roleMenu.setParentMenuCode(dynaForm.get("parentMenuCode").toString());
		}
		
		request.setAttribute("roleMenuHas",listHas);
		request.setAttribute("roleMenuNoHas",listNoHas);
		
		return mapping.findForward("addmenu");
	}
	
	public ActionForward addAssign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("delAssign");

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_ASSIGN);
		if (forward != null) { return forward; }
	
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));
		
		int result = -1;
		try{
			Login userLogin = (Login) session.getAttribute("UserLogin");
			RoleMenu roleMenu = new RoleMenu();
			BeanUtils.copyProperties(roleMenu, dynaForm);
			result = service.insertRoleMenu(roleMenu, userLogin.getUser(), "insert");		
			if (result > 0) {
				forward = assign(mapping, form, request, response);
			}else{
				errors.add(Constant.GLOBALERROR, new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
				saveErrors(request, errors);
				forward = assign(mapping, form, request, response);			
			}
		}catch(Exception e) { 						
			log.error(e.getMessage(), e);			
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
			saveErrors(request, errors);
			forward = assign(mapping, form, request, response);						
		}		
		return forward;
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("back");
		
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
	
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));		
		dynaForm.set("menuCode", dynaForm.get("parentMenuCode"));		
		
		if (dynaForm.get("parentMenuCode").equals(Constant.FIRST_MENU)) {
			dynaForm.set("dispatch", Constant.SEARCH);
			forward = search(mapping, form, request, response);	
		}else{
			dynaForm.set("dispatch", "assign");
			String menuCode = dynaForm.get("menuCode").toString();
			Menu menu = menuService.getMenu(menuCode);
			dynaForm.set("parentMenuCode", menu.getParentMenuCode());
			forward = assign(mapping, form, request, response);	
		}
		return forward;
	}
	
	public ActionForward delAssign(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("delAssign");

		ActionForward forward = new ActionForward();		
		ActionErrors errors = new ActionErrors();

		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_ROLE_ASSIGN_DELETE);
		if (forward != null) { return forward; }
	
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));		

		Vector vector = new Vector();
		vector = getDataDelete(vector, service, dynaForm, dynaForm.get("menuCode").toString());

		int result = -1;
		try{
			Login userLogin = (Login) session.getAttribute("UserLogin");
			result = service.deleteRoleMenu(dynaForm.get("roleCode").toString(), vector, dynaForm.getMap(), userLogin.getUser(), "delete");
		}catch(Exception e) { log.error(e.getMessage(), e); }
		
		if (result > 0) {
			forward = assign(mapping, form, request, response);			
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("error.deleteFail", getMessage(request, "error.noRowUpdated")));
			saveErrors(request, errors);
			forward = assign(mapping, form, request, response);			
		}

		return forward;
	}

	private synchronized Vector getDataDelete(Vector vector, RoleService service,DynaActionForm dynaForm, String menuCode) throws Exception{
		dynaForm.set("menuCode", menuCode);
		vector.addElement(menuCode);
		RoleMenu roleMenu = new RoleMenu();
		BeanUtils.copyProperties(roleMenu, dynaForm);
		List list = service.getDeleteRoleMenu(roleMenu);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			RoleMenu menuLink = (RoleMenu) itr.next();
			getDataDelete(vector, service, dynaForm, menuLink.getMenuCode());
		}
		return vector;
	}
}