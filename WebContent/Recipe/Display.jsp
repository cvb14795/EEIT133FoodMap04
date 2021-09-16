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
<title>食譜確認</title>
<style type="text/css">
header {
            background: #FFBB77;
            color: white;
            padding: 20px;
            text-align: center;
            margin-bottom: 10px;
        }
        table {
        	margin:auto;
        }
        .sub {
            width: 450px;
            margin: auto;
            text-align: center;
        }
</style>
</head>
<body>
	<header>
		<h1>食譜確認</h1>
	</header>
	<jsp:useBean id="recipe" class="recipe.bean.RecipeBean" scope="session"></jsp:useBean>
	<form action="./RecipeServlet" method="post">
		<table>
			<tr bgcolor="#FFFFE1">
				<td>品項:</td>
				<td><jsp:getProperty property="name" name="recipe" /></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>分類:</td>
				<td><jsp:getProperty property="category" name="recipe" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材1:</td>
				<td><jsp:getProperty property="food1" name="recipe" /></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材2:</td>
				<td><jsp:getProperty property="food2" name="recipe" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材3:</td>
				<td><jsp:getProperty property="food3" name="recipe" /></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材4:</td>
				<td><jsp:getProperty property="food4" name="recipe" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料1:</td>
				<td><jsp:getProperty property="sauce1" name="recipe" /></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>調味料2:</td>
				<td><jsp:getProperty property="sauce2" name="recipe" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料3:</td>
				<td><jsp:getProperty property="sauce3" name="recipe" /></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>照片:</td>
				
				<td><img src="data:image/jpg;base64,${sessionScope.base64Img}" width="100" height="100" ></td>
			</tr>
		</table>
		<div class="sub">
			<input type="submit" name="confirm" value="確認">
		</div>



	</form>







</body>
</html>