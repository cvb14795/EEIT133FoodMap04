<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<jsp:useBean id="recipe" class="recipe.model.RecipeBean" scope="session"></jsp:useBean>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

th{
    text-overflow:ellipsis;
    white-space:nowrap;
    }

td{
	border:1px solid black;
	
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
	<form action="./UserStartingPage.jsp">
		<input type="submit" value="首頁">
	</form>

	<table>
		<thead>
			<th>編號</th>
			<th width="10%">品項</th>
			<th>分類</th>
			<th>食材1</th>
			<th>食材2</th>
			<th>食材3</th>
			<th>食材4</th>
			<th width="10%">調味料1</th>
			<th width="10%">調味料2</th>
			<th width="10%">調味料3</th>
			<th>照片</th>
			<th>選項</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${sessionScope.lists.size() != 0}">
					<c:forEach var="i" begin="0" end="${sessionScope.lists.size()-1 }">
						<tr bgcolor="#FFFFE1">
							<td>${lists.get(i).id}</td>

							<td>${lists.get(i).userName}</td>
							
							<td>${lists.get(i).foodName}</td>

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

<%-- 							<td><a id="edit" href="./AdminShowEditRecipe?id=${lists.get(i).id}">修改</a> --%>
<%-- 								<a id="delete" href="./AdminShowDeleteRecipe?id=${lists.get(i).id}">刪除</a></td> --%>
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
</body>
</html>