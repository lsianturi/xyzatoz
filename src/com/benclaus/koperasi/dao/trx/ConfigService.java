package com.benclaus.koperasi.dao.trx;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.Book;
import com.benclaus.koperasi.model.BookItem;
import com.benclaus.koperasi.model.Company;
import com.benclaus.koperasi.model.CompanyBook;
import com.benclaus.koperasi.model.Config;
import com.benclaus.koperasi.model.Data;
import com.benclaus.koperasi.model.Mapping;
import com.benclaus.koperasi.model.Pillar;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;

public class ConfigService {
	private ConfigDao dao = ConfigDao.getInstance();
	private static Logger log = Logger.getLogger(ConfigService.class);

	private static volatile ConfigService instance;

	public static ConfigService getInstance() {
        final ConfigService currentInstance;
        if (instance == null) {
            synchronized (ConfigService.class) {
                if (instance == null) {
                    instance = new ConfigService();
                }
                currentInstance = instance;
            }
        } else {
            currentInstance = instance;
        }
        return currentInstance;
    }

	private ConfigService() {
		super();
	}
	
	public Config getRorConfig() throws DaoException {
		try {
			return dao.getRorConfig();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<String> getSsoUrl() throws DaoException {
		try {
			return dao.getSsoUrl();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public List<Pillar> getPillars() throws DaoException {
		try {
			return dao.getPillars();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer insertPillar(Pillar pilar) throws DaoException {
		try {
			return dao.insertPillar(pilar);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer updatePillar(Pillar pilar) throws DaoException {
		try {
			return dao.updatePillar(pilar);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer deletePillar(Integer id) throws DaoException {
		try {
			return dao.deletePillar(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Pillar getPillar(Integer id) throws DaoException {
		try {
			return dao.getPillar(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	

	public PaginatedList searchCompany(Map map) throws DaoException {
		try {
			return dao.searchCompany(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer searchCompanySize(Map map) throws DaoException {
		try {
			return dao.searchCompanySize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public List<LabelValueBean> getSPMList() throws DaoException {
		try {
			return dao.getSPMList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Integer insertCompany(Company comp) throws DaoException {
		try {
			return dao.insertCompany(comp);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Integer updateCompany(Company comp) throws DaoException {
		try {
			return dao.updateCompany(comp);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public Company getCompany(Map map) throws DaoException {
		try {
			return dao.getCompany(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Company getCompanyByName(Map map) {
		try {
			return dao.getCompanyByName(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	public Company getCompanyByPrefix(String prefix) {
		try {
			return dao.getCompanyByPrefix(prefix);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer deleteCompany(Integer id)  throws DaoException {
		try {
			return dao.deleteCompany(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public PaginatedList searchBook(Map map) throws DaoException {
		try {
			return dao.searchBook(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer searchBookSize(Map map) throws DaoException {
		try {
			return dao.searchBookSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public List<LabelValueBean> getBookList() throws DaoException {
		try {
			return dao.getBookList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<LabelValueBean> getCompanyList() throws DaoException {
		try {
			return dao.getCompanyList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer insertBook(CompanyBook book)  throws DaoException {
		try {
			return dao.insertBook(book);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer updateBook(CompanyBook book)  throws DaoException {
		try {
			return dao.updateBook(book);
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
	
	public List<Book> getUnavailableBook(Integer company) {
		try {
			return dao.getUnavailableBook(company);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer deleteBook(Integer id)  throws DaoException {
		try {
			return dao.deleteBookItem(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public PaginatedList searchBookItem(Map map) {
		try {
			return dao.searchBookItem(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer searchBookItemSize(Map map) {
		try {
			return dao.searchBookItemSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer insertBookItem(BookItem book) {
		try {
			return dao.insertBookItem(book);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer updateBookItem(BookItem book) {
		try {
			return dao.updateBookItem(book);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public BookItem getBookItem(Integer id) throws DaoException {
		try {
			return dao.getBookItem(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer deleteBookItem(Integer bookItemId) throws DaoException {
		try {
			dao.deleteMapping(bookItemId);
			return dao.deleteBookItem(bookItemId);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	public List<LabelValueBean> getBookItemList()  {
		try {
			return dao.getBookItemList();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<Mapping> getUnavailableMappingByCompany(Integer company) {
		try {
			return dao.getUnavailableMappingByCompany(company);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public List<Mapping> getUnavailableMapping() {
		try {
			return dao.getUnavailableMapping();
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer insertMapping(Mapping map) throws DaoException {
		try {
			return dao.insertMapping(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public PaginatedList searchMapping(Map map) throws DaoException {
		try {
			return dao.searchMapping(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer searchMappingSize(Map map) throws DaoException {
		try {
			return dao.searchMappingSize(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public List<Mapping> getMapping(Map map) throws DaoException {
		try {
			return dao.searchMapping(map);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Mapping getMapping(Integer id) {
		try {
			return dao.getMapping(id);
		} catch (Exception e) {
			log.debug(e);
		}
		return null;
	}
	
	public Integer updateMapping(Mapping mapp) {
		try {
			return dao.updateMapping(mapp);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
	public Integer deleteMapping(Integer bookItemId) {
		try {
			return dao.deleteMapping(bookItemId);
		} catch (Exception e) {
			log.debug(e);
		}
		return 0;
	}
	
}
