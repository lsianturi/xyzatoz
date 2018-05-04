package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Nasabah;
import com.benclaus.koperasi.model.master.StatusPK;
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
		return (Integer) super.update("NasabahSQL.insertNasabah", nasabah);
	}
	
	public Integer updateNasabah(Nasabah nasabah) throws DaoException {
		log.info("NasabahSQL.updateNasabah");
		return (Integer) super.update("NasabahSQL.updateNasabah", nasabah);
	}
	public Integer deleteNasabah(Integer id) throws DaoException {
		log.info("NasabahSQL.deleteNasabah");
		return (Integer) super.update("NasabahSQL.deleteNasabah", id);
	}
	
	public Nasabah getNasabah(Integer id) throws DaoException {
		log.info("NasabahSQL.getNasabah");
		return (Nasabah) super.getObject("NasabahSQL.getNasabah", id);
	}
	
	public List<StatusPK> getStatusNasabah() throws DaoException {
		log.info("NasabahSQL.getStatusNasabah");
		return (List<StatusPK>) super.getList("NasabahSQL.getStatusNasabah", null);
	}
	public List<StatusPK> getStatusSipil() throws DaoException {
		log.info("NasabahSQL.getStatusSipil");
		return (List<StatusPK>) super.getList("NasabahSQL.getStatusSipil", null);
	}
}
