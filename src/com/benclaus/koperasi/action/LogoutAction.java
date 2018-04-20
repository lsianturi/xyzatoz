package com.benclaus.koperasi.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.benclaus.koperasi.dao.usm.UsmService;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.LoginTrail;
import com.benclaus.koperasi.utility.Constant;


/**
 * @author Lambok
 *
 */
public class LogoutAction extends Action {
	private static Logger log = Logger.getLogger(LogoutAction.class);
	private UsmService usmService = UsmService.getInstance();

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();
		
		Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		
		if (login!=null) {
			LoginTrail lt = new LoginTrail();
			lt.setUserCode(login.getUser().getUserCode());
			lt.setSessionId(session.getId());
			lt.setLastUpdProcess("Logout");
			usmService.insertLoginTrail(lt);
		}
		log.info("Logout");					
		session.invalidate();
		
		return mapping.findForward("continue");
	}
}
