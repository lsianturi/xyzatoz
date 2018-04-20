<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="theme/Master.css" rel="stylesheet" type="text/css">
<link rel="Shortcut Icon" href="icons/favicon.ico" type="image/x-icon">
<script src="scripts/view.js"></script>
<title><bean:message key="application.title"></bean:message></title>
</head>

<frameset id="mainFrameset" rows="46,1*,30" border="0" framespacing="0">
	<frame name="header" scrolling="no" noresize src="includes/header.html"
		marginwidth="1" marginheight="1" frameborder="0">
	<frameset id="contentFrameset" cols="266,1*" border="0"
		bordercolor="black" framespacing="0">
		<frame name="left" src="includes/loginLeft.html" marginwidth="0"
			marginheight="0" scrolling="no" frameborder="0">
		<frame id="content" name="content" src="login.do" marginwidth="4"
			marginheight="4" scrolling="yes" frameborder="0">
	</frameset>
	<frame name="footer" scrolling="no" noresize src="includes/footer.html"
		marginwidth="1" marginheight="1" frameborder="0">
	<noframes>
	<body>

	<p>This page uses frames, but your browser doesn&#39;t support them.</p>

	</body>
	</noframes>
</frameset>

</html>