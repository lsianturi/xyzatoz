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
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script  language="JavaScript">
	function cancel() {
		doGoToUrl('<c:url value="/company.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<html:javascript formName="companyForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="companyForm" property="dispatch" value="updateSave" >
					<bean:message key="form.companyUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="companyForm" property="dispatch" value="addSave">
					<bean:message key="form.companyAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updateCompany" onsubmit="return validateCompanyForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="companyId"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.company.name"></bean:message></td>
				<td class="conText">
					<html:text property="name" maxlength="60" size="40"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.company.prefix"></bean:message></td>
				<td class="conText">
					<html:text property="prefix" maxlength="6" size="6"></html:text>
				</td>
			</tr>
			<%-- <tr>
				<td width="100" class="conLabel"><bean:message key="form.company.pilar"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="pilar">
						<html:options collection="pilarList" property="id" labelProperty="name" />
					</html:select>
				</td>
			</tr> --%>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.company.spm"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="user">
						<html:options collection="spmList" property="value" labelProperty="label" />
					</html:select>
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