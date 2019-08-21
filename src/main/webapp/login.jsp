<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/8
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>登录</h1>
<form action="login" method="post">
    <input name="email" placeholder="EMAIL"><br>
    <input name="password" type="password" placeholder="PASSWORD"><br>
    <%--<%=request.getAttribute("message") == null ? "": request.getAttribute("message")%><br>--%>
    ${requestScope.message}
    <input type="submit" value="LOGIN">
</form>


</body>
</html>
