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
	<c:if test="${DataList!=null}">
	lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
	totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
</script>


</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.log.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/logs.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.company"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="companyId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="companyList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.year"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="year">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.month"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="month">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="monthList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.search"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
	</table>
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="6">Search Result</td>
				<%--<td ><html:button  property="#btnedit" styleClass="frmButton" onclick="edit(this.form)"><bean:message key="button.edit"/></html:button></td> --%>
			</tr>

			
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="16" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="log" items="${DataList.list}" varStatus="status">
					<tr > 
						<td class="tblHeader"><bean:message key="form.log.company"/></td>
						<td class="celBorder" colspan="4"><c:out value="${log.company.name}"/>&nbsp;</td>
					</tr>
					<tr > 
						<td class="tblHeader"><bean:message key="form.log.year"/></td>
						<td class="celBorder" colspan="4"><c:out value="${log.year}"/>&nbsp;</td>
					</tr>
					<tr > 
						<td class="tblHeader"><bean:message key="form.log.month"/></td>
						<td class="celBorder" colspan="4"><c:out value="${log.month}"/>&nbsp;</td>
					</tr>
					<tr > 
						<td class="tblHeader"><bean:message key="form.log.file"/></td>
						<td class="celBorder" colspan="4"><c:out value="${log.fileName}"/>&nbsp;</td>
					</tr>
					<tr > 
						<td class="tblHeader"><bean:message key="form.log.time"/></td>
						<td class="celBorder" colspan="4"><c:out value="${log.processTime}"/>&nbsp;</td>
					</tr>
					<tr class="tblHeader"> 
						<td><bean:message key="form.log.book"/></td>
						<td><bean:message key="form.log.bookItem"/></td>
						<td><bean:message key="form.log.formula"/></td>
						<td><bean:message key="form.log.data"/></td>
						<td><bean:message key="form.log.amount"/></td>
					</tr>
					<c:forEach var="arg" items="${log.formulaItems}" varStatus="status">
						<c:choose>
							<c:when test="${status.count % 2 != 0}">
								<tr class="oddRow">
							</c:when>
							<c:otherwise>
								<tr class="evenRow">
							</c:otherwise>
						</c:choose>
						<td class="celBorder"><c:out value="${arg.bookItem.book.name}"/>&nbsp;</td>
						<td class="celBorder"><c:out value="${arg.bookItem.name}"/>&nbsp;</td>
						<td class="celBorder"><c:out value="${arg.formula}"/>&nbsp;</td>
						<td class="celBorder"><c:out value="${arg.formulaArgs}"/>&nbsp;</td>
						<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="3" value="${arg.value}"/>&nbsp;</td>
						</tr>
					</c:forEach>
					<tr>
						<td>&nbsp;</td>
					</tr>
			</c:forEach>
		</table>
	<%-- <%@include file="../includes/paging.html"%> --%>
	</c:if>
</html:form>
</BODY>
</html:html>
