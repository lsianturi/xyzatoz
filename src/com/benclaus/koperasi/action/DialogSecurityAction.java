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

import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;


/**
 * @author Lambok
 *
 */
public class DialogSecurityAction extends DispatchAction {
	private static Logger log = Logger.getLogger(DialogSecurityAction.class);
	
	// Get messages from Application Resources file
//	protected static MessageResources messages = MessageResources.getMessageResources("com/hmf/resources/ApplicationResources");

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		log.info("execute");
			
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		
		Login userLogin = (Login)session.getAttribute("UserLogin");
		
		// check user logged in
		if (userLogin==null || !userLogin.isLoggedIn()) {
			errors.add(Constant.GLOBALERROR, new ActionError("dialog.error.invalidLogin"));
		}
		
		// check token
	/*	if (!isTokenValid(request)) {
saveToken(request);

			errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
			return mapping.findForward("InvalidPage");
		}*/
		
		if (errors.size()>0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}

		return super.execute(mapping, form, request, response);
	}
	
	protected ActionForward hasMenuAccess(
		ActionMapping mapping,
		HttpServletRequest request,
		String menuCode)
		throws Exception {
			
		log.info("HasMenuAccess");
			
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
			
		Login userLogin = (Login)session.getAttribute("UserLogin");
		
		log.debug(String.valueOf(userLogin.isHasMenuAccess(menuCode)));
		if (!userLogin.isHasMenuAccess(menuCode)) {
			log.debug("Invalid Menu Access");
			errors.add(Constant.GLOBALERROR, new ActionError("dialog.error.invalidMenuAccess"));
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}
			
		return null;
	}
	
	protected String getMessage(HttpServletRequest request, String key) {
		return ((ResourceBundle)request.getSession().getAttribute("UserRB")).getString(key);
	}
}
