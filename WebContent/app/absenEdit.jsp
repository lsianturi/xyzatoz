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
		doGoToUrl('<c:url value="/abs/InputAction.do?dispatch=returned"/>');
	}

</script>
<script src="../scripts/view.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/calendar2.js"></script>
<html:javascript formName="absenForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="absenForm" property="dispatch" value="updateSave" >
					<bean:message key="form.absenUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="absenForm" property="dispatch" value="addSave">
					<bean:message key="form.absenAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/abs/UpdateAction" onsubmit="return validateAbsenForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel" width="180"><bean:message key="form.report.empId"></bean:message></td>
				<td class="conText" colspan="3">
					<logic:equal name="absenForm" property="dispatch" value="updateSave" >
						<html:text property="empId" maxlength="15" size="15" readonly="true" styleClass="frmReadOnly"></html:text>
					</logic:equal>
					<logic:equal name="absenForm" property="dispatch" value="addSave">
						<html:select property="empId">
						<html:options collection="empList" property="value" labelProperty="label" />
					</html:select>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.inputAbsen.tglKerja"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="tglKerja" maxlength="18" size="16"></html:text>
					<a href="javascript:cal1.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.inputAbsen.jamAbsen"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="jam" maxlength="2" size="2"></html:text>:<html:text property="menit" maxlength="2" size="2"></html:text>:<html:text property="detik" maxlength="2" size="2"></html:text>
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
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['absenForm'].elements['tglKerja']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
</script>
</html:html>