<%@page import="com.benclaus.koperasi.utility.Constant"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<head>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>
<script src="scripts/view.js"></script>
<title></title>
</head>
<body>
	<logic:messagesPresent property="<%=Constant.GLOBALERROR%>">
		<table class="msgBorder" width="90%" border="1" cellpadding="0"
			cellspacing="0" align="center">
			<tbody>
				<tr>
					<td>
						<table width="100%" border="0">
							<tbody>
								<tr>
									<td><h1>Ooops</h1>
										<div class="main-wthree">
											<h1><html:errors property="<%=Constant.GLOBALERROR%>" /><c:out value="${message}" /></h1>
										</div>
									</td>
								</tr>
							</tbody>
						</table> 
					</td>
				</tr>
			</tbody>
		</table>
	</logic:messagesPresent>
	<logic:messagesPresent property="<%=Constant.GLOBALMESSAGE%>">
		<table class="msgBorder" width="90%" border="1" cellpadding="0"
			cellspacing="0" align="center">
			<tbody>
				<tr>
					<td>
					<table width="100%" border="0">
							<tbody>
								<tr>
									<td><h1>Success</h1>
										<div class="main-wthree">
											<h1><html:errors property="<%=Constant.GLOBALMESSAGE%>" /><c:out value="${message}" /></h1>
										</div>
									</td>
								</tr>
							</tbody>
						</table> 
					</td>
				</tr>
			</tbody>
		</table>
	</logic:messagesPresent>
</body>
</html:html>
