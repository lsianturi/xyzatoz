package com.benclaus.koperasi.dao.master;

import java.util.List;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.StatusPK;


public class StatusPKDao extends BaseDAO {
	private static Logger log = Logger.getLogger(StatusPKDao.class);
	private static volatile StatusPKDao instance;

	public static StatusPKDao getInstance() {
        final StatusPKDao currentInstance;
        if (instance == null) {
            synchronized (StatusPKDao.class) {
                if (instance == null) {
                    instance = new StatusPKDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private StatusPKDao() { super(); }
	
	public List<StatusPK> getStatusPegawai() throws DaoException {
		log.info("StatusPKSQL.getStatusPegawai");
		return (List<StatusPK>) super.getList("StatusPKSQL.getStatusPegawai", null);
	}
	public List<StatusPK> getStatusSipil() throws DaoException {
		log.info("StatusPKSQL.getStatusSipil");
		return (List<StatusPK>) super.getList("StatusPKSQL.getStatusSipil", null);
	}
	
	public List<StatusPK> getJnsKelamin() throws DaoException {
		log.info("StatusPKSQL.getJnsKelamin");
		return (List<StatusPK>) super.getList("StatusPKSQL.getJnsKelamin", null);
	}
	public List<StatusPK> getStatusKaryawan() throws DaoException {
		log.info("StatusPKSQL.getStatusKaryawan");
		return (List<StatusPK>) super.getList("StatusPKSQL.getStatusKaryawan", null);
	}
	public List<StatusPK> getJenisAnggota() throws DaoException {
		log.info("StatusPKSQL.getJenisAnggota");
		return (List<StatusPK>) super.getList("StatusPKSQL.getJenisAnggota", null);
	}
	
	public List<StatusPK> getStatusAnggota() throws DaoException {
		log.info("StatusPKSQL.getStatusAnggota");
		return (List<StatusPK>) super.getList("StatusPKSQL.getStatusAnggota", null);
	}
	
	public List<StatusPK> getPerusahaan() throws DaoException {
		log.info("StatusPKSQL.getPerusahaan");
		return (List<StatusPK>) super.getList("StatusPKSQL.getPerusahaan", null);
	}
	public List<StatusPK> getBank() throws DaoException {
		log.info("StatusPKSQL.getBank");
		return (List<StatusPK>) super.getList("StatusPKSQL.getBank", null);
	}
}
