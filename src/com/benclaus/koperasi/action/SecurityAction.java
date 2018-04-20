package com.benclaus.koperasi.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.benclaus.koperasi.model.system.Process;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
/**
 * @author Lambok
 *
 */
public class SecurityAction extends DispatchAction {
	private static Logger log = Logger.getLogger(SecurityAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("execute");
			
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		Login userLogin =  (Login)session.getAttribute(Constant.SES_USERLOGIN);
			
		// check user logged in
		if (userLogin==null || !userLogin.isLoggedIn()) {
			log.info("notLogin");
			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidLogin"));
		}
		
		if (errors.size()>0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}

		return super.execute(mapping, form, request, response);
	}
	
	protected ActionForward hasMenuAccess(ActionMapping mapping, HttpServletRequest request, String menuCode) throws Exception {
		log.info("HasMenuAccess");
			
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
			
		Login userLogin = (Login)session.getAttribute(Constant.SES_USERLOGIN);
		
		log.debug(String.valueOf(userLogin.isHasMenuAccess(menuCode)));
		if (!userLogin.isHasMenuAccess(menuCode)) {
			log.debug("Invalid Menu Access");
			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidMenuAccess"));
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}
			
		return null;
	}
	
	protected String getMessage(HttpServletRequest request, String key) {
		return ((ResourceBundle)request.getSession().getAttribute("UserRB")).getString(key);
	}
	
	public void startProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("userProcessTime");
		if (object == null) {
			Process process = new Process();
			process.setStartProcess(System.currentTimeMillis());
			session.setAttribute("userProcessTime", process);
		}else{
			Process process = (Process) object;
			process.setStartProcess(System.currentTimeMillis());
		}
	}
	public void endProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("userProcessTime");
		if (object == null) {
			Process process = new Process();
			process.setEndProcess(System.currentTimeMillis());
			session.setAttribute("userProcessTime", process);
		}else{
			Process process = (Process) object;
			process.setEndProcess(System.currentTimeMillis());
		}		
	}	
	
}
