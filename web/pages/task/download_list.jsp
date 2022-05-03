<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>下载文件</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style>
        a {
            text-decoration: none;
        }
        a:hover {
            color: red;
        }
    </style>
</head>
<body id="first">
<div id="header">
    <div>
        <a href="pages/manager/manager.jsp">返回后台</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="index.jsp">返回主页</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="managerServlet/logout">注销</a>
    </div>
    <span class="wel_word">下载文件</span>
</div>
<div id="main">
    <c:if test="${not empty requestScope.list}">
        <c:forEach var="item" items="${requestScope.list}">
                <input type="hidden" name="taskId" value="${item.taskId}">
                <table>
                    <tr>
                        <td>任务编号:</td>
                        <td>${item.taskId}</td>
                        <td>${item.describe}</td>
                        <td></td>
                        <td><a href="fileServlet/download?taskId=${item.taskId}&describe=${item.describe}">一键下载</a></td>
                    </tr>
                </table>
        </c:forEach>
    </c:if>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
