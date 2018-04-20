<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="false"%>

<%@page import="com.benclaus.koperasi.model.usm.Login"%>
<%@page import="com.benclaus.koperasi.utility.Constant"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="icons/favicon.ico" type="image/x-icon">
<script src="scripts/view.js" type="text/javascript"></script>
<title><bean:message key="application.title"></bean:message></title>
</head>
<%
	Login userLogin = (Login) session.getAttribute(Constant.SES_USERLOGIN);
	if (userLogin != null) {
%>
<frameset id="mainFrameset" rows="64,1*,30" border="0" framespacing="0">
	<frame name="header" scrolling="no" noresize src="header.jsp"
		marginwidth="1" marginheight="1" frameborder="0">
	<frameset id="contentFrameset" cols="215,*" border="2"
		bordercolor="black" framespacing="0">
		<frame name="menu" src="menu.jsp" marginwidth="1" marginheight="1"
			scrolling="auto">
		<frame id="content" name="content" src="home.do?dispatch=firstPage"
			marginwidth="4" marginheight="4" scrolling="yes" frameborder="0">
	</frameset>
	<frame name="footer" scrolling="no" noresize src="includes/footer.html"
		marginwidth="1" marginheight="1" frameborder="0">
	<noframes>
	<body>
	<p>This page uses frames, but your browser doesn't support them.</p>
	</body>
	</noframes>
</frameset>

<%
	} else {
%>
<body>
<script type="text/javascript">doTopGoToUrl('<%=request.getContextPath()%>/index.jsp')</script>
</body>
<%
	}
%>
</html>
