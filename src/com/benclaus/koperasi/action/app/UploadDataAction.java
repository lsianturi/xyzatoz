package com.benclaus.koperasi.action.app;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.struts.upload.FormFile;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.action.app.utils.ReadExcelUtil;
import com.benclaus.koperasi.dao.Page;
import com.benclaus.koperasi.dao.app.A3Service;
import com.benclaus.koperasi.dao.app.ConfigService;
import com.benclaus.koperasi.dao.app.DataService;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.FormulaArg;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.YearHelper;

/**
 * @author Lambok
 * 
 */
public final class UploadDataAction extends SecurityAction {
	private static Logger log = Logger.getLogger(UploadDataAction.class);
	private String MENU_LOG_VIEW = "data_logs_view";

	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();
	private A3Service a3Service = A3Service.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		request.setAttribute("companyList", cfgService.getCompanyList());
		request.setAttribute("yearList", service.getDataYearList());
		request.setAttribute("monthList", YearHelper.getInstance().getMonthList());
	}

	public ActionForward prepare(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("prepare");

		ActionForward forward = new ActionForward();

		forward = hasMenuAccess(mapping, request, MENU_LOG_VIEW);
		if (forward != null) {
			return forward;
		}
		
		saveToken(request);

		forward = mapping.findForward("continue");
		return forward;
	}
	
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UploadForm uplForm = (UploadForm) form;
		
		FormFile formFile = uplForm.getFile();
		
		File file = new File(formFile.getFileName());
		
		byte[] fileData = formFile.getFileData();
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		bos.write(fileData);
		bos.flush();
		bos.close();
		
		ReadExcelUtil util = new ReadExcelUtil();
		util.readExcel(file);
		
		return logs(mapping, uplForm, request, response, formFile.getFileName());
		
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

		return mapping.findForward("continue");
	}

	public ActionForward logs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, String fileName)
			throws Exception {

		log.debug("Logs");
		ActionErrors errors = new ActionErrors();
		UploadForm logForm = (UploadForm) form;

		try {
			prepareSearch(request);
			int pos = fileName.indexOf("-");
			String prefix = fileName.substring(0, pos);
			
			Integer year = Integer.parseInt("20"+ fileName.substring(pos + 1, pos + 3));
			Integer month = Integer.parseInt(fileName.substring(pos + 3,pos + 5));
			
			Company comp = cfgService.getCompanyByPrefix(prefix);
			Integer company = comp.getId();
			
			//log.debug(currentYear +", " + fromYear + ", " + toYear);
			
			Map<String, Object> param = new HashMap<>();
			param.put("company", company);
			param.put("year", year);
			param.put("month", month);
			List<FormulaArg> args = a3Service.getDataLog(param);
			
			request.setAttribute("DataList", new Page(args,
					args.size()));
			logForm.setCompanyId(company);
			logForm.setYear(year);
			logForm.setMonth(month);
			request.setAttribute("logSearch", logForm);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception",
					e.getMessage()));
		}

		if (errors.size() > 0) {
			saveErrors(request, errors);
			return mapping.findForward("invalidPage");
		}


		return mapping.findForward("success");
	}
	
}