<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>

<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
	<title>評論區</title>
</head>



<body>

	<div class="container">

	<h3>評論區</h3>
	<hr>
	<form:form action="" class="form-inline" id="searchForm" method="get">
	<a href="<c:url value='/comments/showFormForAdd'/>" class="btn btn-primary btn-sm mb-3">
		新增評論
	</a>
	
	<input class="form-control  ml-5 mr-sm-2 mb-3" type="search" name="commentName" placeholder="輸入關鍵字" />

		<button class="btn btn-success mb-3" type="submit">搜尋</button>
	
	<br>
	
	</form:form>		
	
	<!-- 
	排序方式： <select class="form-control" name="sortingType" id="sortingType" th:action="@{/comments/sorting}">
                <option th:value="''">請選擇排序方式</option>
                <option th:value="'high'">評分高 → 低</option>
    			<option th:value="'lowToHigh'">評分低 → 高</option>
    			<option th:value="'new'" >時間新 → 舊</option>
    			<option th:value="'oldToNew'" >時間舊 → 新</option>
            	</select>
     -->        	
            	
            	
    <br><br>
	
	<table class="table table-bordered table-striped">
		<thead class="thead-dark">
			<tr>
				<th>姓名</th>
				<th>評分</th>
				<th>評論</th>
				<th>評論時間</th>
				<th>編輯/刪除</th>
			</tr>
		</thead>	
		<tbody>
			<c:forEach items="${comments}" var="tempComment">
				<tr>
					<td>${tempComment.userName}</td>
					<!-- <td th:text="${tempComment.score}" />  -->
					
					<td><img src="<c:url value='/image/star${tempComment.score}.png'/>" /></td>
					<td>${tempComment.userComment}</td>
					<td>${tempComment.userDate}</td>
					
					<td>
						<!-- Add "update" button/link -->
						<a href="<c:url value='/comments/showFormForUpdate/${tempComment.id}'/>"
							class="btn btn-info btn-sm">
							更新					
						</a>
						
						<!-- Add "delete" button/link -->
						<a href="<c:url value='/comments/delete/${tempComment.id}'/>"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('確定要刪除嗎？'))) return false">
							刪除					
						</a>
						
					</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
	<script>
	 $("#searchForm").attr("action", "<c:url value='/comments/search'/>")
	</script>	

</body>

</html>








