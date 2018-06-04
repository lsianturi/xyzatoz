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
import com.benclaus.koperasi.dao.usm.UsmService;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok
 *
 */
public class ChangePasswordDispatchAction extends SecurityAction {
	private static Logger log =
		Logger.getLogger(ChangePasswordDispatchAction.class);
	private UsmService usmService = UsmService.getInstance();
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
		DynaActionForm passwordForm = (DynaActionForm) form;

		passwordForm.set("dispatch", Constant.UPDATESAVE);

		Login userLogin = (Login) session.getAttribute("UserLogin");

		// get user code from userlogin session
		BeanUtils.copyProperties(passwordForm, userLogin.getUser());

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
		DynaActionForm passwordForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute("UserLogin");

		try {
			if (isTokenValid(request)) {
				
				saveToken(request);

				// check valid old password
				String oldPassword = (String) passwordForm.get("password");
				log.info(oldPassword);
				log.info(userLogin.getUser().getPassword());
				if (userLogin.getUser().getPassword().equals(oldPassword)) {
					// Update
					int affectedRow =
						userService.updatePassword(
							passwordForm.getMap(),
							userLogin.getUser(),
							"change password");
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
						actionMessages.add(Constant.GLOBALMESSAGE,new ActionMessage("message.changePasswordSuccess"));
					}
				} else {
					errors.add("password",new ActionError("error.invalidPassword"));
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
