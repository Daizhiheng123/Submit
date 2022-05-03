<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>未提交</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>
<div id="header">
    <div>
        <a href="taskServlet/showTask">返回任务列表</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="index.jsp">返回主页</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="managerServlet/logout">注销</a>
    </div>
    <span class="wel_word">未提交名单</span>
</div>

<div id="main">

    <c:if test="${not empty requestScope.list}">
        <h2>未提交&nbsp;<a href="javascript:void(0)">${requestScope.msg}</a>&nbsp;的人员名单如下</h2>
        <c:forEach var="item" items="${requestScope.list}">
            <table>
                <tr>
                    <td>学号：${item.userId}</td>
                    <td></td>
                    <td></td>
                    <td>姓名：${item.name}</td>
                    <td></td>
                </tr>
            </table>
        </c:forEach>
    </c:if>


</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
