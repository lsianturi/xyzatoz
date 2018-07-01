package com.benclaus.koperasi.dao.trx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.trx.Realisasi;
import com.ibatis.common.util.PaginatedList;


public class RealisasiDao extends BaseDAO {
	private static Logger log = Logger.getLogger(RealisasiDao.class);
	private static volatile RealisasiDao instance;

	public static RealisasiDao getInstance() {
        final RealisasiDao currentInstance;
        if (instance == null) {
            synchronized (RealisasiDao.class) {
                if (instance == null) {
                    instance = new RealisasiDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private RealisasiDao() { super(); }
	
	public PaginatedList searchReal(Map map) throws DaoException {
		log.info("RealSQL.searchReal");
		return (PaginatedList) super.getPaginatedList("RealSQL.searchReal", map);
	}
	public Integer searchRealSize(Map map) throws DaoException {
		log.info("RealSQL.searchRealSize");
		return (Integer)super.getObject("RealSQL.searchRealSize", map);
	}
	public Realisasi getReal(Integer id) throws DaoException {
		log.info("RealSQL.getReal");
		return (Realisasi)super.getObject("RealSQL.getReal", id);
	}
	
	public Integer insertReal(Realisasi real) throws DaoException {
		log.info("RealSQL.insertReal");
		return (Integer) super.insert("RealSQL.insertReal", real);
	}
	public Integer updateReal(Realisasi real) throws DaoException {
		log.info("RealSQL.updateReal");
		return (Integer) super.insert("RealSQL.updateReal", real);
	}
	
}
