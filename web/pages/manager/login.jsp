<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录页面</title>
    <%@ include file="/pages/common/head.jsp" %>

</head>
<body>
<div id="login_header">
    <img class="logo_img" src="static/img/loader.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">管理员登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员登录</h1>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${empty requestScope.msg ? "请输入管理员ID和密码":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="managerServlet/login" method="post">
                        <label>ID号：</label>
                        <input class="itxt" type="text" placeholder="请输入ID号" autocomplete="off" tabindex="1"
                               name="managerId"/>
                        <br/>
                        <br/>
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>