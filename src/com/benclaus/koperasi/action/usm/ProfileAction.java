/**
 * 
 */
package com.benclaus.koperasi.action.usm;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.usm.UserService;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok
 *
 */
public class ProfileAction extends SecurityAction {
	private static Logger log =
		Logger.getLogger(ChangePasswordDispatchAction.class);
	private UserService userService = UserService.getInstance();

	public ActionForward update(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("Update");

		//ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm profileForm = (DynaActionForm) form;

		profileForm.set("dispatch", Constant.UPDATESAVE);

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

		// get user code from userlogin session
		BeanUtils.copyProperties(profileForm, userLogin.getUser());

		saveToken(request);
		return mapping.findForward("continue");
	}

	public ActionForward updateSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("UpdateSave");

		ActionErrors errors = new ActionErrors();
		ActionMessages actionMessages = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm profileForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

		try {
			if (isTokenValid(request)) {
				
				saveToken(request);

				// Update
				int affectedRow =
					userService.updateProfile(
						profileForm.getMap(),
						userLogin.getUser(),
						"profile update");
				if (affectedRow == 0) {
					errors.add(
						Constant.GLOBALERROR,
						new ActionError(
							"error.updateFail",
							getMessage(request,"error.noRowUpdated")));
				} else {
					// refresh login data
					userLogin.setUser(
						userService.getUserRole(
							userLogin.getUser().getUserCode()));
					actionMessages.add(Constant.GLOBALMESSAGE,new ActionMessage("message.profileUpdateSuccess"));
				}
			} else {
				errors.add(
					Constant.GLOBALERROR,
					new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("continue");
		}

		saveMessages(request, actionMessages);
		//saveToken(request);
		return mapping.findForward("continue");
	}

}
