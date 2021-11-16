<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店家確認</title>
<style type="text/css">
header {
            background: #536e75;
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
		<h1>店家確認</h1>
	</header>
	<form:form action="./adminInsertToDB" modelAttribute="recipe" method="POST">
		<table>
			<tr bgcolor="#FFFFE1">
				<td>店家名稱:<form:input path="mapname"/></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家地址:<form:input path="mapku"/></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>店家電話:<form:input path="mapnb"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家座標位置:<form:input path="mapxy"/></td>
				
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>是否為安全店家:<form:input path="mapcheck"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>分類:<form:input path="category"/></td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>
					照片:<img src="data:image/jpg;base64,${base64String}" width="100" height="100" >
<%-- 					<input hidden="true" name="base64String" value="${base64String}"/> --%>
					<form:input path="filename" hidden="true"/>
				</td>
			</tr>
		</table>
		<div class="sub">
			<input type ="button" onclick="history.back()" value="資料有誤，需修改">
			<input type="submit" name="submit" value="確認無誤，送出">
		</div>
	</form:form>
</body>
</html>