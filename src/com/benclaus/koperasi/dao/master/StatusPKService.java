package com.benclaus.koperasi.dao.master;

import java.util.List;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.StatusPK;

public class StatusPKService {
	private StatusPKDao dao = StatusPKDao.getInstance();
	private static Logger log = Logger.getLogger(StatusPKService.class);

	private static volatile StatusPKService instance;

	public static StatusPKService getInstance() {
        final StatusPKService currentInstance;
        if (instance == null) {
            synchronized (StatusPKService.class) {
                if (instance == null) {
                    instance = new StatusPKService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private StatusPKService() {
		super();
	}
	
	
	public List<StatusPK> getStatusPegawai() throws DaoException {
		try {
			return dao.getStatusPegawai();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> getStatusSipil() throws DaoException {
		try {
			return dao.getStatusSipil();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> getJnsKelamin() throws DaoException {
		try {
			return dao.getJnsKelamin();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> getStatusKaryawan() throws DaoException {
		try {
			return dao.getStatusKaryawan();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> getJenisAnggota() throws DaoException {
		try {
			return dao.getJenisAnggota();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> getStatusAnggota() throws DaoException {
		try {
			return dao.getStatusAnggota();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> getPerusahaan() throws DaoException {
		try {
			return dao.getPerusahaan();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> getBank() throws DaoException {
		try {
			return dao.getBank();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
}
