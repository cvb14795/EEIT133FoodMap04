<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
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
	<form:form action="./adminInsertToDB" modelAttribute="recipe" method="POST">
		<table>
			<tr bgcolor="#FFFFE1">
<%-- 				<td>品項:${recipe.name}</td> --%>
				<td>品項:<form:input path="name"/></td>
			</tr>
			<tr bgcolor="#F2F4FB">
<%-- 				<td>分類:${recipe.category}</td> --%>
				<td>分類:<form:input path="category"/></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材1:<form:input path="food1"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材2:<form:input path="food2"/></td>
				
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材3:<form:input path="food3"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>食材4:<form:input path="food4"/></td>
				
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料1:<form:input path="sauce1"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>調味料2:<form:input path="sauce2"/></td>
				
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>調味料3:<form:input path="sauce3"/></td>
				
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>
					照片:<img src="data:image/jpg;base64,${base64String}" width="100" height="100" >
<%-- 					<input hidden="true" name="base64String" value="${base64String}"/> --%>
					<form:input path="photo" hidden="true"/>
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