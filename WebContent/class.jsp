<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<table style="border:1px solid black;">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>班级编号</th>
        <th>分数</th>
        <th>用户编号</th>
    </tr>
    <c:forEach var="student" items="${STUDENT_LIST}">
        <tr>
            <td>${student.studentID}</td>
            <td>${student.name}</td>
            <td>${student.gender}</td>
            <td>${student.age}</td>
            <td>${student.classID}</td>
            <td>${student.score}</td>
            <td>${student.uid}</td>
        </tr>
        <!-- 学号：<br/>
        姓名：<br/>
        性别：<br/>
        年龄：<br/>
        班级编号：<br/>
        分数：<br/>
        用户编号：<br/> -->
    </c:forEach>
</table>

</body>
</html>