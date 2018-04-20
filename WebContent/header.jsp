
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.benclaus.koperasi.model.usm.User"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@page import="com.benclaus.koperasi.model.usm.Login"%>
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<html:html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isErrorPage="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="theme/Header.css" rel="stylesheet" type="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="icons/favicon.ico" type="image/x-icon">
<title></title>
<script src="scripts/view.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
var parentDoc = window.parent.document;
var menuStatus = 1;
function doMenu() {
	var cfs = parentDoc.getElementById("contentFrameset");
	if (menuStatus==1) {
		cfs.cols="0,*";
		menuStatus=0;
	} else {
		cfs.cols="215,*";
		menuStatus=1;
	}
}

</script>

</head>
<body marginheight="0" marginwidth="0" topmargin="0" leftmargin="0">

<table background="images/logo-e4.gif" cellspacing="0" cellpadding="0" border="0" width="100%" height="42">
	<tbody>
		<tr>
			<td width="238"><img src="images/logo-e1.gif"></td>
			<td align="right" width="100%"><img src="images/logo-e21.gif" alt=""></td>
		</tr>
	</tbody>
</table>

<!--
<table background="images/logo-e4.gif" cellspacing="0" cellpadding="0" border="0" width="100%">
	<tbody>
		<tr>
			<td width="238"><img height="40" width="238" src="images/logo-e.gif"></td>
			<td align="right" width="100%"><img height="40" width="178" src="images/logo-e2.gif"></td>
		</tr>
	</tbody>
</table>
-->

<%	
	Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
	if (userLogin!=null) {
	String userCode = userLogin.getUser().getUserCode();
	String[] us = userCode.split("@");
	if (us.length>1) userCode = us[0];
	
%>
<html:form action="/home">
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	background="images/gradien.gif">
	<tr>
		<td nowrap="nowrap" valign="middle" width="215" height="22"
			background="images/gradien1a.jpg" class="heaText"><html:button
			styleClass="frmInput" property="menu" onclick="javascript:doMenu()">
			<bean:message key="header.menu"></bean:message>
		</html:button></td>
		<td nowrap="nowrap" valign="middle" class="heaText" align="left"><font
			color="#000000"><strong>&nbsp;<%=userCode%>&nbsp;</strong>logged in as <strong><%=userLogin.getUser().getRoleName() %></strong></font></td>
		<td nowrap="nowrap" valign="middle" class="heaText" align="right">&nbsp;
		<a href="home.do?dispatch=firstPage"
			target="content"><bean:message key="header.home"></bean:message></a>&nbsp;|&nbsp;
		<a href="javascript:doTopGoToUrl('<%=request.getContextPath()%>/loggedout.jsp')"><bean:message key="header.logout"></bean:message></a>&nbsp; </font></td>
	</tr>
</table>
</html:form>
<% } %>
</body>
</html:html>
