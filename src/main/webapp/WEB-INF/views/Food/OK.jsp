<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>資料刪除</title>
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<style type="text/css">
header {
	background: #FFBB77;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<header>
	<h1>請確認以下將刪除之資料</h1>
	</header>
	<script>
		Swal.fire({
			position : 'center',
			icon : 'success',
			title : '刪除成功',
			showConfirmButton : false,
			timer : 3000
		})
// 		setTimeout("location.href = 'AdminStartingPage.jsp'",3000);
		setTimeout("window.location.pathname = 'FoodMap04/Food/admin'",1500);
		
	</script>
</body>

</html>