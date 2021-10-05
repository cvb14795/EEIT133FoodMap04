<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<<<<<<< HEAD
<jsp:useBean id="recipe" class="model.UserRecipeBean" scope="session"></jsp:useBean>
=======
<jsp:useBean id="recipe" class="recipe.model.UserRecipeBean" scope="session"></jsp:useBean>
>>>>>>> 1aa4bb469a768fe0d0a7474056f00cb62caa2ea0
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
	<script src="/WebContent/js/jquery-3.6.0.js"></script>
	<header>
		<h1>查詢官方食譜</h1>
	</header>
	<form action="./UserViewRecipe.jsp">
		<input type="submit" value="上一頁">
	</form>
	<div class="wrapper">
		<i class="fas fa-search"></i>
		<input type="text" id="myInput" onkeyup="myFunction()"
			placeholder="Search for category.."></input>
	</div>
	<table id="myTable">
		<thead>
			<th>編號</th>
			<th>品項</th>
			<th>分類</th>
			<th>食材1</th>
			<th>食材2</th>
			<th>食材3</th>
			<th>食材4</th>
			<th>調味料1</th>
			<th>調味料2</th>
			<th>調味料3</th>
			<th>照片</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${sessionScope.lists.size() != 0}">
					<c:forEach var="i" begin="0"
						end="${sessionScope.lists.size()-1  }">

						<tr
							<c:if test="${sessionScope.lists.size() % 2 == 0 }"></c:if>
							bgcolor="#FFFFE1">
							<td>${lists.get(i).id}</td>

							<td>${lists.get(i).name}</td>

							<td><c:out value="${lists.get(i).category}" /></td>

							<td><c:out value="${lists.get(i).food1}" /></td>

							<td><c:out value="${lists.get(i).food2}" /></td>

							<td><c:out value="${lists.get(i).food3}" /></td>

							<td><c:out value="${lists.get(i).food4}" /></td>

							<td><c:out value="${lists.get(i).sauce1}" /></td>

							<td><c:out value="${lists.get(i).sauce2}" /></td>

							<td><c:out value="${lists.get(i).sauce3}" /></td>

							<td><img src="data:image/jpg;base64,${imgList.get(i)}"
								width="100" height="100"></td>
						</tr>
					</c:forEach>

				</c:when>

				<c:otherwise>
					<td colspan=11 style="text-align: center;">無資料!</td>
					<td><a id="edit" href="./UserStartingPage.jsp">回首頁</a></td>
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