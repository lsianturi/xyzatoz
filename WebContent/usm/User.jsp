
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html> 
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"
%> 
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
</HEAD>

<script src="../scripts/view.js"></script>
<script language="JavaScript">
	<c:if test="${DataList!=null}">
		lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
		totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	function edit(pk) {
		document.forms[0].action = '<c:url value="/usm/UserAction.do"/>';
		document.forms[0].dispatch.value = "update";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].userCode.value = document.forms[0].pk.value;
		} else {
			document.forms[0].userCode.value = document.forms[0].pk[pk-1].value;
		}
		document.forms[0].submit();
	}
	function del(pk) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			document.forms[0].action = '<c:url value="/usm/UserAction.do"/>';
			document.forms[0].dispatch.value = "delete";
			if (document.forms[0].pk[pk-1] == null) {
				document.forms[0].roleCode.value = document.forms[0].pk.value;
			} else {
				document.forms[0].roleCode.value = document.forms[0].pk[pk-1].value;
			}
			document.forms[0].submit();
		}
	}
	function assign(pk) {
		document.forms[0].action = '<c:url value="/usm/RoleAction.do"/>';
		document.forms[0].dispatch.value = "assign";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].roleCode.value = document.forms[0].pk.value;				
		} else {
			document.forms[0].roleCode.value = document.forms[0].pk[pk-1].value;
		}
		document.forms[0].submit();
	}	
	function detail(pk) {
		document.forms[0].action = '<c:url value="/usm/RoleAction.do"/>';
		document.forms[0].dispatch.value = "detail";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].roleCode.value = document.forms[0].pk.value;				
		} else {
			document.forms[0].roleCode.value = document.forms[0].pk[pk-1].value;
		}
		document.forms[0].submit();
	}		
	
</script>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"/>
<!--
<div class="conTitle"><bean:message key="form.role.title"/></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.user.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/peran.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/usm/UserAction.do"> 
<html:hidden property="dispatch" value="search"></html:hidden>
<html:hidden property="totalPage"> </html:hidden>
<html:hidden property="userCode"> </html:hidden>
<table width="100%">
	<tbody>
		<tr>
			<td class="conLabel" width="180"><bean:message key="form.search"/></td>
			<td colspan="3" class="conText">
				<html:select property="searchColumn">
					<html:option value="userCode"><bean:message key="form.userCode"/></html:option>
					<html:option value="firstName"><bean:message key="form.user.firstName"/></html:option>
					<html:option value="lastName"><bean:message key="form.user.lastName"/></html:option>
					<html:option value="roleCode"><bean:message key="form.user.role"/></html:option>
				</html:select>
			<html:text property="searchValue" maxlength="30" size="30" onchange="upperValue(this)"/>

				<html:submit property="searchButton" styleClass="frmButton" onclick="changeDispatchMenu(this.form,'search');">
					<bean:message key="form.search"/>
				</html:submit>
			</td>
		</tr>
	</tbody>
</table>

<c:if test="${DataList!=null}">
	<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
	<tr class="tblHeader"> 
		<td><bean:message key="form.user.userCode"/></TD>
		<td><bean:message key="form.user.firstName"/></TD>
		<td><bean:message key="form.user.lastName"/></TD>
		<td><bean:message key="form.user.role"/></TD>
		<td><bean:message key="form.user.mobile"/></TD>
		<td><bean:message key="form.user.timeout"/></TD>
		<td><bean:message key="form.user.status"/></TD>
		<td><bean:message key="form.user.lastLogon"/></TD>
		<td align="center"><bean:message key="form.action"/></TD>
	</TR> 
	<c:if test="${DataList.totalSize==0}">
		<tr class="oddRow">
			<td class="celBorder" colspan="9" align="center"><bean:message
				key="form.noDataFound"></bean:message></td>
		</tr>
	</c:if>
			
<c:forEach var="dataRole" items="${DataList.list}" varStatus="status">
	<c:choose>
		<c:when test="${status.count % 2 != 0}">
			<tr class="oddRow">
		</c:when>
		<c:otherwise>
			<tr class="evenRow">
		</c:otherwise>
	</c:choose>
	<TR>	
		<td class="celBorder"><c:out value="${dataRole.userCode}"/>&nbsp;</TD>
		<td class="celBorder"><c:out value="${dataRole.firstName}"/>&nbsp;</TD>
		<td class="celBorder"><c:out value="${dataRole.lastName}"/>&nbsp;</TD>	
		<td class="celBorder"><c:out value="${dataRole.roleName}"/>&nbsp;</TD>	
		<td class="celBorder"><c:out value="${dataRole.mobile}"/>&nbsp;</TD>
		<td class="celBorder"><c:out value="${dataRole.sessionTimeOut}"/>&nbsp;</TD>
		<td class="celBorder"><c:out value="${dataRole.statusLabel}"/>&nbsp;</TD>
		<fmt:formatDate value="${dataRole.lastLogon}" pattern="yyyy/MM/dd HH:mm:ss" var="newdatevar" />
		<td class="celBorder"><c:out value="${newdatevar}"/>&nbsp;</TD>
		<td class="celBorder" align="center">
			<a href="javascript:edit(<c:out value="${status.count}"/>)"><bean:message key="form.edit"></bean:message></a>&nbsp;|&nbsp;
			<a href ="javascript:del(<c:out value="${status.count}"/>)"><bean:message key="form.delete"/></a>&nbsp;
			<input type="hidden" name="pk" value="<c:out value="${dataRole.userCode}"/>" >
		</td>
	</TR>	
</c:forEach>
</table>
<%@include file="../includes/paging.html"%>
</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:submit property="btnAdd" styleClass="frmButton" onclick="changeDispatch(this.form,'add');">
					<bean:message key="button.add"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
	</table>
</html:form>
</BODY>
</html:html>