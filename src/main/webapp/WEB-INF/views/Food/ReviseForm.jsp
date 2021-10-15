<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ReviseForm</title>
</head>

<body>
	<sql:query var="result" dataSource="jdbc/TeamProject">SELECT * from MapData;</sql:query>
	<form action="./Revise" method="get">
		<label for="name">店家名稱:</label><br>
		<!-- 		<input type="text" value="" name="name"><br> -->
		<select name="name">
			<c:forEach var="row" items="${result.rows}">
				<option value="${row.mapname}">${row.mapname}</option>
			</c:forEach>
		</select><br>
		<label for="map">店家地址:</label><br><input type="text" value="" name="map"><br>
		<label for="number">店家電話:</label><br><input type="text" value="" name="number"><br>
		<label for="XY">經緯度:</label><br><input type="text" value="" name="XY"><br>
		<label for="checkiso">是否為安全商家:</label><br>
		<label><input type="radio" value="YES" name="checkiso">是</label>
		<label><input type="radio" value="NO" name="checkiso">否</label><br>
		<label for="imgName">圖片名稱:</label><br>
		<input type="text" placeholder="修改圖片名稱 不想修改則留空" name="imgName"><br>

		<div>
			<input type="submit" value="送出"> <input type="reset" value="清除">
		</div>

	</form>

</body>

</html>