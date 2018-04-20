<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>

<script src="../scripts/view.js"></script>
<script src="../scripts/md5.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script type="text/javascript" language="javascript"> 

	<c:if test="${DataList!=null}">
		lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
		totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	function show(year) {
		path ='<c:url value="/ReportViewer" />'+'?type=summary&year='+year;
		doGoToUrl(path);
		//window.open(path,null,"height=600,width=800,dependent=yes,toolbar=no,location=no,resizable=yes,scrollbars=yes");

	}
	
var state = 'none';

function showhide(layer_ref) {

	if (layer_ref == "div2" ) {
		document.summaryReportForm.dispatch.value = 'generate';
		con = confirm('Anda yakin untuk mengenerate report ?');
		if (!con) {
			return false;
		}
	} else {
		document.summaryReportForm.dispatch.value = 'list';
	}

	if (state == 'block') {
		state = 'none';
	} else {
		state = 'block';
	}
	if (document.all) { //IS IE 4 or 5 (or 6 beta)
		eval( "document.all." + layer_ref + ".style.display = state");
	}
	if (document.layers) { //IS NETSCAPE 4 or below
		document.layers[layer_ref].display = state;
	}
	if (document.getElementById &&!document.all) {
		hza = document.getElementById(layer_ref);
		hza.style.display = state;
	}
}
//--> 
</script>
<html:javascript formName="summaryReportForm" dynamicJavascript="true" staticJavascript="false" />
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.rptSumary.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->
<html:form action="/abs/SummaryAction.do" method="post" >	
	<html:hidden property="dispatch"/>
	
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.calcAbsen.year"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="year">
						<html:options collection="yearList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton" onclick="javascript:showhide('div1');">
					<bean:message key="button.list"></bean:message>
				</html:submit></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton" onclick="javascript:showhide('div2');">
					<bean:message key="button.generate"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
	</table>
	<div id="div1" style="display: none">
		<table border="0">
			<tbody>
				<tr>
					<td width="100">&nbsp;</td>
					<td width="100">&nbsp;</td>
				</tr>
				<tr>
					<td width="100" >&nbsp;</td>
					<td width="100"><img src="../images/progress.gif"></td>
				</tr>
				<tr>
					<td width="100" >&nbsp;</td>
					<td class="heaText"><b>Please wait while report is being listed...</b></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="div2" style="display: none">
		<table border="0">
			<tbody>
				<tr>
					<td width="100">&nbsp;</td>
					<td width="100">&nbsp;</td>
				</tr>
				<tr>
					<td width="100" >&nbsp;</td>
					<td width="100"><img src="../images/progress.gif"></td>
				</tr>
				<tr>
					<td width="100" >&nbsp;</td>
					<td class="heaText"><b>Please wait while report is being generated...</b></td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="3">Search Result</td>
				
			</tr>
			<tr class="tblHeader"> 
				<td><bean:message key="form.report.year"/></td>
				<td><bean:message key="form.report.file"/></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="rpt" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><c:out value="${rpt.year}"/>&nbsp;</td>
					<td class="celBorder">
					<a href="#" onclick="javascript:show('<c:out value="${rpt.year}"/>')" title="View attendace report"><c:out value="${rpt.fileName}"/></a></td>				
				</TR>	
			</c:forEach>
		</table>
	<%@include file="../includes/paging.html"%>
	</c:if>
</html:form>
</BODY>

</html:html>
