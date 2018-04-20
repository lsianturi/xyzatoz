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
<script src="../scripts/calendar2.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script type="text/javascript" language="javascript"> 
var state = 'none';

function showhide(layer_ref) {

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
<html:javascript formName="uploadForm" dynamicJavascript="true" staticJavascript="false" />
</HEAD>
<BODY>
<jsp:include page="../includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.uploadAbsen.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('../help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->
<html:form method="post" action="/abs/DownloadAction">
	<html:hidden property="dispatch" value="upload"></html:hidden>
	
	<table border="0">
		<tbody>
			<tr><td class="conLabel" colspan="2"><bean:message key="form.inputAbsen.tglKerja"></bean:message></td></tr>
			<tr>
				<td class="conLabel">From</td>
				<td class="conText" colspan="3">
					<html:text property="startDate" maxlength="18" size="16"></html:text>
					<a href="javascript:cal1.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td class="conLabel">To</td>
				<td class="conText" colspan="3">
					<html:text property="endDate" maxlength="18" size="16"></html:text>
					<a href="javascript:cal2.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton" onclick="javascript:showhide('div1');">
					<bean:message key="button.download"></bean:message>
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
					<td class="heaText"><b>Please wait while system downloading attendance data is ...</b></td>
				</tr>
			</tbody>
		</table>
	</div>
</html:form>
</BODY>
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['uploadForm'].elements['startDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['uploadForm'].elements['endDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
</script>
</html:html>
