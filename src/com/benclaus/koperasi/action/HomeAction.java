package com.benclaus.koperasi.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Lambok
 *
 */
public class HomeAction extends SecurityAction {
	private static Logger log = Logger.getLogger(HomeAction.class);
	//private UsmService usmService = UsmService.getInstance();
	//private SystemPropertyService spService = SystemPropertyService.getInstance();

	public ActionForward firstPage(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		//ActionForward forward = new ActionForward();
//		ActionMessages actionMessages = new ActionMessages();
//		HttpSession session = request.getSession();
		//DynaActionForm homeForm = (DynaActionForm) form;
		
		/*try {
			// show password expired warning
			User user = userLogin.getUser();
			//Date lastChangePass = user.getL astChangePassword();
			Calendar lastChangeCal = Calendar.getInstance();
			//lastChangeCal.setTime(lastChangePass);
			
			SystemProperty sp = spService.getSystemProperty(Constant.SPR_PASSWORD_EXPIRED_PERIOD);
			lastChangeCal.add(Calendar.DATE, new Integer(sp.getValue()).intValue());
			log.info(lastChangeCal.getTime());
		
			Date currentDate = new Date();
			Calendar currentCal = Calendar.getInstance();
			currentCal.setTime(currentDate);
			log.info(currentCal.getTime());	
			if (currentCal.before(lastChangeCal)) {
				int diff = Helper.getDateDiff(Calendar.DATE, currentCal.getTime(), lastChangeCal.getTime());
				log.info(String.valueOf(diff));	

				sp = spService.getSystemProperty(Constant.SPR_PASSWORD_WARNING_PERIOD);
				if (diff<=new Integer(sp.getValue()).intValue()) {
					actionMessages.add(Constant.GLOBALMESSAGE,new ActionMessage("message.passwordExpired", String.valueOf(diff)));
					saveMessages(request, actionMessages);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}*/

		if (errors.size() > 0) {
			saveErrors(request, errors);
		}

		return mapping.findForward("continue");
	}
	
	public void changeCompany(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("change company");
		HttpSession session = request.getSession();
		try {
			/*response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type");
			response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");*/

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}


		// Return to Search
	}
}

