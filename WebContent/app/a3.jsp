<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<%@page import="com.benclaus.koperasi.model.usm.Login"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>

<script src="scripts/view.js"></script>
<script src="scripts/md5.js"></script>
<script src="scripts/staticJS.jsp"></script>
<script type="text/javascript">
	
</script>


</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.a3.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/a3.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.a3.currentYear"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="month">
						<html:options collection="monthList" property="value" labelProperty="label" />
					</html:select>&nbsp;
					<html:text property="currentYear" maxlength="12" size="10" ></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.a3.year"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="fromYear">
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>&nbsp;to&nbsp;
					<html:select property="toYear">
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.download"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
	</table>
	
</html:form>
</BODY>
</html:html>
