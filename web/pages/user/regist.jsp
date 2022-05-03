<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {

            $("#sub_btn").on("click", function () {

                //将所有事件都触发一遍
                $("#name").focus();
                $("#name").blur();
                $("#userId").focus();
                $("#userId").blur();
                $("#password").focus();
                $("#password").blur();
                $("#repwd").focus();
                $("repwd").blur();

                var err = $("span.errorMsg").text().trim()
                if (err.length != 0) {
                    return false;
                }
                var code = $("#code").val();
                var trim = code.trim();
                if (trim.length == 0) {
                    $("span.errorMsg").text("验证码有误");
                    return false;
                }
            });

            //给验证码绑定一个点击的事件
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            })

            //验证确认密码
            $("#repwd").blur(function () {
                var repwd = this.value;
                var pwd = $("#password").val();
                if (pwd.length == 0 && $(".errorMsg").text().length == 0) {
                    $("span.errorMsg").text("密码不能为空");
                } else if (pwd != repwd && $(".errorMsg").text().length == 0) {
                    $("span.errorMsg").text("密码不一致");
                }
            })

            $("#repwd").focus(function () {
                var err = $("span.errorMsg").text();
                if (err.length != 0 && err.startsWith("密码不一")) {
                    $("#repwd").val("")
                    $("span.errorMsg").text("");
                }
            })

            //验证密码
            $("#password").blur(function () {
                var pwd = this.value;
                if (pwd.length == 0 && $("span.errorMsg").text().length == 0) {
                    $(".errorMsg").text("密码不能为空");
                }
            })

            $("#password").focus(function () {
                var err = $("span.errorMsg").text();
                if (err.length != 0 && err.startsWith("密码不能")) {
                    $("#password").val("");
                    $("span.errorMsg").text("");
                }
            })

            //对姓名判断，只要不为空
            $("#name").blur(function () {
                var name = this.value.trim();
                if (name.length == 0 && $("span.errorMsg").text().length == 0) {
                    $("span.errorMsg").text("姓名不能为空");
                }
            })
            $("#name").focus(function () {
                var err = $("span.errorMsg").text();
                if (err.length != 0 && err.startsWith("姓名")) {
                    $("#name").val("");
                    $("span.errorMsg").text("");
                }
            })

            //对学号的检查
            $("#userId").blur(function () {

                let id = this.value.trim();	//获取学号
                if (id.length == 0) {
                    //首先判断学号是否不为空
                    $("span.errorMsg").text("学号不能为空");
                } else {
                    //再判断是否符合学号的要求
                    let reg = /^20[0-9]{6}/;
                    if (!reg.test(id)) {
                        $("span.errorMsg").text("学号不符合要求");
                    } else {
                        //都符合要求后发送异步请求，判断数据库中学号是否存在
                        $.ajax({
                            url: "userServlet/ajaxExist",
                            data: {"userId": id},
                            dataType: "json",
                            success: function (resp) {
                                if (resp.exist) { //表示用户以存在
                                    $("span.errorMsg").text("学号已存在");
                                }
                            },
                            type: "get"
                        })
                    }
                }
            })
            $("#userId").focus(function () {
                var err = $("span.errorMsg").text();

                if (err.length != 0 && err.startsWith("学号")) {
                    $("#userId").val("");
                    $("span.errorMsg").text("");
                }
            });
        });

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>

<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/loader.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>用户注册</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet/regist" method="post">
                        <label>学号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input class="itxt" type="text" placeholder="请输入学号" autocomplete="off" tabindex="1"
                               name="userId" id="userId"/>
                        <br/>
                        <br/>
                        <label>姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input class="itxt" type="text" placeholder="请输入姓名" autocomplete="off" tabindex="1"
                               name="name" id="name"/>
                        <br/>
                        <br/>
                        <label>密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               id="repwd"/>
                        <br/>
                        <br/>

                        <label>验证码：&nbsp;&nbsp;&nbsp;</label>
                        <input class="itxt" type="text" name="code" style="width: 120px;" id="code"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 40px; width: 110px; height: 40px">
                        <br/>
                        <br/>
                        <input type="submit" id="sub_btn" value="注册">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>

</html>