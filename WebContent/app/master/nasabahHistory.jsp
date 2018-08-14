<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<%@page import="com.benclaus.koperasi.model.usm.Login"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>

<script src="scripts/view.js"></script>
<script src="scripts/md5.js"></script>
<script src="scripts/staticJS.jsp"></script>
<script type="text/javascript">
</script>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.nasabahHist.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="3">Search Result</td>
			</tr>
			
			<tr class="tblHeader"> 
				<td><bean:message key="form.nasabah.tglMasuk"/></td>
				<td><bean:message key="form.nasabah.name"/></td>
				<td><bean:message key="form.nasabah.jenisKelamin"/></td>
				<td><bean:message key="form.nasabah.address"/></td>
				<td><bean:message key="form.nasabah.domisili"/></td>
				<td><bean:message key="form.nasabah.telepon"/></td>
				<td><bean:message key="form.nasabah.statusSipil"/></td>
				<td><bean:message key="form.nasabah.cabang"/></td>
				<td><bean:message key="form.nasabah.unit"/></td>
				
				<td><bean:message key="form.nasabah.perusahaan"/></td>
				<td><bean:message key="form.nasabah.bagian"/></td>
				<td><bean:message key="form.nasabah.tglPayroll"/></td>
				<td><bean:message key="form.nasabah.statusKaryawan"/></td>
				
				<td><bean:message key="form.nasabah.bank"/></td>
				<td><bean:message key="form.nasabah.nomorKartu"/></td>
				<td><bean:message key="form.nasabah.validThru"/></td>
				<td><bean:message key="form.nasabah.rekening"/></td>
				<td><bean:message key="form.nasabah.pinAtm"/></td>
				
				<td><bean:message key="form.nasabah.noRekeningRef"/></td>
				<td><bean:message key="form.nasabah.namaRef"/></td>
				
				<td><bean:message key="form.nasabah.jenisAnggota"/></td>
				<td><bean:message key="form.nasabah.statusKerja"/></td>
				
				<td><bean:message key="form.nasabah.nik"/></td>
				<td><bean:message key="form.nasabah.anAgent"/></td>
				<td><bean:message key="form.nasabah.aplikasi"/></td>
				<td><bean:message key="form.nasabah.keterangan"/></td>
				<td><bean:message key="form.createBy"/></td>
				<td><bean:message key="form.createTime"/></td>
			</tr>
				<c:if test="${empty DataList}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="nsbh" items="${DataList}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><fmt:formatDate value="${nsbh.tglMasuk}" pattern="dd/MM/yyyy"/></td>
					<td class="celBorder"><c:out value="${nsbh.nama}"/></td>
					<td class="celBorder"><c:out value="${nsbh.jenisKelamin.status}"/></td>
					<td class="celBorder"><c:out value="${nsbh.alamat}"/></td>
					<td class="celBorder"><c:out value="${nsbh.domisili}"/></td>
					<td class="celBorder"><c:out value="${nsbh.telepon}"/></td>
					<td class="celBorder"><c:out value="${nsbh.statusSipil.status}"/></td>
					<td class="celBorder"><c:out value="${nsbh.cabang.nama}"/></td>
					<td class="celBorder"><c:out value="${nsbh.unit.nama}"/></td>
					<td class="celBorder"><c:out value="${nsbh.pt.nama}"/></td>
					<td class="celBorder"><c:out value="${nsbh.bagian}"/></td>
					<td class="celBorder"><fmt:formatDate value="${nsbh.tglPayroll}" pattern="dd/MM/yyyy"/></td>
					<td class="celBorder"><c:out value="${nsbh.statusKaryawan.status}"/></td>
					<td class="celBorder"><c:out value="${nsbh.bank.nama}"/></td>
					<td class="celBorder"><c:out value="${nsbh.nomorKartu}"/></td>
					<td class="celBorder"><c:out value="${nsbh.validThru}"/></td>
					<td class="celBorder"><c:out value="${nsbh.noRekening}"/></td>
					<td class="celBorder"><c:out value="${nsbh.pinAtm}"/></td>
					<td class="celBorder"><c:out value="${nsbh.noRekeningRef}"/></td>
					<td class="celBorder"><c:out value="${nsbh.namaRef}"/></td>
					<td class="celBorder"><c:out value="${nsbh.jenisAnggota.status}"/></td>
					
					<td class="celBorder"><c:out value="${nsbh.statusKerja.status}"/></td>
					<td class="celBorder"><c:out value="${nsbh.nik}"/></td>
					<td class="celBorder"><c:out value="${nsbh.anAgent}"/></td>
					<td class="celBorder"><c:out value="${nsbh.aplikasi}"/></td>
					<td class="celBorder"><c:out value="${nsbh.keterangan}"/></td>
					<td class="celBorder"><c:out value="${nsbh.createdBy}"/></td>
					<td class="celBorder"><fmt:formatDate value="${nsbh.createDtm}" pattern="dd/MM/yyyy HH:mm"/></td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnAdd" styleClass="frmButton" onclick="javascript:back()">
					<bean:message key="button.close"></bean:message>
				</html:button></td>
			</tr>
		</tbody>
	</table>
</BODY>
</html:html>
