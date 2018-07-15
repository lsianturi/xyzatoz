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
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<LINK href="../theme/w3.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<script  language="JavaScript">
	var popUp;
	function cancel() {
		doGoToUrl('<c:url value="/trx/aju.do?dispatch=returned"/>');
	}
	function openTab(tabName) {
	    var i;
	    var x = document.getElementsByClassName("aju");
	    for (i = 0; i < x.length; i++) {
	       x[i].style.display = "none";  
	    }
	    document.getElementById(tabName).style.display = "block";  
	}
	function openNasabahList() {
		var url = 'nasabah.do?dispatch=list'
		var name = 'NasabahList';
		var style = 'width=750,height=420,left=280,top=125,scrollbars=yes';
		
		popUp = openWindow(url, name, style);
	}
	function setPopUpValue(value, source) {
		if (source === "nasabah") {
			arr = value.split(";");
			document.ajuForm.elements['nsbhId'].value = arr[0];
			document.ajuForm.elements['nsbhNama'].value = arr[1];
			document.ajuForm.elements['nsbhAlamat'].value = arr[2];
			document.ajuForm.elements['nsbhJnsKelamin'].value = arr[3];
			document.ajuForm.elements['nsbhNik'].value = arr[4];
			document.ajuForm.elements['nsbhPt'].value = arr[5];
			document.ajuForm.elements['nsbhBagian'].value = arr[6];
			document.ajuForm.elements['nsbhBank'].value = arr[7];
			document.ajuForm.elements['nsbhTelepon'].value = arr[8];
			document.ajuForm.elements['nsbhNoRekening'].value = arr[9];
			document.ajuForm.elements['nsbhTglPayrol'].value = arr[10];
			document.ajuForm.elements['nsbhAplikasi'].value = arr[11];
			document.ajuForm.elements['nsbhDomisili'].value = arr[12];
			popUp.close();
		}
	}
	
	function simulasi() {
		$('input[name="btnSimulasi"]').click();
		$('input[name="btnCekCicilan"]').click();
		openTab('Aju');
	}
	
</script>
<script src="../scripts/view.js"></script>
<script src="../scripts/staticJS.jsp"></script>
<script src="../scripts/util.js"></script>
<script src="../scripts/calendar2.js"></script>
<script src="../scripts/jquery.min.js"></script>
<script src="../scripts/terbilang.min.js"></script>
<html:javascript formName="realForm" dynamicJavascript="true" staticJavascript="false"/>
</HEAD>
<BODY onload="simulasi()">
<jsp:include page="/includes/message.jsp" flush="false"></jsp:include>
	<!-- Help Page Start -->
	<table width="100%">
		<tr>
			<td class="conTitle">
				<logic:equal name="realForm" property="dispatch" value="updateSave" >
					<bean:message key="form.realUpdate.title"></bean:message>
				</logic:equal>
				<logic:equal name="realForm" property="dispatch" value="addSave">
					<bean:message key="form.realAdd.title"></bean:message>
				</logic:equal>
				
			</td>
			<td class="conText" align="right">
				<a href="javascript:openHelp('help/<bean:message key="form.language.name"></bean:message>/blank.html');"><img src="icons/help.gif" width="21" height="21" border="0" alt=""></a>
			</td>
		</tr>
	</table>
	<!-- Help Page Finish -->


<div class="w3-bar w3-blue">
  <button class="w3-bar-item w3-button conTitle" onclick="openTab('Nasabah')">Nasabah</button>
  <button class="w3-bar-item w3-button conTitle" onclick="openTab('Jaminan')">Jaminan</button>
  <button class="w3-bar-item w3-button conTitle" onclick="openTab('Aju')">Realisasi</button>
  <button class="w3-bar-item w3-button conTitle" onclick="openTab('Simulasi')">Simulasi</button>
</div>
<table border="0">
		<tbody>
			<tr>
				<td>&nbsp;
				</td>
			</tr>
		</tbody>
	</table>
<html:form action="/trx/realUpdate" onsubmit="return validateRealForm(this);">
	<html:hidden property="dispatch"></html:hidden>
	<html:hidden property="id"></html:hidden>
	<html:hidden property="nsbhId"></html:hidden>
	<div id="Nasabah" class="w3-container aju">
	<table border="0">
		<tbody>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.noKredit"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="noKredit" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.tglAju"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="tglAju" maxlength="14" size="20" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.nasabah"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhNama" size="43" maxlength="60" readonly="true" styleClass="frmReadOnly"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.address"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="nsbhAlamat" rows="3" cols="44" styleClass="frmReadOnly" readonly="true"> </html:textarea>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.domisili"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="nsbhDomisili" rows="3" cols="44" styleClass="frmReadOnly" readonly="true"> </html:textarea>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.telepon"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhTelepon" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.jenisKelamin"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhJnsKelamin" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.nik"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhNik" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.perusahaan"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhPt" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.bagian"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhBagian" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.bank"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhBank" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.rekening"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhNoRekening" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.nasabah.tglPayroll"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="nsbhTglPayrol" maxlength="14" size="43" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="Jaminan" class="w3-container aju" style="display:none">
	  <table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.aplikasi"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="nsbhAplikasi" rows="4" cols="43" styleClass="frmReadOnly" readonly="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.penjamin"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="penjamin" rows="4" cols="42" styleClass="frmReadOnly" readonly="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.agunan"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="agunan" rows="4" cols="42" styleClass="frmReadOnly" readonly="true"></html:textarea>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div id="Aju" class="w3-container aju" style="display:none">
	<table border="0">
		<tbody>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.jenisPinjam"></bean:message></td>
				<td class="conText">
					<html:select property="jenisPinjam" disabled="">
						<html:options collection="JenisPinjam" property="value" labelProperty="label" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.jumlahAju"></bean:message></td>
				<td class="conText">
					<html:text property="jumlahAju" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.interestRate"></bean:message></td>
				<td class="conText">
					<div class="select-editable">
					<html:select property="interestRate" onchange="this.nextElementSibling.value=this.value" disabled="true">
						<html:options collection="SukuBunga" property="value" labelProperty="label" />
					</html:select>
					<html:text property="bunga" size="5" styleClass="frmReadOnly" readonly="true"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.tenor"></bean:message></td>
				<td class="conText">
					<html:text property="tenor" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.aju.angsuranAju"></bean:message></td>
				<td class="conText">
					<html:text property="angsuranAju" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.sponsor"></bean:message></td>
				<td class="conText">
					<html:select property="sponsor" disabled="true">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="AgentList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.aju.marketing"></bean:message></td>
				<td class="conText" >
					<html:select property="marketing" disabled="true">
						<html:option value=""><bean:message key="form.all"></bean:message></html:option>
						<html:options collection="MarketingList" property="id" labelProperty="status" />
					</html:select>
				</td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${DueList != null}">
	<table border="0">
		<tbody>
			<tr > 
				<td colspan="6">&nbsp;</td>
			</tr>
			<tr class="tblHeader"> 
				<td colspan="6">Pinjaman Aktif</td>
			</tr>
			
			<c:set var="kekurangan" value="${0}"/>
			<c:forEach var="aju" items="${DueList}" varStatus="status">
				<c:set var="totalUtang" value="${0}"/>
				<c:set var="totalBayar" value="${0}"/>
				
				<tr> 
					<td class="conLabel"><bean:message key="form.aju.noKredit"></bean:message></td>
					<td class="conText" colspan="5"><bean:write name="aju" property="noKredit"/></td>
				</tr>
				<tr class="tblHeader"> 
					<td>No</td>
					<td>Tanggal Cicilan</td>
					<td>Saldo</td>
					<td>Pokok</td>
					<td>Bunga</td>
					<td>Angsuran</td>
					<td>Dibayar</td>
					<td>Tanggal Bayar</td>
				</tr>
				<c:forEach var="sim" items="${aju.simulasi}" varStatus="statusSim">
					<c:choose>
						<c:when test="${statusSim.count % 2 != 0}">
							<tr class="oddRow">
						</c:when>
						<c:otherwise>
							<tr class="evenRow">
						</c:otherwise>
					</c:choose>
					<td class="celBorder"><c:out value="${sim.noUrut}"/></td>
					<td class="celBorder"><fmt:formatDate value="${sim.tglCicilan}" pattern="dd/MM/yyyy"/></td>
					<td class="celBorder"><fmt:formatNumber value="${sim.saldo}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="celBorder"><fmt:formatNumber value="${sim.pokok}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="celBorder"><fmt:formatNumber value="${sim.bunga}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="celBorder"><fmt:formatNumber value="${sim.pokok+sim.bunga}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="celBorder"><fmt:formatNumber value="${sim.dibayar}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="celBorder"><fmt:formatDate value="${sim.tglBayar}" pattern="dd/MM/yyyy"/></td>
				</tr>
					<c:set var="totalUtang" value="${totalUtang + sim.pokok + sim.bunga}" />
					<c:set var="totalBayar" value="${totalBayar + sim.dibayar}" />
				</c:forEach>
				<tr> 
					<td class="conLabel" colspan="5">Total</td>
					<td class="conText"><fmt:formatNumber value="${totalUtang}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<td class="conText"><fmt:formatNumber value="${totalBayar}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
					<c:set var="kekurangan" value="${kekurangan + totalUtang - totalBayar}" />
				</tr>
				<tr> 
					<td class="conLabel" colspan="5">Kekurangan</td>
					<td class="conText"><fmt:formatNumber value="${totalUtang - totalBayar}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
				</tr>
			</c:forEach>
			<tr> 
				<td class="conLabel" colspan="5">Total kekurangan</td>
				<td class="conText"><fmt:formatNumber value="${kekurangan}" type="number" groupingUsed="true" maxFractionDigits="0"/></td>
			</tr>
		</tbody>
	</table>
	</c:if>
	
	<table border="0">
		<tbody>
			<tr > 
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr class="tblHeader"> 
				<td colspan="2">Data Realisasi</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.tglRealisasi"></bean:message></td>
				<td class="conText" colspan="3">
					<html:text property="tglReal" maxlength="14" size="20"></html:text>
					<a href="javascript:cal1.popup();"><img src="../icons/cal.gif"  border="0" height="16" width="16"></a>
				</td>
			</tr>
			<tr>
				<td width="100" class="conLabel"><bean:message key="form.real.jmlReal"></bean:message></td>
				<td class="conText">
					<html:text property="jumlahReal" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.admin"></bean:message></td>
				<td class="conText">
					<html:text property="biayaAdmin" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.provisi"></bean:message></td>
				<td class="conText">
					<html:text property="biayaProvisi" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.lain"></bean:message></td>
				<td class="conText">
					<html:text property="biayaLain" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.sisa"></bean:message></td>
				<td class="conText">
					<html:text property="sisaAngsuran" maxlength="14" size="60"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.diterima"></bean:message></td>
				<td class="conText">
					<html:text property="diterima" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>&nbsp;<html:button property="btnCekCicilan" styleClass="frmButton" onclick="cekCicilan()">Hitung</html:button>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.terbilang"></bean:message></td>
				<td class="conText">
					<html:text property="terbilang" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<td class="conLabel"><bean:message key="form.real.keterangan"></bean:message></td>
				<td class="conText" colspan="3">
					<html:textarea property="keterangan" rows="4" cols="60" > </html:textarea>
				</td>
			</tr>
		</tbody>
		<script type="text/javascript">	
			function cekCicilan() {
				jumlahReal = $('input[name="jumlahReal"]').val();
				jumlahReal = Number(jumlahReal.replace(/[^0-9\.-]+/g, ""));
				$('input[name="jumlahReal"]').val(Intl.NumberFormat().format(jumlahReal));

				biayaAdmin = $('input[name="biayaAdmin"]').val();
				biayaAdmin = Number(biayaAdmin.replace(/[^0-9\.-]+/g, ""));
				$('input[name="biayaAdmin"]').val(Intl.NumberFormat().format(biayaAdmin));

				biayaProvisi = $('input[name="biayaProvisi"]').val();
				biayaProvisi = Number(biayaProvisi.replace(/[^0-9\.-]+/g, ""));
				$('input[name="biayaProvisi"]').val(Intl.NumberFormat().format(biayaProvisi));

				biayaLain = $('input[name="biayaLain"]').val();
				biayaLain = Number(biayaLain.replace(/[^0-9\.-]+/g, ""));
				$('input[name="biayaLain"]').val(Intl.NumberFormat().format(biayaLain));
				
				sisaAngsuran = $('input[name="sisaAngsuran"]').val();
				sisaAngsuran = Number(sisaAngsuran.replace(/[^0-9\.-]+/g, ""));
				$('input[name="sisaAngsuran"]').val(Intl.NumberFormat().format(sisaAngsuran));

				diterima = jumlahReal - (biayaAdmin + biayaProvisi + biayaLain + sisaAngsuran);
				$('input[name="diterima"]').val(Intl.NumberFormat().format(diterima));
				$('input[name="terbilang"]').val(terbilang(diterima));
			}
		</script>
	</table>
	</div>
	
	<div id="Simulasi" class="w3-container aju" style="display:none">
	<script type="text/javascript">	
			function cekSimulasi() {
				jmlAju = $('input[name="jumlahAju"]').val();
				bunga = $('input[name="bunga"]').val();
			    tenor = $('input[name="tenor"]').val();
			    tglPayroll= $('input[name="nsbhTglPayrol"]').val();
			    tglAju= $('input[name="tglAju"]').val();
			    $.post("ajuUpdate.do",
		            {
			    	  dispatch:"simulasiKredit",
			    	  sukuBunga: bunga,
		              jmlAju: jmlAju,
		              tenor: tenor,
		              tglAju: tglAju,
		              tglPayroll: tglPayroll
		            },
		            function(data,status){
		            	if(data.indexOf(";") !== -1) {
		            		arr = data.split(";");
		            		$('input[name="jatuhTempo"]').val(arr[0]);
		            		$('tbody[id="barisSimulasi"]').replaceWith(arr[1]);
		            	} else {
		            		$('input[name="jatuhTempo"]').val(data);
		            	}
		            	
		            });
			}
		</script>
	<table width="100%" cellpadding="3" cellspacing="0" class="tblBorder">
		<tr>
			<td class="conLabel"><bean:message key="form.aju.jatuhTempo"></bean:message></td>
			<td class="conText" colspan="5">
				<html:text property="jatuhTempo" maxlength="14" size="42" styleClass="frmReadOnly" readonly="true"></html:text>
				&nbsp;<html:button property="btnSimulasi" styleClass="frmButton" onclick="cekSimulasi()">Simulasi</html:button>
			</td>
		</tr>
		<tr> 
			<td class="conLabel"><bean:message key="form.aju.noKredit"></bean:message></td>
			<td class="conText" colspan="3"><bean:write name="aju" property="noKredit"/></td>
		</tr>
		<tr class="tblHeader"> 
			<td>No</td>
			<td>Tanggal</td>
			<td>Saldo</td>
			<td>Pokok</td>
			<td>Bunga</td>
			<td>Angsuran</td>
		</tr>
		<tbody id="barisSimulasi" >
		</tbody>
	</table>
	</div>
	
	
	<table width="100%" border="0">
		<tbody>
			<tr>
				<td>&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<html:submit property="btnSubmit" styleClass="frmButton"><bean:message key="button.save"></bean:message></html:submit>
					<html:button property="btnCancel" styleClass="frmButton" onclick="cancel()"><bean:message key="button.cancel"></bean:message></html:button>
				</td>
			</tr>
		</tbody>
	</table>	
</html:form>

</BODY>

<script language="JavaScript">
	var cal1 = new calendar2(document.forms['realForm'].elements['tglReal']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
</script>

<style>
 .select-editable {
     position:relative;
     background-color:white;
     border:solid grey 1px;
     width:120px;
     height:18px;
 }
 .select-editable select {
     position:absolute;
     top:0px;
     left:0px;
     font-size:14px;
     border:none;
     width:120px;
     margin:0;
 }
 .select-editable input {
     position:absolute;
     top:0px;
     left:0px;
     width:100px;
     padding:1px;
     font-size:12px;
     border:none;
 }
 .select-editable select:focus, .select-editable input:focus {
     outline:none;
 }
</style>
</html:html>