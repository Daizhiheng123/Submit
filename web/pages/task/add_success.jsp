<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布成功</title>
    <%@ include file="/pages/common/head.jsp" %>

    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <div>
        <a href="index.jsp">返回首页</a>
    </div>
</div>

<div id="main">

    <h1>任务已成功发布 <a href="pages/task/release.jsp">返回任务发布页面</a></h1>

</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>

