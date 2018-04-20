<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script type="text/javascript">
	<c:if test="${DataList!=null}">
	lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
	totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	function add() {
		doGoToUrl('<c:url value="//abs/UpdateMesinAction.do?dispatch=add" />');
	}
	function del(ip) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			doGoToUrl('<c:url value="/abs/UpdateMesinAction.do?dispatch=delete"/>'+'&id='+ip);
		}
	}

	function edit(ip) {
	    doGoToUrl('<c:url value="/abs/UpdateMesinAction.do?dispatch=update"/>'+'&ip='+ip);
    }
</script>
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.searchMesin.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->
<html:form action="/abs/MesinAbsenAction.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mesin.ip"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="ip" maxlength="15" size="15"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mesin.bagian"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="bagian" maxlength="20" size="20"></html:text>
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
			<tr class="conTitle"> 
                <td colspan="6" align="center" class="celBorderYellowGrey"><bean:message key="form.mesin.warning"></bean:message></td>
            </tr>
			<tr class="tblHeader"> 
				<td><bean:message key="form.mesin.ip"/></td>
				<td><bean:message key="form.mesin.bagian"/></td>
				<td><bean:message key="form.mesin.terminal"/></td>
				<td><bean:message key="form.mesin.user"/></td>
                <td><bean:message key="form.mesin.password"/></td>
				<td align="center"><bean:message key="form.action"></bean:message></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="mesin" items="${DataList.list}" varStatus="status">
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
					<td class="celBorder"><bean:write name="mesin" property="ip"/>&nbsp;</td>
					<td class="celBorder"><bean:write name="mesin" property="bagian"/>&nbsp;</td>
					<td class="celBorder"><bean:write name="mesin" property="terminal"/>&nbsp;</td>
					<td class="celBorder"><bean:write name="mesin" property="userid"/>&nbsp;</td>
                    <td class="celBorder"><bean:write name="mesin" property="userpass"/>&nbsp;</td>
					<td class="celBorder" align="center">
						<a href="javascript:del('<c:out value="${mesin.id}"/>')"><bean:message key="form.delete"></bean:message></a> |
						<a href="javascript:edit('<c:out value="${mesin.ip}"/>')"><bean:message key="form.edit"></bean:message></a>
					</td>	
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
				</html:button></td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>

</html:html>
