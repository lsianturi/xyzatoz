package com.benclaus.koperasi.action.usm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.usm.MonitorService;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.Helper;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.benclaus.koperasi.utility.SessionHolder;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public final class MonitorAction extends SecurityAction{
	private static Logger log = Logger.getLogger(MenuAction.class);
	private static final String USER_MONITORING_QUERY = "usermonitor";

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Search");

		// Prepare
		List searchList = new ArrayList();
		searchList.add(new LabelValueBean(getMessage(request, "form.userCode"), "userCode"));
		request.setAttribute("SearchList", searchList);
		
		ActionForward forward = new ActionForward();		
		//ActionErrors errors = new ActionErrors();

		DynaActionForm dynaForm = (DynaActionForm) form;
		HttpSession session = request.getSession();						

		forward = hasMenuAccess(mapping, request, USER_MONITORING_QUERY);
		if (forward != null) { return forward; }
		
        ServletContext servletContext = session.getServletContext();
        SessionHolder sessionHolder = (SessionHolder)servletContext.getAttribute("sessionHolder");
        HashMap map = sessionHolder.getSessionHolder();

		String userCode = (String)dynaForm.getMap().get("searchValue");
	
		Collection collection = map.values();
		Iterator itr = collection.iterator();
		int i = 0;
		List userList = new ArrayList();
		while (itr.hasNext()) {
			HttpSession sid = (HttpSession)itr.next();
			i += 1;
			try {
				Login login = (Login)sid.getAttribute(Constant.SES_USERLOGIN);
				if(login != null) {
					User user = login.getUser();
					if(!userCode.equals(Constant.EMPTY_STRING)) {
					 	if(user.getUserCode().equalsIgnoreCase(userCode)) {
							user.setSessionId(sid.getId());
							userList.add(user);
					 	}
					} else {
						user.setSessionId(sid.getId());
						userList.add(user);
					}
				}
			} catch (ClassCastException c) {
				continue;
			}
		}

		request.setAttribute("DataList",userList);
		request.setAttribute("totalSize",new Integer(userList.size()));

		return mapping.findForward("main");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("Detail");

		ActionForward forward = new ActionForward();		
		
		MonitorService service = MonitorService.getInstance();	
		DynaActionForm userMonitorForm = (DynaActionForm) form;
		HttpSession session = request.getSession();						

		forward = hasMenuAccess(mapping, request, USER_MONITORING_QUERY);
		if (forward != null) { return forward; }
		
		String isLastUpdDtmFill = userMonitorForm.get("isLastUpdDtmFill").toString();		
		if (isLastUpdDtmFill.equals("1")) {
			String lastUpdDtm = userMonitorForm.get("lastUpdDtm").toString();
			lastUpdDtm = Helper.convertDateToOpposite(lastUpdDtm, true)+ " 00:00:00";
			userMonitorForm.set("lastUpdDtm", lastUpdDtm);
		}
		
		PaginatedList userMonitorDetailList = (PaginatedList) service.selectAuditTrailForUserMonitor(userMonitorForm.getMap());
		int totalSize = 0;
		if(userMonitorDetailList != null) totalSize = userMonitorDetailList.size();

		int page = Integer.parseInt((String)userMonitorForm.get("pageIndex"));

		if (page*userMonitorDetailList.getPageSize()>totalSize) page = ((totalSize-1)/userMonitorDetailList.getPageSize())+1;
		if (page*userMonitorDetailList.getPageSize()<1) page = 1;
		
		userMonitorDetailList.gotoPage(page-1);

		request.setAttribute("DataList", new Page(userMonitorDetailList, totalSize));
		
		log.info(new Integer(userMonitorDetailList.getPageIndex()));
		
		DAFContainer sessionForm = new DAFContainer(userMonitorForm.getMap());
		session.setAttribute(USER_MONITORING_QUERY, sessionForm);
			
		return mapping.findForward("detail");
	}

}