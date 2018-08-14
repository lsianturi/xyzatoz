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

	public PaginatedList searchReal(Map map) {
		try {
			return dao.searchReal(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer searchRealSize(Map map) {
		try {
			return dao.searchRealSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}

	public Realisasi getReal(Integer id) {
		try {
			return dao.getReal(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}

	public Integer saveReal(Realisasi real) {
		try {
			return dao.saveReal(real);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer updateReal(Realisasi real) {
		try {
			return dao.updateReal(real);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer saveReal(Realisasi real, Map<String, Object> param, Integer ajuId) throws DaoException {
		Integer result = 0;
		dao.startTransaction();
		try {
			result +=dao.saveReal(real);
			if (param != null) result += dao.setSimulasiLunas(param);
			if (ajuId != null) result += dao.setAjuLunas(ajuId);
			dao.commitTransaction();
		} catch (Exception e) {
			dao.endTransaction();
		}
		
		return result;
	}
	
}
