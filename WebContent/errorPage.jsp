<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page 
language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"
isErrorPage="true"
%>
<% 
	String appName = getServletContext().getServletContextName();
%>
<html>
<head>
<script src="scripts/view.js"></script>
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" >
<title>Error in <%= appName %></title>
</head>
<body>
<table class="msgBorder" width="90%" border="1" cellpadding="0" cellspacing="0" align="center">
	<tbody>
		<tr>
			<td>
			<table width="100%" border=0>
				<tbody>
					<tr>
						<td align="left" valign="top" class="conLabel"><img border="0" src="<%=request.getContextPath()%>/icons/exclamation.gif" width="19" height="19"></td>
						<td align="left" width="100%" class="conText">Unhandled Exception (<%= exception %>) ocurred while accessing: <%= request.getAttribute("javax.servlet.error.request_uri") %> (servlet: <%= request.getAttribute("javax.servlet.error.servlet_name") %>)
						<pre>
<%	if (exception instanceof javax.servlet.ServletException) {
		Throwable cause = ((javax.servlet.ServletException)exception).getRootCause();
		if (cause!=null) {
			exception.printStackTrace(new java.io.PrintWriter(out));
		} else {
			exception.printStackTrace(new java.io.PrintWriter(out));
		}
	} else {
		exception.printStackTrace(new java.io.PrintWriter(out));
	}
%>
</pre>
</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>
