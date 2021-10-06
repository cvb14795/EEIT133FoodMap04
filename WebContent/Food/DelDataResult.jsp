<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="mapData" class="Food.model.MapData" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DelDataResult</title>
</head>
<body BGCOLOR="#FDF5E6">
	<H1 ALIGN=CENTER>刪除結果</H1>
	<div style="margin: auto; width: 80%">
		<table border="1" width="100%">
			<thead>
				<th>店家名稱</th>
				<th>店家地址</th>
				<th>店家電話</th>
				<th>經緯度</th>
				<th>是否為安全店家</th>
			</thead>
			<tr>
				<td><jsp:getProperty name="mapData" property="mapname" /></td>
				<td><jsp:getProperty name="mapData" property="mapku" /></td>
				<td><jsp:getProperty name="mapData" property="mapnb"/></td>
				<td><jsp:getProperty name="mapData" property="mapxy"/></td>
				<td><jsp:getProperty name="mapData" property="mapcheck"/></td>
			</tr>
			
			
		</table>
	</div>


	<input id="backBtn" type="button" name="back" value="回首頁">

	<script>
		var backBtn = document.getElementById("backBtn");
		backBtn.addEventListener("click", function(e) {
			location.href = "./FoodMap";
		})
	</script>
</body>
</html>