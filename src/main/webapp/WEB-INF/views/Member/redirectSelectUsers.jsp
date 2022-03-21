<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選擇登入使用者</title>
</head>
<body>
	<script>
		window.open("<c:url value='/selectUsers'/>", "_blank", "width=1000,height=500");
	</script>
</body>
</html>