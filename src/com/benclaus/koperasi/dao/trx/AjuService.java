package com.benclaus.koperasi.dao.trx;

import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.trx.Aju;
import com.ibatis.common.util.PaginatedList;

public class AjuService {
	private AjuDao dao = AjuDao.getInstance();
	private static Logger log = Logger.getLogger(AjuService.class);

	private static volatile AjuService instance;

	public static AjuService getInstance() {
		final AjuService currentInstance;
		if (instance == null) {
			synchronized (AjuService.class) {
				if (instance == null) {
					instance = new AjuService();
				}
				currentInstance = instance;
			}
		} else {
			currentInstance = instance;
		}
		return currentInstance;
	}

	private AjuService() {
		super();
	}

	public PaginatedList searchAju(Map map) throws DaoException {
		try {
			return dao.searchAju(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer searchAjuSize(Map map) throws DaoException {
		try {
			return dao.searchAjuSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}

	public Aju getAju(Integer id) throws DaoException {
		try {
			return dao.getAju(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer insertAju(Aju aju) throws DaoException {
		try {
			return dao.insertAju(aju);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer updateAju(Aju aju) throws DaoException {
		try {
			return dao.updateAju(aju);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer getLastNo(String tipeKredit) throws DaoException {
		try {
			return dao.getLastNo(tipeKredit);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
}