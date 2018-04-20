package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class UserDAO extends BaseDAO {
	private static UserDAO userDAO = null;

	public static synchronized UserDAO getInstance() {
		if (userDAO == null)
			userDAO = new UserDAO();
		return userDAO;
	}
	
	private UserDAO() { super(); }
	
	public PaginatedList searchUser(Map map) throws DaoException {
		return (PaginatedList) super.getPaginatedList("UserSQL.searchUser", map); 
	}
	
	public int searchUserSize(Map map) throws DaoException {
		return ((Integer) super.getObject("UserSQL.searchUserSize", map)).intValue();
	}
	
	public int insertUser(User user) throws DaoException{		
		return super.update("UserSQL.insertUser", user);
	}

	public int updateUser(User user) throws DaoException {
		return super.update("UserSQL.updateUser", user);
	}	

	public int deleteUser(User user) throws DaoException {
		return super.update("UserSQL.deleteUser", user);
	}

    public int deactivateUser(User user) throws DaoException {
        return super.update("UserSQL.deactivateUser", user);
    }
    public User getUser(String userCode) throws DaoException {
		return (User) super.getObject("UserSQL.getUser", userCode);
	}
    public PaginatedList getUsers() throws DaoException {
		return (PaginatedList) super.getPaginatedList("UserSQL.getUsers", null);
	}
    public int updateLastLogon(String userCode) throws DaoException {
        return super.update("UserSQL.updateLastLogon", userCode);
    }
    public int updatePassword(Map map) throws DaoException {
        return super.update("UserSQL.updatePassword", map);
    }
    public int updateProfile(Map map) throws DaoException {
        return super.update("UserSQL.updateProfile", map);
    }
    public User getUserRole(String userCode) throws DaoException {
        return (User) super.getObject("UserSQL.getUserRole", userCode);
    }
    public User getUserRoleEmployee(String userCode) throws DaoException {
        return (User) super.getObject("UserSQL.getUserRoleEmployee", userCode);
    }
    public List<LabelValueBean> getUserCompanies(String userCode) throws DaoException {
        return (List<LabelValueBean>) super.getList("UserSQL.getUserCompanies", userCode);
    }
    public List<LabelValueBean> getUserCompany(String userCode) throws DaoException {
        return (List<LabelValueBean>) super.getList("UserSQL.getUserCompany", userCode);
    }
    
}