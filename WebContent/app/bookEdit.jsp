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
		doGoToUrl('<c:url value="/book.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<html:javascript formName="bookForm" dynamicJavascript="1" staticJavascript="0"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="0"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="bookForm" property="dispatch" value="updateSave" >
					<bean:message key="form.bookUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="bookForm" property="dispatch" value="addSave">
					<bean:message key="form.bookAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updateBook" onsubmit="return validateBookForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<html:hidden property="bookId"></html:hidden>
	<html:hidden property="companyId"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.book.company"></bean:message></td>
				<td class="conText" colspan="3">
					<logic:equal name="bookForm" property="dispatch" value="updateSave" >
						<html:select property="companyId" disabled="true">
							<html:options collection="companyList" property="value" labelProperty="label" />
						</html:select>
					</logic:equal>
					<logic:equal name="bookForm" property="dispatch" value="addSave">
						<html:select property="companyId">
							<html:options collection="companyList" property="value" labelProperty="label" />
						</html:select>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.book.name"></bean:message></td>
				<td class="conText" colspan="3">
					<logic:equal name="bookForm" property="dispatch" value="updateSave" >
						<html:select property="bookId" disabled="true">
							<html:options collection="bookList" property="value" labelProperty="label" styleClass="frmReadOnly" />
						</html:select>
					</logic:equal>
					<logic:equal name="bookForm" property="dispatch" value="addSave">
						<html:select property="bookId">
							<html:options collection="bookList" property="value" labelProperty="label" />
						</html:select>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.book.value"></bean:message></td>
				<td class="conText" colspan="3">
					<logic:equal name="book" property="fullAmount" value="1">
						<input type="radio" name="theValue" value='fullAmount' checked="checked" ><bean:message key="form.book.fullAmount"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="fullAmount" value="0">
						<input type="radio" name="theValue" value='fullAmount' ><bean:message key="form.book.fullAmount"></bean:message>
					</logic:equal>
					
					<logic:equal name="book" property="inThousand" value="1">
						<input type="radio" name="theValue" value='inThousand' checked="checked" ><bean:message key="form.book.inThousand"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="inThousand" value="0">
						<input type="radio" name="theValue" value='inThousand' ><bean:message key="form.book.inThousand"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="inMio" value="1">
						<input type="radio" name="theValue" value='inMio' checked="checked" ><bean:message key="form.book.inMio"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="inMio" value="0">
						<input type="radio" name="theValue" value='inMio' ><bean:message key="form.book.inMio"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="inBio" value="1">
						<input type="radio" name="theValue" value='inBio' checked="checked" ><bean:message key="form.book.inBio"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="inBio" value="0">
						<input type="radio" name="theValue" value='inBio' ><bean:message key="form.book.inBio"></bean:message>
					</logic:equal>
					
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.book.period"></bean:message></td>
				<td class="conText" colspan="3">
					<logic:equal name="book" property="periodic" value="1">
						<input type="radio" name="valuePeriod" value='periodic' checked="checked" ><bean:message key="form.book.periodic"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="periodic" value="0">
						<input type="radio" name="valuePeriod" value='periodic' ><bean:message key="form.book.periodic"></bean:message>
					</logic:equal>
					
					<logic:equal name="book" property="ytd" value="1">
						<input type="radio" name="valuePeriod" value='ytd' checked="checked" ><bean:message key="form.book.ytd"></bean:message>
					</logic:equal>
					<logic:equal name="book" property="ytd" value="0">
						<input type="radio" name="valuePeriod" value='ytd' ><bean:message key="form.book.ytd"></bean:message>
					</logic:equal>
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
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['absenForm'].elements['tglKerja']);
	cal1.year_scroll = 1;
	cal1.time_comp = 0;
	
</script>
</html:html>