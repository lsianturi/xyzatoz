
package com.benclaus.koperasi.action.master;

import java.util.List;

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
import com.benclaus.koperasi.dao.trx.ConfigService;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
/**
 * @author Lambok
 *
 */
public final class PillarAction extends SecurityAction{
	private static Logger log = Logger.getLogger(PillarAction.class);
	private String MENU_PILAR_VIEW = "cfg_pilar_view";
	private String MENU_PILAR_EDIT = "cfg_pilar_edit";
	
	private ConfigService service = ConfigService.getInstance();
	
	private void prepareSearch (HttpServletRequest request) {
		/*HttpSession session = request.getSession();
		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		try {
			request.setAttribute("monthList", YearHelper.getInstance().getMonthList());
			request.setAttribute("yearList", (service.getYearList() != null ||service.getYearList().size()==0)? service.getYearList() :
				new ArrayList().add(new LabelValueBean(""+Calendar.getInstance().get(Calendar.YEAR), ""+Calendar.getInstance().get(Calendar.YEAR))));
			if (userLogin.getUser().getRoleCode().equalsIgnoreCase("USER")) {
				Map<String, Object> map = new HashMap<>();
				request.setAttribute("empList", service.getEmployeeList(map));
			} else {
				request.setAttribute("empList", service.getEmployeeList(null));
			}
		} catch (Exception e) {
			request.setAttribute("monthList", new ArrayList());
			request.setAttribute("yearList", new ArrayList().add(new LabelValueBean(""+Calendar.getInstance().get(Calendar.YEAR), ""+Calendar.getInstance().get(Calendar.YEAR))));
		}*/
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("prepare");
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();				
		
		forward = hasMenuAccess(mapping, request, MENU_PILAR_VIEW);
		if (forward != null) { return forward; }
		prepareSearch(request);
		saveToken(request);
		List<Pillar> listPilar = service.getPillars();
		
		request.setAttribute("DataList", new Page(listPilar,
				listPilar.size()));
		
		return mapping.findForward("main");
	}
	
	public ActionForward returned(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_PILAR_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_PILAR_VIEW);
			dafProxy.populate((DynaActionForm) form);
		}

		return prepare(mapping, form, request, response);
	}
	
	public ActionForward add(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Add");

		// Check Menu Access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PILAR_EDIT);
		if (forward != null)
			return forward;

		saveToken(request);

		DynaActionForm pilarForm = (DynaActionForm) form;
		pilarForm.set("dispatch", Constant.ADDSAVE);
		return mapping.findForward("success");

	}

	public ActionForward addSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		log.debug("Add Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PILAR_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm pilarForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			if (isTokenValid(request)) {
				saveToken(request);
				
				// Update
				Pillar pilar = new Pillar();
				pilar.setName(pilarForm.get("name").toString());
				
				int affectedRow = service.insertPillar(pilar);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
			} else {
				errors.add( Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward update(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Update");

		// Check Menu Access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PILAR_EDIT);
		DynaActionForm pilarForm = (DynaActionForm) form;
		ActionErrors errors = new ActionErrors();
		if (forward != null)
			return forward;

		try {
			saveToken(request);
			Integer id = Integer.parseInt(request.getParameter("id"));
			Pillar pilar = service.getPillar(id);
			pilarForm.set("pilarId", pilar.getId());
			pilarForm.set("name", pilar.getName());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}
		
		pilarForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("success");

	}

	public ActionForward updateSave(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PILAR_EDIT);
		if (forward != null)
			return forward;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm pilarForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			if (isTokenValid(request)) {
				saveToken(request);
				
				// Update
				Pillar pilar = new Pillar();
				pilar.setId((Integer)pilarForm.get("pilarId"));
				pilar.setName(pilarForm.get("name").toString());
				
				int affectedRow = service.updatePillar(pilar);
				if (affectedRow < 1)
					errors.add(Constant.GLOBALERROR,
							new ActionError("error.insertFail", getMessage(request, "error.noRowUpdated")));
			} else {
				errors.add( Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Delete");
		
		// Check Menu Access

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PILAR_EDIT);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm absenForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		if (userLogin == null) {
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			// do delete
			Integer id = Integer.parseInt(request.getParameter("id"));
			int affectedRow =
				service.deletePillar(id);
			if (affectedRow == 0)
				errors.add(Constant.GLOBALERROR,
					new ActionError("error.deleteFail", getMessage(request, "error.noRowUpdated")));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
		}

		// Return to Search
		return prepare(mapping, absenForm, request, response);
	}
	
	/*public ActionForward process(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("process");
		
		prepareSearch(request);
		ActionForward forward = new ActionForward();		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		DynaActionForm calcForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_CALC_PROCESS);
		if (forward != null) { return forward; }
		
		try{
			if (isTokenValid(request)) {
				saveToken(request);
				Integer month = new Integer(calcForm.get("month").toString());
				Integer year = new Integer(calcForm.get("year").toString());
				try {
					// hapus dulu absensi sebelumnya 
					service.deleteAbsensiByMonth(calcForm.getMap());
					
					// ambil semua data dari absensi_raw berdasar bulan/tahun/empId
					List distinctEmpTgl = service.getDistinctRawAbsen(calcForm.getMap());
					AbsensiFilter absFilter=null;
					EmployeeAbsensi absen = null;
					for (int i=0; i<distinctEmpTgl.size(); i++) {
						absFilter = (AbsensiFilter) distinctEmpTgl.get(i);
						//berdasar kombinasi empdid + tglkerja, ambil absennya
						if (service.getRawAbsenCountByEmpId(absFilter) == 1) {
							absen = service.getAbsenByEmpIdInOnly(absFilter);
						} else {
							absen = service.getAbsenByEmpId(absFilter);
						}
						// simpan data absen ke absensi
						service.insertAbsensi(absen);
					}
					// masukkan empid, nama jika ada employee baru muncul di data raw absen
					service.updateEmployeeData(month, year);
					messages.add(Constant.GLOBALMESSAGE, new ActionMessage("calculate.success",distinctEmpTgl.size(),""+month, ""+year));
					saveMessages(request, messages);
				} catch (DaoException e) {
					log.error(e.getMessage(), e);			
					errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
					saveErrors(request, errors);
				}
				
			} else {
				errors.add(Constant.GLOBALERROR, new ActionError("error.invalidToken"));
				saveErrors(request, errors);
				forward = mapping.findForward("invalidPage");					
			}
		}catch(Exception e) { 						
			log.error(e.getMessage(), e);			
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception", e.getMessage()));
			saveErrors(request, errors);
		}		
		return mapping.findForward("main");
	} */
	
}