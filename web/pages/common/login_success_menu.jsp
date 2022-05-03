<%@ page contentType="text/html;charset=UTF-8" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.name}</span>使用在线提交系统</span>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a href="pages/dotask/view.jsp">查看任务</a>
    &nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="userServlet/logout">注销</a>
</div>
