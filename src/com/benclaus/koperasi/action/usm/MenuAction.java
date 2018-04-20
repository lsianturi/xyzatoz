package com.benclaus.koperasi.action.usm;

import java.util.ArrayList;
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
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public final class MenuAction extends SecurityAction{
	private static Logger log = Logger.getLogger(MenuAction.class);
	private String MENU_USER_MENU_QUERY = "menu";
	private String MENU_USER_MENU_UPDATE = "menuupd";
	private String MENU_USER_MENU_ADD = "menuadd";
	private String MENU_USER_MENU_DELETE = "menudel";
	private String SubMenu = "SubMenu";
	private String Transaction = "Transaction";
	
	private MenuService service = MenuService.getInstance();

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("prepare");
		
		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();				
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_QUERY);
		if (forward != null) { return forward; }


		if (session.getAttribute(MENU_USER_MENU_QUERY) != null) {
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

		if (session.getAttribute(MENU_USER_MENU_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_USER_MENU_QUERY);
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
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_QUERY);
		if (forward != null) { return forward; }

		if (session.getAttribute(MENU_USER_MENU_QUERY) != null && !dynaForm.get("dispatch").equals(Constant.SEARCH)) {
			Object pageIndex = dynaForm.get("pageIndex");
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_USER_MENU_QUERY);
			dafProxy.populate((DynaActionForm) form);
			((DynaActionForm) form).set("pageIndex", pageIndex);
		}	
		
		PaginatedList page = service.getPaging(dynaForm.getMap());
		int intNavi = Integer.parseInt(dynaForm.get("pageIndex").toString());

		request.getSession().setAttribute("pageIndex", dynaForm.get("pageIndex"));

		int totalSize = service.getCountRow(dynaForm.getMap());
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
		session.setAttribute(MENU_USER_MENU_QUERY, sessionForm);
		
		return mapping.findForward("main");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Add");
				
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_ADD);
		if (forward != null) { return forward; }
		
		List list = new ArrayList();
		if (dynaForm.get("menuType").equals("1") || dynaForm.get("menuType").equals("0")) {
			LabelValueBean label1 = new LabelValueBean("1", SubMenu);
			list.add(label1);
		}
		LabelValueBean label2 = new LabelValueBean("2", Transaction);
		list.add(label2);
		
		dynaForm.set("menuCode", "");
		dynaForm.set("parentMenuCode", dynaForm.get("parentMenuCode").equals("")? Constant.FIRST_MENU : dynaForm.get("parentMenuCode"));
		
		request.setAttribute("menuTypeCombo", list);		
		saveToken(request);
		
		return mapping.findForward("add");
	}
	
	public ActionForward addSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AddSave");

		ActionForward forward = new ActionForward();		
		ActionErrors errors = new ActionErrors();			
		
		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_ADD);
		if (forward != null) { return forward; }
		
		int result = -1;
		try{
			if (isTokenValid(request)) {
				saveToken(request);
				Menu menu = new Menu();
				BeanUtils.copyProperties(menu, dynaForm);
				Login userLogin = (Login) session.getAttribute("UserLogin");
				result = service.insertMenu(menu, userLogin.getUser(), "insert");
				if (result > 0) {
					dynaForm.set("menuCode", dynaForm.get("parentMenuCode"));
					forward = tree(mapping, form, request, response);
				}else{				
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
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_DELETE);
		if (forward != null) { return forward; }
		
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));		
		
		Login userLogin = (Login) session.getAttribute("UserLogin");
		
		int result = -1;
		try{
			vector = getDataDelete(vector, service, dynaForm, dynaForm.get("menuCode").toString());
			result = service.deleteMenu(dynaForm.getMap(), vector,  userLogin.getUser(), "delete");
		}catch(DaoException e) { log.error(e.getMessage(), e); }
		
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
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_UPDATE);
		if (forward != null) { return forward; }
		
		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));
		String menuCode = dynaForm.get("menuCode").toString();
		
		Object userMenuList = service.getMenu(menuCode);
		BeanUtils.copyProperties(dynaForm, userMenuList);
		
		List list = new ArrayList();

		if (dynaForm.get("menuType").equals("1") || dynaForm.get("menuType").equals("0")) {
			System.out.println("into one");
			LabelValueBean label = new LabelValueBean("1", SubMenu);
			list.add(label);
		}
		if (dynaForm.get("menuType").equals("2")) {
			LabelValueBean label = new LabelValueBean("2", Transaction);
			list.add(label);					
		}

		request.setAttribute("menuTypeCombo", list);
		request.setAttribute("menuForm", dynaForm);
		saveToken(request);
		
		return mapping.findForward("update");
	}
	
	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("UpdateSave");

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		
		DynaActionForm dynaForm = (DynaActionForm) form;	
		HttpSession session = request.getSession();		
		
		forward = hasMenuAccess(mapping, request, MENU_USER_MENU_UPDATE);
		if (forward != null) { return forward; }

		dynaForm.set("pageIndex", request.getSession().getAttribute("pageIndex"));

		int result = -1;
		if (isTokenValid(request)) {
			saveToken(request);
			try{
				Login userLogin = (Login) session.getAttribute("UserLogin");
				Menu menu = new Menu();
				BeanUtils.copyProperties(menu, dynaForm);
				result = service.updateMenu(menu, userLogin.getUser(), "update");
			}catch(DaoException e) { log.error(e.getMessage(), e); }
			if (result > 0) {
				dynaForm.set("menuCode", dynaForm.get("parentMenuCode"));
				forward = tree(mapping, form, request, response);
			}else{
				errors.add(Constant.GLOBALERROR, new ActionError("error.updateFail", getMessage(request, "error.noRowUpdated")));
				saveErrors(request, errors);
				forward = update(mapping, form, request, response);
			}		
		}else{
			errors.add(Constant.GLOBALERROR, new ActionError("err.invalidToken"));
			saveErrors(request, errors);
			forward = mapping.findForward("invalidPage");					
		}
		
		return forward;
	}

	public ActionForward tree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Tree");
				
		DynaActionForm dynaForm = (DynaActionForm) form;
		String menuCode = dynaForm.get("menuCode").toString();
		
		List dataUserForm = service.getMenuParent(menuCode);		
		request.setAttribute("dataUserForm", dataUserForm);
		
		if (!dataUserForm.isEmpty()) {
			Menu menuUser = (Menu)dataUserForm.get(0);			
			Menu menuParent = service.getMenu(menuCode);
			dynaForm.set("menuCode", menuUser.getMenuCode());
			Menu menuChild = service.getMenu(menuUser.getMenuCode());

			Iterator itr = dataUserForm.iterator();
			int menuOrder = 1;
			while(itr.hasNext()) {
				Menu menu = (Menu)itr.next();
				menuOrder = menuOrder > Integer.parseInt(menu.getMenuOrder()) ? menuOrder : Integer.parseInt(menu.getMenuOrder());
			}
			
			dynaForm.set("parentMenuCode", menuChild.getParentMenuCode());
			dynaForm.set("menuLevel", menuChild.getMenuLevel());
			dynaForm.set("menuOrder", String.valueOf(++menuOrder));
			dynaForm.set("menuType", menuParent.getMenuType());
		}
		else{			
			Menu menuParent = service.getMenu(menuCode);
			Menu menu = service.getMenu(menuCode);

			dynaForm.set("parentMenuCode", dynaForm.get("menuCode"));			
			dynaForm.set("menuLevel", String.valueOf(Integer.parseInt(menu.getMenuLevel())+1));
			dynaForm.set("menuOrder", "1");
			dynaForm.set("menuType", menuParent.getMenuType());
		}
		
		if (dynaForm.get("parentMenuCode").equals(Constant.FIRST_MENU)) {
			dynaForm.set("dispatch", Constant.SEARCH);	
			dynaForm.set("parentMenuCode", "");
		}

		return mapping.findForward("tree");		
	}
	
	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Returned");
	
		ActionForward forward = new ActionForward();		
		DynaActionForm dynaForm = (DynaActionForm) form;
		
		dynaForm.set("menuCode", dynaForm.get("parentMenuCode"));		
		forward = tree(mapping, form, request, response);		
		
		return forward;
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Back");
	
		ActionForward forward = new ActionForward();		

		DynaActionForm dynaForm = (DynaActionForm) form;

		if (dynaForm.get("parentMenuCode").equals("")) {
			forward = search(mapping, form, request, response);
		}else{
			dynaForm.set("menuCode", dynaForm.get("parentMenuCode"));
			String menuCode = dynaForm.get("menuCode").toString();							
			Menu menu = service.getMenu(menuCode);
			dynaForm.set("menuCode", menu.getParentMenuCode());
			forward = tree(mapping, form, request, response);
		}
		return forward; 
	}
	
	public ActionForward mainmenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("MainMenu");

		return search(mapping, form, request, response);
	}
	
	private synchronized Vector getDataDelete(Vector vector, MenuService service,DynaActionForm dynaForm, String menuCode) throws Exception{
		dynaForm.set("menuCode", menuCode);
		vector.addElement(menuCode);
		List list = service.getMenuParent(menuCode);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Menu menuLink = (Menu) itr.next();
			getDataDelete(vector, service, dynaForm, menuLink.getMenuCode());
		}
		return vector;
	}
}