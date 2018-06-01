package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Nasabah;
import com.benclaus.koperasi.model.master.StatusPK;
import com.ibatis.common.util.PaginatedList;

public class NasabahService {
	private NasabahDao dao = NasabahDao.getInstance();
	private static Logger log = Logger.getLogger(NasabahService.class);

	private static volatile NasabahService instance;

	public static NasabahService getInstance() {
        final NasabahService currentInstance;
        if (instance == null) {
            synchronized (NasabahService.class) {
                if (instance == null) {
                    instance = new NasabahService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private NasabahService() {
		super();
	}
	
	
	public PaginatedList searchNasabah(Map map) {
		try {
			return dao.searchNasabah(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchNasabahSize(Map map) {
		try {
			return dao.searchNasabahSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer insertNasabah(Nasabah nasabah) {
		try {
			return dao.insertNasabah(nasabah);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer updateNasabah(Nasabah nasabah) {
		try {
			return dao.updateNasabah(nasabah);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer deleteNasabah(Integer id) {
		try {
			return dao.deleteNasabah(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Nasabah getNasabah(Integer id) {
		try {
			return dao.getNasabah( id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	
}