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
	
	
	public List<StatusPK> listStatusPegawai() throws DaoException {
		try {
			return dao.listStatusPegawai();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> listStatusSipil() throws DaoException {
		try {
			return dao.listStatusSipil();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> listJnsKelamin() throws DaoException {
		try {
			return dao.listJnsKelamin();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<StatusPK> listStatusKaryawan() throws DaoException {
		try {
			return dao.listStatusKaryawan();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> listJenisAnggota() throws DaoException {
		try {
			return dao.listJenisAnggota();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> listStatusAnggota() throws DaoException {
		try {
			return dao.listStatusAnggota();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> listPerusahaan() throws DaoException {
		try {
			return dao.listPerusahaan();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> listBank() throws DaoException {
		try {
			return dao.listBank();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<StatusPK> listAgent() throws DaoException {
		try {
			return dao.listAgent();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	
}
