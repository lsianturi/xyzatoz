<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<%@page import="com.benclaus.koperasi.model.usm.Login"%>
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
	function add() {
		doGoToUrl('<c:url value="/updateMapping.do?dispatch=add"/>');
	}
	function del(id) {
		doGoToUrl('<c:url value="/updateMapping.do?dispatch=delete"/>'+'&id='+id);
	}
	function edit(companyId, bookId, bookItemId, fromYear, toYear) {
		doGoToUrl('<c:url value="/updateMapping.do?dispatch=update"/>'+'&companyId='+companyId +'&bookId='+bookId +'&bookItemId='+bookItemId);
	}
</script>


</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.mapping.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/mapping.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.spm"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="usercode">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="spmList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.company"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="companyId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="companyList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.book"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bookId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="bookList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.bookItem"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bookItemId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="bookItemList" property="value" labelProperty="label" />
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

			<tr class="tblHeader"> 
				<td><bean:message key="form.mapping.company"/></td>
				<td><bean:message key="form.mapping.book"/></td>
				<td><bean:message key="form.mapping.bookItem"/></td>
				<td><bean:message key="form.mapping.cellYtdPrev"/></td>
				<td><bean:message key="form.mapping.cellYtdCurrentActual"/></td>
				<td><bean:message key="form.mapping.cellYtdCurrentPlan"/></td>
				<td><bean:message key="form.mapping.cellFy"/></td>
				<td><bean:message key="form.mapping.formula"/></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="map" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><c:out value="${map.company.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.bookItem.book.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.bookItem.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.cellYtdPrevActual}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.cellYtdCurrentActual}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.cellYtdCurrentPlan}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.cellFy}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${map.formula}"/>&nbsp;</td>
				</tr>	
			</c:forEach>
		</table>
	<%@include file="../includes/paging.html"%>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnEdit" styleClass="frmButton" onclick="edit(this.form.companyId.value, this.form.bookId.value, this.form.bookItemId.value)">
						<bean:message key="button.edit"></bean:message>
					</html:button>
				</td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>
</html:html>
