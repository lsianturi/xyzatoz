package com.benclaus.koperasi.dao.trx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.A3;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Forecast;
import com.benclaus.koperasi.model.FormulaArg;
import com.benclaus.koperasi.model.FormulaArgItem;
import com.benclaus.koperasi.model.Mapping;
import com.ibatis.common.util.PaginatedList;

public class A3Service {
	private A3Dao dao = A3Dao.getInstance();
	private static Logger log = Logger.getLogger(A3Service.class);

	private static volatile A3Service instance;

	public static A3Service getInstance() {
        final A3Service currentInstance;
        if (instance == null) {
            synchronized (A3Service.class) {
                if (instance == null) {
                    instance = new A3Service();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private A3Service() {
		super();
	}
	
	
	public List<Mapping> getMapping(String prefix) {
		try {
			return dao.getMapping(prefix);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public FormulaArg insertFormulaArgs(FormulaArg args)  {
		FormulaArg obj = null;
		try {
			dao.insertFormulaArgs(args);
		} catch (Exception e) {
			log.debug(e);
		} finally {
			obj = getFormulaArg(args);
		}
		return obj;
	}
	
	public FormulaArg getFormulaArg(FormulaArg arg) throws DaoException {
		try {
			return dao.getFormulaArg(arg);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer insertFormulaArgItem(FormulaArgItem args)  {
		try {
			return dao.insertFormulaArgItems(args);
		} catch (Exception e) {
			log.debug(e);
			try {
				return dao.updateFormulaArgItems(args);
			} catch (Exception e1) {
				log.debug(e1);
			}
		}
		return 0;
	}
	
	public List<FormulaArg> getDataLog(Map map) throws DaoException {
		List<FormulaArg> list = null;
		try {
			list = dao.getDataLog(map);
			FormulaArg arg = null;
			for (int i=0; i<list.size(); i++) {
				arg = (FormulaArg)list.get(i);
				arg.setFormulaItems(dao.getDataLogItem(arg.getId()));
			}
		} catch (Exception e) {
			log.debug(e);
		}
		return list;
	}
	
	public Integer updateActual(Data data)  {
		try {
			return dao.updateActual(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer getDataId(Map map) throws DaoException {
		try {
			return dao.getDataId(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public CompanyBook getBook(Map map) throws DaoException {
		try {
			return dao.getBook(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Double getPreviousAmt(Data data) throws DaoException {
		try {
			return dao.getPreviousAmt(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0.0;
	}
	public List<A3> getYtdDataByYear(Map map) throws DaoException {
		try {
			return dao.getYtdDataByYear(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<A3> getFullYearForecast(Integer year) throws DaoException {
		try {
			return dao.getFullYearForecast(year);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<A3> getFullYearPrevYearActual(Integer year) throws DaoException {
		try {
			return dao.getFullYearPrevYearActual(year);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<A3> getFullYearCurrentYearActual(Map map) throws DaoException {
		try {
			return dao.getFullYearCurrentYearActual(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
}
