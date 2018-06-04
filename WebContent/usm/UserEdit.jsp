<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
</HEAD>

<script languange="javascript">
	function cancel(form, _dispatch) {
		window.location = '<c:url value="/usm/UserAction.do" />'+'?dispatch='+_dispatch+'&pageIndex='+form.pageIndex.value;
	}
</script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/view.js"></script>
<html:javascript formName="userForm" dynamicJavascript="true" staticJavascript="false" />
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"/>
<!--
<div class="conTitle"><bean:message key="form.roleUpdate.title"/></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="userForm" property="dispatch" value="updateSave" >
					<bean:message key="form.userUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="userForm" property="dispatch" value="addSave">
					<bean:message key="form.userAdd.title"></bean:message>
				</logic:equal>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/peran.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/usm/UserAction.do"	onsubmit="return validateUserForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="pageIndex"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel" width="180"><bean:message key="form.userCode"/></td>
				<logic:equal name="userForm" property="dispatch" value="updateSave" >
					<td class="conText" colspan="3"><html:text property='userCode' maxlength="16" size="16" readonly="true" styleClass="frmReadOnly"/><font color="red">&nbsp;<html:errors property="userCode" /></font></td>
				</logic:equal>
				<logic:equal name="userForm" property="dispatch" value="addSave">
					<td class="conText" colspan="3"><html:text property='userCode' maxlength="16" size="16"/><font color="red">&nbsp;<html:errors property="userCode" /></font></td>
				</logic:equal>
				
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.user.firstName"></bean:message></td>
				<td class="conText" colspan="3"><html:text property='firstName' maxlength="30" size="30" ></html:text><font color="red">&nbsp;<html:errors property="firstName" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.user.lastName"></bean:message></td>
				<td class="conText" colspan="3"><html:text property='lastName' maxlength="30" size="30" ></html:text><font color="red">&nbsp;<html:errors property="lastName" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.user.mobile"></bean:message></td>
				<td class="conText" colspan="3"><html:text property='mobile' maxlength="30" size="30" ></html:text><font color="red">&nbsp;<html:errors property="mobile" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.user.role"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="roleCode">
						<html:options collection="RoleList" property="roleCode" labelProperty="description" />
					</html:select>
				</td>
			</tr>
			<logic:equal name="userForm" property="dispatch" value="updateSave" >
			<tr>
				<td class="conLabel"><bean:message key="form.user.timeout"></bean:message></td>
				<td class="conText" colspan="3"><html:text property='sessionTimeOut' maxlength="30" size="30" ></html:text><font color="red">&nbsp;<html:errors property="sessionTimeOut" /></font></td>
			</tr>
			</logic:equal>
			<logic:equal name="userForm" property="dispatch" value="addSave">
			<html:hidden property="sessionTimeOut" value="30"></html:hidden>
			</logic:equal>
			<tr>
				<td class="conLabel"><bean:message key="form.user.status"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="activeStatus">
						<html:option value="1">Active</html:option>
						<html:option value="0">Inactive</html:option>
					</html:select>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="180">
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton"><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel(this.form, 'returned');"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>