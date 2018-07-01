package com.benclaus.koperasi.dao.kantor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Pusat;
import com.benclaus.koperasi.model.kantor.Unit;

public class KantorDao extends BaseDAO {
	private static Logger log = Logger.getLogger(KantorDao.class);
	private static volatile KantorDao instance;

	public static KantorDao getInstance() {
        final KantorDao currentInstance;
        if (instance == null) {
            synchronized (KantorDao.class) {
                if (instance == null) {
                    instance = new KantorDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private KantorDao() { super(); }
	
	public Integer updatePusat(Pusat pusat) throws DaoException {
		log.info("KantorSQL.updatePusat");
		return (Integer) super.update("KantorSQL.updatePusat", pusat);
	}
	
	public Pusat getPusat() throws DaoException {
		log.info("KantorSQL.getPusat");
		return (Pusat) super.getObject("KantorSQL.getPusat", null);
	}
	
	public Integer insertPusatHistory(Pusat prshn) throws DaoException {
		log.info("KantorSQL.insertPusatHistory");
		return (Integer) super.update("KantorSQL.insertPusatHistory", prshn);
	}
	
	public List<Pusat> getPusatHistory(Integer id) throws DaoException {
		log.info("KantorSQL.getPusatHistory");
		return (List<Pusat>) super.getList("KantorSQL.getPusatHistory", id);
	}
	
	public Cabang getCabang(Map map) throws DaoException {
		log.info("KantorSQL.getCabang");
		return (Cabang) super.getObject("KantorSQL.getCabang", map);
	}
	public List<Cabang> getCabang() throws DaoException {
		log.info("KantorSQL.getCabang");
		return (List<Cabang>) super.getList("KantorSQL.getCabang", null);
	}
	
	// get daftar unit berdasar cabang
	public List<Unit> getUnit(Map map) throws DaoException {
		log.info("KantorSQL.getUnit");
		return (List<Unit>) super.getList("KantorSQL.getUnit", map);
	}
	public List<Unit> getUnit() throws DaoException {
		log.info("KantorSQL.getUnit");
		return (List<Unit>) super.getList("KantorSQL.getUnit", null);
	}

}
