<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>重新導向...</title>
</head>
<body>
	<script>
		var msg = "請先登入！";
		if("${param.msg}" != ""){
			msg = "${param.msg}";	
		}
		alert(msg);
		location.href = "<c:url value='/Member/Login' />";
	</script>
</body>
</html>