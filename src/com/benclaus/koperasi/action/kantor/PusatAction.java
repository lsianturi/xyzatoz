/**
 * 
 */
package com.benclaus.koperasi.action.kantor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.kantor.KantorService;
import com.benclaus.koperasi.model.kantor.Pusat;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok
 *
 */
public class PusatAction extends SecurityAction {
	private static Logger log =
		Logger.getLogger(PusatAction.class);
	
	private String SET_KANTOR_UPD = "SET_KANTOR_upd";
	
	private KantorService service = KantorService.getInstance();

	public ActionForward prepare(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		log.debug("Prepare");

		ActionForward forward = new ActionForward();
		DynaActionForm profileForm = (DynaActionForm) form;

		forward = hasMenuAccess(mapping, request, SET_KANTOR_UPD);
		if (forward != null) {
			return forward;
		}

		profileForm.set("dispatch", Constant.UPDATESAVE);

		Pusat prshn = KantorService.getInstance().getPerusahaan();
		
		// get user code from userlogin session
		BeanUtils.copyProperties(profileForm, prshn);

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

		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm profileForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);

		try {
			if (isTokenValid(request)) {
				
				saveToken(request);

				// Update
				Pusat pusat = new Pusat();
				BeanUtils.copyProperties(pusat, profileForm);
				int affectedRow = service.updatePusat(pusat);
				if (affectedRow == 0) {
					errors.add(
						Constant.GLOBALERROR,
						new ActionMessage(
							"error.updateFail",
							getMessage(request,"error.noRowUpdated")));
				} else {
					// refresh login data
					errors.add(Constant.GLOBALMESSAGE,new ActionMessage("message.updateKantorSuccess"));
				}
			} else {
				errors.add(
					Constant.GLOBALERROR,
					new ActionMessage("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("continue");
		}

		saveMessages(request, errors);
		//saveToken(request);
		return mapping.findForward("continue");
	}

}
