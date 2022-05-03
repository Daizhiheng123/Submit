<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>上传失败</title>
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
        <a href="index.jsp">转到主页</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="userServlet/logout">注销</a>
    </div>
</div>

<div id="main">
    <h1>上传失败，${requestScope.msg}&nbsp;&nbsp;<a href="pages/dotask/view.jsp">返回任务列表</a></h1>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
