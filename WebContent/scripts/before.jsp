<%@page import="java.util.Enumeration"%>
<html>
<head>
<title>JSP Page</title>
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
</head>
<body>

<%-- Start of enviroment setting --%>
    
    <%-- Start Declaration --%>
    <%-- End Declaration --%>


    <%-- Start page --%>
    <%-- End page --%>

    <%-- Request page --%>
    <%-- Request page --%>

    <%-- Session page --%>
    <%-- Session page --%>

    <%-- Application page --%>
    <%-- Application page --%>
<%-- End of enviroment setting --%>


<% for (int i = 2; i < 4; i++) {%>
    <% 
        String app = "";
        if (i == 2) { app = "REQUEST";
        }else if (i == 3) { app = "SESSION"; }
    %>
    <CENTER><H3><%=app%> (<%=i%>) Before</H3> </CENTER>
    <TABLE border='1' width='100%' bgcolor='black'>
        <%
            Enumeration tmpEnum  = pageContext.getAttributeNamesInScope(i);
            while(tmpEnum.hasMoreElements()) {
                String key = tmpEnum.nextElement().toString();
                if (key.startsWith("javax.servlet") || key.startsWith("org.apache")) { continue; }
                String value  = pageContext.getAttribute(key, i).toString();
            %>
            <%
            if (i == 1) {%>
                <TR bgcolor='lightyellow'><td class="conLabel" width="50%"><%=key%></TD><td class="conLabel" width="50%"><%=value%></TD></TR>
            <%}else if (i == 7) {%>
                <TR  bgcolor='orange'><td class="conLabel" width="50%"><%=key%></TD><td class="conLabel" width="50%"><%=value%></TD></TR>
            <%}else if (i == 3) {%>
                <TR  bgcolor='lightgreen'><td class="conLabel" width="50%"><%=key%></TD><td class="conLabel" width="50%"><%=value%></TD></TR>
            <%}else if (i == 4) {%>
                <TR  bgcolor='lightblue'><td class="conLabel" width="50%"><%=key%></TD><td class="conLabel" width="50%"><%=value%></TD></TR>
            <%}%>            
        <%
            }
        %>
    </TABLE>
    <P>
<%
}%>
</body>
</html>