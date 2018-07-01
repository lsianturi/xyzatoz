package com.benclaus.koperasi.action.master;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.kantor.KantorService;
import com.benclaus.koperasi.dao.master.NasabahService;
import com.benclaus.koperasi.dao.master.StatusPKService;
import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.master.Bank;
import com.benclaus.koperasi.model.master.Nasabah;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.benclaus.koperasi.model.master.StatusPK;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

public class NasabahAction extends SecurityAction {
	private static Logger log = Logger.getLogger(NasabahAction.class);
	private String MENU_NSB_QUERY = "MST_NSB_search";
	private String MENU_NSB_ADD= "MST_NSB_add";
	private String MENU_NSB_UPD = "MST_NSB_upd";
	private String MENU_NSB_DEL= "MST_NSB_del";

	private StatusPKService stService = StatusPKService.getInstance();
	private NasabahService nService = NasabahService.getInstance();
	private KantorService kService = KantorService.getInstance();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private void prepareSearch(HttpServletRequest request) {

		try {
			request.setAttribute("PerusahaanList", stService.listPerusahaan());
			request.setAttribute("BankList", stService.listBank());
			request.setAttribute("JnsAgtList", stService.listJenisAnggota());
			request.setAttribute("StsAgtList", stService.listStatusAnggota());
			request.setAttribute("AgentList", stService.listAgent());
			request.setAttribute("CabangList", kService.getCabang());
			request.setAttribute("UnitList", kService.getUnit());
		} catch (Exception e) {
		}
	}
	
	private void prepareData(HttpServletRequest request, Integer cabangId) {
		try {
			request.setAttribute("SexList", stService.listJnsKelamin());
			request.setAttribute("SipilList", stService.listStatusSipil());
			request.setAttribute("PerusahaanList", stService.listPerusahaan());
			request.setAttribute("BankList", stService.listBank());
			request.setAttribute("JnsAgtList", stService.listJenisAnggota());
			request.setAttribute("StsAgtList", stService.listStatusAnggota());
			request.setAttribute("StsKrywnList", stService.listStatusKaryawan());
			request.setAttribute("AgentList", stService.listAgent());
			request.setAttribute("CabangList", kService.getCabang());
			if (cabangId != null) {
				request.setAttribute("UnitList", kService.getUnit(cabangId));
			} else {
				request.setAttribute("UnitList", kService.getUnit());
			}
		} catch (Exception e) {
		}
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

	public ActionForward prepare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
//		DynaActionForm actualForm = (DynaActionForm) form;

		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		session.removeAttribute(MENU_NSB_QUERY);

//		Integer year = Calendar.getInstance().get(Calendar.YEAR);
//
//		actualForm.set("fromYear", year);
//		actualForm.set("toYear", year);

		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
//		DynaActionForm actualForm = (DynaActionForm) form;

		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		session.removeAttribute(MENU_NSB_QUERY);

//		Integer year = Calendar.getInstance().get(Calendar.YEAR);
//
//		actualForm.set("fromYear", year);
//		actualForm.set("toYear", year);

//		saveToken(request);

		forward = mapping.findForward("list");
		return forward;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_NSB_QUERY) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_NSB_QUERY);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null)
			return forward;

		ActionMessages errors = new ActionMessages();
		HttpSession session = request.getSession();
		DynaActionForm mapForm = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);

			PaginatedList mapList = (PaginatedList) nService.searchNasabah(mapForm.getMap());
			int totalSize = nService.searchNasabahSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList, totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_NSB_QUERY, sessionForm);
			session.setAttribute("Data", mapList);
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

		if (session.getAttribute(MENU_NSB_QUERY) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_NSB_QUERY);
			dafProxy.populate((DynaActionForm) form);

			((DynaActionForm) form).set("pageIndex", pageIndex);
		}

		return search(mapping, form, request, response);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("View");

		ActionMessages errors = new ActionMessages();
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_NSB_QUERY);
		if (forward != null)
			return forward;

		try {
			prepareData(request, null);
			Integer nsbhId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			Nasabah nsbh = nService.getNasabah(nsbhId);
			request.setAttribute("nsbh", nsbh);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}

		saveToken(request);

		return mapping.findForward("view");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Add");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_NSB_ADD);
		if (forward != null)
			return forward;

		try {
			prepareData(request, null);

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
		forward = hasMenuAccess(mapping, request, MENU_NSB_ADD);
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
		Nasabah nsbh = new Nasabah();
		try {
			prepareSearch(request);
			if (isTokenValid(request)) {
				saveToken(request);
				BeanUtils.copyProperties(nsbh, planForm);
				nsbh.setJenisKelamin(new StatusPK((Integer)planForm.get("jnsKelamin")));
				nsbh.setStatusSipil(new StatusPK((Integer)planForm.get("stsSipil")));
				nsbh.setPt(new Perusahaan((Integer)planForm.get("perusahaan")));
				nsbh.setStatusKaryawan(new StatusPK((Integer)planForm.get("stsKrywn")));
				nsbh.setBank(new Bank((Integer)planForm.get("bankId")));
				nsbh.setTglMasuk(sdf.parse(planForm.getString("strTglMasuk")));
				nsbh.setTglPayroll(sdf.parse(planForm.getString("strTglPayroll")));
				nsbh.setStatusKerja(new StatusPK((Integer)planForm.get("stsKerja")));
				nsbh.setJenisAnggota(new StatusPK((Integer)planForm.get("jnsAnggota")));
				nsbh.setAnAgent(planForm.get("anAgent") != null ? true : false);
				nsbh.setCabang(new Cabang((Integer)planForm.get("cabangId")));
				nsbh.setUnit(new Unit((Integer)planForm.get("unitId")));
				
				Integer nsbhId = nService.insertNasabah(nsbh);
				nsbh.setId(nsbhId);
				nsbh.setCreatedBy(userLogin.getUser().getUserCode());
				nsbh.setDeleted(0);
				nService.insertNasabahHistory(nsbh);

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
			prepareData(request, nsbh.getCabang().getId());
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
		forward = hasMenuAccess(mapping, request, MENU_NSB_UPD);
		if (forward != null)
			return forward;

		try {
			
			Integer nsbhId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			Nasabah nsbh = nService.getNasabah(nsbhId);
			BeanUtils.copyProperties(planForm, nsbh);
			prepareData(request, nsbh.getCabang().getId());
			planForm.set("strTglMasuk", sdf.format(nsbh.getTglMasuk()));
			planForm.set("strTglPayroll", sdf.format(nsbh.getTglPayroll()));
			planForm.set("jnsKelamin", nsbh.getJenisKelamin().getId());
			planForm.set("stsSipil", nsbh.getStatusSipil().getId());
			planForm.set("perusahaan", nsbh.getPt().getId());
			planForm.set("stsKrywn", nsbh.getStatusKaryawan().getId());
			planForm.set("bankId", nsbh.getBank().getId());
			planForm.set("stsKerja", nsbh.getStatusKerja().getId());
			planForm.set("jnsAnggota", nsbh.getJenisAnggota().getId());
			planForm.set("cabangId", nsbh.getCabang().getId());
			planForm.set("unitId", nsbh.getUnit().getId());
			
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
		forward = hasMenuAccess(mapping, request, MENU_NSB_UPD);
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
			
			if (isTokenValid(request)) {
				saveToken(request);
				Nasabah nsbh = new Nasabah();
				BeanUtils.copyProperties(nsbh, planForm);
				nsbh.setJenisKelamin(new StatusPK((Integer)planForm.get("jnsKelamin")));
				nsbh.setStatusSipil(new StatusPK((Integer)planForm.get("stsSipil")));
				nsbh.setPt(new Perusahaan((Integer)planForm.get("perusahaan")));
				nsbh.setStatusKaryawan(new StatusPK((Integer)planForm.get("stsKrywn")));
				nsbh.setBank(new Bank((Integer)planForm.get("bankId")));
				nsbh.setTglMasuk(sdf.parse(planForm.getString("strTglMasuk")));
				nsbh.setTglPayroll(sdf.parse(planForm.getString("strTglPayroll")));
				nsbh.setStatusKerja(new StatusPK((Integer)planForm.get("stsKerja")));
				nsbh.setJenisAnggota(new StatusPK((Integer)planForm.get("jnsAnggota")));
				nsbh.setAnAgent(planForm.get("anAgent") != null ? true : false);
				nsbh.setCabang(new Cabang((Integer)planForm.get("cabangId")));
				nsbh.setUnit(new Unit((Integer)planForm.get("unitId")));
				
				nService.updateNasabah(nsbh);
				
				nsbh.setCreatedBy(userLogin.getUser().getUserCode());
				nsbh.setDeleted(0);
				nService.insertNasabahHistory(nsbh);
				prepareSearch(request);

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
	
	public ActionForward history(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("History");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
//		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_NSB_UPD);
		if (forward != null)
			return forward;

		try {
			prepareData(request,null);
			Integer nsbhId = request.getParameter("id").equals("") ? 0
					: Integer.parseInt(request.getParameter("id"));
			if (errors.size() > 0) {
				saveErrors(request, errors);
				return mapping.findForward("continue");
			}

			List<Nasabah> nsbh = nService.getNasabahVersion(nsbhId);
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

	public ActionForward delete(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {

		log.debug("Delete");
		
		// Check Menu Access

		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_NSB_DEL);
		if (forward != null)
			return forward;

		ActionMessages errors = new ActionMessages();
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
			Nasabah nsbh = nService.getNasabah(id);
			nsbh.setDeleted(1);
			nsbh.setLastUpdBy(userLogin.getUser().getUserCode());
			nsbh.setLastUpdProcess("delete");
			nsbh.setCreatedBy(userLogin.getUser().getUserCode());
			int affectedRow = nService.deleteNasabah(nsbh);
			if (affectedRow == 0) {
				errors.add(Constant.GLOBALERROR,
					new ActionMessage("error.deleteFail", getMessage(request, "error.noRowUpdated")));
			} else {
				nService.insertNasabahHistory(nsbh);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(
				Constant.GLOBALERROR,
				new ActionMessage("error.exception", e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
		}
		prepareSearch(request);

		// Return to Search
		return mapping.findForward("success");
	}
	
}