<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta charset="UTF-8">
<title>查詢食譜</title>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/memberDetail.css">
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/memberAuth.js"></script>
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
	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
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

	<form action="./UserStartingPage.jsp">
		<input type="submit" value="首頁">
	</form>
	<form action="./AdminViewRecipeServlet" method="Post">
		<button type="submit" name="confirm" class="adminScope"><i class="fas fa-pizza-slice"></i>查詢官方食譜</button>
	</form>
	<form action="./UserInsertRecipe.jsp" method="Post">

		<button type="submit" name="submit" class="userScope">新增您的個人食譜</button>
	</form>
	<form action="" method="Post">
		<button type="submit" name="submit" class="userScope">查詢您的個人食譜</button>
	</form>
	<form action="./UserViewMembersRecipe" method="Post">
		<button type="submit" name="submit" class="userScope">查詢所有會員食譜</button>
	</form>

</body>

</html>