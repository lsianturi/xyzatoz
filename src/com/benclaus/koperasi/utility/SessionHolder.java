package com.benclaus.koperasi.utility;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



/**
 * @author 
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SessionHolder implements HttpSessionListener {
	private ServletContext context = null;
	private HashMap sessions = new HashMap();
	
	public SessionHolder() {
	}
	
	public HashMap getSessionHolder() {
		return sessions;
	}

	public HashMap getSessionLoginHolder() {
		return sessions;
	}
	
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.put(session.getId(), session);
		
		//if(context == null)
		//storeInServletContext(event);
	}
	
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
	}
	
	/*private void storeInServletContext(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		context = session.getServletContext();
		context.setAttribute("sessionHolder", this);
	}*/
	
} 
