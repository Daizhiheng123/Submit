<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布任务</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            $("#sub_btn").click(function () {

                var text = $("#describe").val().trim();
                if (text.length > 0) {
                    var flag = confirm("确认发布该任务吗");
                    if (flag)
                        $("#info").submit();
                }
            })
        })
    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            height: 75%;
        }

        #sub_btn {
            height: 26px;
            width: 50%;
        }
    </style>
</head>

<body>
<div id="header">
    <span class="wel_word">发布任务</span>
    <div>
        <a href="pages/manager/manager.jsp">返回后台</a>
        &nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="index.jsp">返回首页</a>
    </div>
</div>

<div id="main">
    <form action="taskServlet/releaseTask" method="post" id="info">
        <table>
            <tr>
                <td colspan="1">任务描述</td>
                <td></td>
            </tr>
            <tr>
                <td><input id="describe" name="describe" type="text" size="100"/></td>
                <td><input type="button" value="发布" id="sub_btn"/></td>
            </tr>
        </table>
    </form>

</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>

</html>
