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
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>

<script src="../scripts/view.js"></script>
<script src="../scripts/md5.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/calendar2.js"></script>
<script type="text/javascript">
	<c:if test="${DataList!=null}">
	lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
	totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	function add() {
		doGoToUrl('<c:url value="/abs/UpdateSkipAction.do?dispatch=add" />');
	}
	function edit(empid) {
	    doGoToUrl('<c:url value="/abs/UpdateSkipAction.do?dispatch=update"/>'+'&empId='+empid);
    }
</script>
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.inputAbsen.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/abs/SkipAction.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.user.department"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="department">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="deptList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.report.empId"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="employeeNo">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="empList" property="value" labelProperty="label" />
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
				<td colspan="3">Search Result</td>
				
			</tr>
			<tr class="tblHeader"> 
				<td><bean:message key="form.report.empId"/></td>
				<td><bean:message key="form.user.firstName"/></td>
				<td><bean:message key="form.user.lastName"/></td>
				<td><bean:message key="form.user.department"/></td>
				<td><bean:message key="form.user.position"/></td>
				<td>Skip for empty tap check</td>
				<td>Skip as notification subject</td>
				<td align="center"><bean:message key="form.action"></bean:message></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="emp" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><c:out value="${emp.employeeNo}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.firstName}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.lastName}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.department}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.jobLevel}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.skipForEmptyTapCheck}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${emp.skipAsNotificationSubject}"/>&nbsp;</td>
					<td class="celBorder" align="center">
						<a href="javascript:edit('<c:out value="${emp.employeeNo}"/>')"><bean:message key="form.edit"></bean:message></a>
					</td>	
				</tr>	
			</c:forEach>
		</table>
	<%@include file="../includes/paging.html"%>
	</c:if>
	<!-- <table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnAdd" styleClass="frmButton" onclick="add()">
					<bean:message key="button.add"></bean:message>
				</html:button></td>
			</tr>
		</tbody>
	</table>  -->
</html:form>
</BODY>
</html:html>
