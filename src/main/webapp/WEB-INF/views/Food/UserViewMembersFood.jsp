<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<style>
table {
	border: 1px solid black;
	border-collapse: collapse;
	margin: auto;
}

header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}

#myInput {
  background-image: url('/css/searchicon.png'); 
  background-position: 10px 12px; 
  background-repeat: no-repeat; /* Do not repeat the icon image */
  width: 50%; 
  font-size: 16px; 
  padding: 12px 20px 12px 40px; 
  border: 1px solid #ddd; 
  margin-bottom: 12px; 
}

#myTable {
  border-collapse: collapse; 
  width: 100%; 
  border: 5px solid #FFDCB9; 
  font-size: 16px; 
  margin:auto;
  font-family: monospace;
  font-weight: bolder;
            
}

#myTable th, #myTable td {
  text-align: left; 
  padding: 12px; 
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #FFB5B5;
}
</style>
</head>
<body>
	<header>
		<h1>會員店家查詢</h1>
	</header>
	<form>
		<input type="button" onclick="history.back()" value="上一頁">
	</form>

	<table id="myTable" class="display">
		<thead>
			<th>編號</th>
			<th width="9%">店家名稱</th>
			<th width="9%">店家地址</th>
			<th width="9%">店家電話</th>
			<th width="9%">店家座標位置</th>
			<th width="9%">是否為安全商家</th>
			<th width="9%">分類</th>
			<th width="9%">照片</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${lists.size() != 0}">
					<c:forEach var="i" begin="0" end="${lists.size()-1 }">
						<tr bgcolor="#FFFFE1">
							<td>${lists.get(i).id}</td>
							<td>${lists.get(i).mapname}</td>
							<td><c:out value="${lists.get(i).mapku}" /></td>
							<td><c:out value="${lists.get(i).mapnb}" /></td>
							<td><c:out value="${lists.get(i).mapxy}" /></td>
							<td><c:out value="${lists.get(i).mapcheck}" /></td>
							<td><c:out value="${lists.get(i).category}" /></td>
							<td><img src="data:image/jpg;base64,${imgList.get(i)}"
								width="100" height="100"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan=11 style="text-align: center;">無資料!</td>
					<td><a id="edit" href="./">回首頁</a></td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<script>
	
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>
</body>
</html>