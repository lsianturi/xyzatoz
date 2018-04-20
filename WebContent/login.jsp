<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script src="scripts/staticJS.jsp"></script>
<script src="scripts/md5.js"></script>
<script language="JavaScript">
	function hashPassword(form) {
		result = validateLoginForm(form);
		if (result) {
			form.jsPassword.value=hex_md5(form.password.value);
			form.password.value = "";
			result=true;
		}
		return result;
	}
	function putFocus() {
		document.getElementById('userCode').focus();
		document.loginForm.userCode.focus();
	}
</script>
<script src="scripts/view.js"></script>
<html:javascript formName="loginForm" dynamicJavascript="true" staticJavascript="false" />
</HEAD>
<BODY onload="document.loginForm.userCode.focus();">
<table align="center" height="100%">
	<tbody>
		<tr height="20%">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<jsp:include page="includes/message.jsp" flush="false"></jsp:include>
				<div class="conTitle" align="center"><bean:message key="form.login.title"></bean:message></div>
				<html:form action="/login" onsubmit="hashPassword(this)">
					<html:hidden property="jsPassword" value=""></html:hidden>
					<table align="center">
						<tbody>
							<tr>
								<td class="conLabel"><bean:message key="form.userCode"></bean:message></td>
								<td class="conText" colspan="3" align="right"><html:text property="userCode" value="lambok.sianturi" styleClass="frmInput" maxlength="30" size="20" ></html:text></td>
							</tr>
							<tr>
								<td class="conLabel"><bean:message key="form.password"></bean:message></td>
								<td class="conText" colspan="3" align="right"><html:password property="password" value="abc" styleClass="frmInput" maxlength="30" size="20" ></html:password></td>
							</tr>
							<tr>
								<td colspan="4" align="right"><html:submit property="loginButton" styleClass="frmButton">
									<bean:message key="button.login"></bean:message>
								</html:submit></td>
							</tr>
						</tbody>
					</table>
				</html:form>
			</td>
		</tr>	
	</tbody>
</table>
</BODY>
</html:html>
