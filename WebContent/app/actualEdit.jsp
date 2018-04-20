<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@taglib uri="/tags/struts-logic" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

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
		doGoToUrl('<c:url value="/plan.do?dispatch=returned"/>');
	}

</script>
<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<html:javascript formName="planForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="planForm" property="dispatch" value="updateSave" >
					<bean:message key="form.planUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="planForm" property="dispatch" value="addSave">
					<bean:message key="form.planAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/savePlan" onsubmit="return validatePlanForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="companyId"></html:hidden>
	<html:hidden property="bookId"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.spm"></bean:message></td>
				<td class="conText" colspan="11">
					<html:select property="usercode" disabled="true">
						<html:options collection="spmList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.company"></bean:message></td>
				<td class="conText" colspan="11">
					<html:select property="companyId" disabled="true">
						<html:options collection="companyList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.plan.book"></bean:message></td>
				<td class="conText" colspan="11">
					<html:select property="bookId" disabled="true">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="bookList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<!-- <tr>
				<td colspan="12">&nbsp;</td>
			</tr> -->
			<tr>
				<td class="conLabel"><bean:message key="form.plan.year"></bean:message></td>
				<td class="conText">
					<html:text property="year" maxlength="6" size="4"  styleClass="frmReadOnly"></html:text>
				</td>
			</tr>
			<c:if test="${DataList!=null}">
				<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
					<tr class="tblHeader"> 
						<td><bean:message key="form.plan.bookItem"/></td>
						<td><bean:message key="form.plan.plan1"/></td>
						<td><bean:message key="form.plan.plan2"/></td>
						<td><bean:message key="form.plan.plan3"/></td>
						<td><bean:message key="form.plan.plan4"/></td>
						<td><bean:message key="form.plan.plan5"/></td>
						<td><bean:message key="form.plan.plan6"/></td>
						<td><bean:message key="form.plan.plan7"/></td>
						<td><bean:message key="form.plan.plan8"/></td>
						<td><bean:message key="form.plan.plan9"/></td>
						<td><bean:message key="form.plan.plan10"/></td>
						<td><bean:message key="form.plan.plan11"/></td>
						<td><bean:message key="form.plan.plan12"/></td>
					</tr>
						<c:if test="${DataList.totalSize==0}">
							<tr class="oddRow">
								<td class="celBorder" colspan="9" align="center"><bean:message
									key="form.noDataFound"></bean:message></td>
							</tr>
						</c:if>
					<c:forEach var="plan" items="${DataList.list}" varStatus="status">
						<c:choose>
							<c:when test="${status.count % 2 != 0}">
								<tr class="oddRow">
							</c:when>
							<c:otherwise>
								<tr class="evenRow">
							</c:otherwise>
						</c:choose>
							<html:hidden name="plan" property="id"></html:hidden>
							<html:hidden name="plan" property="bookItem.id"></html:hidden>
							<td class="conText"><c:out value="${plan.bookItem.name}"></c:out> </td>
							<td class="conText"><html:text name="plan" property="plan1Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan2Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan3Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan4Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan5Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan6Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan7Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan8Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan9Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan10Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan11Str" maxlength="12" size="10"></html:text></td>
							<td class="conText"><html:text name="plan" property="plan12Str" maxlength="12" size="10"></html:text></td>
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