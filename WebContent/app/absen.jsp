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
		doGoToUrl('<c:url value="/abs/UpdateAction.do?dispatch=add" />');
	}
	function del(empId, tglKerja, jamAbsen) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			doGoToUrl('<c:url value="/abs/UpdateAction.do?dispatch=delete"/>'+'&empId='+empId+'&tglKerja='+tglKerja +'&jamAbsen='+jamAbsen);
		}
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
<html:form action="/abs/InputAction.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.report.empId"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="empId">
					<%if (!login.getUser().getRoleCode().equalsIgnoreCase("USER")) { %>
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<%} %>
						<html:options collection="empList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.calcAbsen.year"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="year">
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.calcAbsen.month"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="month">
						<html:options collection="monthList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.inputAbsen.tglKerja"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="tglKerja" maxlength="18" size="16"></html:text>
					<a href="javascript:cal1.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
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
				<td><bean:message key="form.report.fullname"/></td>
				<td><bean:message key="form.inputAbsen.tglKerja"/></td>
				<td><bean:message key="form.report.masuk"/></td>
				<td><bean:message key="form.report.keluar"/></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="abs" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${abs.isManual==1}">
						<tr class="manualRow">
					</c:when>
				</c:choose>
					<td class="celBorder"><c:out value="${abs.empId}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${abs.fullName}"/>&nbsp;</td>
					<td class="celBorder"><bean:write name="abs" property="tglKerja" format="yyyy-MM-dd"/>&nbsp;</td>
					<td class="celBorder"><bean:write name="abs" property="jamMasuk" format="HH:mm:ss"/></td>
					<td class="celBorder"><bean:write name="abs" property="jamKeluar" format="HH:mm:ss"/></td>
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
	</table> -->
</html:form>
</BODY>
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['absenSearch'].elements['tglKerja']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
</script>
</html:html>
