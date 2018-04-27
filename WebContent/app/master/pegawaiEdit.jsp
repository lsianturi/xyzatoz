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
		doGoToUrl('<c:url value="/pegawai.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<script src="scripts/calendar2.js"></script>
<html:javascript formName="pegawaiForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="pegawaiForm" property="dispatch" value="updateSave" >
					<bean:message key="form.pegawaiUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="pegawaiForm" property="dispatch" value="addSave">
					<bean:message key="form.pegawaiAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updatePegawai" onsubmit="return validatePegawaiForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.name"></bean:message></td>
				<td class="conText">
					<html:text property="nama" maxlength="60" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.address"></bean:message></td>
				<td class="conText">
					<html:textarea property="alamat" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.domisili"></bean:message></td>
				<td class="conText">
					<html:textarea property="domisili" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.telepon"></bean:message></td>
				<td class="conText">
					<html:text property="telepon" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.tglMasuk"></bean:message></td>
				<td class="conText">
					<html:text property="tanggalMasuk" maxlength="14" size="20"></html:text>
					<a href="javascript:cal1.popup();"><img src="icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.pegawai.keterangan"></bean:message></td>
				<td class="conText">
					<html:textarea property="keterangan" rows="2" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.pegawai.statusPegawai"></bean:message></td>
				<td class="conText">
					<html:select property="stsPegawai">
						<html:options collection="StatusList" property="id" labelProperty="status" />
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
	var cal1 = new calendar2(document.forms['pegawaiForm'].elements['tanggalMasuk']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
</script>
</html:html>