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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
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
		<h1>管理會員食譜</h1>
	</header>
	<form>
		<input type ="button" onclick="history.back()" value="首頁">
	</form>

	<table id="myTable" class="display">
		<thead>
			<th>編號</th>
			<th>會員姓名</th>
			<th width="5%">品項</th>
			<th width="5%">分類</th>
			<th width="5%">食材1</th>
			<th width="5%">食材2</th>
			<th width="5%">食材3</th>
			<th width="5%">食材4</th>
			<th width="10%">調味料1</th>
			<th width="10%">調味料2</th>
			<th width="10%">調味料3</th>
			<th>照片</th>
			<th>步驟</th>
			<th width="5%">選項</th>
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
							<td><img src="data:image/jpg;base64,${imgList.get(i)}" width="100" height="100"></td>
							<td><c:out value="${lists.get(i).step}" /></td>
							<td>
								<a href="javascript:void(0)" onclick="doDelete('${lists.get(i).id}', '${lists.get(i).userName}', '${lists.get(i).foodName}')"><i class="fas fa-skull"></i></a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan=11 style="text-align: center;">無資料!</td>
					<td><a id="edit" href="<a href="<c:url value='/Recipe/admin/AdminStartingPage'/>">">新增食譜</a></td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable({
				 rowReorder: true,
			        columnDefs: [
			            { orderable: true, className: 'reorder', targets: 0 },
			            { orderable: false, targets: '_all' }
			        ]
			});
		});
		
			function doDelete(id, userName, foodName){
				let isAccept = confirm("您確認要從食譜中移除會員："+ userName +"的食譜："+foodName+" 嗎？")
				if (isAccept) {
					let url = "<c:url value='/Recipe/admin/AdminDeleteMembersRecipe/'/>"+id;
					console.log(url);
					$.ajax({
						url: url,
						method: "delete",
						success: function () {
							Swal.fire({
								position : 'center',
								icon : 'success',
								title : '刪除成功'
							})
							location.reload();
						}
					})
				}
			}
		
	</script>
</body>
</html>