package com.benclaus.koperasi.dao.usm;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.benclaus.koperasi.exception.DaoException;
import com.benclaus.koperasi.model.usm.LoginTrail;
import com.benclaus.koperasi.model.usm.Menu;
import com.benclaus.koperasi.utility.Constant;

/**
 * @author Lambok
 *
 */
public class UsmService {
	private static Logger log = Logger.getLogger(UsmService.class);
	private static UsmService usmService = null;
	
	private UsmDAO dao = UsmDAO.getInstance();
	private MenuService menuService = MenuService.getInstance();
	private UserService userService = UserService.getInstance();

	public static synchronized UsmService getInstance() {
		if (usmService==null) usmService= new UsmService();
		return usmService;
	}
	
	private UsmService() {
		super();
	}
	
	public int insertLoginTrail(LoginTrail loginTrail) throws DaoException {
        return dao.insertLoginTrail(loginTrail);
    }
    
    public List selectLoginTrail(Map map) throws DaoException {
        return dao.selectLoginTrail(map);
    }
    
    public int selectLoginTrailSize(Map map) throws DaoException {
        return dao.selectLoginTrailSize(map);
    }

	public String buildTreeMenu(String userCode, ResourceBundle rb) {
		StringBuffer sb = new StringBuffer("var TREE_ITEMS = [");
		
		try {
			traverseTreeMenu(menuService.getRootMenu(Constant.FIRST_MENU), userCode, sb, rb);
		} catch (Exception de) {
			log.info(de.getMessage(), de);
			return "var TREE_ITEMS = [['"+Constant.FIRST_MENU.toUpperCase()+"',0,0]];";
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("];");

		return sb.toString();
	}

	private void traverseTreeMenu(Menu menu, String userCode, StringBuffer sb, ResourceBundle rb) throws DaoException {
		if (menu != null) {
			// build self
			sb.append("['").append(rb.getString(menu.getMenuCode())).append("',");
			if (!menu.getMenuType().equals("2")) {
				// sub folder, traverse all child
				sb.append("0,0,");
				List child = menuService.selectUserMenu(userCode, menu.getMenuCode());
				if (child == null || child.size() == 0) child = menuService.selectUserMenu2("USER", menu.getMenuCode());
				Iterator itr = child.iterator();
				while (itr.hasNext()) {
					Menu childMenu = (Menu)itr.next();
					traverseTreeMenu(childMenu, userCode, sb, rb);
				}
			} else {
				sb.append("'").append(menu.getCommand()).append("'");
			}
			sb.append("],");
		}
	}
}