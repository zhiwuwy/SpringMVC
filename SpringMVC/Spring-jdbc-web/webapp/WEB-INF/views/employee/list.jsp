<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>员工列表,你好${user_in_session.username}</h3>
	<a href="/login.jsp">注销</a>
	<hr>
	<a href="/employee/input">新增</a>
	<table border="1" cellspacing="0" cellpadding="0" width="500">
		<tr style="background-color: gray">
			<th>ID</th>
			<th>用户名</th>
			<th>密码</th>
			<th>年龄</th>
			<th>入职时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${employees}" var="emp" varStatus="vs">
			<tr style="background-color: ${vs.count % 2 == 0 ? "yellow" : ""}">
				<td>${emp.id}</td>
				<td>${emp.username}</td>
				<td>${emp.password}</td>
				<td>${emp.age}</td>
				<td>${emp.hiredate}</td>
				<td>
					<a href="/employee/input?id=${emp.id}">编辑</a>&emsp;<a href="/employee/delete?id=${emp.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>