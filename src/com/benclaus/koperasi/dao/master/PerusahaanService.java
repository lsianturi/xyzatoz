package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Area;
import com.benclaus.koperasi.model.master.Industri;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.ibatis.common.util.PaginatedList;

public class PerusahaanService {
	private PerusahaanDao dao = PerusahaanDao.getInstance();
	private static Logger log = Logger.getLogger(PerusahaanService.class);

	private static volatile PerusahaanService instance;

	public static PerusahaanService getInstance() {
        final PerusahaanService currentInstance;
        if (instance == null) {
            synchronized (PerusahaanService.class) {
                if (instance == null) {
                    instance = new PerusahaanService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private PerusahaanService() {
		super();
	}
	
	
	public PaginatedList searchPerusahaan(Map map) {
		try {
			return dao.searchPerusahaan(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchPerusahaanSize(Map map) {
		try {
			return dao.searchPerusahaanSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer insertPerusahaan(Perusahaan perusahaan) {
		try {
			return dao.insertPerusahaan(perusahaan);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer updatePerusahaan(Perusahaan perusahaan) {
		try {
			return dao.updatePerusahaan( perusahaan);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Perusahaan getPerusahaan(Integer id) {
		try {
			return dao.getPerusahaan( id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<Industri> getIndustries() throws DaoException {
		try {
			return dao.getIndustries();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<Area> getAreas() throws DaoException {
		try {
			return dao.getAreas();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
}
