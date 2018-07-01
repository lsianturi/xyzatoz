<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.benclaus.koperasi.utility.Constant"%>
<%@page import="com.benclaus.koperasi.model.usm.Login"%>
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
<script src="../scripts/jquery.min.js"></script>

<script type="text/javascript">
	<c:if test="${DataList!=null}">
	lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
	totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	function chooseNasabah(value) {
		realValue = value;
		window.opener.setPopUpValue(realValue, "nasabah");
	}
</script>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.nasabah.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<%Login login = (Login) session.getAttribute(Constant.SES_USERLOGIN);%>
<!-- Help Page Finish -->
<html:form action="/trx/nasabah.do" method="post" >	
	<html:hidden property="dispatch" value="search"/>
	<table border="0" width="80%">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.name"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nama" maxlength="60" size="20"></html:text>
				</td>
				
				<td class="conLabel"><bean:message key="form.nasabah.telepon"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="telepon" maxlength="60" size="20"></html:text>
				</td>
				
				<td class="conLabel"><bean:message key="form.nasabah.cabang"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="cabang">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="CabangList" property="id" labelProperty="nama" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td  class="conLabel"><bean:message key="form.nasabah.perusahaan"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="perusahaan">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="PerusahaanList" property="id" labelProperty="status" />
					</html:select>
				</td>
				<td  class="conLabel"><bean:message key="form.nasabah.bank"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="bank">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="BankList" property="id" labelProperty="status" />
					</html:select>
				</td>
				<td class="conLabel"><bean:message key="form.nasabah.unit"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="unit">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="UnitList" property="id" labelProperty="nama" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td  class="conLabel"><bean:message key="form.nasabah.statusKerja"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="stsKerja">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="StsAgtList" property="id" labelProperty="status" />
					</html:select>
				</td>
				<td  class="conLabel"><bean:message key="form.nasabah.jenisAnggota"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="jnsAnggota">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="JnsAgtList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td  class="conLabel"><bean:message key="form.nasabah.nik"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nik" maxlength="60" size="20"></html:text>
				</td>
				<td class="conLabel"></td>
				<td class="conText" colspan="3">
					<html:checkbox property="anAgent"><bean:message key="form.nasabah.anAgent"></bean:message>&nbsp;only</html:checkbox>
				</td>
			</tr>
			<tr>
				<td  class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.search"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
		<script type="text/javascript">	
			$('select[name="cabang"]').on('change', function(){    
			    cabId = $(this).val();
			    $('select[name="unit"]').html('');
			    $.post("nasabah.do",
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
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="3">Search Result</td>
			</tr>
			
			<tr class="tblHeader"> 
				<td>&nbsp;</td>
				<td><bean:message key="form.nasabah.name"/></td>
				<td><bean:message key="form.nasabah.telepon"/></td>
				<td><bean:message key="form.nasabah.rekening"/></td>
				<td><bean:message key="form.nasabah.perusahaan"/></td>
				<td><bean:message key="form.nasabah.bank"/></td>
				<td><bean:message key="form.nasabah.nik"/></td>
			</tr>
				<c:if test="${DataList.totalSize==0}">
					<tr class="oddRow">
						<td class="celBorder" colspan="9" align="center"><bean:message
							key="form.noDataFound"></bean:message></td>
					</tr>
				</c:if>
			<c:forEach var="comp" items="${DataList.list}" varStatus="status">
				<c:choose>
					<c:when test="${status.count % 2 != 0}">
						<tr class="oddRow">
					</c:when>
					<c:otherwise>
						<tr class="evenRow">
					</c:otherwise>
				</c:choose>
					<td class="celBorder"><a href="javascript:chooseNasabah('<bean:write name="comp" property="id"/>;<bean:write name="comp" property="nama"/>;<bean:write name="comp" property="alamat"/>;<bean:write name="comp" property="jenisKelamin.status"/>;<bean:write name="comp" property="nik"/>;<bean:write name="comp" property="pt.nama"/>;<bean:write name="comp" property="bagian"/>;<bean:write name="comp" property="bank.nama"/>;<bean:write name="comp" property="telepon"/>;<bean:write name="comp" property="noRekening"/>;<bean:write name="comp" property="tglPayroll" format="dd/MM/yyyy"/>;<bean:write name="comp" property="aplikasi"/>;<bean:write name="comp" property="domisili"/>')">
						        				<img src="../icons/apply16.gif" border="none">
						        			</a></td>
					<td class="celBorder"><c:out value="${comp.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.telepon}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.noRekening}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.pt.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.bank.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.nik}"/>&nbsp;</td>
				</tr>	
			</c:forEach>
		</table>
	<%@include file="../../includes/paging.html"%>
	</c:if>
</html:form>
</BODY>
</html:html>
