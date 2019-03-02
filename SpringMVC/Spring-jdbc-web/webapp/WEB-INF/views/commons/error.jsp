<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	1:默认异常对象名称方式<%=exception.getMessage()%><br>
	2:xml设置异常对象名称方式:${ex.message}<br>
	3:ExceptionHandler注解方式${errorMsg}
</body>
</html>