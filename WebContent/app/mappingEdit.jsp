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
		doGoToUrl('<c:url value="/mapping.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<html:javascript formName="mapForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="mapForm" property="dispatch" value="updateSave" >
					<bean:message key="form.mappingUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="mapForm" property="dispatch" value="addSave">
					<bean:message key="form.mappingAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updateMapping" onsubmit="return validateMapForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="companyId"></html:hidden>
	<html:hidden property="bookId"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.spm"></bean:message></td>
				<td class="conText" colspan="3">
						<html:select property="usercode" disabled="true">
							<html:options collection="spmList" property="value" labelProperty="label" />
						</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.company"></bean:message></td>
				<td class="conText" colspan="3">
						<html:select property="companyId" disabled="true">
							<html:options collection="companyList" property="value" labelProperty="label" />
						</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.mapping.book"></bean:message></td>
				<td class="conText" colspan="3">
						<html:select property="bookId" disabled="true">
							<html:option value=""><bean:message key="form.all"></bean:message></html:option>
							<html:options collection="bookList" property="value" labelProperty="label" />
						</html:select>
				</td>
			</tr>
			
			<c:if test="${DataList!=null}">
				<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
					<tr class="tblHeader"> 
						<td><bean:message key="form.mapping.book"/></td>
						<td><bean:message key="form.mapping.bookItem"/></td>
						<td><bean:message key="form.mapping.cellYtdPrev"/></td>
						<td><bean:message key="form.mapping.cellYtdCurrentActual"/></td>
						<td><bean:message key="form.mapping.cellYtdCurrentPlan"/></td>
						<td><bean:message key="form.mapping.cellFy"/></td>
						<td><bean:message key="form.mapping.formula"/></td>
					</tr>
						<c:if test="${DataList.totalSize==0}">
							<tr class="oddRow">
								<td class="celBorder" colspan="9" align="center"><bean:message
									key="form.noDataFound"></bean:message></td>
							</tr>
						</c:if>
					<c:forEach var="mapp" items="${DataList.list}" varStatus="status">
						<c:choose>
							<c:when test="${status.count % 2 != 0}">
								<tr class="oddRow">
							</c:when>
							<c:otherwise>
								<tr class="evenRow">
							</c:otherwise>
						</c:choose>
							<html:hidden name="mapp" property="id"></html:hidden>
							<td class="conText"><c:out value="${mapp.bookItem.book.name}"></c:out> </td>
							<td class="conText"><c:out value="${mapp.bookItem.name}"></c:out> </td>
							<td class="conText"><html:textarea name="mapp" property="cellYtdPrevActual" cols="12" rows="2"/></td>
							<td class="conText"><html:textarea name="mapp" property="cellYtdCurrentActual" cols="12" rows="2"/></td>
							<td class="conText"><html:textarea name="mapp" property="cellYtdCurrentPlan" cols="12" rows="2"/></td>
							<td class="conText"><html:textarea name="mapp" property="cellFy" cols="12" rows="2"/></td>
							<td class="conText"><html:textarea name="mapp" property="formula" cols="60" rows="2"/></td>
						</tr>	
					</c:forEach>
				</table>
			</c:if>
			
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