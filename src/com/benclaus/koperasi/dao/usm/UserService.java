package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.User;
import com.benclaus.koperasi.utility.LabelValueBean;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class UserService {
	private UserDAO dao = UserDAO.getInstance();
	private static UserService userDaoService = null;
	
	public static synchronized UserService getInstance() {
		if (userDaoService == null)
			userDaoService = new UserService();
		return userDaoService;
	}
	
	private UserService() { super(); }
	
	public PaginatedList searchUser(Map map) throws DaoException {
		return dao.searchUser(map);
	}
	
	public int searchUserSize(Map map) throws DaoException {
		return dao.searchUserSize(map);
	}
	
    public int insertUser(User userBean, User user, String process) throws DaoException {
    	userBean.setAuditTrail(user, process);
		return dao.insertUser(userBean);
	}	

	public int updateUser(User userBean, User user, String process) throws DaoException {
    	userBean.setAuditTrail(user, process);		
		return  dao.updateUser(userBean);
    }
    
	public int deleteUser(User userBean, User user, String process) throws DaoException {
		int result = 0;
    	try{
    		dao.startTransaction();
    		
	    	userBean.setAuditTrail(user, process);		    		
			result = dao.deleteUser(userBean);
			if (result < 1) { throw new DaoException(); }
			
			dao.commitTransaction();
    	}catch(DaoException de){
    		dao.endTransaction();
    		throw de;
    	}
    	return result;
    }

    public User getUser(String userCode) throws DaoException {
		return dao.getUser(userCode);
	}
	
	public int updateLastLogon(String userCode) throws DaoException {
		return dao.updateLastLogon(userCode);
	}
	
	public int updatePassword(Map map, User user, String process) throws DaoException {
		return dao.updatePassword(map);
	}
	public int updateProfile(Map map, User user, String process) throws DaoException {
		return dao.updateProfile(map);
	}
	
	public User getUserRole(String userRole) throws DaoException {
		return (User) dao.getUserRole(userRole);
	}
	public User getUserRoleEmployee(String userRole) throws DaoException {
		return (User) dao.getUserRoleEmployee(userRole);
	}
	public List<LabelValueBean> getUserCompanies(String userCode) throws DaoException {
        return dao.getUserCompanies(userCode);
    }
	public List<LabelValueBean> getUserCompany(String userCode) throws DaoException {
        return dao.getUserCompany(userCode);
    }
}