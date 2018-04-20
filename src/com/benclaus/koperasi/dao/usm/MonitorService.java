package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;

/**
 * @author Lambok
 *
 */
public class MonitorService {
	private static Logger log = Logger.getLogger(MonitorService.class);
	private MonitorDAO userMonitorDao = MonitorDAO.getInstance();
	private static MonitorService userMonitorService = null;

	public static synchronized MonitorService getInstance() {
		if (userMonitorService == null) userMonitorService = new MonitorService();
		return userMonitorService;
	}
	
	private MonitorService() {
		super();
	}
	
	public List selectAuditTrailForUserMonitor(Map map) throws DaoException {
		return userMonitorDao.selectAuditTrailForUserMonitor(map);
	}
} 