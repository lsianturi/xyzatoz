<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		doGoToUrl('<c:url value="/updatePillar.do?dispatch=add" />');
	}
	function del(id) {
		doGoToUrl('<c:url value="/pillar.do?dispatch=delete"/>'+'&id='+id);
	}
	function edit(id) {
		doGoToUrl('<c:url value="/updatePillar.do?dispatch=update"/>'+'&id='+id);
	}
</script>
</HEAD>
<BODY>
	<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle"><bean:message key="form.pilar.title"></bean:message></td>
			<td class="conText" align="right"><a
				href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img
					src="<%=request.getContextPath()%>/icons/help.gif" width="21"
					height="21" border="0" alt=""></a></td>
		</tr>
	</table>
	<!-- Help Page Finish -->
	<html:hidden property="dispatch" value="search" />
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0"
			class="tblBorder">
			<tr class="tblHeader">
				<td><bean:message key="form.pilar.id" /></td>
				<td><bean:message key="form.pilar.name" /></td>
				<td align="center"><bean:message key="form.action"></bean:message></td>
			</tr>
			<c:if test="${DataList.totalSize==0}">
				<tr class="oddRow">
					<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
				</tr>
			</c:if>
			<c:forEach var="pilar" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>

				</c:choose>
				<td class="celBorder"><c:out value="${pilar.id}" />&nbsp;</td>
				<td class="celBorder"><c:out value="${pilar.name}" />&nbsp;</td>
				<td class="celBorder" align="center">
						<a
							href="javascript:del('<c:out value="${pilar.id}"/>')"><bean:message
								key="button.delete"></bean:message></a>&nbsp;|&nbsp;<a
							href="javascript:edit('<c:out value="${pilar.id}"/>')"><bean:message
								key="button.edit"></bean:message></a>
				</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnAdd" styleClass="frmButton" onclick="add()">
					<bean:message key="button.add"></bean:message>
				</html:button></td>
			</tr>
		</tbody>
	</table>
</BODY>

</html:html>
