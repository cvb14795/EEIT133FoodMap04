<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:useBean id="recipe" class="recipe.bean.RecipeBean" scope="session"></jsp:useBean>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>


<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>食譜查詢</title>
<style>
table {
	border: 1px solid black;
	margin:auto;
}

header {
	background: #FFBB77;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}

thead {
	text-align: center;
}

</style>
</head>

<body>
	<header>
		<h1>食譜查詢</h1>
	</header>
	<p>
		<a href="./RecipeForm.html">首頁</a>
	</p>

	<table>
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
			<th>選項</th>
		</thead>
		<tbody>
			<%-- 			<c:set var="maxIndex" value="0" scope="session"/> --%>
			<%-- 			<c:if test="${sessionScope.recipeList.size() > 0}"> --%>
			<%-- 			   <c:set var="maxIndex" value="${sessionScope.recipeList.size()-1}" scope="session"/> --%>
			<%-- 			</c:if> --%>

			<!-- 有資料時 (index != 0) -->
			<%-- 				<c:forEach var="i" begin="0" end="${maxIndex}"> --%>

			<%-- 				<c:if test="${maxIndex}>0"> --%>

			<%-- 				</c:if> --%>
			<c:choose>
				<c:when test="${sessionScope.recipeList.size() != 0}">
<%-- 					<c:out value="有${sessionScope.recipeList.size()}筆" /> --%>
					<c:forEach var="i"   begin="0" end="${sessionScope.recipeList.size()-1 }">
						<tr bgcolor="#FFFFE1">
							<td>${recipeList.get(i).id}</td>

							<td>${recipeList.get(i).name}</td>

							<td><c:out value="${recipeList.get(i).category}" /></td>

							<td><c:out value="${recipeList.get(i).food1}" /></td>

							<td><c:out value="${recipeList.get(i).food2}" /></td>

							<td><c:out value="${recipeList.get(i).food3}" /></td>

							<td><c:out value="${recipeList.get(i).food4}" /></td>

							<td><c:out value="${recipeList.get(i).sauce1}" /></td>

							<td><c:out value="${recipeList.get(i).sauce2}" /></td>

							<td><c:out value="${recipeList.get(i).sauce3}" /></td>

							<td><img src="data:image/jpg;base64,${imgList.get(i)}"
								width="100" height="100"></td>

							<!-- ./Edit.jsp?id=${recipe.id} -->
							<td><a id="edit" href="./Edit?id=${recipeList.get(i).id}">修改</a>
								<a id="delete" href="./Delete?id=${recipeList.get(i).id}">刪除</a></td>
							<!-- ./DeleteServlet?id=${recipe.id} -->
							<!-- 					<td></td> -->
						</tr>
					</c:forEach>

				</c:when>
				
				<c:otherwise>
<%-- 					<c:out value="什麼都沒有" /> --%>
<%-- 					<c:out value="有${sessionScope.recipeList.size()}筆!!" /> --%>
					<td colspan=11 style="text-align: center;">無資料!</td>
					<td><a id="edit" href="./RecipeForm.html">回首頁</a></td>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>

	<!-- <script>
		var edit = document.getElementById("edit");
		edit.addEventListener("click", function (e) {
			
		})

	</script> -->
</body>

</html>