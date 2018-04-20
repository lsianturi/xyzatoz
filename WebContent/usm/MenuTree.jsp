<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
</HEAD>
<script language="JavaScript">

	function del(menuCode, parentMenuCode) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			doGoToUrl('<c:url value="/usm/MenuAction.do?dispatch=delete" />'+'&menuCode='+menuCode+'&parentMenuCode='+parentMenuCode);
		}
	}

	function edit(pk) {
		document.forms[0].action = '<c:url value="/usm/MenuAction.do" />';
		document.forms[0].dispatch.value = "update";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].menuCode.value = document.forms[0].pk.value;
		} else {
			document.forms[0].menuCode.value = document.forms[0].pk[pk-1].value;
		}
		document.forms[0].submit();
	}
	function del(pk) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			document.forms[0].action = '<c:url value="/usm/MenuAction.do" />';
			document.forms[0].dispatch.value = "delete";
			if (document.forms[0].pk[pk-1] == null) {
				document.forms[0].menuCode.value = document.forms[0].pk.value;
				document.forms[0].parentMenuCode.value = document.forms[0].pk1.value;				
			} else {
				document.forms[0].menuCode.value = document.forms[0].pk[pk-1].value;
				document.forms[0].parentMenuCode.value = document.forms[0].pk1[pk-1].value;				
			}
			document.forms[0].submit();
		}
	}
	
	function tree(pk) {
		document.forms[0].action = '<c:url value="/usm/MenuAction.do" />';
		document.forms[0].dispatch.value = "tree";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].menuCode.value = document.forms[0].pk.value;
			document.forms[0].parentMenuCode.value = document.forms[0].pk1.value;				
		} else {
			document.forms[0].menuCode.value = document.forms[0].pk[pk-1].value;
			document.forms[0].parentMenuCode.value = document.forms[0].pk1[pk-1].value;				
		}
		document.forms[0].submit();
	}
</script>

<script src="../scripts/view.js"></script>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false" />
<!--
<div class="conTitle"><bean:message key="form.menuUser.title"/></div>
-->

<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.menuUser.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/menu.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->

<html:form action="/usm/MenuAction.do">
	<html:hidden property="menuLevel" />
	<html:hidden property="menuOrder" />
	<html:hidden property="menuType" />
	<html:hidden property="menuCode" />
	<html:hidden property="dispatch" />
	<bean:message key="form.parentMenuCode" /> : <html:text property="parentMenuCode" readonly="true" styleClass="frmReadOnly" />
	<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
		<tr class="tblHeader">
			<td><bean:message key="form.menuCode" /></td>
			<td><bean:message key="form.parentMenuCode" /></td>
			<td><bean:message key="form.name" /></td>
			<td><bean:message key="form.menuType" /></td>
			<td><bean:message key="form.description" /></td>
			<td align="center"><bean:message key="form.action"></bean:message></td>
		</TR>


		<c:forEach var="dataUser" items="${dataUserForm}" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 != 0}">
					<tr class="oddRow">
				</c:when>
				<c:otherwise>
					<tr class="evenRow">
				</c:otherwise>
			</c:choose>
			<tr>
				<td class="celBorder"><a href="javascript:tree(<c:out value="${status.count}"/>)"><c:out value="${dataUser.menuCode}" /></a>&nbsp;(<c:out value="${dataUser.child}" />)</TD>

				<td class="celBorder"><c:out value="${dataUser.parentMenuCode}" />&nbsp;</TD>
				<td class="celBorder"><c:out value="${dataUser.name}" />&nbsp;</TD>
				<td class="celBorder"><c:if test="${dataUser.menuType == '1'}">
					<bean:message key="form.subMenu" />
				</c:if> <c:if test="${dataUser.menuType == '2'}">
					<bean:message key="form.transaction" />
				</c:if> &nbsp;</TD>
				<td class="celBorder"><c:out value="${dataUser.description}" />&nbsp;</TD>
				<td class="celBorder" align="center"><a href="javascript:edit(<c:out value="${status.count}"/>)"><bean:message key="form.edit"></bean:message></a>&nbsp;|&nbsp; <a href="javascript:del(<c:out value="${status.count}"/>)"><bean:message key="form.delete" /></a></TD>
				<input type="hidden" name="pk" value="<c:out value="${dataUser.menuCode}"/>">
				<input type="hidden" name="pk1" value="<c:out value="${dataUser.parentMenuCode}"/>">
			
			</TR>
		</c:forEach>
	</table>


	<table width="100%">
		<tbody>
			<tr>
				<td class="conText"><html:submit property="btnBack" styleClass="frmButton" onclick="changeDispatch(this.form,'back');">
					<bean:message key="form.back" />
				</html:submit> <html:submit property="btnBack" styleClass="frmButton" onclick="changeDispatch(this.form,'mainmenu');">
					<bean:message key="form.mainList" />
				</html:submit></td>
				<td align="right"><html:submit property="btnAdd" styleClass="frmButton" onclick="changeDispatch(this.form,'add');">
					<bean:message key="form.add" />
				</html:submit></td>
			</tr>
		</tbody>
	</table>



</html:form>
</BODY>
</html:html>
