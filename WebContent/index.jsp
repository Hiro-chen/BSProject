<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1 style="text-align:center;color:green;">欢迎来到首页</h1>
<a href="${pageContext.request.contextPath}/User?method=findStudentByClassID&classid=6"
   style="text-align:center;color:green;">软件6班</a><br/>
<a href="${pageContext.request.contextPath}/User?method=findRoleInfo&role=student&uid=1"
   style="text-align:center;color:green;">uid=1的用户</a><br/>
<a href="${pageContext.request.contextPath}/User?method=changePassword&username=1713157601&password=000000&newpassword=123456"
   style="text-align:center;color:green;">修改1713157601的密码</a>
</body>
</html>