<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page 
language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
isErrorPage="false"
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="icons/favicon.ico" type="image/x-icon">
<script src="scripts/view.js"></script>
<title><bean:message key="application.title"></bean:message></title>
</head>
<body>
<script language="JavaScript">window.top.location="<%=request.getContextPath()%>/welcome.jsp";</script>
</body>
</html>
