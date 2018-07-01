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
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script language="JavaScript">
	function cancel() {
		doGoToUrl('<c:url value="/home.do?dispatch=firstPage"/>');
	}
</script>
<script src="../scripts/view.js"></script>
<%-- <html:javascript formName="passwordForm" dynamicJavascript="true" staticJavascript="false" /> --%>
</HEAD>
<BODY>
<jsp:include page="../../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle">
				<logic:equal name="pusatForm" property="dispatch" value="view" >
					<bean:message key="form.kantor.title"></bean:message>
				</logic:equal>
				<logic:equal name="pusatForm" property="dispatch" value="updateSave">
					<bean:message key="form.kantorUpdate.title"></bean:message>
				</logic:equal>
				
			</td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->
<html:form action="/kantor/pusat" onsubmit="return confirmPassword(this);">
	<html:hidden property="dispatch"></html:hidden>
	<table border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.kantor.nama"></bean:message></td>
				<td class="conText">
					<html:text property="nama" maxlength="60" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.kantor.alamat"></bean:message></td>
				<td class="conText">
					<html:textarea property="alamat" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.telepon"></bean:message></td>
				<td class="conText">
					<html:text property="telepon" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.fax"></bean:message></td>
				<td class="conText">
					<html:text property="fax" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.badanHukum"></bean:message></td>
				<td class="conText">
					<html:text property="badanHukum" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.npwp"></bean:message></td>
				<td class="conText">
					<html:text property="npwp" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.siup"></bean:message></td>
				<td class="conText">
					<html:text property="siup" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.tdp"></bean:message></td>
				<td class="conText">
					<html:text property="tdp" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.logoKoperasi"></bean:message></td>
				<td class="conText">
					<html:text property="logoKoperasi" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.kantor.logoPerusahaan"></bean:message></td>
				<td class="conText">
					<html:text property="logoPerusahaan" size="60" maxlength="60"></html:text>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td width="180"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.save"></bean:message>
				</html:submit>&nbsp;
				<html:button property="btnCancel" styleClass="frmButton" onclick="cancel()"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>
</html:html>
