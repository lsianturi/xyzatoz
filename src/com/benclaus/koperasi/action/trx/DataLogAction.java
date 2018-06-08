package com.benclaus.koperasi.action.trx;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.trx.A3Service;
import com.benclaus.koperasi.dao.trx.ConfigService;
import com.benclaus.koperasi.dao.trx.DataService;
import com.benclaus.koperasi.model.FormulaArg;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.YearHelper;

/**
 * @author Lambok
 * 
 */
public final class DataLogAction extends SecurityAction {
	private static Logger log = Logger.getLogger(DataLogAction.class);
	private String MENU_LOG_VIEW = "data_logs_view";

	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();
	private A3Service a3Service = A3Service.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("companyList", cfgService.getCompanyList());
			request.setAttribute("yearList", service.getDataYearList());
			request.setAttribute("monthList", YearHelper.getInstance().getMonthList());
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		DynaActionForm logForm = (DynaActionForm) form;

		HttpSession session = request.getSession();
		Calendar cal = GregorianCalendar.getInstance();
//		a3Form.set("currentYear", cal.get(Calendar.YEAR));
		/*int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_LOG_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		Integer month = Calendar.getInstance().get(Calendar.MONTH) +1;
		
		logForm.set("year", year);
		logForm.set("month", month);
		
		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_LOG_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_LOG_VIEW);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Search");
		

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_LOG_VIEW);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm logForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);

			// Set owner
			Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
			Integer company = (Integer) logForm.get("companyId");
			Integer year= (Integer) logForm.get("year");
			Integer month = (Integer) logForm.get("month");
			
			//log.debug(currentYear +", " + fromYear + ", " + toYear);
			
			Map<String, Object> param = new HashMap<>();
			param.put("company", company);
			param.put("year", year);
			param.put("month", month);
			List<FormulaArg> args = a3Service.getDataLog(param);
			int totalSize = args.size();
			
//			int page = Integer.parseInt((String) logForm.get("pageIndex"));

			/*if (page * args.getPageSize() > totalSize)
				page = ((totalSize - 1) / args.getPageSize()) + 1;
			if (page * args.getPageSize() < 1)
				page = 1;*/

			//args.gotoPage(page - 1);
			
			request.setAttribute("DataList", new Page(args,
					args.size()));

			DAFContainer sessionForm = new DAFContainer(logForm.getMap());
			session.setAttribute(MENU_LOG_VIEW, sessionForm);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception",
					e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}


		return mapping.findForward("continue");
	}
	
}