package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;

/**
 * @author Lambok
 *
 */
public class MonitorDAO extends BaseDAO {
	private static MonitorDAO userMonitorDao= null;
	
	public static synchronized MonitorDAO getInstance() {
		if (userMonitorDao==null) userMonitorDao= new MonitorDAO();
		return userMonitorDao;
	}
	
	private MonitorDAO() {
		super();
	}

    public List selectAuditTrailForUserMonitor(Map map) throws DaoException {
        return super.getPaginatedList("AuditTrailSQL.selectAuditTrailForUserMonitor", map);
    }
} 

