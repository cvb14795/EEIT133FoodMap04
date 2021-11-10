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
<style>
header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}

.wrapper {
	position: relative;
}

i.fas {
	position: absolute;
	top: 5px;
	left: 5px;
}

.wrapper input {
	text-indent: 20px;
}
</style>
</head>
<body>
	<header>
		<h1>查詢店家資料</h1>
	</header>
	<form>
		<input type ="button" onclick="history.back()" value="上一頁">
	</form>
	<div class="wrapper">
		<i class="fas fa-search"></i>
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Search for category.."></input>
	</div>
	<table id="myTable">
		<thead>
			<th>編號</th>
			<th>店家名稱</th>
			<th>店家地址</th>
			<th>店家電話</th>
			<th>店家座標位置</th>
			<th>是否為安全店家</th>
			<th>分類</th>
			<th>照片</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${lists.size() != 0}">
					<c:forEach var="i" begin="0" end="${lists.size()-1  }">
						<tr bgcolor="#FFFFE1">
							<td>${lists.get(i).id}</td>
							<td>${lists.get(i).mapname}</td>
							<td><c:out value="${lists.get(i).mapku}" /></td>
							<td><c:out value="${lists.get(i).mapnb}" /></td>
							<td><c:out value="${lists.get(i).mapxy}" /></td>
							<td><c:out value="${lists.get(i).mapcheck}" /></td>
							<td><c:out value="${lists.get(i).category}" /></td>
							<td><img src="data:image/jpg;base64,${imgList.get(i)}" width="100" height="100"></td>
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
		function myFunction() {
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[2];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>

</body>
</html>