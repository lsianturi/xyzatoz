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
		doGoToUrl('<c:url value="/abs/SkipAction.do?dispatch=returned"/>');
	}

</script>
<script src="../scripts/view.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/calendar2.js"></script>
<html:javascript formName="skipEmpForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="skipEmpForm" property="dispatch" value="updateSave" >
					<bean:message key="form.skip.title"></bean:message>
				</logic:equal>
				<logic:equal name="skipEmpForm" property="dispatch" value="addSave">
					<bean:message key="form.skip.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/abs/SaveSkipAction" onsubmit="return validateSkipForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	
	<table  border="0">
		<tbody>
			
			<tr>
				<td class="conLabel"><bean:message key="form.report.empId"></bean:message></td>
				<td class="conText"><bean:write name="emp" property="employeeNo"/>
				</td><html:hidden property="employeeNo"></html:hidden>
			</tr>
			<tr>
                <td class="conLabel"><bean:message key="form.user.firstName"></bean:message></td>
                <td class="conText" colspan="3">
                    <bean:write name="emp" property="firstName"/>
                </td><html:hidden property="firstName"></html:hidden>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.user.lastName"></bean:message></td>
                <td class="conText" colspan="3"><bean:write name="emp" property="lastName"/>
                </td><html:hidden property="lastName"></html:hidden>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.user.department"></bean:message></td>
                <td class="conText" colspan="3"><bean:write name="emp" property="department"/>
                </td><html:hidden property="department"></html:hidden>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.user.position"></bean:message></td>
                <td class="conText" colspan="3"><bean:write name="emp" property="jobLevel"/>
                </td><html:hidden property="jobLevel"></html:hidden>
            </tr>
            <tr>
                <td class="conLabel"><bean:message key="form.user.email"></bean:message></td>
                <td class="conText" colspan="3"><bean:write name="emp" property="email"/>
                </td><html:hidden property="email"></html:hidden>
            </tr>
            <tr>
				<td class="conLabel">&nbsp;</td>
				<td class="conText" colspan="3">
					<logic:equal name="emp" property="skipForEmptyTapCheck" value="1">
						<input type="checkbox" name="skipForEmptyTapCheck" checked='checked'>Skip For Empty Tap Check</input>
					</logic:equal>
					<logic:equal name="emp" property="skipForEmptyTapCheck" value="0">
						<input type="checkbox" name="skipForEmptyTapCheck" >Skip For Empty Tap Check</input>
					</logic:equal>
					<html:hidden property="skipForEmptyTapCheck" value="false"/>
				</td>
			</tr>
			<tr>
                <td class="conLabel">&nbsp;</td>
                <td class="conText" colspan="3">
                	<logic:equal name="emp" property="skipAsNotificationSubject" value="1">
						<input type="checkbox" name="skipAsNotificationSubject" checked='checked'>Skip As Notification Subject</input>
					</logic:equal>
					<logic:equal name="emp" property="skipAsNotificationSubject" value="0">
						<input type="checkbox" name="skipAsNotificationSubject" >Skip As Notification Subject</input>
					</logic:equal>
					<html:hidden property="skipAsNotificationSubject" value="false"/>
					
                	<html:hidden property="skipAsNotificationSubject" value="false"/>
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