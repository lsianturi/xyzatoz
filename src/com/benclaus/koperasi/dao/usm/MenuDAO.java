package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.model.usm.User;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class MenuDAO extends BaseDAO {
	private static MenuDAO menuDAO = null;

	public static synchronized MenuDAO getInstance() {
		if (menuDAO == null)
			menuDAO = new MenuDAO();
		return menuDAO;
	}
	
	private MenuDAO() { super(); }
	
	public PaginatedList getPaging(Map map) throws DaoException {
		return (PaginatedList) super.getPaginatedList("MenuSQL.getPagingMenu", map);
	}
	
	public int getPagingCount(Map map) throws DaoException {
		return ((Integer) super.getObject("MenuSQL.getCountRowMenu", map)).intValue();
	}	
	
	public int insertMenu(Menu menu) throws DaoException{		
		return super.update("MenuSQL.insertMenu", menu);
	}
	
	public int updateMenu(Menu menu) throws DaoException {
		return super.update("MenuSQL.updateMenu", menu);
	}
	
	public int deleteMenu(Menu menu) throws DaoException {
		return super.update("MenuSQL.deleteMenu", menu);
	}

	public Menu getMenu(String menuCode) throws DaoException {
		return (Menu) super.getObject("MenuSQL.getMenu", menuCode);
	}

	public List getMenuParent(String menuCode) throws DaoException {
		return super.getList("MenuSQL.getMenuParent", menuCode);
	}		

    public Menu getRootMenu(String value) throws DaoException {
        return (Menu) super.getObject("MenuSQL.getRootMenu", value);
    }

	public List getMenuLabelValue() throws DaoException {
		return super.getList("MenuSQL.getMenuLabelValue", null);
	}

    public List selectUserMenu(User user) throws DaoException {
        return super.getList("MenuSQL.selectUserMenu", user);
    }
    public List selectUserMenu2(User user) throws DaoException {
        return super.getList("MenuSQL.selectUserMenu2", user);
    }
	
    public List<String> selectAllUserMenuCode(String usercode) throws DaoException {
        return (List<String>) super.getList("MenuSQL.selectAllUserMenuCode", usercode);
    }
    public List<String> selectUserMenuCode(String roleCode) throws DaoException {
        return (List<String>) super.getList("MenuSQL.selectUserMenuCode", roleCode);
    }
    
}