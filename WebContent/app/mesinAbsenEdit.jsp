<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script  language="JavaScript">
	function cancel() {
		doGoToUrl('<c:url value="/abs/MesinAbsenAction.do?dispatch=returned"/>');
	}

</script>
<script src="../scripts/view.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/calendar2.js"></script>
<html:javascript formName="mesinForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="mesinForm" property="dispatch" value="updateSave" >
					<bean:message key="form.mesinUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="mesinForm" property="dispatch" value="addSave">
					<bean:message key="form.mesinAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/abs/InputMesinAction" onsubmit="return validateAbsenForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<table  border="0">
		<tbody>
			<tr>
				<td class="conLabel" width="180"><bean:message key="form.mesin.id"></bean:message></td>
				<td class="conText" colspan=4">
					<html:text property="id" maxlength="10" size="10" readonly="true" styleClass="frmReadOnly"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.mesin.ip"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="ip" maxlength="15" size="15"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.mesin.bagian"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="bagian" maxlength="20" size="30"></html:text>
				</td>
			</tr>
			<tr>
                <td class="conLabel"><bean:message key="form.mesin.terminal"></bean:message></td>
                <td class="conText" colspan="3">
                    <html:text property="terminal" maxlength="4" size="4"></html:text>
                </td>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.mesin.user"></bean:message></td>
                <td class="conText" colspan="3">
                    <html:text property="userid" maxlength="20" size="30"></html:text>
                </td>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.mesin.password"></bean:message></td>
                <td class="conText" colspan="3">
                    <html:text property="userpass" maxlength="20" size="30"></html:text>
                </td>
            </tr>
		</tbody>
	</table>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton" ><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel()"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>