package com.benclaus.koperasi.dao.master;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.master.Pegawai;
import com.benclaus.koperasi.model.master.Perusahaan;
import com.benclaus.koperasi.model.master.StatusPK;
import com.ibatis.common.util.PaginatedList;


public class PegawaiDao extends BaseDAO {
	private static Logger log = Logger.getLogger(PegawaiDao.class);
	private static volatile PegawaiDao instance;

	public static PegawaiDao getInstance() {
        final PegawaiDao currentInstance;
        if (instance == null) {
            synchronized (PegawaiDao.class) {
                if (instance == null) {
                    instance = new PegawaiDao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private PegawaiDao() { super(); }
	
	public PaginatedList searchPegawai(Map map) throws DaoException {
		log.info("PegawaiSQL.searchPegawai");
		return (PaginatedList) super.getPaginatedList("PegawaiSQL.searchPegawai", map);
	}
	public Integer searchPegawaiSize(Map map) throws DaoException {
		log.info("PegawaiSQL.searchPegawaiSize");
		return (Integer) super.getObject("PegawaiSQL.searchPegawaiSize", map);
	}
	
	public Integer insertPegawai(Pegawai pegawai) throws DaoException {
		log.info("PegawaiSQL.insertPegawai");
		return (Integer) super.insert("PegawaiSQL.insertPegawai", pegawai);
	}
	public Integer insertPegawaiHistory(Pegawai pegawai) throws DaoException {
		log.info("PegawaiSQL.insertPegawaiHistory");
		return (Integer) super.update("PegawaiSQL.insertPegawaiHistory", pegawai);
	}
	public Integer updatePegawai(Pegawai pegawai) throws DaoException {
		log.info("PegawaiSQL.updatePegawai");
		return (Integer) super.update("PegawaiSQL.updatePegawai", pegawai);
	}
	public Integer deletePegawai(Integer id) throws DaoException {
		log.info("PegawaiSQL.deletePegawai");
		return (Integer) super.update("PegawaiSQL.deletePegawai", id);
	}
	
	public Pegawai getPegawai(Integer id) throws DaoException {
		log.info("PegawaiSQL.getPegawai");
		return (Pegawai) super.getObject("PegawaiSQL.getPegawai", id);
	}
	
	public List<Pegawai> getPegawaiHistory(Integer id) throws DaoException {
		log.info("PegawaiSQL.getPegawaiHistory");
		return (List<Pegawai>) super.getList("PegawaiSQL.getPegawaiHistory", id);
	}
}
