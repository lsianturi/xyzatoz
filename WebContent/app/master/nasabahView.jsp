<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
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
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.nasabahView.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->
	<c:if test="${nsbh != null}">
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.tglMasuk"></bean:message></td>
				<td class="conText">
					<fmt:formatDate value="${nsbh.tglMasuk}" pattern="dd/MM/yyyy"/>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.name"></bean:message></td>
				<td class="conText">
					<c:out value="${nsbh.nama}"></c:out>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.jenisKelamin"></bean:message></td>
				<td class="conText">
					<c:forEach var="sex" items="${SexList}" varStatus="status">
						<c:choose>
							<c:when test="${sex.id == nsbh.jenisKelamin.id}">
								<c:out value="${sex.status}"></c:out>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.address"></bean:message></td>
				<td class="conText">
					<c:out value="${nsbh.alamat}"></c:out>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.domisili"></bean:message></td>
				<td class="conText">
					<c:out value="${nsbh.domisili}"></c:out>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.telepon"></bean:message></td>
				<td class="conText">
					<c:out value="${nsbh.telepon}"></c:out>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusSipil"></bean:message></td>
				<td class="conText">
					<c:forEach var="sipil" items="${SipilList}" varStatus="status">
						<c:choose>
							<c:when test="${sipil.id == nsbh.statusSipil.id}">
								<c:out value="${sipil.status}"></c:out>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.perusahaan"></bean:message></td>
				<td class="conText">
					<c:forEach var="pt" items="${PerusahaanList}" varStatus="status">
						<c:choose>
							<c:when test="${pt.id == nsbh.pt.id}">
								<c:out value="${pt.status}"></c:out>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.bagian"></bean:message></td>
				<td class="conText">
					<c:out value="${nsbh.bagian}"></c:out>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.tglPayroll"></bean:message></td>
				<td class="conText">
					<fmt:formatDate value="${nsbh.tglPayroll}" pattern="dd/MM/yyyy"/>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusKaryawan"></bean:message></td>
				<td class="conText">
					<c:forEach var="pt" items="${StsKrywnList}" varStatus="status">
						<c:choose>
							<c:when test="${pt.id == nsbh.statusKaryawan.id}">
								<c:out value="${pt.status}"></c:out>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.bank"></bean:message></td>
				<td class="conText">
					<c:forEach var="pt" items="${BankList}" varStatus="status">
						<c:choose>
							<c:when test="${pt.id == nsbh.bank.id}">
								<c:out value="${pt.status}"></c:out>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.rekening"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.noRekeningRef"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.namaRef"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.jenisAnggota"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.statusKerja"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.keterangan"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.nasabah.nik"></bean:message></td>
				<td class="conText">
				</td>
			</tr>
			<tr>
				<td class="conLabel"></td>
				<td class="conText">
				</td>
			</tr>
		</tbody>
	</table>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td>
					<html:button property="btnBack" styleClass="frmButton" onclick="back()"><bean:message key="button.close"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</BODY>
</html:html>