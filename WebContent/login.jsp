<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/User">
    <input type="hidden" name="method" value="login">
    账号：<input type="text" name="username"><br/>
    密码：<input type="text" name="password"><br/>
    <input type="submit" value="登录"><br/>
</form>
</body>
</html>