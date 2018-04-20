package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.LoginTrail;
/**
 * @author Lambok
 *
 */
public class UsmDAO extends BaseDAO {
	private static UsmDAO usmDao= null;
	
	public static synchronized UsmDAO getInstance() {
		if (usmDao == null) usmDao = new UsmDAO();
		return usmDao;
	}
	
	private UsmDAO() {
		super();
	}

    public int insertLoginTrail(LoginTrail loginTrail) throws DaoException {
        return super.update("UsmSQL.insertLoginTrail", loginTrail);
    }
    
    public List selectLoginTrail(Map map) throws DaoException {
        return super.getPaginatedList("UsmSQL.selectLoginTrail", map);
    }
    
    public int selectLoginTrailSize(Map map) throws DaoException {
        return ((Integer) super.getObject("UsmSQL.selectLoginTrailSize", map)).intValue();
    }
}

