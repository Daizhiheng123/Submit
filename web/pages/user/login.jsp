<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                if ($("#userId").val().trim().length == 0 || $("#password").val().length == 0) {
                    $(".errorMsg").text("学号和密码不能为空")
                } else {
                    $("#info").submit()
                }
            })
            $("#userId").focus(function () {
                $(".errorMsg").text("请输入学号和密码");
            })
            $("#password").focus(function () {
                $(".errorMsg").text("请输入学号和密码");
            })
        })
    </script>

</head>
<body>
<div id="login_header">
    <img class="logo_img" src="static/img/loader.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>用户登录</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>

                    <span class="errorMsg">
                        ${empty requestScope.msg ? "请输入学号和密码":requestScope.msg}
                    </span>

                </div>
                <div class="form">
                    <form action="userServlet/login" id="info" method="post">
                        <label>学号：</label>
                        <input class="itxt" type="text" placeholder="请输入学号" autocomplete="off" tabindex="1"
                               name="userId" id="userId"/>
                        <br/>
                        <br/>
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <input type="button" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>