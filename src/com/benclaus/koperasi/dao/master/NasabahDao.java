package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Nasabah;
import com.ibatis.common.util.PaginatedList;


public class NasabahDao extends BaseDAO {
	private static Logger log = Logger.getLogger(NasabahDao.class);
	private static volatile NasabahDao instance;

	public static NasabahDao getInstance() {
        final NasabahDao currentInstance;
        if (instance == null) {
            synchronized (NasabahDao.class) {
                if (instance == null) {
                    instance = new NasabahDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private NasabahDao() { super(); }
	
	public PaginatedList searchNasabah(Map map) throws DaoException {
		log.info("NasabahSQL.searchNasabah");
		return (PaginatedList) super.getPaginatedList("NasabahSQL.searchNasabah", map);
	}
	public Integer searchNasabahSize(Map map) throws DaoException {
		log.info("NasabahSQL.searchNasabahSize");
		return (Integer) super.getObject("NasabahSQL.searchNasabahSize", map);
	}
	
	public Integer insertNasabah(Nasabah nasabah) throws DaoException {
		log.info("NasabahSQL.insertNasabah");
		return super.insert("NasabahSQL.insertNasabah", nasabah);
	}
	
	public Integer updateNasabah(Nasabah nasabah) throws DaoException {
		log.info("NasabahSQL.updateNasabah");
		return (Integer) super.update("NasabahSQL.updateNasabah", nasabah);
	}
	
	public Integer insertNasabahHistory(Nasabah nasabah) throws DaoException {
		log.info("NasabahSQL.insertNasabahHistory");
		return (Integer) super.update("NasabahSQL.insertNasabahHistory", nasabah);
	}
	
	public Nasabah getNasabah(Integer id) throws DaoException {
		log.info("NasabahSQL.getNasabah");
		return (Nasabah) super.getObject("NasabahSQL.getNasabah", id);
	}
	
	public List<Nasabah> getNasabahVersion(Integer id) throws DaoException {
		log.info("NasabahSQL.getNasabahVersion");
		return (List<Nasabah>) super.getList("NasabahSQL.getNasabahVersion", id);
	}
	
	public Integer deleteNasabah(Nasabah nasabah) throws DaoException {
		log.info("NasabahSQL.deleteNasabah");
		return (Integer) super.update("NasabahSQL.deleteNasabah", nasabah);
	}
	public Integer incLastKreditNo(Integer id) throws DaoException {
		log.info("NasabahSQL.incLastKreditNo");
		return (Integer) super.update("NasabahSQL.incLastKreditNo", id);
	}
	
}
