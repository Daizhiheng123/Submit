<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            var len = 0;
            $.ajax({
                url: "taskServlet/viewTask",
                dataType: "json",
                success: function (resp) {
                    len = resp.length;
                    if (jQuery.isEmptyObject(resp)) {
                        $("#main").append("<h2>尚未有要提交的任务</h2>")
                    } else {
                        $.each(resp, function (i, item) {
                            $("#main").append("" +
                                "<form action='fileServlet/upload' enctype='multipart/form-data' method='post'>" +
                                "<table>" +
                                "<tr>" +
                                "<td>" + "任务编号:" + item.taskId + "</td>" +
                                "<td>" + item.describe + "</td>" +
                                "<td><input type='file' name='task'/></td>" +
                                "<td><input type='hidden' name='taskId' value='" + item.taskId + "'/><input type='submit' value='上传'/></td>" +
                                "</tr>" +
                                "</table>" +
                                "</form>")
                        })
                    }
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.name}</span>使用在线提交系统(文件大小 < 10MB)</span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="index.jsp">返回主页</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="userServlet/logout">注销</a>

    </div>
    <span class="wel_word">任务列表</span>
</div>

<div id="main">

</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>