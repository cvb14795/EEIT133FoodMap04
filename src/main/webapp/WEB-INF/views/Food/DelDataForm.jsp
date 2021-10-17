<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>DelDataForm</title>
</head>

<body>
	<sql:query var="result" dataSource="jdbc/TeamProject">SELECT * from MapData;</sql:query>

	<form action="./DelData" method="get">
		<label for="name">店家名稱:</label><br>
		<!-- 		<input type="text" value="" name="name"><br> -->
		<select name="name">
			<c:forEach var="row" items="${result.rows}">
				<option value="${row.mapname}">${row.mapname}</option>
			</c:forEach>
		</select>

		<div>
			<input type="submit" value="送出"> <input type="reset"
				value="清除">
		</div>

	</form>
</body>

</html>