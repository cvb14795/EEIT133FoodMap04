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

th {
	text-overflow: ellipsis;
	white-space: nowrap;
}

td {
	border: 1px solid black;
}

thead {
	text-align: center;
}
</style>
</head>
<body>
	<header>
		<h1>會員食譜查詢</h1>
	</header>
	<form>
		<input type="button" onclick="history.back()" value="上一頁">
	</form>

	<table>
		<thead>
			<th>編號</th>
			<th width="5%">會員姓名</th>
			<th width="9%">品項</th>
			<th width="9%">分類</th>
			<th width="9%">食材1</th>
			<th width="9%">食材2</th>
			<th width="9%">食材3</th>
			<th width="9%">食材4</th>
			<th width="9%">調味料1</th>
			<th width="9%">調味料2</th>
			<th width="9%">調味料3</th>
			<th width="9%">照片</th>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${lists.size() != 0}">
					<c:forEach var="i" begin="0" end="${lists.size()-1 }">
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
</body>
</html>