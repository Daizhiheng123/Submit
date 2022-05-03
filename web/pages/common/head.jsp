<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>"/>
<link rel="shortcut icon" href="#"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css"/>
<script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>