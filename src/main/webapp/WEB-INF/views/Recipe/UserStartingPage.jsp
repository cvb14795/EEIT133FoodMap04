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
<title>使用者起始畫面</title>
<style>
    header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>
<body>
    <header>
		<h1>食譜 or 營養</h1>
	</header>
	
    <form action="./UserRecipe">
        <button type="submit">食譜</button>
    </form>
    <form action="">
        <button type="submit">營養</button>
    </form>
</body>
</html>