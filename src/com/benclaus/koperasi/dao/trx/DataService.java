package com.benclaus.koperasi.dao.trx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.Book;
import com.benclaus.koperasi.model.BookItem;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Forecast;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;

public class DataService {
	private DataDao dao = DataDao.getInstance();
	private static Logger log = Logger.getLogger(DataService.class);

	private static volatile DataService instance;

	public static DataService getInstance() {
        final DataService currentInstance;
        if (instance == null) {
            synchronized (DataService.class) {
                if (instance == null) {
                    instance = new DataService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private DataService() {
		super();
	}
	
	
	
	public List<LabelValueBean> getDataYearList() {
		try {
			return dao.getDataYearList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public PaginatedList searchPlan(Map map) throws DaoException {
		try {
			return dao.searchPlan(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchPlanSize(Map map) throws DaoException {
		try {
			return dao.searchPlanSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public List<Data> getPlan(Map map) throws DaoException {
		try {
			return dao.getPlan(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Data getPlan(Integer id) {
		try {
			return dao.getPlan(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer updatePlan(Data data) {
		try {
			return dao.updatePlan(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer insertPlan(Map data) {
		try {
			return dao.insertPlan(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public List<Data> getNewInsertedPlan(Map map) {
		try {
			return dao.getNewInsertedPlan(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	
	
	public List<Integer> getForecastYearList() {
		try {
			return dao.getForecastYearList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public PaginatedList searchForecast(Map map) throws DaoException {
		try {
			return dao.searchForecast(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchForecastSize(Map map) throws DaoException {
		try {
			return dao.searchForecastSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public List<Forecast> getForecast(Map map) throws DaoException {
		try {
			return dao.getForecast(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Forecast getForecast(Integer id) {
		try {
			return dao.getForecast(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer updateForecast(Forecast data) {
		try {
			return dao.updateForecast(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer insertForecast(Map data) {
		try {
			return dao.insertForecast(data);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public List<Forecast> getNewInsertedForecast(Map map) {
		try {
			return dao.getNewInsertedForecast(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	
	public PaginatedList searchActual(Map map) throws DaoException {
		try {
			return dao.searchActual(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchActualSize(Map map) throws DaoException {
		try {
			return dao.searchActualSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public List<Data> getActual(Map map) throws DaoException {
		try {
			return dao.getActual(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
}
