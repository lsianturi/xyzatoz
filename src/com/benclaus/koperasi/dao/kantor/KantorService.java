package com.benclaus.koperasi.dao.kantor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.kantor.Cabang;
import com.benclaus.koperasi.model.kantor.Pusat;
import com.benclaus.koperasi.model.kantor.Unit;

public class KantorService {
	private KantorDao dao = KantorDao.getInstance();
	private static Logger log = Logger.getLogger(KantorService.class);

	private static volatile KantorService instance;

	public static KantorService getInstance() {
        final KantorService currentInstance;
        if (instance == null) {
            synchronized (KantorService.class) {
                if (instance == null) {
                    instance = new KantorService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private KantorService() {
		super();
	}
	
	public Integer updatePusat(Pusat pusat) {
		try {
			return dao.updatePusat(pusat);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Pusat getPerusahaan() {
		try {
			return dao.getPusat();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer insertPerusahaanHistory(Pusat prshn) {
		try {
			return dao.insertPusatHistory(prshn);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public List<Pusat> getPerusahaanHistory(Integer id) {
		try {
			return dao.getPusatHistory(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Cabang getCabang(Integer cabangId) throws DaoException {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("cabangId", cabangId);
			return dao.getCabang(param);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<Cabang> getCabang() throws DaoException {
		try {
			return dao.getCabang();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	// get daftar unit berdasar cabang
	public List<Unit> getUnit(Integer cabangId) throws DaoException {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("cabangId", cabangId);
			return dao.getUnit(param);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<Unit> getUnit() throws DaoException {
		try {
			return dao.getUnit();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
}
