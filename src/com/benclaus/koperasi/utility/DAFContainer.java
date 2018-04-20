package com.benclaus.koperasi.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts.action.DynaActionForm;

/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DAFContainer extends DynaActionForm {
	private Map map = new HashMap();

	public DAFContainer(Map map) {
		this.map.putAll(map);
	}

	public void populate(DynaActionForm daf) {
		Iterator itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			try {
				daf.set(key, map.get(key));
			} catch (java.lang.IllegalArgumentException iae) {}
		}		
	}
	public String print() {
		return map.toString();
	}
}
