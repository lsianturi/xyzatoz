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
import com.benclaus.koperasi.model.Config;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;


public class ConfigDao extends BaseDAO {
	private static Logger log = Logger.getLogger(ConfigDao.class);
	private static volatile ConfigDao instance;

	public static ConfigDao getInstance() {
        final ConfigDao currentInstance;
        if (instance == null) {
            synchronized (ConfigDao.class) {
                if (instance == null) {
                    instance = new ConfigDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private ConfigDao() { super(); }
	
	
	public Config getRorConfig() throws DaoException {
		log.info("ConfigSQL.getSsoUrl");
		return (Config)super.getObject("ConfigSQL.getRorConfig", null);
	}
	
	public List<String> getSsoUrl() throws DaoException {
		log.info("ConfigSQL.getSsoUrl");
		return (List<String>)super.getList("ConfigSQL.getSsoUrl", null);
	}
	
	
	public List<Pillar> getPillars() throws DaoException {
		log.info("ConfigSQL.getPillars");
		return (List<Pillar>) super.getList("ConfigSQL.getPillars", null);
	}
	public int deletePillar(Integer id) throws DaoException {
		log.info("ConfigSQL.deletePillar");
		return super.update("ConfigSQL.deletePillar",id);
	}
	public int insertPillar(Pillar pilar) throws DaoException {
		log.info("ConfigSQL.insertPillar");
		return super.update("ConfigSQL.insertPillar", pilar);
	}
	public int updatePillar(Pillar pilar) throws DaoException {
		log.info("ConfigSQL.updatePillar");
		return super.update("ConfigSQL.updatePillar", pilar);
	}
	public Pillar getPillar(Integer id) throws DaoException {
		log.info("ConfigSQL.getPillar");
		return (Pillar) super.getObject("ConfigSQL.getPillar", id);
	}

	
	public PaginatedList searchCompany(Map map) throws DaoException {
		log.info("ConfigSQL.searchCompany");
		return (PaginatedList) super.getPaginatedList("ConfigSQL.searchCompany", map);
	}
	public Integer searchCompanySize(Map map) throws DaoException {
		log.info("ConfigSQL.searchCompanySize");
		return (Integer)super.getObject("ConfigSQL.searchCompanySize", map);
	}
	public List<LabelValueBean> getSPMList() throws DaoException {
		log.info("ConfigSQL.getSPMList");
		return (List<LabelValueBean>) super.getList("ConfigSQL.getSPMList", null);
	}
	public int insertCompany(Company comp)  throws DaoException {
		log.info("ConfigSQL.insertCompany");
		return super.update("ConfigSQL.insertCompany", comp);
	}
	public int updateCompany(Company comp)  throws DaoException {
		log.info("ConfigSQL.updateCompany");
		return super.update("ConfigSQL.updateCompany", comp);
	}
	public Company getCompany(Map map) throws DaoException {
		log.info("ConfigSQL.searchCompany");
		return (Company)super.getObject("ConfigSQL.searchCompany", map);
	}
	public Company getCompanyByName(Map map) throws DaoException {
		log.info("ConfigSQL.getCompanyByName");
		return (Company)super.getObject("ConfigSQL.getCompanyByName", map);
	}
	public Company getCompanyByPrefix(String prefix) throws DaoException {
		log.info("ConfigSQL.getCompanyByPrefix");
		return (Company)super.getObject("ConfigSQL.getCompanyByPrefix", prefix);
	}
	
	public int deleteCompany(Integer id)  throws DaoException {
		log.info("ConfigSQL.deleteCompany");
		return super.update("ConfigSQL.deleteCompany", id);
	}
	
	
	public PaginatedList searchBook(Map map) throws DaoException {
		log.info("ConfigSQL.searchBook");
		return (PaginatedList) super.getPaginatedList("ConfigSQL.searchBook", map);
	}
	public Integer searchBookSize(Map map) throws DaoException {
		log.info("ConfigSQL.searchBookSize");
		return (Integer)super.getObject("ConfigSQL.searchBookSize", map);
	}
	public List<LabelValueBean> getBookList() throws DaoException {
		log.info("ConfigSQL.getBookList");
		return (List<LabelValueBean>) super.getList("ConfigSQL.getBookList", null);
	}
	public List<LabelValueBean> getCompanyList() throws DaoException {
		log.info("ConfigSQL.getCompanyList");
		return (List<LabelValueBean>) super.getList("ConfigSQL.getCompanyList", null);
	}
	public int insertBook(CompanyBook book)  throws DaoException {
		log.info("ConfigSQL.insertBook");
		return super.update("ConfigSQL.insertBook", book);
	}
	public int updateBook(CompanyBook book)  throws DaoException {
		log.info("ConfigSQL.updateBook");
		return super.update("ConfigSQL.updateBook", book);
	}
	public CompanyBook getBook(Map map) throws DaoException {
		log.info("ConfigSQL.searchBook");
		return (CompanyBook)super.getObject("ConfigSQL.searchBook", map);
	}
	public int deleteBook(Integer id)  throws DaoException {
		log.info("ConfigSQL.deleteBook");
		return super.update("ConfigSQL.deleteBook", id);
	}
	public List<Book> getUnavailableBook(Integer company) throws DaoException {
		log.info("ConfigSQL.getUnavailableBook");
		return (List<Book>) super.getList("ConfigSQL.getUnavailableBook", company);
	}
	
	public PaginatedList searchBookItem(Map map) throws DaoException {
		log.info("ConfigSQL.searchBookItem");
		return (PaginatedList) super.getPaginatedList("ConfigSQL.searchBookItem", map);
	}
	public Integer searchBookItemSize(Map map) throws DaoException {
		log.info("ConfigSQL.searchBookItemSize");
		return (Integer)super.getObject("ConfigSQL.searchBookItemSize", map);
	}
	public Integer insertBookItem(BookItem book)  throws DaoException {
		log.info("ConfigSQL.insertBookItem");
		return super.update("ConfigSQL.insertBookItem", book);
	}
	public Integer updateBookItem(BookItem book)  throws DaoException {
		log.info("ConfigSQL.updateBookItem");
		return super.update("ConfigSQL.updateBookItem", book);
	}
	public BookItem getBookItem(Integer id) throws DaoException {
		log.info("ConfigSQL.getBookItem");
		return (BookItem)super.getObject("ConfigSQL.getBookItem", id);
	}
	public Integer deleteBookItem(Integer bookItemId)  throws DaoException {
		log.info("ConfigSQL.deleteBookItem");
		return super.update("ConfigSQL.deleteBookItem", bookItemId);
	}
	public List<LabelValueBean> getBookItemList() throws DaoException {
		log.info("ConfigSQL.getBookItemList");
		return (List<LabelValueBean>) super.getList("ConfigSQL.getBookItemList", null);
	}
	
	
	
	
	public List<Mapping> getUnavailableMappingByCompany(Integer company) throws DaoException {
		log.info("ConfigSQL.getUnavailableMappingByCompany");
		return (List<Mapping>) super.getList("ConfigSQL.getUnavailableMappingByCompany", company);
	}
	public List<Mapping> getUnavailableMapping() throws DaoException {
		log.info("ConfigSQL.getUnavailableMapping");
		return (List<Mapping>) super.getList("ConfigSQL.getUnavailableMapping", null);
	}
	
	public Integer insertMapping(Mapping map)  throws DaoException {
		log.info("ConfigSQL.insertMapping");
		return super.update("ConfigSQL.insertMapping", map);
	}
	
	public PaginatedList searchMapping(Map map) throws DaoException {
		log.info("ConfigSQL.searchMapping");
		return (PaginatedList) super.getPaginatedList("ConfigSQL.searchMapping", map);
	}
	public Integer searchMappingSize(Map map) throws DaoException {
		log.info("ConfigSQL.searchMappingSize");
		return (Integer)super.getObject("ConfigSQL.searchMappingSize", map);
	}
	public List<Mapping> getMapping(Map map) throws DaoException {
		log.info("ConfigSQL.searchMapping");
		return (List<Mapping>) super.getList("ConfigSQL.searchMapping", map);
	}
	public Mapping getMapping(Integer id) throws DaoException {
		log.info("ConfigSQL.getMapping");
		return (Mapping)super.getObject("ConfigSQL.getMapping", id);
	}
	public Integer updateMapping(Mapping mapp)  throws DaoException {
		log.info("ConfigSQL.updateMapping");
		return super.update("ConfigSQL.updateMapping", mapp);
	}
	
	public Integer deleteMapping(Integer bookItemId)  throws DaoException {
		log.info("ConfigSQL.deleteMapping");
		return super.update("ConfigSQL.deleteMapping", bookItemId);
	}
	
	
	/*
	//Insert Employee Start
	public void insertEmployee(Employee emp) throws DaoException {
		log.info("ConfigSQL.getPillars");
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
