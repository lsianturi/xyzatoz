package com.benclaus.koperasi.dao.trx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.A3;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Forecast;
import com.benclaus.koperasi.model.FormulaArg;
import com.benclaus.koperasi.model.FormulaArgItem;
import com.benclaus.koperasi.model.Mapping;
import com.ibatis.common.util.PaginatedList;


public class A3Dao extends BaseDAO {
	private static Logger log = Logger.getLogger(A3Dao.class);
	private static volatile A3Dao instance;

	public static A3Dao getInstance() {
        final A3Dao currentInstance;
        if (instance == null) {
            synchronized (A3Dao.class) {
                if (instance == null) {
                    instance = new A3Dao();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }
	
	private A3Dao() { super(); }
	
	public List<Mapping> getMapping(String prefix) throws DaoException {
		log.info("A3SQL.getMapping");
		return (List<Mapping>)super.getList("A3SQL.getMapping", prefix);
	}
	
	public Integer insertFormulaArgs(FormulaArg args) throws DaoException {
		log.info("A3SQL.insertFormulaArgs");
		return super.update("A3SQL.insertFormulaArgs", args);
	}
	public FormulaArg getFormulaArg(FormulaArg arg) throws DaoException {
		log.info("A3SQL.getFormulaArg");
		return (FormulaArg) super.getObject("A3SQL.getFormulaArg", arg);
	}
	
	public Integer insertFormulaArgItems(FormulaArgItem args) throws DaoException {
		log.info("A3SQL.insertFormulaArgItems");
		return super.update("A3SQL.insertFormulaArgItems", args);
	}
	public Integer updateFormulaArgItems(FormulaArgItem args) throws DaoException {
		log.info("A3SQL.updateFormulaArgItems");
		return super.update("A3SQL.updateFormulaArgItems", args);
	}
	
	public List<FormulaArg> getDataLog(Map map) throws DaoException {
		log.info("A3SQL.selectFormulaArg");
		return (List<FormulaArg>) super.getPaginatedList("A3SQL.selectFormulaArg", map);
	}
	
	public List<FormulaArgItem> getDataLogItem(Integer id) throws DaoException {
		log.info("A3SQL.selectFormulaArg");
		return super.getList("A3SQL.selectFormulaArgItem", id);
	}
	
	
	public Integer updateActual(Data data) throws DaoException {
		log.info("A3SQL.updateActual");
		return super.update("A3SQL.updateActual", data);
	}
	
	public Integer getDataId(Map map) throws DaoException {
		log.info("A3SQL.getDataId");
		return (Integer)super.getObject("A3SQL.getDataId", map);
	}

	public CompanyBook getBook(Map map) throws DaoException {
		log.info("A3SQL.getBook");
		return (CompanyBook)super.getObject("A3SQL.getBook", map);
	}
	
	public Double getPreviousAmt(Data data) throws DaoException {
		log.info("A3SQL.getPreviousAmt");
		return (Double)super.getObject("A3SQL.getPreviousAmt", data);
	}
	public List<A3> getYtdDataByYear(Map map) throws DaoException {
		log.info("A3SQL.getYtdDataByYear");
		return (List<A3>)super.getList("A3SQL.getYtdDataByYear", map);
	}
	
	public List<A3> getFullYearForecast(Integer year) throws DaoException {
		log.info("A3SQL.getFullYearForecast");
		return (List<A3>)super.getList("A3SQL.getFullYearForecast", year);
	}
	
	public List<A3> getFullYearPrevYearActual(Integer year) throws DaoException {
		log.info("A3SQL.getFullYearPrevYearActual");
		return (List<A3>)super.getList("A3SQL.getFullYearPrevYearActual", year);
	}
	
	public List<A3> getFullYearCurrentYearActual(Map map) throws DaoException {
		log.info("A3SQL.getFullYearCurrentYearActual");
		return (List<A3>)super.getList("A3SQL.getFullYearCurrentYearActual", map);
	}
}
