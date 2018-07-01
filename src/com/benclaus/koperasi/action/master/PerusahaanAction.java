package com.benclaus.koperasi.action.master;

import java.io.PrintWriter;
import java.util.List;

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
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.kantor.KantorService;
import com.benclaus.koperasi.dao.master.PerusahaanService;
import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Unit;
import com.benclaus.koperasi.model.master.Industri;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.ibatis.common.util.PaginatedList;

public class PerusahaanAction extends SecurityAction {
	private static Logger log = Logger.getLogger(PerusahaanAction.class);
	private String MENU_PRSHN_VIEW = "MST_PRSHN_search";
	private String MENU_PRSHN_ADD= "MST_PRSHN_add";
	private String MENU_PRSHN_UPD = "MST_PRSHN_upd";
	private String MENU_PRSHN_DEL= "MST_PRSHN_del";

	private PerusahaanService service = PerusahaanService.getInstance();
	private KantorService kService = KantorService.getInstance();

	private void prepareData(HttpServletRequest request) {

		try {
			request.setAttribute("IndustriList", service.getIndustries());
			request.setAttribute("CabangList", kService.getCabang());
			request.setAttribute("UnitList", kService.getUnit());
		} catch (Exception e) {
		}
	}
	
	private void prepareData(HttpServletRequest request, Integer cabangId) {

		try {
			request.setAttribute("IndustriList", service.getIndustries());
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
		StringBuilder sb = new StringBuilder("<select name=\"unit\"></select>");
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

		forward = hasMenuAccess(mapping, request, MENU_PRSHN_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareData(request);
		session.removeAttribute(MENU_PRSHN_VIEW);

		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}

	public ActionForward returned(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Returned");

		HttpSession session = request.getSession();
		// Replace new form with old form
		if (session.getAttribute(MENU_PRSHN_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_PRSHN_VIEW);
			dafProxy.populate((DynaActionForm) form);
		}

		return search(mapping, form, request, response);
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Search");

		// Check menu access
		ActionForward forward = new ActionForward();
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_VIEW);
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
			PaginatedList mapList = (PaginatedList) service.searchPerusahaan(mapForm.getMap());
			int totalSize = service.searchPerusahaanSize(mapForm.getMap());
			int page = Integer.parseInt((String) mapForm.get("pageIndex"));

			if (page * mapList.getPageSize() > totalSize)
				page = ((totalSize - 1) / mapList.getPageSize()) + 1;
			if (page * mapList.getPageSize() < 1)
				page = 1;

			mapList.gotoPage(page - 1);

			request.setAttribute("DataList", new Page(mapList, totalSize));

			DAFContainer sessionForm = new DAFContainer(mapForm.getMap());
			session.setAttribute(MENU_PRSHN_VIEW, sessionForm);
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

		if (session.getAttribute(MENU_PRSHN_VIEW) != null) {
			Object pageIndex = newForm.get("pageIndex");

			DAFContainer dafProxy = (DAFContainer) session.getAttribute(MENU_PRSHN_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_ADD);
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
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_ADD);
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
				Perusahaan prshn = new Perusahaan();
				prshn.setId((Integer)planForm.get("id"));
				prshn.setAlamat(planForm.getString("alamat"));
				prshn.setNama(planForm.getString("nama"));
				prshn.setCabang(new Cabang((Integer)planForm.get("cabang")));
				prshn.setUnit(new Unit((Integer)planForm.get("unit")));
				prshn.setIndustri(new Industri((Integer)planForm.get("industri")));
				Integer nsbhId = service.insertPerusahaan(prshn);
				
				prshn.setId(nsbhId);
				prshn.setCreatedBy(userLogin.getUser().getUserCode());
				prshn.setDeleted(0);
				service.insertPerusahaanHistory(prshn);

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

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("Update");

		ActionMessages errors = new ActionMessages();
		// Check Menu Access
//		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		DynaActionForm planForm = (DynaActionForm) form;
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_UPD);
		if (forward != null)
			return forward;

		try {
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			Perusahaan prshn = service.getPerusahaan(id);
			prepareData(request, prshn.getCabang().getId());
			BeanUtils.copyProperties(planForm, prshn);
			planForm.set("cabang", prshn.getCabang().getId());
			planForm.set("unit", prshn.getUnit().getId());
			planForm.set("industri", prshn.getIndustri().getId());

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
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_UPD);
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
				Perusahaan prshn = new Perusahaan();
				prshn.setId((Integer)planForm.get("id"));
				prshn.setAlamat(planForm.getString("alamat"));
				prshn.setNama(planForm.getString("nama"));
				prshn.setCabang(new Cabang((Integer)planForm.get("cabang")));
				prshn.setUnit(new Unit((Integer)planForm.get("unit")));
				prshn.setIndustri(new Industri((Integer)planForm.get("industri")));
				service.updatePerusahaan(prshn);
				
				prshn.setCreatedBy(userLogin.getUser().getUserCode());
				prshn.setDeleted(0);
				service.insertPerusahaanHistory(prshn);

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
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_DEL);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm compForm = (DynaActionForm) form;

		Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
		if (userLogin == null) {
			errors.add(
				Constant.GLOBALERROR,
				new ActionError("error.invalidLogin"));
		}

		try {
			// do delete
			//int affectedRow = companyService.deleteCompany(companyForm.getMap(), userLogin.getUser(), "delete");
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			int affectedRow = service.deletePerusahaan(id);
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
		prepareData(request);

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
		forward = hasMenuAccess(mapping, request, MENU_PRSHN_VIEW);
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

			List<Perusahaan> nsbh = service.getPerusahaanHistory(nsbhId);
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