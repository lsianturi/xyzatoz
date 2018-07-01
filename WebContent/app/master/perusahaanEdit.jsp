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
		doGoToUrl('<c:url value="/perusahaan.do?dispatch=returned"/>');
	}
</script>

<script src="scripts/view.js"></script>
<script src="scripts/staticJS.jsp"></script>
<script src="scripts/jquery.min.js"></script>

<html:javascript formName="perusahaanForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="perusahaanForm" property="dispatch" value="updateSave" >
					<bean:message key="form.companyUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="perusahaanForm" property="dispatch" value="addSave">
					<bean:message key="form.companyAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->



<html:form action="/updatePerusahaan" onsubmit="return validatePerusahaanForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.company.name"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nama" maxlength="60" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.company.address"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="alamat" rows="3" cols="60"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.company.cabang"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="cabang" >
						<html:options collection="CabangList" property="id" labelProperty="nama" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.company.unit"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="unit">
						<html:options collection="UnitList" property="id" labelProperty="nama" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.company.industri"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="industri">
						<html:options collection="IndustriList" property="id" labelProperty="nama" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel">&nbsp;</td>
				<td class="conText"></td>
			</tr>
			<tr>
				<td class="conLabel"></td>
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton" ><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel()"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
		<script type="text/javascript">	
			$('select[name="cabang"]').on('change', function(){    
			    cabId = $(this).val();
			    $('select[name="unit"]').html('');
			    $.post("perusahaan.do",
		            {
			    	  dispatch:"getUnitHtml",
		              cabangId: cabId
		            },
		            function(data,status){
		                $('select[name="unit"]').html(data);
		            });
			});
	</script>
	</table>
	<table width="100%" border="0">
		<tbody>
			
		</tbody>
	</table>	
</html:form>
</BODY>
</html:html>