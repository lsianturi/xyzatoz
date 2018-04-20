package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;

import com.benclaus.koperasi.dao.BaseDAO;
import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Role;
import com.benclaus.koperasi.model.usm.RoleMenu;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class RoleDAO extends BaseDAO {
	private static RoleDAO roleDao = null;

	public static synchronized RoleDAO getInstance() {
		if (roleDao == null)
			roleDao = new RoleDAO();
		return roleDao;
	}
	
	private RoleDAO() { super(); }

	public PaginatedList getPaging(Map map) throws DaoException {
		return (PaginatedList) super.getPaginatedList("RoleSQL.getPagingRole", map);
	}
	
	public int getCountRow(Map map) throws DaoException {
		return ((Integer) super.getObject("RoleSQL.getCountRowRole", map)).intValue();
	}	
	
	public List listRole() throws DaoException {
		return super.getList("RoleSQL.listRole", null);
	}
	public int insertRole(Role role) throws DaoException{		
		return super.update("RoleSQL.insertRole", role);
	}
	
	public int updateRole(Role role) throws DaoException {
		return super.update("RoleSQL.updateRole", role);
	} 
	
	public int deleteRole(Role role) throws DaoException {
		return super.update("RoleSQL.deleteRole", role);
	}

	public int deleteRoleMenuAll(Role role) throws DaoException {
		return super.update("RoleSQL.deleteRoleMenuAll", role);
	}
	
	public Role getRole(String roleCode) throws DaoException {
		return (Role)super.getObject("RoleSQL.getRole", roleCode);
	}
	
	public List<Role> getRoles() throws DaoException {
		return (List<Role>) super.getList("RoleSQL.getRoles", null);
	}

	public List getRoleMenuHas(RoleMenu roleMenu) throws DaoException {
		return super.getList("RoleSQL.getRoleMenuHas", roleMenu);
	}
	
	public List getRoleMenuNoHas(RoleMenu roleMenu) throws DaoException {
		return super.getList("RoleSQL.getRoleMenuNoHas", roleMenu);
	}

	public int insertRoleMenu(RoleMenu roleMenu) throws DaoException {
		return super.update("RoleSQL.insertRoleMenu", roleMenu);
	} 	

	public int deleteRoleMenu(RoleMenu roleMenu) throws DaoException {
		return super.update("RoleSQL.deleteRoleMenu", roleMenu);
	}

	public List getDeleteRoleMenu(RoleMenu roleMenu) throws DaoException {
		return super. getList("RoleSQL.getDeleteRoleMenu", roleMenu);
	}
}