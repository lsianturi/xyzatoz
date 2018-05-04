package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Area;
import com.benclaus.koperasi.model.master.Industri;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.ibatis.common.util.PaginatedList;


public class PerusahaanDao extends BaseDAO {
	private static Logger log = Logger.getLogger(PerusahaanDao.class);
	private static volatile PerusahaanDao instance;

	public static PerusahaanDao getInstance() {
        final PerusahaanDao currentInstance;
        if (instance == null) {
            synchronized (PerusahaanDao.class) {
                if (instance == null) {
                    instance = new PerusahaanDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private PerusahaanDao() { super(); }
	
	public PaginatedList searchPerusahaan(Map map) throws DaoException {
		log.info("PrshnSQL.searchPerusahaan");
		return (PaginatedList) super.getPaginatedList("PrshnSQL.searchPerusahaan", map);
	}
	public Integer searchPerusahaanSize(Map map) throws DaoException {
		log.info("PrshnSQL.searchPerusahaanSize");
		return (Integer) super.getObject("PrshnSQL.searchPerusahaanSize", map);
	}
	
	public Integer insertPerusahaan(Perusahaan perusahaan) throws DaoException {
		log.info("PrshnSQL.insertPerusahaan");
		return (Integer) super.update("PrshnSQL.insertPerusahaan", perusahaan);
	}
	
	public Integer updatePerusahaan(Perusahaan perusahaan) throws DaoException {
		log.info("PrshnSQL.updatePerusahaan");
		return (Integer) super.update("PrshnSQL.updatePerusahaan", perusahaan);
	}
	public Integer deletePerusahaan(Integer id) throws DaoException {
		log.info("PrshnSQL.deletePerusahaan");
		return (Integer) super.update("PrshnSQL.deletePerusahaan", id);
	}
	
	public Perusahaan getPerusahaan(Integer id) throws DaoException {
		log.info("PrshnSQL.getPerusahaan");
		return (Perusahaan) super.getObject("PrshnSQL.getPerusahaan", id);
	}
	
	public List<Industri> getIndustries() throws DaoException {
		log.info("PrshnSQL.getIndustries");
		return (List<Industri>) super.getList("PrshnSQL.getIndustries", null);
	}
	public List<Area> getAreas() throws DaoException {
		log.info("PrshnSQL.getAreas");
		return (List<Area>) super.getList("PrshnSQL.getAreas", null);
	}
}
