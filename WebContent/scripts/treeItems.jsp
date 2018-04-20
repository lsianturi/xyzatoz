
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<%@page import="com.benclaus.koperasi.model.usm.Login"%><%@ page language="java" %>
<%@ page contentType="application/x-javascript" %>
<%	Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN); 
	if (userLogin!=null) { 
		userLogin.getMenuTree(); 
%>
		<%= userLogin.getMenuTree() %>
<%	} else { %>
	var TREE_ITEMS = [['<%= getServletContext().getServletContextName().toUpperCase() %>',0,0]];
<%	} %>
