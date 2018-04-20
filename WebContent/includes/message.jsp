<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="com.benclaus.koperasi.utility.Constant" %>

<logic:messagesPresent message="true">
	<table class="msgBorder" width="90%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tbody>
			<tr>
				<td>
				<table width="100%" border="0">
					<tbody>
						<tr>
							<td align="left" valign="top" class="conLabel"><img border="0" src="<%=request.getContextPath()%>/icons/information.gif" width="19" height="19"></td>
							<td align="left" width="100%" class="conText"><html:messages message="true" id="message" property="<%=Constant.GLOBALMESSAGE%>" /><c:out value="${message}" /></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table><br>
</logic:messagesPresent>
<logic:messagesPresent property="<%=Constant.GLOBALERROR%>">
	<table class="msgBorder" width="90%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tbody>
			<tr>
				<td>
				<table width="100%" border="0">
					<tbody>
						<tr>
							<td align="left" valign="top" class="conLabel"><img border="0" src="<%=request.getContextPath()%>/icons/exclamation.gif" width="19" height="19"></td>
							<td align="left" width="100%" class="conText"><html:errors property="<%=Constant.GLOBALERROR%>" /></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</logic:messagesPresent>
<logic:messagesPresent property="<%=Constant.GLOBALMESSAGE%>">
	<table class="msgBorder" width="90%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tbody>
			<tr>
				<td>
				<table width="100%" border="0">
					<tbody>
						<tr>
							<td align="left" valign="top" class="conLabel"><img border="0" src="<%=request.getContextPath()%>/icons/information.gif" width="19" height="19"></td>
							<td align="left" width="100%" class="conText"><html:errors property="<%=Constant.GLOBALMESSAGE%>" /></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</logic:messagesPresent>
