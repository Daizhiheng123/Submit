<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>
<div id="header">
    <div>
        <a href="pages/manager/manager.jsp">返回后台</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="index.jsp">返回主页</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="managerServlet/logout">注销</a>
    </div>
    <span class="wel_word">任务列表</span>
</div>

<div id="main">

    <c:if test="${not empty requestScope.tasks}">
        <c:forEach var="item" items="${requestScope.tasks}">
            <form action="mapperServlet/notSubmit" method="post">
                <input type="hidden" name="taskId" value="${item.taskId}">
                <input type="hidden" name="describe" value="${item.describe}">
                <table>
                    <tr>
                        <td>任务编号:${item.taskId}</td>
                        <td></td>
                        <td>${item.describe}</td>
                        <td></td>
                        <td></td>
                        <td><input type="submit" value="查询详情"></td>
                    </tr>
                </table>
            </form>
        </c:forEach>
    </c:if>
    <c:if test="${empty requestScope.tasks}">
        <h2>尚未有待提交的任务</h2>
    </c:if>

</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
