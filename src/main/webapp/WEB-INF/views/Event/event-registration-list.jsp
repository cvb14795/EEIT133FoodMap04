<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>here</title>
<link rel="stylesheet"
	href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<link rel="stylesheet"
	href="<c:url value='/css/admin/css/cs-skin-elastic.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/admin.css'/>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<style>
body {
	font-size: 20px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: white;
	line-height: 1.5em;
}

.main {
	margin-left: 280px;
	padding: 20px;
}

.overlay-show {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 999;
	background-color: rgba(0, 0, 0, 0.6);
	display: flex;
	justify-content: center;
	align-items: center;
}

.overlay-hide {
	display: none;
}
</style>
</head>
<body>
	<!-- left panel -->
	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">
			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value='/Home'/>"><i
							class="menu-icon fa fa-laptop"></i>會員系統 </a></li>
					<li class="menu-title">Icons</li>
					<!-- /.menu-title -->

					<li><a href="<c:url value='/Food/FoodMap'/>"> <i
							class="menu-icon ti-email"></i>商家資訊
					</a></li>
					<li><a href="<c:url value='/Coupon/adminforCoupon'/>"> <i class="menu-icon ti-email"></i>折價券管理 </a></li>
					<li><a href="<c:url value='/Event/registration-list'/>"> <i
							class="menu-icon ti-email"></i>活動報名
					</a></li>
					<li><a href="<c:url value='/Comment/CommentControllerServlet'/>"> <i
							class="menu-icon ti-email"></i>評論專區
					</a></li>
					<li><a href="<c:url value='/Recipe/admin'/>"> <i
							class="menu-icon ti-email"></i>營養資訊
					</a></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-glass"></i>帳號功能
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="menu-icon fa fa-sign-in"></i><a
								href="./Member/Login">登入</a></li>
							<li><i class="menu-icon fas fa-sign-out-alt"></i><a
								href="./Member/Logout">登出</a></li>
							<li><i class="menu-icon fa fa-sign-in"></i><a
								href="./Member/Register">管理者註冊</a></li>
							<li><i class="menu-icon fa fa-paper-plane"></i><a
								href="./Member/ForgetPassword">忘記密碼</a></li>
							<!-- 另外做一頁 修改所有會員  -->
							<li><i class="menu-icon fa fa-sign-in"></i><a
								href="./Member/Revise">修改所有會員資料</a></li>
						</ul></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-th"></i>Forms
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="menu-icon fa fa-th"></i><a
								href="forms-basic.html">Basic Form</a></li>
							<li><i class="menu-icon fa fa-th"></i><a
								href="forms-advanced.html">Advanced Form</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- <div class="header">
    <h1>Recipe</h1>
</div> -->
	<div id="right-panel" class="right-panel">
		<!-- Header-->
		<header id="header" class="header">
			<div class="top-left">
				<div class="navbar-header">
					<a class="navbar-brand" href="./">
						<div class="logo-text">
							<h1>
								想食What<strong>!</strong>
							</h1>
						</div>
					</a> <a class="navbar-brand hidden" href="./"><img
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
	<div class="main">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">姓名</th>
					<th scope="col">人數</th>
					<th scope="col">時間</th>
					<th scope="col">說明</th>
					<th scope="col">功能</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}">
					<tr id="${item.id}">
						<td>${item.eventname}</td>
						<td>${item.eventpeople}</td>
						<td>${item.eventtime}</td>
						<td>${item.eventcontent}</td>
						<td>
							<button type="button" class="btn btn-danger delete"
								onclick="del(this)">刪除</button>
						</td>
					</tr>
				</c:forEach>
				<tr></tr>
			</tbody>
		</table>
	</div>

	<div id="loading" class="overlay-hide">
		<div class="d-flex justify-content-center">
			<div class="spinner-border" role="status">
				<%--            <span class="visually-hidden">Loading...</span>--%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function del(obj) {
			let id = $(obj).parents("tr").attr('id');
			$('#loading').addClass('overlay-show').removeClass('overlay-hide')
			$.ajax({
				method : "DELETE",
				datatype : "text", // 回傳型態
				url : `/FoodMap04/Event/registration/` + id,
				success : function() {
					$(obj).parents("tr").remove();
				},
				error : function() {
					console.log('刪除失敗')
				},
				complete : function() {
					$('#loading').removeClass('overlay-show').addClass(
							'overlay-hide')
				},
			})
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