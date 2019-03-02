<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		pageContext.setAttribute("myDate", new java.util.Date()); 
	%>
	北京时间:<fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	
</body>
</html>