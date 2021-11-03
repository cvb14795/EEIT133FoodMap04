<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>資料刪除</title>
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<style>
body {
	font-size: 20px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: white;
	line-height: 1.5em;
}

header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}

.container {
	width: 90%;
	/* 外距:元素跟元素的間距 */
	margin: auto;
	/* 內距:元素內跟邊界的間距 */
	padding: 10px;
}

fieldset {
	/*因為是包住 div 所以要比 div 大*/
	width: 500px;
	margin: 15px;
	border: 3px solid #FF0080;
	border-radius: 20px;
}

legend {
	font-size: 24px;
	color: #66B3FF;
	/* text-align: center; */
	margin-left: 10px;
}

.st1 {
	width: 450px;
	border-bottom: 2px dashed #FF95CA;
	margin: 20px;
	padding-bottom: 20px;
}

table {
	margin: auto;
}

.sub {
	width: 450px;
	margin: auto;
	text-align: center;
}

.t1 {
	width: 100px;
	float: left;
	text-align: right;
}
</style>
</head>
<body>
	<header>
		<h1>請確認以下將刪除之資料</h1>
	</header>
	<form:form action="./UserDeleteRecipeAction" modelAttribute="recipe" method="POST">
		<table>
			<tr bgcolor="#F2F4FB">
				<td>品項:${recipe.userName}</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>品項:${recipe.foodName}</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>分類:${recipe.category }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材1:${recipe.food1 }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材2:${recipe.food2 }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材3:${recipe.food3 }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材4:${recipe.food4 }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料1:${recipe.sauce1 }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>調味料2:${recipe.sauce2 }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料3:${recipe.sauce3 }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>照片:<img src="data:image/jpg;base64,${base64String}" width="100" height="100"></td>
			</tr>
			<div>
				<input type="text" name="id" value="${param.id}" hidden>
			</div>
		</table>
		<div class="sub">
			<input type="submit" name="submit" value="確認刪除">
		</div>
	</form:form>
</body>
</html>