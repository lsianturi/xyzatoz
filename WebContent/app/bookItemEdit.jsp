<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script  language="JavaScript">
	function cancel() {
		doGoToUrl('<c:url value="/bookItem.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<html:javascript formName="bookItemForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="bookItemForm" property="dispatch" value="updateSave" >
					<bean:message key="form.bookItemUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="bookItemForm" property="dispatch" value="addSave">
					<bean:message key="form.bookItemAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updateBookItem" onsubmit="return validatebookItemForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.bookItem.book"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bookId">
						<html:options collection="bookList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.bookItem.name"></bean:message></td>
				<td class="conText">
					<html:text property="name" maxlength="60" size="40"></html:text>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton" ><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel()"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>