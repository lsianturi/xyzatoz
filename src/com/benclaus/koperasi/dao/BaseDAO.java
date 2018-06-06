package com.benclaus.koperasi.dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.system.Audit;
import com.benclaus.koperasi.utility.Constant;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
/**
 * @author Lambok
 *
 */
public class BaseDAO {
	private static Logger log = Logger.getLogger(BaseDAO.class);
	private static SqlMapClient sqlMap = null;
	
	static {
		try {
			String resource = "com/benclaus/koperasi/resources/sql-map-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		}catch (Exception ex) {
			log.debug("BaseDAO static block: " + ex);
			throw new RuntimeException("Error Initializing BaseDAO :" + ex);
		}
	}
	public List getPaginatedList(String statementName, Object parameterObject) throws DaoException {
		List list = null;
		try {
			//debugSqlMap(statementName, parameterObject);
			list = sqlMap.queryForPaginatedList(statementName, parameterObject, Constant.PAGESIZE);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
		return list;
	}

	public List getList(String statementName, Object parameterObject) throws DaoException {
		List list = null;
		try {
			//debugSqlMap(statementName, parameterObject);
			list = sqlMap.queryForList(statementName, parameterObject);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}

		return list;
	}
	public int delete(String statementName, Object parameterObject) throws DaoException {
		int result = 0;
		try {
			result = sqlMap.delete(statementName,parameterObject);
			if (result > 0 && parameterObject instanceof Audit) { 
				//debugSqlMap("insertAuditTrail", parameterObject);
				sqlMap.update("AuditTrailSQL.insertAuditTrail", parameterObject);
			}
		}catch(SQLException se) {
			throw new DaoException(se.getMessage(), se);
		}
		return result;
	}
	public Object getObject(String statementName, Object parameterObject) throws DaoException {
		Object result = null;
		try {
			//debugSqlMap(statementName, parameterObject);
			result = sqlMap.queryForObject(statementName, parameterObject);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}

		return result;
	}

	public int update(String statementName, Object parameterObject) throws DaoException {
		int result = 0;
		try {
			//debugSqlMap(statementName, parameterObject);
			result = sqlMap.update(statementName, parameterObject);
			/*if (result > 0 && parameterObject instanceof Audit) { 
				//debugSqlMap("insertAuditTrail", parameterObject);
				sqlMap.update("AuditTrailSQL.insertAuditTrail", parameterObject);
			}*/
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	public Integer insert(String statementName, Object parameterObject) throws DaoException {
		Integer result = 0;
		try {
			//debugSqlMap(statementName, parameterObject);
			result = (Integer) sqlMap.insert(statementName, parameterObject);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}
	
	public int updateNotAuditTrail(String statementName, Object parameterObject) throws DaoException {
		int result = 0;
		try {
			//debugSqlMap(statementName, parameterObject);
			result = sqlMap.update(statementName, parameterObject);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}

	public void startTransaction() throws DaoException {
		try {
			sqlMap.startTransaction();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
	}

	public void commitTransaction() throws DaoException {
		try {
			sqlMap.commitTransaction();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
	}

	public void endTransaction() {
		try {
			sqlMap.endTransaction();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	public static Connection getConnection() throws SQLException {
		log.debug("BaseDAO : getConnection");
		Connection con = sqlMap.getDataSource().getConnection();
		if (!con.getAutoCommit()) con.setAutoCommit(true);
		return con;
	}

	/*

	public void debugSqlMap(String statementName, Object parameterObject) {
		log.debug("Map Name  = " + statementName);
		if (parameterObject != null) 
		log.debug("Parameter = {" + parameterObject.toString() + "}");
		MappedStatement mappedstatement = sqlMap.getMappedStatement(statementName);
		log.debug("SQL       = " + mappedstatement.getSql(parameterObject));
		log.debug("");
	}*/

}
