<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>error</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>

出错啦，请联系后台服务人员维修<br/>
<a href="${pageScope.bathPath}index.jsp">返回首页</a>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>