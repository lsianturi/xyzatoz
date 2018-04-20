<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
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

<script languange="javascript">
function cancel(_dispatch, form) {
	pmenucode = form.parentMenuCode.value;
	window.location = '<c:url value="/usm/MenuAction.do" />'+'?dispatch='+_dispatch+'&parentMenuCode='+pmenucode;
}
</script>
<script src="../scripts/staticJS.jsp" ></script>
<script src="../scripts/view.js"></script>
<html:javascript formName="menuForm" dynamicJavascript="true" staticJavascript="false"/>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"/>
<!--
<div class="conTitle"><bean:message key="form.menuUserUpdate.title"/></div>
-->

	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<bean:message key="form.menuUserUpdate.title"></bean:message>
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/master/menu.html');"><img src="../icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->

<html:form action="/usm/MenuAction.do" onsubmit="return validateMenuForm(this);">
<html:hidden property="menuLevel" > </html:hidden>	
<html:hidden property="dispatch" value=""> </html:hidden>
<html:hidden property="pageIndex" > </html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel" width="180"><bean:message key="form.menuCode"/></td>
				<td class="conText" colspan="3"><html:text property='menuCode' maxlength="16" size="16"/><font color="red">&nbsp;<html:errors property="menuCode" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.parentMenuCode"></bean:message></td>
				<td class="conText" colspan="3"><html:text property="parentMenuCode" maxlength="16" size="16" readonly="true" styleClass="frmReadOnly"></html:text><font color="red">&nbsp;<html:errors property="parentMenuCode" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.name"></bean:message></td>
				<td class="conText" colspan="3"><html:text property="name" maxlength="30" size="30"></html:text><font color="red">&nbsp;<html:errors property="name" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.description"></bean:message></td>
				<td class="conText" colspan="3"><html:textarea property="description" cols="32" rows="4"></html:textarea><font color="red">&nbsp;<html:errors property="description" /></font></td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.menuType"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="menuType">
						<html:options collection="menuTypeCombo" property="label" labelProperty="value" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.menuOrder"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="menuOrder">
						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
						<html:option value="3">3</html:option>
						<html:option value="4">4</html:option>
						<html:option value="5">5</html:option>
						<html:option value="6">6</html:option>
						<html:option value="7">7</html:option>
						<html:option value="8">8</html:option>																											
						<html:option value="9">9</html:option>
						<html:option value="10">10</html:option>
						<html:option value="11">11</html:option>
						<html:option value="12">12</html:option>
						<html:option value="13">13</html:option>
						<html:option value="14">14</html:option>
						<html:option value="15">15</html:option>
						<html:option value="16">16</html:option>
						<html:option value="17">17</html:option>
						<html:option value="18">18</html:option>
						<html:option value="19">19</html:option>
						<html:option value="20">20</html:option>
						<html:option value="21">21</html:option>
						<html:option value="22">22</html:option>
						<html:option value="23">23</html:option>																											
						<html:option value="24">24</html:option>
						<html:option value="25">25</html:option>
						<html:option value="26">26</html:option>
						<html:option value="27">27</html:option>
						<html:option value="28">28</html:option>
						<html:option value="29">29</html:option>
						<html:option value="30">30</html:option>						
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.command"></bean:message></td>
				<td class="conText" colspan="3"><html:textarea property="command" cols="32" rows="4"/><font color="red">&nbsp;<html:errors property="command" /></font></td>
			</tr>

		</tbody>
	</table>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td width="180">
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton" onclick="changeDispatch(this.form,'addSave');"><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="javascript:cancel('returned', this.form);"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>