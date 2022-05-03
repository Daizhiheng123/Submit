<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">

    <span class="wel_word">后台管理</span>
    <div>
        <a href="pages/task/release.jsp">发布任务</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="taskServlet/manageTask?action=manage">管理任务</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="taskServlet/showTask">查看已发布的任务</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="taskServlet/manageTask?action=download">下载文件</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="index.jsp">返回首页</a>
    </div>
</div>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
