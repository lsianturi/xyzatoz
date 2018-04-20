<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<html:html> 
<HEAD>
	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
	function tree(pk) {
		document.forms[0].action = '<c:url value="/usm/MenuAction.do" />';
		document.forms[0].dispatch.value = "tree";
		if (document.forms[0].pk[pk-1] == null) {
			document.forms[0].menuCode.value = document.forms[0].pk.value;
		} else {
			document.forms[0].menuCode.value = document.forms[0].pk[pk-1].value;
		}
		document.forms[0].submit();
	}
</script>

<BODY>
	<jsp:include page="../includes/message.jsp" flush="false"/>

<!--	
	<div class="conTitle"><bean:message key="form.menuUser.title"/></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.menuUser.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/menu.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->
	
	<html:form action="/usm/MenuAction.do">	
		<html:hidden property="dispatch" value="search"/>
		<html:hidden property="menuCode"/>
		<table width="100%">
			<tbody>
				<tr>
					<td class="conLabel" width="180"><bean:message key="form.search"/></td>
					<td colspan="3" class="conText">
						<html:select property="searchColumn">
							<html:option value="MenuCode"><bean:message key="form.menuCode"/></html:option>
							<html:option value="ParentMenuCode"><bean:message key="form.parentMenuCode"/></html:option>
							<html:option value="Name"><bean:message key="form.name"/></html:option>
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
				<td><bean:message key="form.menuCode"/></td>
				<td><bean:message key="form.parentMenuCode"/></td>
				<td><bean:message key="form.name"/></td>
				<td><bean:message key="form.menuLevel"/></td>
				<td><bean:message key="form.menuOrder"/></td>
				<td><bean:message key="form.menuType"/></td>
				<td><bean:message key="form.command"/></td>								
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="dataUser" items="${DataList.list}" varStatus="status">
								<c:choose>
						<c:when test="${status.count % 2 != 0}">
							<tr class="oddRow">
						</c:when>
						<c:otherwise>
							<tr class="evenRow">
						</c:otherwise>
					</c:choose>
					<td class="celBorder">
					<a href="javascript:tree(<c:out value="${status.count}"/>)"><c:out value="${dataUser.menuCode}"/></a>&nbsp;(<c:out value="${dataUser.child}"/>)
					<input type="hidden" name="pk" value="<c:out value="${dataUser.menuCode}"/>" >&nbsp;</TD>
					
					<td class="celBorder"><c:out value="${dataUser.parentMenuCode}"/>&nbsp;</TD>
					<td class="celBorder"><c:out value="${dataUser.name}"/>&nbsp;</TD>
					<td class="celBorder" align="right"><c:out value="${dataUser.menuLevel}"/>&nbsp;</TD>				
					<td class="celBorder" align="right"><c:out value="${dataUser.menuOrder}"/>&nbsp;</TD>		
					<td class="celBorder">
						<c:if test="${dataUser.menuType == '1'}">
					   		<bean:message key="form.subMenu"/>
						</c:if>
						<c:if test="${dataUser.menuType == '2'}">
					   		<bean:message key="form.transaction"/>
						</c:if>
						&nbsp;
					</TD>
					<td class="celBorder"><c:out value="${dataUser.command}"/>&nbsp;</TD>
				</TR>	
			</c:forEach>
		</table>
	<%@include file="../includes/paging.html"%>
	</c:if>
	</html:form>

</BODY>
</html:html>