package com.benclaus.koperasi.model.usm;

import java.util.List;

import com.benclaus.koperasi.model.Employee;

/**
 * @author Lambok S
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Login {
	private User user;
	private List menuCodeList;
	private boolean loggedIn = false;
	private String menuTree;
	
	/**
	 * Returns the user.
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user The user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Returns the loggedIn.
	 * @return boolean
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the loggedIn.
	 * @param loggedIn The loggedIn to set
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean isHasMenuAccess(String menuCode) {
		return menuCodeList.contains(menuCode);
	}
	/**
	 * Returns the menuCodeList.
	 * @return List
	 */
	public List getMenuCodeList() {
		return menuCodeList;
	}

	/**
	 * Sets the menuCodeList.
	 * @param menuCodeList The menuCodeList to set
	 */
	public void setMenuCodeList(List menuCodeList) {
		this.menuCodeList = menuCodeList;
	}
	/**
	 * Returns the menuTree.
	 * @return String
	 */
	public String getMenuTree() {
		return menuTree;
	}

	/**
	 * Sets the menuTree.
	 * @param menuTree The menuTree to set
	 */
	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}

}
