<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script src="/WebContent/js/jquery-3.6.0.js"></script>
	<header>
		<h1>食譜查詢</h1>
	</header>
	<form action="./UserStartingPage.jsp">
		<input type="submit" value="首頁">
	</form>
	<form action="./AdminViewRecipeServlet" method="Post">
		<button type="submit" name="confirm"><i class="fas fa-pizza-slice"></i>查詢官方食譜</button>
	</form>
	<form action="./UserInsertRecipe.jsp" method="Post">
		<button type="submit" name="submit">新增個人食譜</button>
	</form>
	<form action="" method="Post">
		<button type="submit" name="submit">查詢個人食譜</button>
	</form>
	<form action="" method="Post">
		<button type="submit" name="submit">查詢會員食譜</button>
	</form>
</body>

</html>