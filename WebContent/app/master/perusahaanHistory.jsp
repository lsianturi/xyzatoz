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
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.company.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
	<c:if test="${DataList != null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="3">Search Result</td>
			</tr>
			<tr class="tblHeader"> 
				<td><bean:message key="form.company.name"/></td>
				<td><bean:message key="form.company.address"/></td>
				<td><bean:message key="form.company.area"/></td>
				<td><bean:message key="form.company.industri"/></td>
				<td><bean:message key="form.createBy"/></td>
				<td><bean:message key="form.createTime"/></td>
			</tr>
				<c:if test="${empty DataList}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="comp" items="${DataList}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><c:out value="${comp.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.alamat}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.area.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.industri.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.createdBy}"/></td>
					<td class="celBorder"><fmt:formatDate value="${comp.createDtm}" pattern="dd/MM/yyyy HH:mm"/></td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnBack" styleClass="frmButton" onclick="javascript:back()">
					<bean:message key="button.close"></bean:message>
				</html:button></td>
			</tr>
		</tbody>
	</table>
</BODY>
</html:html>
