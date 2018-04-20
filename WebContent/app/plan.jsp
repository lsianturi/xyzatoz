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
	function add() {
		doGoToUrl('<c:url value="/updatePlan.do?dispatch=add"/>');
	}
	function del(id) {
		doGoToUrl('<c:url value="/updatePlan.do?dispatch=delete"/>'+'&id='+id);
	}
	function edit(companyId, bookId, bookItemId, fromYear, toYear) {
		doGoToUrl('<c:url value="/updatePlan.do?dispatch=update"/>'+'&companyId='+companyId +'&bookId='+bookId +'&bookItemId='+bookItemId +'&fromYear='+fromYear +'&toYear='+toYear);
	}
	function excel() {
		doGoToUrl('<c:url value="/plan.do?dispatch=export2Excel" />');
	}
</script>


</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.plan.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/plan.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.spm"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="usercode">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="spmList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
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
				<td width="100" class="conLabel"><bean:message key="form.plan.book"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bookId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="bookList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.bookItem"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bookItemId">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="bookItemList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.year"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="fromYear">
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>&nbsp;to&nbsp;
					<html:select property="toYear">
						<html:options collection="yearList" property="value" labelProperty="label" />
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
				<td colspan="4">Search Result</td>
				<%--<td ><html:button  property="#btnedit" styleClass="frmButton" onclick="edit(this.form)"><bean:message key="button.edit"/></html:button></td> --%>
				<c:if test="${DataList.totalSize>0}">
				<td align="right"><html:button property="btnExcel" styleClass="frmButton" onclick="excel()">
						<bean:message key="button.download"></bean:message>
					</html:button></td>
				</c:if>
			</tr>

			<tr class="tblHeader"> 
				<td><bean:message key="form.plan.company"/></td>
				<td><bean:message key="form.plan.book"/></td>
				<td><bean:message key="form.plan.bookItem"/></td>
				<td><bean:message key="form.plan.year"/></td>
				<td><bean:message key="form.plan.plan1"/></td>
				<td><bean:message key="form.plan.plan2"/></td>
				<td><bean:message key="form.plan.plan3"/></td>
				<td><bean:message key="form.plan.plan4"/></td>
				<td><bean:message key="form.plan.plan5"/></td>
				<td><bean:message key="form.plan.plan6"/></td>
				<td><bean:message key="form.plan.plan7"/></td>
				<td><bean:message key="form.plan.plan8"/></td>
				<td><bean:message key="form.plan.plan9"/></td>
				<td><bean:message key="form.plan.plan10"/></td>
				<td><bean:message key="form.plan.plan11"/></td>
				<td><bean:message key="form.plan.plan12"/></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="16" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="plan" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><c:out value="${plan.company.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${plan.bookItem.book.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${plan.bookItem.name}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${plan.year}"/>&nbsp;</td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="" value="${plan.plan1}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan2}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan3}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan4}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan5}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan6}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan7}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan8}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan9}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan10}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan11}"/></td>
					<td class="celBorder"><fmt:formatNumber type="number" maxFractionDigits="11" minFractionDigits="2" value="${plan.plan12}"/></td>
				</tr>	
			</c:forEach>
		</table>
	<%@include file="../includes/paging.html"%>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnAdd" styleClass="frmButton" onclick="add()">
					<bean:message key="button.add"></bean:message>
				</html:button>
				<c:if test="${DataList!=null}">
				<c:if test="${DataList.totalSize>0}">
					<html:button property="btnEdit" styleClass="frmButton" onclick="edit(this.form.companyId.value, this.form.bookId.value, this.form.bookItemId.value, this.form.fromYear.value, this.form.toYear.value)">
						<bean:message key="button.edit"></bean:message>
					</html:button>
				</c:if>
				</c:if></td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>
</html:html>
