package com.benclaus.koperasi.dao.app;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.Book;
import com.benclaus.koperasi.model.BookItem;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Forecast;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;


public class DataDao extends BaseDAO {
	private static Logger log = Logger.getLogger(DataDao.class);
	private static volatile DataDao instance;

	public static DataDao getInstance() {
        final DataDao currentInstance;
        if (instance == null) {
            synchronized (DataDao.class) {
                if (instance == null) {
                    instance = new DataDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private DataDao() { super(); }
	
	public List<LabelValueBean> getDataYearList()  throws DaoException {
		log.info("DataSQL.getDataYearList");
		return (List<LabelValueBean>)super.getList("DataSQL.getDataYearList", null);
	}
	
	public PaginatedList searchPlan(Map map) throws DaoException {
		log.info("DataSQL.searchPlan");
		return (PaginatedList) super.getPaginatedList("DataSQL.searchPlan", map);
	}
	public Integer searchPlanSize(Map map) throws DaoException {
		log.info("DataSQL.searchPlanSize");
		return (Integer)super.getObject("DataSQL.searchPlanSize", map);
	}
	
	public List<Data> getPlan(Map map) throws DaoException {
		log.info("DataSQL.searchPlan");
		return (List<Data>) super.getList("DataSQL.searchPlan", map);
	}
	public Data getPlan(Integer id) throws DaoException {
		log.info("DataSQL.getPlan");
		return (Data)super.getObject("DataSQL.getPlan", id);
	}
	public Integer updatePlan(Data data)  throws DaoException {
		log.info("DataSQL.updatePlan");
		return super.update("DataSQL.updatePlan", data);
	}
	public Integer insertPlan(Map data)  throws DaoException {
		log.info("DataSQL.insertPlan");
		return super.update("DataSQL.insertPlan", data);
	}
	public List<Data> getNewInsertedPlan(Map map) throws DaoException {
		log.info("DataSQL.getNewInsertedPlan");
		return (List<Data>)super.getList("DataSQL.getNewInsertedPlan", map);
	}
	
	
	
	public List<Integer> getForecastYearList()  throws DaoException {
		log.info("DataSQL.getForecastYearList");
		return (List<Integer>)super.getList("DataSQL.getForecastYearList", null);
	}
	
	public PaginatedList searchForecast(Map map) throws DaoException {
		log.info("DataSQL.searchForecast");
		return (PaginatedList) super.getPaginatedList("DataSQL.searchForecast", map);
	}
	public Integer searchForecastSize(Map map) throws DaoException {
		log.info("DataSQL.searchForecastSize");
		return (Integer)super.getObject("DataSQL.searchForecastSize", map);
	}
	
	public List<Forecast> getForecast(Map map) throws DaoException {
		log.info("DataSQL.searchForecast");
		return (List<Forecast>) super.getList("DataSQL.searchForecast", map);
	}
	public Forecast getForecast(Integer id) throws DaoException {
		log.info("DataSQL.getForecast");
		return (Forecast)super.getObject("DataSQL.getForecast", id);
	}
	public Integer updateForecast(Forecast data)  throws DaoException {
		log.info("DataSQL.updateForecast");
		return super.update("DataSQL.updateForecast", data);
	}
	public Integer insertForecast(Map data)  throws DaoException {
		log.info("DataSQL.insertForecast");
		return super.update("DataSQL.insertForecast", data);
	}
	public List<Forecast> getNewInsertedForecast(Map map) throws DaoException {
		log.info("DataSQL.getNewInsertedForecast");
		return (List<Forecast>)super.getList("DataSQL.getNewInsertedForecast", map);
	}
	
	
	public PaginatedList searchActual(Map map) {
		log.info("DataSQL.searchActual");
		return (PaginatedList) super.getPaginatedList("DataSQL.searchActual", map);
	}

	public Integer searchActualSize(Map map) {
		log.info("DataSQL.searchPlanSize");
		return (Integer)super.getObject("DataSQL.searchActualSize", map);
	}
	
	public List<Data> getActual(Map map) throws DaoException {
		log.info("DataSQL.searchActual");
		return (List<Data>) super.getList("DataSQL.searchActual", map);
	}
	/*
	//Insert Employee Start
	public void insertEmployee(Employee emp) throws DaoException {
		log.info("DataSQL.getPillars");
		super.update("AbsensiSQL.insertEmployee", emp);
	}
	public void insertQuestSync(Employee emp) throws DaoException {
		log.info("AbsensiSQL.insertQuestSync");
		super.update("AbsensiSQL.insertQuestSync", emp);
	}
	public void insertMachineSync(Employee emp) throws DaoException {
		log.info("AbsensiSQL.insertMachineSync");
		super.update("AbsensiSQL.insertMachineSync", emp);
	}
	
	public List<Employee> getQuestEmployeeNotInMachine() throws DaoException {
		log.info("AbsensiSQL.selectQuestEmployeeNotInMachine");
		return (List<Employee>)super.getList("AbsensiSQL.selectQuestEmployeeNotInMachine", null);
	}
	
	public void truncateTableSyncEmpData() throws DaoException {
		log.info("AbsensiSQL.deleteSyncEmpData");
		super.update("AbsensiSQL.deleteSyncEmpMachineData",null);
		super.update("AbsensiSQL.deleteSyncEmpQuestData",null);
	}
	public void deleteEmployeeByCompId(Integer compId) throws DaoException {
		log.info("AbsensiSQL.deleteEmployeeByCompId");
		super.update("AbsensiSQL.deleteEmployeeByCompId",compId);
	}
	
	//Insert Employee End
	
	//Config Start
	public WsUrl getWsUrl(Integer company) throws DaoException {
		log.info("ReportSQL.getWsUrl");
		return (WsUrl)super.getObject("AbsensiSQL.getWsUrl", company);
	}
	public List<String> getSsoUrl() throws DaoException {
		log.info("ReportSQL.getSsoUrl");
		return (List<String>)super.getList("AbsensiSQL.getSsoUrl", null);
	}
	
	
	//Config End
	
	
	//Holiday Start
	public void insertHoliday(Holiday hol) throws DaoException {
		log.info("AbsensiSQL.insertHoliday");
		super.update("AbsensiSQL.insertHoliday", hol);
	}
	
	public void deleteHolidayByCompId(Holiday hol) throws DaoException {
		log.info("AbsensiSQL.deleteHolidayByCompId");
		super.update("AbsensiSQL.deleteHolidayByCompId",hol);
	}
	
	public List<Holiday> getHolidayByCompany(Holiday hol) throws DaoException {
		log.info("AbsensiSQL.getHolidayByCompany");
		return (List<Holiday>) super.getList("AbsensiSQL.getHolidayByCompany", hol);
	}
	
	public Holiday getHolidayByCompanyAndDate(Holiday hol) throws DaoException {
		log.info("AbsensiSQL.getHolidayByCompanyAndDate");
		return (Holiday) super.getObject("AbsensiSQL.getHolidayByCompanyAndDate", hol);
	}
	public List<Holiday> getHolidayByCompanyAndMonth(Map map) throws DaoException {
		log.info("AbsensiSQL.getHolidayByCompanyAndMonth");
		return (List<Holiday>) super.getList("AbsensiSQL.getHolidayByCompanyAndMonth", map);
	}
	
	//Holiday End
	
	//Notification Start
	
	public void insertNotification(Notification notf) throws DaoException {
		log.info("AbsensiSQL.insertNotification");
		super.update("AbsensiSQL.insertNotification", notf);
	}
	
	public List<Notification> getAvailableNotification(Map map) throws DaoException {
		log.info("AbsensiSQL.getAvailableNotification");
		return (List<Notification>) super.getList("AbsensiSQL.getAvailableNotification", map);
	}
	
	public List<Notification> getExpiredNotification(Integer company) throws DaoException {
		log.info("AbsensiSQL.getExpiredNotification");
		return (List<Notification>) super.getList("AbsensiSQL.getExpiredNotification", company);
	}
	
	public PaginatedList getPaginatedAvailableNotification(Map map) throws DaoException {
		log.info("AbsensiSQL.getAvailableNotification");
		return (PaginatedList)super.getPaginatedList("AbsensiSQL.getAvailableNotification", map);
	}
	
	public void insertNotificationTime(NotificationTime notf) throws DaoException {
		log.info("AbsensiSQL.insertNotificationTime");
		super.update("AbsensiSQL.insertNotificationTime", notf);
	}
	
	public List<NotificationTime> getAvailableNotificationTime(Integer notfId) throws DaoException {
		log.info("AbsensiSQL.getAvailableNotificationTime");
		return (List<NotificationTime>) super.getList("AbsensiSQL.getAvailableNotificationTime", notfId);
	}
	
	public void updateNotification(Notification notf) throws DaoException {
		log.info("AbsensiSQL.updateNotification");
		super.update("AbsensiSQL.updateNotification", notf);
	}
	
	public List<String> getGAandSuperiorEmail(Map map) throws DaoException {
		log.info("AbsensiSQL.getGAandSuperiorEmail");
		return (List<String>) super.getList("AbsensiSQL.getGAandSuperiorEmail", map);
	}
	
	public List<String> getGAEmail(Map map) throws DaoException {
		log.info("AbsensiSQL.getGAandSuperiorEmail");
		return (List<String>) super.getList("AbsensiSQL.getGAEmail", map);
	}
	
	
	//Notification End
	
	public SmtpConfig getSmtpConfig() {
		log.info("AbsensiSQL.getSmtpConfig");
		return (SmtpConfig) super.getObject("AbsensiSQL.getSmtpConfig", null);
	}

	public EmployeeAbsensi getAbsensi(String empId, Date tgl)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertAbsensiRaw(EmployeeAbsensi absensi) throws DaoException {
		log.info("AbsensiSQL.insertAbsensiRaw");
		super.update("AbsensiSQL.insertAbsensiRaw", absensi);
	}
	
	public void updateEmployeeData(Integer month, Integer year) throws DaoException {
		log.info("AbsensiSQL.selectAndInsertNewEmployee");
		Map param = new HashMap();
		param.put("month", month);
		param.put("year", year);
		super.update("AbsensiSQL.selectAndInsertNewEmployee", param);
	}
	
	 CALCULATE related tasks 
	public List getYearList() throws DaoException {
		log.info("AbsensiSQL.getYearList");
		return super.getList("AbsensiSQL.getYearList", null);
	}
	public List getEmployeeList(Map map) throws DaoException {
		log.info("AbsensiSQL.getEmployeeList");
		return super.getList("AbsensiSQL.getEmployeeList", map);
	}
	
	public List<Employee> getAllEmployeeByCompany(Integer company) throws DaoException {
		log.info("AbsensiSQL.getAllEmployeeByCompany");
		return (List<Employee>) super.getList("AbsensiSQL.getAllEmployeeByCompany", company);
	}
	public Employee getEmployee(Map map) throws DaoException {
		log.info("AbsensiSQL.getEmployee");
		return (Employee) super.getObject("AbsensiSQL.getEmployee", map);
	}
	
	public void deleteAbsensiByMonth(Map param) throws DaoException {
		log.info("AbsensiSQL.deleteAbsensiByMonth");
		super.update("AbsensiSQL.deleteAbsensiByMonth", param);
	}
	public void deleteAbsensiByDate(Map param) throws DaoException {
		log.info("AbsensiSQL.deleteAbsensiByDate");
		super.update("AbsensiSQL.deleteAbsensiByDate", param);
	}
	
	public List getDistinctRawAbsen(Map param) throws DaoException {
		log.info("AbsensiSQL.getDistinctRawAbsen");
		return super.getList("AbsensiSQL.getDistinctRawAbsen", param);
	}
	public List getDistinctRawAbsenByDate(Map param) throws DaoException {
		log.info("AbsensiSQL.getDistinctRawAbsenByDate");
		return super.getList("AbsensiSQL.getDistinctRawAbsenByDate", param);
	}
	public Integer getRawAbsenCountByEmpId(AbsensiFilter filter) throws DaoException {
		log.info("AbsensiSQL.getRawAbsenCountByEmpId");
		return (Integer) super.getObject("AbsensiSQL.getRawAbsenCountByEmpId", filter);
	}
	public EmployeeAbsensi getAbsenByEmpIdInOnly(AbsensiFilter filter) throws DaoException {
		log.info("AbsensiSQL.getOneAbsenByEmpId");
		return (EmployeeAbsensi) super.getObject("AbsensiSQL.getOneAbsenByEmpId", filter);
	}
	public EmployeeAbsensi getAbsenByEmpId(AbsensiFilter filter) throws DaoException {
		log.info("AbsensiSQL.getAbsenByEmpId");
		return (EmployeeAbsensi) super.getObject("AbsensiSQL.getAbsenByEmpId", filter);
	}
	
	public void insertAbsensi(EmployeeAbsensi absensi) throws DaoException {
		log.info("AbsensiSQL.insertAbsensi");
		super.update("AbsensiSQL.insertEmployeeAbsensi", absensi);
	}
	public void insertEmployeeLeave(EmployeeAbsensi absensi) throws DaoException {
		log.info("AbsensiSQL.insertEmployeeLeave");
		super.update("AbsensiSQL.insertEmployeeLeave", absensi);
	}
	
	
	
	 * REPORT related daos
	 
	
	public List<LabelValueBean> getDeptList(Integer company) throws DaoException {
		return (List<LabelValueBean>) super.getList("AbsensiSQL.getDeptList", company);
	}
	
	public PaginatedList getSkippedEmployee(Map company) throws DaoException {
		return (PaginatedList) super.getPaginatedList("AbsensiSQL.getSkippedEmployee", company);
	}
	
	public Integer getSkippedEmployeeCount(Map company) throws DaoException {
		log.info("AbsensiSQL.getReportListByMonthCount");
		return (Integer)super.getObject("AbsensiSQL.getSkippedEmployeeCount", company);
	}
	
	public int updateSkipEmployee(Employee emp) throws DaoException {
    	log.info("AbsensiSQL.updateSkipEmployee");
		return super.update("AbsensiSQL.updateSkipEmployee", emp);
    }
	
	public SkipEmpUtil getSkipEmpUtil(Integer company) throws DaoException {
		log.info("AbsensiSQL.getSkipEmpUtil");
		return (SkipEmpUtil) super.getObject("AbsensiSQL.getSkipEmpUtil", company);
	}
	
	public AbsenReport getAttendanceReport(Integer company, Integer empId, Integer month, Integer year) throws DaoException {
		Map param = new HashMap();
		param.put("company", company);
		param.put("empId", empId);
		param.put("month", month);
		param.put("year", year);
		return (AbsenReport) super.getObject("ReportSQL.getReportListByMonth", param);
	}
	public AbsenSummary getSummaryReport(Integer year) throws DaoException {
		return (AbsenSummary) super.getObject("ReportSQL.getSummaryReportList", year);
	}
	public PaginatedList getReportListByMonth(Map map) throws DaoException {
		log.info("ReportSQL.getReportListByMonth");
		return (PaginatedList)super.getPaginatedList("ReportSQL.getReportListByMonth", map);
	}
	public PaginatedList getSummaryList(Map map) throws DaoException {
		log.info("ReportSQL.getSummaryList");
		return (PaginatedList)super.getPaginatedList("ReportSQL.getSummaryReportList", map);
	}
	public Integer getSummaryListSize(Map map) throws DaoException {
		log.info("ReportSQL.getSummaryListSize");
		return (Integer)super.getObject("ReportSQL.getSummaryReportListSize", map);
	}
	public Integer getReportListByMonthCount(Map map) throws DaoException {
		log.info("ReportSQL.getReportListByMonthCount");
		return (Integer)super.getObject("ReportSQL.getReportListByMonthCount", map);
	}
	public void deleteAbsensiReportByMonth(Map param) throws DaoException {
		log.info("ReportSQL.deleteAbsensiReportByMonth");
		super.update("ReportSQL.deleteAbsensiReportByMonth", param);
	}
	public List<Employee> getDistinctAbsenEmpIdList(Map param) throws DaoException {
		return (List<Employee>) super.getList("ReportSQL.getDistinctAbsenEmpId", param);
	}
	public InLateSummary getInLateOverTimeSummary (Map param) throws DaoException {
		
		return (InLateSummary) super.getObject("ReportSQL.getInLateOverTimeSummary", param);
	}
	public void insertAbsensiReport(AbsenReport absensi) throws DaoException {
		log.info("ReportSQL.insertAbsensiReport");
		super.update("ReportSQL.insertAbsensiReport", absensi);
	}
	public void insertSummaryReport(AbsenSummary summary) throws DaoException {
		log.info("ReportSQL.insertSummaryReport");
		super.update("ReportSQL.insertSummaryReport", summary);
	}
	public void deleteSummaryReport(Map param) throws DaoException {
		log.info("ReportSQL.deleteSummaryReport");
		super.update("ReportSQL.deleteSummaryReport", param);
	}
	public String getEmployeeName(String empId) throws DaoException {
		log.info("AbsensiSQL.getEmployeeName");
		return (String) super.getObject("AbsensiSQL.getEmployeeName", empId);
	}
	
	 * INPUT related
	 
	
	public List searchAbsensi(Map map) throws DaoException {
        return super.getPaginatedList("AbsensiSQL.searchAbsensi", map);
    } 
    public int searchAbsensiSize(Map map) throws DaoException {
        return ((Integer)super.getObject("AbsensiSQL.searchAbsensiSize", map)).intValue();
    }
    public int insertAbsensi(Absensi absensi) throws DaoException {
		log.info("AbsensiSQL.insertAbsensi");
		return super.update("AbsensiSQL.insertAbsensi", absensi);
	}
    public int deleteAbsensi(Absensi absensi) throws DaoException {
    	return super.delete("AbsensiSQL.deleteAbsensi", absensi);
    }
    
    
     * SAP related
     
    public EmployeeAbsensi getDailyAbsenByDate(AbsensiFilter param) throws DaoException {
		log.info("AbsensiSQL.getDailyAbsenByDate");
		return (EmployeeAbsensi) super.getObject("AbsensiSQL.getDailyAbsenByDate", param);
	}
    
    
     * 
     * ---------------
     
    public List<MesinAbsen> getMesinAbsen() throws DaoException {
    	log.info("AbsensiSQL.getMesinAbsen");
		return (List<MesinAbsen>)super.getList("AbsensiSQL.getMesinAbsen", null);
    }
    
    public PaginatedList getMesinAbsen(Map map) throws DaoException {
    	log.info("AbsensiSQL.getMesinAbsen");
		return (PaginatedList)super.getPaginatedList("AbsensiSQL.getMesinAbsen", map);
    }
    
    public int getMesinAbsenSize(Map map) throws DaoException {
    	log.info("AbsensiSQL.getMesinAbsenSize");
		return (Integer) super.getObject("AbsensiSQL.getMesinAbsenSize", map);
    }
    
    public MesinAbsen getMesinAbsenByIp(String ip) throws DaoException {
    	log.info("AbsensiSQL.getMesinAbsen");
		return (MesinAbsen) super.getObject("AbsensiSQL.getMesinAbsenByIp", ip);
    }
    
    public List<MesinAbsen> getMesinAbsenByCompany(int comp) throws DaoException {
    	log.info("AbsensiSQL.getMesinAbsenByCompany");
		return (List<MesinAbsen>) super.getList("AbsensiSQL.getMesinAbsenByCompany", comp);
    }
    
    public String getCompanyName(int comp) throws DaoException {
    	log.info("AbsensiSQL.getCompanyName");
		return (String) super.getObject("AbsensiSQL.getCompanyName", comp);
    }
    public List<Integer> getActiveCompanyCode() throws DaoException {
    	log.info("AbsensiSQL.getActiveCompanyCode");
		return (List<Integer>) super.getList("AbsensiSQL.getActiveCompanyCode", null);
    }
    
    public int insertMesinAbsen(MesinAbsen mesin) throws DaoException {
    	log.info("AbsensiSQL.insertMesinAbsen");
		return super.update("AbsensiSQL.insertMesinAbsen", mesin);
    }
    
    public int updateMesinAbsen(MesinAbsen mesin) throws DaoException {
    	log.info("AbsensiSQL.updateMesinAbsen");
		return super.update("AbsensiSQL.updateMesinAbsen", mesin);
    }
    
    public int deleteMesinAbsen(Integer id) throws DaoException {
    	log.info("AbsensiSQL.deleteMesinAbsen");
		return  super.update("AbsensiSQL.deleteMesinAbsen", id);
    }*/
}
