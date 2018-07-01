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
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">

<TITLE></TITLE>

<script src="../scripts/view.js"></script>

<script src="../scripts/md5.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/calendar2.js"></script>
<script type="text/javascript">
	<c:if test="${DataList!=null}">
	lastPage = <c:out value="${DataList.pageIndex}"></c:out>;
	totalPage = <c:out value="${DataList.totalPage}"></c:out>;
	</c:if>	
	
	function edit(id) {
		doGoToUrl('<c:url value="/trx/ajuUpdate.do?dispatch=update"/>'+'&id='+id);
	}
	function add() {
		doGoToUrl('<c:url value="/trx/ajuUpdate.do?dispatch=add"/>');
	}
	function real(id) {
		doGoToUrl('<c:url value="/trx/realUpdate.do?dispatch=real"/>'+'&id='+id);
	}
</script>
</HEAD>
<BODY>
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
<!-- Help Page Start -->
<table width="100%">
	<tr>
		<td class="conTitle"><bean:message key="form.aju.title"></bean:message></td>
		<td class="conText" align="right"><a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="<%=request.getContextPath()%>/icons/help.gif" width="21" height="21" border="0" alt=""></a></td>
	</tr>
</table>
<!-- Help Page Finish -->

	
<html:form action="/trx/aju.do" method="post">	
	<html:hidden property="dispatch" value="search"/>
	  	<table border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.noKredit"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="noKredit" maxlength="30" size="40"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.nik"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nik" maxlength="30" size="40"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.nasabah"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nasabah" maxlength="30" size="40"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.tglAju"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="tglAjuFrom" maxlength="12" size="15"></html:text>&nbsp;<a href="javascript:cal1.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>&nbsp;To&nbsp;
					<html:text property="tglAjuTo" maxlength="12" size="15"></html:text>&nbsp;<a href="javascript:cal2.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.perusahaan"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="perusahaan">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="PerusahaanList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.tipeKredit"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="tipeKredit">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="TipeKreditList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr> 
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.jenisPinjam"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="jenisPinjam">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="JenisPinjamList" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
	
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.sponsor"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="sponsor">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="AgentList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.marketing"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="marketing">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="MarketingList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.status"></bean:message></td>
				<td class="conText" colspan="3">
					<html:select property="status">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="StatusList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td  class="conLabel"></td>
				<td><html:submit property="btnSubmit" styleClass="frmButton">
					<bean:message key="button.search"></bean:message>
				</html:submit></td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${DataList!=null}">
		<table width="100%" border="0" cellpadding="3" cellspacing="0" class="tblBorder">
			<tr class="conLabel"> 
				<td colspan="3">Search Result</td>
			</tr>
			<tr class="tblHeader"> 
				<td><bean:message key="form.aju.tglAju"/></td>
				<td><bean:message key="form.aju.noKredit"/></td>
				<td><bean:message key="form.aju.tipeKredit"/></td>
				<td><bean:message key="form.nasabah.nik"/></td>
				<td><bean:message key="form.aju.nasabah"/></td>
				<td><bean:message key="form.aju.perusahaan"/></td>
				<td><bean:message key="form.aju.sponsor"/></td>
				<td><bean:message key="form.aju.marketing"/></td>
				<td align="center"><bean:message key="form.action"></bean:message></td>
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
				<c:choose>
				<c:when test="${abs.isManual==1}">
						<tr class="manualRow">
					</c:when>
				</c:choose>
					<%-- <td class="celBorder"><c:out value="${comp.pillar.name}"/>&nbsp;</td> --%>
					<td class="celBorder"><fmt:formatDate value="${comp.tglAju}" pattern="dd/MM/yyyy"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.noKredit}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.tipeKreditName}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.nasabah.nik}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.nasabah.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.nasabah.pt.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.sponsor.nama}"/>&nbsp;</td>
					<td class="celBorder"><c:out value="${comp.marketing.nama}"/>&nbsp;</td>
					<td class="celBorder" align="center">
						<a
							href="javascript:del('<c:out value="${comp.id}"/>')"><bean:message
								key="button.delete"></bean:message></a>&nbsp;|&nbsp;<a
							href="javascript:edit('<c:out value="${comp.id}"/>')"><bean:message
								key="button.edit"></bean:message></a>
							<logic:equal name="comp" property="realisasi" value="0">
								&nbsp;|&nbsp;<a
							href="javascript:real('<c:out value="${comp.id}"/>')"><bean:message
								key="button.real"></bean:message></a>
							</logic:equal>
				</td>
				</tr>	
			</c:forEach>
		</table>
	<%@include file="../../includes/paging.html"%>
	</c:if>
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td align="right"><html:button property="btnAdd" styleClass="frmButton" onclick="add()">
					<bean:message key="button.add"></bean:message>
				</html:button></td>
			</tr>
		</tbody>
	</table>
</html:form>
<script language="JavaScript">
	var cal1 = new calendar2(document.forms['ajuSearch'].elements['tglAjuFrom']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['ajuSearch'].elements['tglAjuTo']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
</BODY>
</html:html>
