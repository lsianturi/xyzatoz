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
		window.location = '<c:url value="/usm/RoleAction.do" />'+'?dispatch='+_dispatch+'&pageIndex='+form.pageIndex.value;
	}
</script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/view.js"></script>
<html:javascript formName="roleForm" dynamicJavascript="true" staticJavascript="false" />
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"/>
<!--
<div class="conTitle"><bean:message key="form.roleUpdate.title"/></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.roleUpdate.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/peran.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/usm/RoleAction.do"	onsubmit="return validateRoleForm(this);">
	<html:hidden property="dispatch" value=""></html:hidden>
	<html:hidden property="pageIndex"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel" width="180"><bean:message key="form.roleCode"/></td>
				<td class="conText" colspan="3"><html:text property='roleCode' maxlength="16" size="16" readonly="true" styleClass="frmReadOnly"/><font color="red">&nbsp;<html:errors property="roleCode" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.name"></bean:message></td>
				<td class="conText" colspan="3"><html:text property='description' maxlength="30" size="30" ></html:text><font color="red">&nbsp;<html:errors property="description" /></font></td>
			</tr>
		</tbody>
	</table>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="180">
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton" onclick="changeDispatch(this.form,'updateSave');"><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel(this.form, 'returned');"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>