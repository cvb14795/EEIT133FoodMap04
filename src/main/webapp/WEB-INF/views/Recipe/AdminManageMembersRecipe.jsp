<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>管理會員食譜</title>
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<link rel="stylesheet"
	href="<c:url value='/css/admin/css/cs-skin-elastic.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/recipe.css'/>">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
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
	<div id="right-panel" class="right-panel">
		<!-- Header-->
		<header id="header" class="header">
			<div class="top-left">
				<div class="navbar-header">
					<a class="navbar-brand" href="./">
						<div class="logo-text">
							會員食譜
						</div>
					</a>
					<a class="navbar-brand hidden" href="./"><img
						src="images/logo2.png" alt="Logo"></a> <a id="menuToggle"
						class="menutoggle"><i class="fa fa-bars"></i></a>
				</div>
			</div>
			<div class="top-right">
				<div class="header-menu">
					<div class="header-left">
						<button class="search-trigger">
							<i class="fa fa-search"></i>
						</button>
						<div class="form-inline">
							<form class="search-form">
								<input class="form-control mr-sm-2" type="text"
									placeholder="Search ..." aria-label="Search">
								<button class="search-close" type="submit">
									<i class="fa fa-close"></i>
								</button>
							</form>
						</div>

						<!-- 小鈴鐺通知 -->
						<div class="dropdown for-notification">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="notification" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
								<i class="fa fa-bell"></i> <span class="count bg-danger">87</span>
							</button>
							<div class="dropdown-menu" aria-labelledby="notification">
								<p class="red">You have 3 Notification</p>
								<a class="dropdown-item media" href="#"> <i
									class="fa fa-check"></i>
									<p>Server #1 overloaded.</p>
								</a> <a class="dropdown-item media" href="#"> <i
									class="fa fa-info"></i>
									<p>Server #2 overloaded.</p>
								</a> <a class="dropdown-item media" href="#"> <i
									class="fa fa-warning"></i>
									<p>Server #3 overloaded.</p>
								</a>
							</div>
						</div>
					</div>

					<div class="user-area dropdown float-right">
						<a href="#" class="dropdown-toggle active" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-sign-in"></i><span class="memberDetail"
							id="userNameContainer"></span> <img
							class="user-avatar rounded-circle"
							src="<c:url value='/Member/user/${user}/photo'/>"
							alt="User Avatar">
						</a>

						<div class="user-menu dropdown-menu">
							<a class="nav-link" href="#"><i class="fa fa- user"></i>My
								Profile</a> <a class="nav-link" href="#"><i
								class="fa fa-power -off"></i>Logout</a>
						</div>
					</div>

				</div>
			</div>
		</header>
	</div>
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
			<th width="5%">調味料1</th>
			<th width="5%">調味料2</th>
			<th width="5%">調味料3</th>
			<th width="10%">照片</th>
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
	<!-- Scripts -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
	<script src="<c:url value='/js/admin/js/main.js'/>"></script>
</body>
</html>