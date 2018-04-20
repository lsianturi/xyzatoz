package com.benclaus.koperasi.dao.usm;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.Role;
import com.benclaus.koperasi.model.usm.RoleMenu;
import com.benclaus.koperasi.model.usm.User;
import com.ibatis.common.util.PaginatedList;
/**
 * @author Lambok
 *
 */
public class RoleService {
	private RoleDAO dao = RoleDAO.getInstance();
	private static RoleService roleService = null;
	
	public static synchronized RoleService getInstance() {
		if (roleService == null)
			roleService = new RoleService();
		return roleService;
	}
	
	private RoleService() { super(); }
	
	public PaginatedList getPaging(Map map) throws DaoException {
		return dao.getPaging(map);
	}
	
	public int getCountRow(Map map) throws DaoException {
		return dao.getCountRow(map);
	}
	
	public List listRole()throws DaoException {
		return dao.listRole();
	}
	public int insertRole(Role role, User user, String process) throws DaoException {
		role.setAuditTrail(user, process);
		return dao.insertRole(role);
	}

	public int updateRole(Role role, User user, String process) throws DaoException {
		role.setAuditTrail(user, process);
		return dao.updateRole(role);
    }   

    public int deleteRole(Role role, User user, String process) throws DaoException {
		int result = 0;
    	try{
    		dao.startTransaction();

			role.setAuditTrail(user, process);    		
			result = dao.deleteRole(role);
			if (result < 1) { throw new DaoException(); }

			role.setAuditTrail(user, "deleteAllMenu");    					
			result += dao.deleteRoleMenuAll(role);			
			
			dao.commitTransaction();
    	}catch(DaoException de){
    		dao.endTransaction();
    		throw de;
    	}
    	return result;
    }
    
    public Role getRole(String roleCode) throws DaoException {
		return dao.getRole(roleCode);
	}
    
    public List<Role> getRoles() throws DaoException {
		return dao.getRoles();
	}
	
    public List getRoleMenuHas(RoleMenu roleMenu) throws DaoException {
		return dao.getRoleMenuHas(roleMenu);
	} 
	
	public List getRoleMenuNoHas(RoleMenu roleMenu) throws DaoException {
		return dao.getRoleMenuNoHas(roleMenu);
	} 
    
    public int insertRoleMenu(RoleMenu roleMenu, User user, String process) throws DaoException {
		roleMenu.setAuditTrail(user, process);    	
		return dao.insertRoleMenu(roleMenu);
	} 	
	
    public int deleteRoleMenu(RoleMenu roleMenu, User user, String process) throws DaoException {
		roleMenu.setAuditTrail(user, process);    	
		return dao.deleteRoleMenu(roleMenu);
	} 		

	
	public int deleteRoleMenu(String roleCode, Vector vMenuCode, Map map, User user, String process) throws DaoException {
		int result = 0;
		try{
			dao.startTransaction();
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRoleCode(roleCode);
			
			for (int i = 0; i < vMenuCode.size(); i++) {
				String menuCode = (String) vMenuCode.get(i);
				roleMenu.setMenuCode(menuCode);
				result = deleteRoleMenu(roleMenu, user, process);
				if (result < 1) { throw new DaoException(); }
			}
			dao.commitTransaction();
		}catch(Exception e) {
			dao.endTransaction();
			throw new DaoException(e.getMessage(), e.fillInStackTrace());
		}
		return result;
	}

	public List getDeleteRoleMenu(RoleMenu roleMenu) throws DaoException {
		return dao.getDeleteRoleMenu(roleMenu);
	}
}