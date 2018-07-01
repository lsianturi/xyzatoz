package com.benclaus.koperasi.dao.trx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.trx.Aju;
import com.ibatis.common.util.PaginatedList;


public class AjuDao extends BaseDAO {
	private static Logger log = Logger.getLogger(AjuDao.class);
	private static volatile AjuDao instance;

	public static AjuDao getInstance() {
        final AjuDao currentInstance;
        if (instance == null) {
            synchronized (AjuDao.class) {
                if (instance == null) {
                    instance = new AjuDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private AjuDao() { super(); }
	
	public PaginatedList searchAju(Map map) throws DaoException {
		log.info("AjuSQL.searchAju");
		return (PaginatedList) super.getPaginatedList("AjuSQL.searchAju", map);
	}
	public Integer searchAjuSize(Map map) throws DaoException {
		log.info("AjuSQL.searchAjuSize");
		return (Integer)super.getObject("AjuSQL.searchAjuSize", map);
	}
	public Aju getAju(Integer id) throws DaoException {
		log.info("AjuSQL.getAju");
		return (Aju)super.getObject("AjuSQL.getAju", id);
	}
	
	public Integer insertAju(Aju aju) throws DaoException {
		log.info("AjuSQL.insertAju");
		return (Integer) super.insert("AjuSQL.insertAju", aju);
	}
	public Integer updateAju(Aju aju) throws DaoException {
		log.info("AjuSQL.updateAju");
		return (Integer) super.insert("AjuSQL.updateAju", aju);
	}
	
	public Integer getLastNo(String tipeKredit) throws DaoException {
		log.info("AjuSQL.getLastNo");
		Integer result=null;
		try {
			Connection conn = super.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery("SELECT getLastNo(UPPER('" +tipeKredit +"'))");
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return result;
	}
	
	
	
	/*
	 * CASE 
		WHEN lastNo IS NULL THEN
			-- INSERT INTO num (tipe_kredit, last_num) VALUES (tipeKredit, 1);
		ELSE
			-- UPDATE num SET last_num = last_num +1 WHERE tipe_kredit = tipeKredit;
	END;
	 */
}
