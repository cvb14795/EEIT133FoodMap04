<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<style>
table {
	border: 1px solid black;
	border-collapse: collapse;
	margin: auto;
}

header {
	background: #FFBB77;
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
	text-align: center;
	
}
thead {
	text-align: center;
}
</style>
</head>
<body>
	<header>
		<h1>店家資料查詢</h1>
	</header>
	<form>
		<input type ="button" onclick="history.back()" value="首頁">
	</form>

	<table>
		<thead>
			<th>編號</th>
			<th>店名</th>
			<th>店家地址</th>
			<th>店家電話</th>
			<th>店家座標位置</th>
			<th>是否為安全商家</th>
			<th>分類</th>
			<th>照片</th>
			<th>選項</th>
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
							<td><img src="data:image/jpg;base64,${imgList.get(i)}" width="100" height="100"></td>
							<td><a id="edit" href="./AdminViewFood/id=${lists.get(i).id}">修改</a>
								<a id="delete" href="./AdminDeleteFood?id=${lists.get(i).id}">刪除</a></td>
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
	<script type="text/javascript">
		


	</script>
</body>
</html>