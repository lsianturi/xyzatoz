<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<%@ page 
language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
isErrorPage="false"
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<title></title>
<script src="scripts/view.js"></script>
</head>
<body>
<table class="msgBorder" width="90%" border="1" cellpadding="0" cellspacing="0" align="center">
	<tbody>
		<tr>
			<td>
			<table width="100%" border="0">
				<tbody>
					<tr>
						<td align="left" valign="top" class="conLabel"><img border="0" src="<%=request.getContextPath()%>/icons/information.gif" width="19" height="19"></td>
						<td align="left" width="100%" class="conText"><bean:message key="form.logoutMessage0"/>&nbsp;<a href="javascript:doTopGoToUrl('<%=request.getContextPath()%>/index.jsp');"><bean:message key="form.logoutMessage1"/></a>&nbsp;<bean:message key="form.logoutMessage2"/></td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html:html>
