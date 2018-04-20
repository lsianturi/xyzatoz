
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
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

<script languange="javascript">
	function changeDispatchMainMenu(form, _dispatch) {
		form.dispatch.value = _dispatch
		form.parentMenuCode.value = 'ess';
	}
	function del(roleCode, menuCode, parentMenuCode) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			doGoToUrl('<c:url value="/usm/RoleAction.do?dispatch=delAssign" />'+'&roleCode='+roleCode+'&menuCode='+menuCode+'&parentMenuCode='+parentMenuCode);
		}
	}
	function zoom(pk) {
		document.forms[0].action = '<c:url value="/usm/RoleAction.do" />';
		document.forms[0].dispatch.value = "assign";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].roleCode.value = document.forms[0].pk.value;
			document.forms[0].parentMenuCode.value = document.forms[0].pk1.value;			
		} else {
			document.forms[0].roleCode.value = document.forms[0].pk[pk-1].value;
			document.forms[0].parentMenuCode.value = document.forms[0].pk1[pk-1].value;			
		}
		document.forms[0].submit();
	}
	function delAssign(pk) {
		if (window.confirm('<bean:message key="form.deleteMessage"></bean:message>')) {
			document.forms[0].action = '<c:url value="/usm/RoleAction.do" />';
			document.forms[0].dispatch.value = "delAssign";
			if (document.forms[0].pk[pk-1] == null) {
				document.forms[0].roleCode.value = document.forms[0].pk.value;
				document.forms[0].menuCode.value = document.forms[0].pk1.value;				
				document.forms[0].parentMenuCode.value = document.forms[0].pk2.value;				
			} else {
				document.forms[0].roleCode.value = document.forms[0].pk[pk-1].value;
				document.forms[0].menuCode.value = document.forms[0].pk1[pk-1].value;
				document.forms[0].parentMenuCode.value = document.forms[0].pk2[pk-1].value;								
			}
			document.forms[0].submit();
		}
	}
	
	function addAssign(pk5) {
		document.forms[0].action = '<c:url value="/usm/RoleAction.do" />';
		document.forms[0].dispatch.value = "addAssign";
		if (document.forms[0].pk5[pk5-1] == null) {
			document.forms[0].roleCode.value = document.forms[0].pk5.value;
			document.forms[0].menuCode.value = document.forms[0].pk6.value;				
			document.forms[0].parentMenuCode.value = document.forms[0].pk7.value;				
		} else {
			document.forms[0].roleCode.value = document.forms[0].pk5[pk5-1].value;
			document.forms[0].menuCode.value = document.forms[0].pk6[pk5-1].value;
			document.forms[0].parentMenuCode.value = document.forms[0].pk7[pk5-1].value;								
		}
		document.forms[0].submit();
	}
</script>
<script src="../scripts/view.js"></script> 
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!--
<div class="conTitle"><bean:message key="form.roleAssign.title"></bean:message></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.roleAssign.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/role.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/usm/RoleAction.do"> 
<html:hidden property="dispatch" value=""></html:hidden>
<table width="40%" border="0" cellpadding="3" cellspacing="0">
<tr><td class="conLabel"><bean:message key="form.roleCode"/></td><td><html:text property="roleCode" readonly="true" styleClass="frmReadOnly"></html:text></td></tr>
<tr><td class="conLabel"><bean:message key="form.parentMenuCode"/></td><td><html:text property="parentMenuCode" readonly="true" styleClass="frmReadOnly"></html:text></td></tr>
</table>
<table width="100%" border="0" cellpadding="3" cellspacing="0">
<tr class="tblHeader"><td align="center"><bean:message key="form.hasMenu"/></td><td align="center"><bean:message key="form.hasMenuNot"/></td></tr>
<tr><td valign="top">
<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
<tr class="tblHeader"><TD><bean:message key="form.menuCode"/></TD><TD><bean:message key="form.name"/></TD><TD><bean:message key="form.delete"/></TD></TR>
<html:hidden property="roleCode"/>
<html:hidden property="menuCode"/>
<%--<html:hidden property="parentMenuCode"/>--%>
	<c:forEach var="var" items="${roleMenuHas}" varStatus="status">
						<c:choose>
						<c:when test="${status.count % 2 != 0}">
							<tr class="oddRow">
						</c:when>
						<c:otherwise>
							<tr class="evenRow">
						</c:otherwise>
					</c:choose>
				<TR >
			<c:url var="urlRoleMenuZoom" value="/usm/RoleAction.do">
				<c:param name="parentMenuCode" value="${var.menuCode}"></c:param>			
				<c:param name="roleCode" value="${var.roleCode}"></c:param>
				<c:param name="dispatch" value="assign"></c:param>
			</c:url>
			<tr>
			
		<%--<td class="celBorder"><a href="<c:out value="${urlRoleMenuZoom}"/>"><c:out value="${var.menuCode}"/></a>&nbsp;</TD>--%>
		<logic:equal name="var" property="child" value="0">
			<td class="celBorder"><c:out value="${var.menuCode}"/>&nbsp;</TD>				
		</logic:equal>
		<logic:notEqual name="var" property="child" value="0">
			<td class="celBorder"><a href="javascript:zoom(<c:out value="${status.count}"/>)"><c:out value="${var.menuCode}"/></a>&nbsp;</TD>		
		</logic:notEqual>
		<td class="celBorder"><c:out value="${var.description}"/>&nbsp;</TD>
		<td class="celBorder">
        <a href="javascript:delAssign(<c:out value="${status.count}"/>)"><bean:message key="form.delete"/></a>
			<input type="hidden" name="pk" value="<c:out value="${var.roleCode}"/>" >
			<input type="hidden" name="pk1" value="<c:out value="${var.menuCode}"/>" >				
			<input type="hidden" name="pk2" value="<c:out value="${var.parentMenuCode}"/>" >								
		</TD></TR>
	</c:forEach>
</table>
</td>
<td valign="top">
<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
<tr class="tblHeader"><TD><bean:message key="form.menuCode"/></TD><TD><bean:message key="form.name"/></TD><TD><bean:message key="form.add"/></TD></TR>
	<c:forEach var="var" items="${roleMenuNoHas}" varStatus="status">
						<c:choose>
						<c:when test="${status.count % 2 != 0}">
							<tr class="oddRow">
						</c:when>
						<c:otherwise>
							<tr class="evenRow">
						</c:otherwise>
					</c:choose>
		<TR><td class="celBorder"><c:out value="${var.menuCode}"/>&nbsp;</TD>
		<td class="celBorder"><c:out value="${var.description}"/>&nbsp;</TD>
			<c:url var="urlRoleMenuAdd" value="/usm/RoleAction.do">
				<c:param name="roleCode" value="${var.roleCode}"></c:param>
				<c:param name="dispatch" value="addAssign"></c:param>
				<c:param name="parentMenuCode" value="${var.parentMenuCode}"></c:param>
				<c:param name="menuCode" value="${var.menuCode}"></c:param>
			</c:url>
		<td class="celBorder">
        <a href="javascript:addAssign(<c:out value="${status.count}"/>)"><bean:message key="form.add"/></a>
			<input type="hidden" name="pk5" value="<c:out value="${var.roleCode}"/>" >
			<input type="hidden" name="pk6" value="<c:out value="${var.menuCode}"/>" >				
			<input type="hidden" name="pk7" value="<c:out value="${var.parentMenuCode}"/>" >								
		</TD></TR>
	</c:forEach>
</table>
</td>
</tr>
</table>

<table width="100%" border="0" cellpadding="3" cellspacing="0">
<tr>
<td align="left">
<html:submit property="btnBack" styleClass="frmButton"  onclick="javascript:changeDispatch(this.form,'back');">
	<bean:message key="form.back"/>
</html:submit>
<html:submit property="btnBack" styleClass="frmButton" value="MainMenu" onclick="javascript:changeDispatchMainMenu(this.form,'returned');">
	<bean:message key="form.mainMenu"/>
</html:submit>
</td>
</tr>
<table>
</html:form>
</BODY>
</html:html>