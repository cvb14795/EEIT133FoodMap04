<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首頁</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/memberDetail.css">
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>

<script>
$(function() {
		const cookies = `${document.cookie}`;
		console.log(`cookies: ${cookies}`);
	let userName = cookies.split("user=")[1];
		$("#user").text(`${userName}`);
})
</script>

</head>
<body>
<!-- 	<div class="text-right memberDetail"> -->
<!-- 		您好，<span id="user"></span>！ -->
<!-- 	</div> -->

	<h1>這是首頁</h1>

	<br>
	<h2>歡迎大家來填問卷</h2>
	<input type="button" value="填問卷"
		onclick="location.href='<c:url value='/Coupon/questionnaire' />'">


	<h2>管理員專用</h2>
	<input type="button" value="功能" onclick="location.href='<c:url value='/Coupon/admin' />'">


</body>
</html>