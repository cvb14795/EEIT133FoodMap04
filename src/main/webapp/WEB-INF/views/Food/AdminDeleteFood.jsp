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
	background: #FFBB77;
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
		<h1>確認以下將刪除之資料</h1>
	</header>
<%-- 	<jsp:useBean id="recipe" class="com.web.model.AdminRecipeBean" scope="session"></jsp:useBean> --%>
	<form:form action="./AdminDeleteFoodAction" modelAttribute="recipe" method="POST">
		<table>
			<tr bgcolor="#FFFFE1">
				<td>店家名稱:${Food.mapname}</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家地址:${Food.mapku }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>店家電話:${Food.mapnb }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家座標位置:${Food.mapxy }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>是否為安全店家:${Food.mapcheck }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>分類:${Food.category }</td>
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