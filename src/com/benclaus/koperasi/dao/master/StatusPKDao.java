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
	
	public List<StatusPK> listStatusPegawai() throws DaoException {
		log.info("StatusPKSQL.listStatusPegawai");
		return (List<StatusPK>) super.getList("StatusPKSQL.listStatusPegawai", null);
	}
	public List<StatusPK> listStatusSipil() throws DaoException {
		log.info("StatusPKSQL.listStatusSipil");
		return (List<StatusPK>) super.getList("StatusPKSQL.listStatusSipil", null);
	}
	
	public List<StatusPK> listJnsKelamin() throws DaoException {
		log.info("StatusPKSQL.listJnsKelamin");
		return (List<StatusPK>) super.getList("StatusPKSQL.listJnsKelamin", null);
	}
	public List<StatusPK> listStatusKaryawan() throws DaoException {
		log.info("StatusPKSQL.listStatusKaryawan");
		return (List<StatusPK>) super.getList("StatusPKSQL.listStatusKaryawan", null);
	}
	public List<StatusPK> listJenisAnggota() throws DaoException {
		log.info("StatusPKSQL.listJenisAnggota");
		return (List<StatusPK>) super.getList("StatusPKSQL.listJenisAnggota", null);
	}
	
	public List<StatusPK> listStatusAnggota() throws DaoException {
		log.info("StatusPKSQL.listStatusAnggota");
		return (List<StatusPK>) super.getList("StatusPKSQL.listStatusAnggota", null);
	}
	
	public List<StatusPK> listPerusahaan() throws DaoException {
		log.info("StatusPKSQL.listPerusahaan");
		return (List<StatusPK>) super.getList("StatusPKSQL.listPerusahaan", null);
	}
	public List<StatusPK> listBank() throws DaoException {
		log.info("StatusPKSQL.listBank");
		return (List<StatusPK>) super.getList("StatusPKSQL.listBank", null);
	}
	public List<StatusPK> listAgent() throws DaoException {
		log.info("StatusPKSQL.listAgent");
		return (List<StatusPK>) super.getList("StatusPKSQL.listAgent", null);
	}
	
}
