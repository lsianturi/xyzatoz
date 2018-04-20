package com.benclaus.koperasi.dao.usm;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.beanutils.BeanUtils;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.model.usm.User;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class MenuService {
	private MenuDAO dao = MenuDAO.getInstance();
	private static MenuService menuService = null;
	
	public static synchronized MenuService getInstance() {
		if (menuService == null)
			menuService = new MenuService();
		return menuService;
	}
	
	private MenuService() { super(); }
	
	public PaginatedList getPaging(Map map) throws DaoException {
		return dao.getPaging(map);
	}
	
	public int getCountRow(Map map) throws DaoException {
		return dao.getPagingCount(map);
	}

    public int insertMenu(Menu menu, User user, String process) throws DaoException {
    	menu.setAuditTrail(user, process);
		return dao.insertMenu(menu);			
    }   
    
	public int updateMenu(Menu menu, User user, String process) throws DaoException {
    	menu.setAuditTrail(user, process);
		return dao.updateMenu(menu);
    }   
    
	public int deleteMenu(Menu menu, User user, String process) throws DaoException {
    	menu.setAuditTrail(user, process);
		return dao.deleteMenu(menu);
    }       
    
    public int deleteMenu(Map map, Vector vector, User user, String process) throws DaoException, InvocationTargetException, IllegalAccessException {
		int result = 0;
    	try{
    		dao.startTransaction();
			for (int i = 0; i < vector.size(); i++) {
				map.put("menuCode", vector.get(i).toString());
				Menu menu = new Menu();
				BeanUtils.copyProperties(menu, map);
				result = deleteMenu(menu, user, process);
				if (result <= 0) { throw new DaoException(); }
			}
			dao.commitTransaction();
    	}catch(DaoException de){
    		dao.endTransaction();
    		throw de;
    	}
    	return result;
    }
 
	public Menu getMenu(String menuCode) throws DaoException {
		return dao.getMenu(menuCode);
	}
	
	public List getMenuLabelValue() throws DaoException {
		return dao.getMenuLabelValue();
	}		
	
    public List getMenuParent(String menuCode) throws DaoException {
		return dao.getMenuParent(menuCode);
	}	
	
	public Menu getRootMenu(String menucode) throws DaoException {
		return (Menu) dao.getRootMenu(menucode);
	}
	
	public List selectUserMenu(String userCode, String parentMenuCode) throws DaoException {
		User user = new User();
		user.setUserCode(userCode);
		user.setParentMenuCode(parentMenuCode);
		return dao.selectUserMenu(user);
	}
	public List selectUserMenu2(String roleCode, String parentMenuCode) throws DaoException {
		User user = new User();
		user.setRoleCode(roleCode);
		user.setParentMenuCode(parentMenuCode);
		return dao.selectUserMenu2(user);
	}
	
	public List<String> selectAllUserMenuCode(String userCode) throws DaoException {
		return dao.selectAllUserMenuCode(userCode);
	}
	public List<String> selectUserMenuCode(String roleCode) throws DaoException {
        return dao.selectUserMenuCode(roleCode);
    }
}