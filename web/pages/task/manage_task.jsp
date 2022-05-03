<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理任务</title>
    <%@ include file="/pages/common/head.jsp" %>

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
    <span class="wel_word">管理任务</span>
</div>

<div id="main">

    <c:if test="${not empty requestScope.list}">
        <c:forEach var="item" items="${requestScope.list}">
            <form action="taskServlet/changeState" method="post">
                <input type="hidden" name="state" value="${item.state}">
                <input type="hidden" name="taskId" value="${item.taskId}">
                <table>
                    <tr>
                        <td>任务编号:</td>
                        <td>${item.taskId}</td>
                        <td>${item.describe}</td>
                        <td>状态:</td>
                        <td>
                            <c:if test="${item.state eq 1}">
                                待提交
                            </c:if>
                            <c:if test="${item.state eq 0}">
                                已截止
                            </c:if>
                        </td>
                        <td><input type="submit" value="修改状态"></td>
                    </tr>
                </table>
            </form>

        </c:forEach>
    </c:if>


</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
