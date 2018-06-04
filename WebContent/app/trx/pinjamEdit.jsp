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
		doGoToUrl('<c:url value="/nasabah.do?dispatch=returned"/>');
	}
</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<script src="scripts/calendar2.js"></script>
<html:javascript formName="nasabahForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="nasabahForm" property="dispatch" value="updateSave" >
					<bean:message key="form.nasabahUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="nasabahForm" property="dispatch" value="addSave">
					<bean:message key="form.nasabahAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updateNasabah" onsubmit="return validateNasabahForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.name"></bean:message></td>
				<td class="conText">
					<html:text property="nama" maxlength="60" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.jenisKelamin"></bean:message></td>
				<td class="conText">
					<html:select property="jnsKelamin">
						<html:options collection="SexList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.address"></bean:message></td>
				<td class="conText">
					<html:textarea property="alamat" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.domisili"></bean:message></td>
				<td class="conText">
					<html:textarea property="domisili" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusSipil"></bean:message></td>
				<td class="conText">
					<html:select property="stsSipil">
						<html:options collection="SipilList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.telepon"></bean:message></td>
				<td class="conText">
					<html:text property="telepon" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.perusahaan"></bean:message></td>
				<td class="conText">
					<html:select property="perusahaan">
						<html:options collection="PerusahaanList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.bagian"></bean:message></td>
				<td class="conText">
					<html:text property="bagian" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusKaryawan"></bean:message></td>
				<td class="conText">
					<html:select property="stsKrywn">
						<html:options collection="StsKrywnList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.bank"></bean:message></td>
				<td class="conText">
					<html:select property="bankId">
						<html:options collection="BankList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.rekening"></bean:message></td>
				<td class="conText">
					<html:text property="noRekening" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.noRekeningRef"></bean:message></td>
				<td class="conText">
					<html:text property="noRekeningRef" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.namaRef"></bean:message></td>
				<td class="conText">
					<html:text property="namaRef" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.tglRekening"></bean:message></td>
				<td class="conText">
					<html:text property="tglRek" maxlength="14" size="20"></html:text>
					<a href="javascript:cal1.popup();"><img src="icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.keterangan"></bean:message></td>
				<td class="conText">
					<html:textarea property="keterangan" rows="2" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.agent"></bean:message></td>
				<td class="conText">
					<html:select property="agentId">
						<html:option value="0">&nbsp;</html:option>
						<html:options collection="AgentList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusAnggota"></bean:message></td>
				<td class="conText">
					<html:select property="stsAnggota">
						<html:options collection="StsAgtList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.jenisAnggota"></bean:message></td>
				<td class="conText">
					<html:select property="jnsAnggota">
						<html:options collection="JnsAgtList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"></td>
				<td class="conText">
					<html:checkbox property="anAgent"></html:checkbox><bean:message key="form.nasabah.agent"></bean:message>
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
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['nasabahForm'].elements['tglRek']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
</script>
</BODY>
</html:html>