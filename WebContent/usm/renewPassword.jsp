<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script language="JavaScript">
	function confirmPassword(form) {
		result = validatePasswordForm(form);
		if (result) {
			if (form.jsNewPassword.value==form.jsNewPasswordConfirm.value) {
				// md5the old and new password;
				form.password.value = b64_md5(form.jsOldPassword.value);
				form.newPassword.value = b64_md5(form.jsNewPassword.value);
				form.jsOldPassword.value="";				
				form.jsNewPassword.value="";
				form.jsNewPasswordConfirm.value="";
				result=true;
			} else {
				alert('<bean:message key="error.passwordConfirm"></bean:message>');
				result=false;
			}
		}
		return result;
	}
</script>
<script src="<%=request.getContextPath()%>/scripts/view.js"></script>
<script src="<%=request.getContextPath()%>/scripts/md5.js"></script>
<script src="<%=request.getContextPath()%>/scripts/staticJS.jsp"></script>
<html:javascript formName="passwordForm" dynamicJavascript="true" staticJavascript="false" />
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.changePassword.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->
<html:form action="/usm/renewPassword" onsubmit="return confirmPassword(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="password" value=""></html:hidden>
	<html:hidden property="newPassword" value=""></html:hidden>
	<table border="0">
		<tbody>
			<tr>
				<td width="180" class="conLabel"><bean:message key="form.userCode"></bean:message></td>
				<td class="conText" colspan="3"><html:text property="userCode" maxlength="16" size="16" readonly="true" styleClass="frmReadOnly"></html:text></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.oldPassword"></bean:message></td>
				<td class="conText" colspan="3"><input type="password" name="jsOldPassword" size="16" maxlength="16"><font color="red">&nbsp;<html:errors property="password" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.password"></bean:message></td>
				<td class="conText" colspan="3"><input type="password" name="jsNewPassword" size="16" maxlength="16"><font color="red">&nbsp;<html:errors property="newPassword" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.passwordConfirm"></bean:message></td>
				<td class="conText" colspan="3"><input type="password" name="jsNewPasswordConfirm" size="16" maxlength="16"></td>
			</tr>
		</tbody>
	</table>
	<table border="0">
		<tbody>
			<tr>
				<td width="180"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.save"></bean:message>
				</html:submit> <html:reset property="btnReset" styleClass="frmButton">
					<bean:message key="button.reset"></bean:message>
				</html:reset></td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>
</html:html>
