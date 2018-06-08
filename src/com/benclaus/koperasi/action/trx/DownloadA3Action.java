package com.benclaus.koperasi.action.trx;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.benclaus.koperasi.action.SecurityAction;
import com.benclaus.koperasi.dao.trx.A3Service;
import com.benclaus.koperasi.dao.trx.ConfigService;
import com.benclaus.koperasi.dao.trx.DataService;
import com.benclaus.koperasi.model.A3;
import com.benclaus.koperasi.model.BookEnum;
import com.benclaus.koperasi.model.Config;
import com.benclaus.koperasi.model.usm.Login;
import com.benclaus.koperasi.utility.Constant;
import com.benclaus.koperasi.utility.DAFContainer;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.benclaus.koperasi.utility.YearHelper;

/**
 * @author Lambok
 * 
 */
public final class DownloadA3Action extends SecurityAction {
	private static Logger log = Logger.getLogger(DownloadA3Action.class);
	private String MENU_COMP_VIEW = "cfg_comp_view";

	private DataService service = DataService.getInstance();
	private ConfigService cfgService = ConfigService.getInstance();
	private A3Service a3Service = A3Service.getInstance();

	private void prepareSearch(HttpServletRequest request) {
		
		try {
			request.setAttribute("bookList", cfgService.getBookList());
			request.setAttribute("companyList", cfgService.getCompanyList());
			request.setAttribute("bookItemList", cfgService.getBookItemList());
			request.setAttribute("spmList", cfgService.getSPMList());
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
		HttpSession session = request.getSession();
		DynaActionForm a3Form = (DynaActionForm) form;
		
		List<LabelValueBean> years = service.getDataYearList();
		try {
			Calendar cal = GregorianCalendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			a3Form.set("month", month);
			a3Form.set("currentYear", cal.get(Calendar.YEAR));
			
			LabelValueBean first = (LabelValueBean) years.get(0);
			LabelValueBean second = (LabelValueBean) years.get(years.size()-1);
			
			a3Form.set("fromYear", Integer.parseInt(first.getLabel()));
			a3Form.set("toYear", Integer.parseInt(second.getLabel()));
			
			
		} catch (Exception e){}
		/*int month = (Calendar.getInstance().get(Calendar.MONTH))+1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		absForm.set("month", month<10 ? "0"+month : ""+month);
		absForm.set("year", ""+year);*/

		forward = hasMenuAccess(mapping, request, MENU_COMP_VIEW);
		if (forward != null) {
			return forward;
		}
		prepareSearch(request);
		
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
		if (session.getAttribute(MENU_COMP_VIEW) != null) {
			DAFContainer dafProxy = (DAFContainer) session
					.getAttribute(MENU_COMP_VIEW);
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
		forward = hasMenuAccess(mapping, request, MENU_COMP_VIEW);
		if (forward != null)
			return forward;

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DynaActionForm a3Form = (DynaActionForm) form;
		// Revalidate form if needed

		try {
			// Prepare
			prepareSearch(request);

			// Set owner
			Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
			Integer month = (Integer) a3Form.get("month");
			Integer currentYear = (Integer) a3Form.get("currentYear");
			Integer fromYear = a3Form.get("fromYear") != null ? (Integer) a3Form.get("fromYear") : 0  ;
			Integer toYear = a3Form.get("toYear") != null ? (Integer) a3Form.get("toYear") : 0;
			Integer prevYear = currentYear - 1;
			
			//log.debug(currentYear +", " + fromYear + ", " + toYear);
			
			Map<String, Object> param = new HashMap<>();
			param.put("year", currentYear);
			param.put("month", month);
			List<A3> ytdCurrentYear = a3Service.getYtdDataByYear(param);
			
			param.put("year", prevYear);
			List<A3> ytdPrevYear = a3Service.getYtdDataByYear(param);
			
			errors = process(response, ytdPrevYear, ytdCurrentYear, currentYear, month, fromYear, toYear);
			if (errors.size() > 0) {
				saveErrors(request, errors);
			}

			
			// Search
			/*Map<String, Object> map = new HashMap<>();
			map.put("user", compForm.get("user"));
			map.put("name", compForm.get("name"));*/
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			errors.add(Constant.GLOBALERROR, new ActionError("error.exception",
					e.getMessage()));
		}


		return mapping.findForward("success");
	}
	
	
	public ActionErrors process(HttpServletResponse response, List<A3> ytdPrevYear, List<A3> ytdCurrentYear, Integer currentYear, Integer month, Integer fromYear, Integer toYear) {
		Config cfg = cfgService.getRorConfig();
		File[] files = null;
		String fileType="";
		
		ActionErrors errors = new ActionErrors();
		
		FileInputStream inputStream = null;
		Workbook wb = null;
		FormulaEvaluator evaluator = null;
		Sheet ytdPLSheet = null;
		Sheet ytdBSSheet = null;
		Sheet ytdCFSheet = null;
		Sheet myPLSheet = null;
		Sheet myBSSheet = null;
		Sheet myCFSheet = null;
		
		CellReference cellReference = null;
		String[] filenames = null;
		int index =0;
        Row row = null;
        Cell cell = null;
        
		try {
			File dir = new File(cfg.getA3TemplateDir());
			files = dir.listFiles();
			filenames = new String[files.length];
			if (files != null && files.length == 1) {
				for (File file : files) {
					filenames [index] = file.getName();
					fileType = Files.probeContentType(file.toPath());
					
					if (fileType.equals("application/vnd.ms-excel") || fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
						try {
							inputStream = new FileInputStream(file);
							wb = WorkbookFactory.create(inputStream);
							evaluator = wb.getCreationHelper().createFormulaEvaluator();
							ytdPLSheet = wb.getSheetAt(0);
							ytdBSSheet = wb.getSheetAt(1);
							ytdCFSheet = wb.getSheetAt(2);
							myPLSheet = wb.getSheetAt(3);
							myBSSheet = wb.getSheetAt(4);
							myCFSheet = wb.getSheetAt(5);
							if (ytdPrevYear != null ) {
								for (A3 a3 : ytdPrevYear) { //YTD previous year
									if (a3.getMapping().getCellYtdPrevActual()!=null &&!a3.getMapping().getCellYtdPrevActual().trim().equals(""))
										cellReference = new CellReference(a3.getMapping().getCellYtdPrevActual());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = ytdPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = ytdBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = ytdCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										}
								}
							}
							if (ytdCurrentYear != null ) {
								for (A3 a3 : ytdCurrentYear) { //YTD Current Year Actual
									if (a3.getMapping().getCellYtdCurrentActual() != null && !a3.getMapping().getCellYtdCurrentActual().trim().equals("")) {
										cellReference = new CellReference(a3.getMapping().getCellYtdCurrentActual());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = ytdPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = ytdBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = ytdCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountActual());
										}
									} //YTD Current Year Plan
									if (a3.getMapping().getCellYtdCurrentPlan() != null && !a3.getMapping().getCellYtdCurrentPlan().trim().equals("")) {
										cellReference = new CellReference(a3.getMapping().getCellYtdCurrentPlan());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = ytdPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountPlan());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = ytdBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountPlan());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = ytdCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol());
											if (cell == null) 
												cell = row.createCell(cellReference.getCol());
											cell.setCellValue(a3.getAmountPlan());
										}
									}
								}
							}
							
							List<A3> prevYearFYActual = null;
							int colAdder =0;
							
							for (int j= fromYear; j<currentYear; j++) { // Multi Year Previous Years
								prevYearFYActual = a3Service.getFullYearPrevYearActual(j);
								if (prevYearFYActual != null) {
									for (A3 a3 : prevYearFYActual) {
										if (a3.getMapping().getCellFy() != null && !a3.getMapping().getCellFy().trim().equals("")) {
											cellReference = new CellReference(a3.getMapping().getCellFy());
											log.debug(a3.getMapping().getBookItem().getName() + "=" +a3.getMapping().getCellFy());
											if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
												row = myPLSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(a3.getAmountActual());
											} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
												row = myBSSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(a3.getAmountActual());
											} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
												row = myCFSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(a3.getAmountActual());
											}
											
										}
									}
									colAdder++;
								} else {
									colAdder++;
								}
							}
							List<A3> currentYearFYActual = null;
							
							// Multi Year Current Years
							Map<String, Object> param = new HashMap<>();
							param.put("year", currentYear);
							param.put("month", month);	
							currentYearFYActual = a3Service.getFullYearCurrentYearActual(param);
							if (currentYearFYActual != null) {
								for (A3 a3 : currentYearFYActual) {
									if (a3.getMapping().getCellFy() != null && !a3.getMapping().getCellFy().trim().equals("")) {
										cellReference = new CellReference(a3.getMapping().getCellFy());
										log.debug(a3.getMapping().getBookItem().getName() + "=" +a3.getMapping().getCellFy());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = myPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountActual());
											
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = myBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountActual());
											
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = myCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountActual());
										}
										
									}
								}
								colAdder++;
								for (A3 a3 : currentYearFYActual) {
									if (a3.getMapping().getCellFy() != null && !a3.getMapping().getCellFy().trim().equals("")) {
										cellReference = new CellReference(a3.getMapping().getCellFy());
										log.debug(a3.getMapping().getBookItem().getName() + "=" +a3.getMapping().getCellFy());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = myPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountPlan());
											
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = myBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountPlan());
											
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = myCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountPlan());
										}
										
									}
								}
								colAdder++;
								for (A3 a3 : currentYearFYActual) {
									if (a3.getMapping().getCellFy() != null && !a3.getMapping().getCellFy().trim().equals("")) {
										cellReference = new CellReference(a3.getMapping().getCellFy());
										log.debug(a3.getMapping().getBookItem().getName() + "=" +a3.getMapping().getCellFy());
										if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
											row = myPLSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountForecast());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
											row = myBSSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountForecast());
										} else if (a3.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
											row = myCFSheet.getRow(cellReference.getRow());
											cell = row.getCell(cellReference.getCol() + colAdder);
											if (cell == null) 
												cell = row.createCell(cellReference.getCol() + colAdder);
											log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
											cell.setCellValue(a3.getAmountForecast());
										}
										
									}
								}
								colAdder++;
							} else {
								colAdder +=3;
							}
							
							List<A3> nextYearFYForecast= null;
							for (int j= currentYear+1; j<=toYear; j++) { // Multi Year Next Years
								nextYearFYForecast = a3Service.getFullYearForecast(j);
								if (nextYearFYForecast != null) {
									for (A3 fc : nextYearFYForecast) {
										if (fc.getMapping().getCellFy() != null && !fc.getMapping().getCellFy().trim().equals("")) {
											cellReference = new CellReference(fc.getMapping().getCellFy());
											log.debug(fc.getMapping().getBookItem().getName() + "=" +fc.getMapping().getCellFy());
											if (fc.getMapping().getBookItem().getBook().getId() == BookEnum.PROFIT_LOSS.getCode()) {
												row = myPLSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(fc.getAmountForecast());
											} else if (fc.getMapping().getBookItem().getBook().getId() == BookEnum.BALANCE_SHEET.getCode()) {
												row = myBSSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(fc.getAmountForecast());
											} else if (fc.getMapping().getBookItem().getBook().getId() == BookEnum.CASH_FLOW.getCode()) {
												row = myCFSheet.getRow(cellReference.getRow());
												cell = row.getCell(cellReference.getCol() + colAdder);
												if (cell == null) 
													cell = row.createCell(cellReference.getCol() + colAdder);
												log.debug("cell: " +CellReference.convertNumToColString(cell.getColumnIndex()) + cell.getRow().getRowNum());
												cell.setCellValue(fc.getAmountForecast());
											}
										}
									}
									colAdder++;
								} else {
									colAdder++;
								}
								
							}
							
							
							evaluator.evaluateAll();
							
							response.setContentType("application/vnd.ms-excel");
							response.setHeader("Content-Disposition", "attachment; filename=A3.xlsx");
							OutputStream fileOut = response.getOutputStream();
							wb.write(fileOut);
							fileOut.flush();
							fileOut.close();
							inputStream.close();  
						} catch (Exception e) {
							e.printStackTrace();
							errors.add(
									Constant.GLOBALERROR,
									new ActionError("error.exception", e.getMessage()));
						}
						
					} else {
						errors.add(
								Constant.GLOBALERROR,
								new ActionError("error.exception", "The file: " + Arrays.toString(filenames) +" you specify as template is not an Excel file."));
					}
					
				}
			} else {
				for (File file : files) {
					filenames [index] = file.getName();
					index++;
				}
				errors.add(
						Constant.GLOBALERROR,
						new ActionError("error.exception", "Template folder contain more than one file: "+ Arrays.toString(filenames)+", please specify only one file as a template.\nMake sure you close the Excel template file. "));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}
}