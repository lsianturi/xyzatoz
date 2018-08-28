package com.benclaus.koperasi.action.master;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.kantor.KantorService;
import com.benclaus.koperasi.dao.master.PegawaiService;
import com.benclaus.koperasi.dao.master.StatusPKService;
import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.master.Pegawai;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.benclaus.koperasi.model.master.StatusPK;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

public class PegawaiAction extends SecurityAction {
	private static Logger log = Logger.getLogger(PegawaiAction.class);
	private String MENU_PEG_VIEW = "MST_PEG_search";
	private String MENU_PEG_ADD= "MST_PEG_add";
	private String MENU_PEG_UPD = "MST_PEG_upd";
	private String MENU_PEG_DEL= "MST_PEG_del";

	private PegawaiService service = PegawaiService.getInstance();
	private StatusPKService stsService = StatusPKService.getInstance();
	private KantorService kService = KantorService.getInstance();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private void prepareData(HttpServletRequest request) {

		try {
			request.setAttribute("StatusList", stsService.listStatusPegawai());
			request.setAttribute("SipilList", stsService.listStatusSipil());
			request.setAttribute("CabangList", kService.getCabang());
			request.setAttribute("UnitList", kService.getUnit());
		} catch (Exception e) {
		}
	}
	
	private void prepareData(HttpServletRequest request, Integer cabangId) {

		try {
			request.setAttribute("StatusList", stsService.listStatusPegawai());
			request.setAttribute("SipilList", stsService.listStatusSipil());
			request.setAttribute("CabangList", kService.getCabang());
			if (cabangId != null) {
				request.setAttribute("UnitList", kService.getUnit(cabangId));
			} else {
				request.setAttribute("UnitList", kService.getUnit());
			}
		} catch (Exception e) {
		}
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		forward = hasMenuAccess(mapping, request, MENU_PEG_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareData(request);
		session.removeAttribute(MENU_PEG_VIEW);

		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}
	public ActionForward getUnitHtml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Integer cabId = Integer.parseInt(request.getParameter("cabangId"));
		List<Unit> units = kService.getUnit(cabId);
		StringBuilder sb = new StringBuilder("<select name=\"unit\"><option value=\"\">All</option></select>");
		if (units != null) {
			for (Unit unit: units) {
				sb.append("<option value=\""+unit.getId() +"\">"+ unit.getNama()+"</option>");
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write(sb.toString());
		pw.flush();
		
		return null;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_PEG_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_PEG_VIEW);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PEG_VIEW);
		if (forward != null)
			return forward;

		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm mapForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareData(request);

			// Set owner
			PaginatedList mapList = (PaginatedList) service.searchPegawai(mapForm.getMap());
			int totalSize = service.searchPegawaiSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList, totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_PEG_VIEW, sessionForm);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}

		return mapping.findForward("continue");
	}

	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("changePage");

		HttpSession session = request.getSession();
		DynaActionForm newForm = (DynaActionForm) form;

		if (session.getAttribute(MENU_PEG_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_PEG_VIEW);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Add");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_PEG_ADD);
		if (forward != null)
			return forward;

		try {
			prepareData(request);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);
		planForm.set("dispatch", Constant.ADDSAVE);
		return mapping.findForward("continue");

	}
	
	public ActionForward addSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Add Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PEG_ADD);
		if (forward != null)
			return forward;
		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm planForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidLogin"));
		}

		try {
			prepareData(request);
			if (isTokenValid(request)) {
				saveToken(request);
				Pegawai prshn = new Pegawai();
				BeanUtils.copyProperties(prshn, planForm);
				prshn.setStatusPegawai(new StatusPK((Integer)planForm.get("stsPegawai")));
				prshn.setStatusSipil(new StatusPK((Integer)planForm.get("stsSipil")));
				prshn.setTglMasuk(sdf.parse(planForm.getString("tanggalMasuk")));
				prshn.setCabang(new Cabang((Integer)planForm.get("cabangId")));
				prshn.setUnit(new Unit((Integer)planForm.get("unitId")));
				service.insertPegawai(prshn);
				
				prshn.setCreatedBy(userLogin.getUser().getUserCode());
				prshn.setDeleted(0);
				service.insertPegawaiHistory(prshn);

			} else {
				errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (ParseException pe) {
			log.error(pe.getMessage(), pe);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", getMessage(request, "error.invalidDate")));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		return mapping.findForward("success");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
//		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_PEG_UPD);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer id = Integer.parseInt(request.getParameter("id"));
			Pegawai prshn = service.getPegawai(id);
			BeanUtils.copyProperties(planForm, prshn);
			planForm.set("stsPegawai", prshn.getStatusPegawai().getId());
			planForm.set("stsSipil", prshn.getStatusSipil().getId());
			planForm.set("tanggalMasuk", sdf.format(prshn.getTglMasuk()));
			planForm.set("cabangId", prshn.getCabang().getId());
			planForm.set("unitId", prshn.getUnit().getId());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		planForm.set("dispatch", Constant.UPDATESAVE);
		return mapping.findForward("continue");

	}

	public ActionForward updateSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update Save");

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PEG_UPD);
		if (forward != null)
			return forward;
		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm planForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		// check loggedIn
		if (userLogin == null) {
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidLogin"));
		}

		try {
			prepareData(request);
			if (isTokenValid(request)) {
				saveToken(request);
				Pegawai prshn = new Pegawai();
				BeanUtils.copyProperties(prshn, planForm);
				prshn.setStatusPegawai(new StatusPK((Integer)planForm.get("stsPegawai")));
				prshn.setStatusSipil(new StatusPK((Integer)planForm.get("stsSipil")));
				prshn.setTglMasuk(sdf.parse(planForm.getString("tanggalMasuk")));
				prshn.setCabang(new Cabang((Integer)planForm.get("cabangId")));
				prshn.setUnit(new Unit((Integer)planForm.get("unitId")));
				service.updatePegawai(prshn);
				
				prshn.setCreatedBy(userLogin.getUser().getUserCode());
				prshn.setDeleted(0);
				service.insertPegawaiHistory(prshn);

			} else {
				errors.add(Constant.GLOBALERROR, new ActionMessage("error.invalidToken"));
				saveErrors(request, errors);
				return mapping.findForward("invalidPage");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
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
		forward = hasMenuAccess(mapping, request, MENU_PEG_DEL);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		if (userLogin == null) {
			errors.add(
				Constant.GLOBALERROR,
				new ActionMessage("error.invalidLogin"));
		}

		try {
			// do delete
			//int affectedRow = companyService.deleteCompany(companyForm.getMap(), userLogin.getUser(), "delete");
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			int affectedRow = service.deletePegawai(id);
			if (affectedRow == 0)
				errors.add(Constant.GLOBALERROR,
					new ActionMessage("error.deleteFail", getMessage(request, "error.noRowUpdated")));

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveMessages(request, errors);
		}

		// Return to Search
		return mapping.findForward("success");
	}
	public ActionForward history(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("History");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
//		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_PEG_VIEW);
		if (forward != null)
			return forward;

		try {
			prepareData(request);
			Integer nsbhId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			List<Pegawai> nsbh = service.getPegawaiHistory(nsbhId);
			request.setAttribute("DataList", nsbh);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		return mapping.findForward("history");

	}
}