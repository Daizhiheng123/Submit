<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style>
        #first-img {
            width: 66%;
        }

        #second-img {
            width: 33%;
        }

    </style>
</head>

<body>

<div id="header">
    <span class="wel_word">在线提交系统</span>

    <c:if test="${empty sessionScope.manager}">
        <c:if test="${empty sessionScope.user}">
            <div>
                <a href="pages/user/login.jsp">登录</a> |
                <a href="pages/user/regist.jsp">注册</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="pages/manager/manager.jsp">后台管理</a>
            </div>
        </c:if>
    </c:if>

    <c:if test="${not empty sessionScope.manager}">
        <div>
            <a href="pages/manager/manager.jsp">后台管理</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="managerServlet/logout">注销</a>
        </div>
    </c:if>

    <c:if test="${not empty sessionScope.user}">
        <div>
            <a href="pages/dotask/view.jsp">查看任务</a>
            &nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="userServlet/logout">注销</a>
        </div>
    </c:if>


</div>

<div id="main">

    <div id="img">
        <img id="first-img" src="static/img/aaa.jpg" alt="">
        <img id="second-img" src="static/img/auth-img1.png" alt="">
    </div>

</div>

<%@ include file="/pages/common/footer.jsp" %>

</body>

</html>