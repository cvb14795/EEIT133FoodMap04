<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>查詢食譜</title>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
<script src='<c:url value="/js/bootstrap.js"/>'></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>
<script>
	$(function() {
		adminAuth();
	})
</script>
<style>
    header {
	background:	#9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<input  id="isAdmin" value="${isAdmin}">
	<input  id="userName" value="${user}">
	<header>
		<h1>食譜查詢</h1>
	</header>
	
	<div class="memberDetail">
		<div class="text-right">
			會員：
			<span id="userNameContainer"></span>
		</div>
		<div class="text-right">
			身分：
			<span id="isAdminContainer"></span>
		</div>
	</div>
	
	<form>
		<input type ="button" onclick="history.back()" value="首頁"> <!-- OK -->
	</form>
	<form action="./UserViewAdminRecipe" method="Post">
		<button type="submit" name="confirm"><i class="fas fa-pizza-slice"></i>查詢官方食譜</button> <!-- OK -->
	</form>
	<form action="./UserInsertRecipe">
		<button type="submit" name="submit">新增您的個人食譜</button> <!-- OK -->
	</form>
	<form action="./ViewYourRecipe" method="Post">
		<button type="submit" name="submit">查詢您的個人食譜</button> 
	</form>
	<form action="./UserViewMembersRecipe" method="Post">
		<button type="submit" name="submit">查詢所有會員食譜</button> <!-- OK -->
	</form>
</body>

</html>