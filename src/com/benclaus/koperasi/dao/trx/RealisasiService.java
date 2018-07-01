package com.benclaus.koperasi.dao.trx;

import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.trx.Realisasi;
import com.ibatis.common.util.PaginatedList;

public class RealisasiService {
	private RealisasiDao  dao = RealisasiDao.getInstance();
	private static Logger log = Logger.getLogger(RealisasiService.class);

	private static volatile RealisasiService instance;

	public static RealisasiService getInstance() {
		final RealisasiService currentInstance;
		if (instance == null) {
			synchronized (RealisasiService.class) {
				if (instance == null) {
					instance = new RealisasiService();
				}
				currentInstance = instance;
			}
		} else {
			currentInstance = instance;
		}
		return currentInstance;
	}

	private RealisasiService() {
		super();
	}

	public PaginatedList searchReal(Map map) throws DaoException {
		try {
			return dao.searchReal(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer searchRealSize(Map map) throws DaoException {
		try {
			return dao.searchRealSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}

	public Realisasi getReal(Integer id) throws DaoException {
		try {
			return dao.getReal(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer insertReal(Realisasi real) throws DaoException {
		try {
			return dao.insertReal(real);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer updateReal(Realisasi real) throws DaoException {
		try {
			return dao.updateReal(real);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
}
