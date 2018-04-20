package com.benclaus.koperasi.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.dao.usm.MenuService;
import com.benclaus.koperasi.dao.usm.UserService;
import com.benclaus.koperasi.dao.usm.UsmService;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.model.usm.LoginTrail;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok
 *
 */
public class LoginAction extends Action {
	private static Logger log = Logger.getLogger(LoginAction.class);
	private UsmService usmService = UsmService.getInstance();
	// private SystemPropertyService spService =
	// SystemPropertyService.getInstance();
	private UserService userService = UserService.getInstance();
	private MenuService menuService = MenuService.getInstance();

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

//		ActionErrors errors = new ActionErrors();
		ActionMessages errors = new ActionMessages();
		// ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		DynaActionForm loginForm = (DynaActionForm) form;

		// check if loginButton not exist
		if (loginForm.get("loginButton") == null || "".equals(loginForm.get("loginButton"))) {
			// not from front screen
			// check if already logged in
			Login userLogin = (Login)session.getAttribute(Constant.SES_USERLOGIN);
			if (userLogin != null ) {
				return mapping.findForward("success");
			}
			return mapping.findForward("fail");
		}
		
		try {
			ResourceBundle rb = ResourceBundle.getBundle("com/benclaus/koperasi/resources/ApplicationResources",
					request.getLocale());
			ResourceBundle mrb = ResourceBundle.getBundle("com/benclaus/koperasi/resources/MenuResources",
					request.getLocale());

			session.setAttribute("UserRB", rb);

			String userCode = (String) loginForm.get("userCode");
			String password = (String) loginForm.get("jsPassword");


			User user = userService.getUser(userCode);
			
			if (user != null && user.getPassword().equals(password)) {

				// Query db and get user data
				user = userService.getUserRole(userCode);

				// if not found show login error
				if (user != null) {
					// if user non aktif show user not active error
					if (Constant.CAT_ACTIVESTATUS_ACTIVE.equals(user.getActiveStatus())) {

						// if found but password don't match show login error

						Date currentDate = new Date();
						Calendar currentCal = Calendar.getInstance();
						currentCal.setTime(currentDate);
						log.info(currentCal.getTime());

						LoginTrail lt = new LoginTrail();

						lt.setUserCode(userCode);
						lt.setSessionId(session.getId());
						lt.setLastUpdProcess("Login");
						usmService.insertLoginTrail(lt);

						// if login ok load all data to user session
						Login userLogin = new Login();

						// set session timeout
						session.setMaxInactiveInterval(60 * Integer.valueOf(user.getSessionTimeOut()).intValue());

						// load user data
						userLogin.setUser(user);

						// load user menu code
						List<String> list = menuService.selectAllUserMenuCode(userCode);
						if (list == null || list.size() == 0) 
							list = menuService.selectUserMenuCode(user.getRoleCode());
						userLogin.setMenuCodeList(list);

						// load user tree menu
						userLogin.setMenuTree(usmService.buildTreeMenu(userCode, mrb));

						// set logged in to true
						userLogin.setLoggedIn(true);

						// update last logon time
						userService.updateLastLogon(userCode);

						// save data to session
						session.setAttribute(Constant.SES_USERLOGIN, userLogin);
					} else {
						errors.add(Constant.GLOBALERROR, new ActionMessage("error.nonActiveLogin"));
					}
				} else {
					errors.add(Constant.GLOBALERROR, new ActionMessage("error.login.roleNotFound"));
				}
			} else {
				errors.add(Constant.GLOBALERROR, new ActionMessage("error.login"));
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			// Clear password
			loginForm.set("password", "");
			return mapping.findForward("fail");
		}

		return mapping.findForward("success");
	}
}
