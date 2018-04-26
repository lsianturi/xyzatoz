package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Area;
import com.benclaus.koperasi.model.master.Industri;
import com.benclaus.koperasi.model.master.Pegawai;
import com.benclaus.koperasi.model.master.StatusPK;
import com.ibatis.common.util.PaginatedList;

public class PegawaiService {
	private PegawaiDao dao = PegawaiDao.getInstance();
	private static Logger log = Logger.getLogger(PegawaiService.class);

	private static volatile PegawaiService instance;

	public static PegawaiService getInstance() {
        final PegawaiService currentInstance;
        if (instance == null) {
            synchronized (PegawaiService.class) {
                if (instance == null) {
                    instance = new PegawaiService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private PegawaiService() {
		super();
	}
	
	
	public PaginatedList searchPegawai(Map map) {
		try {
			return dao.searchPegawai(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchPegawaiSize(Map map) {
		try {
			return dao.searchPegawaiSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer insertPegawai(Pegawai pegawai) {
		try {
			return dao.insertPegawai(pegawai);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer updatePegawai(Pegawai pegawai) {
		try {
			return dao.updatePegawai(pegawai);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer deletePegawai(Integer id) {
		try {
			return dao.deletePegawai(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Pegawai getPegawai(Integer id) {
		try {
			return dao.getPegawai( id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> getStatusPegawai() throws DaoException {
		try {
			return dao.getStatusPegawai();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
}
