<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>資料刪除</title>
<link rel="stylesheet"
	href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<link rel="stylesheet"
	href="<c:url value='/css/admin/css/cs-skin-elastic.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/admin.css'/>">
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

header {
	background: #FFBB77;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}

.container {
	width: 90%;
	/* 外距:元素跟元素的間距 */
	margin: auto;
	/* 內距:元素內跟邊界的間距 */
	padding: 10px;
}

fieldset {
	/*因為是包住 div 所以要比 div 大*/
	width: 500px;
	margin: 15px;
	border: 3px solid #FF0080;
	border-radius: 20px;
}

legend {
	font-size: 24px;
	color: #66B3FF;
	/* text-align: center; */
	margin-left: 10px;
}

.st1 {
	width: 450px;
	border-bottom: 2px dashed #FF95CA;
	margin: 20px;
	padding-bottom: 20px;
}

table {
	margin: auto;
}

.sub {
	width: 450px;
	margin: auto;
	text-align: center;
}

.t1 {
	width: 100px;
	float: left;
	text-align: right;
}
</style>
</head>
<body>
	<!-- <div class="header">
		<h1>Recipe</h1>
	</div> -->
	<div id="right-panel" class="right-panel">
		<!-- Header-->
		<header id="header" class="header">
			<div class="top-left">
				<div class="navbar-header">
					<!--                     <a class="navbar-brand" href="./"> -->
					<!--                         <div class="logo-text" size="50%"> -->
					<!--                             <h1>店家查詢<strong></strong></h1> -->
					<!--                         </div> -->
					<!--                     </a> -->
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
	<header>
		<h1>確認以下將刪除之資料</h1>
	</header>
	<%-- 	<jsp:useBean id="recipe" class="com.web.model.AdminRecipeBean" scope="session"></jsp:useBean> --%>
	<form:form action="./AdminDeleteFoodAction" modelAttribute="recipe"
		method="POST">
		<table>
			<tr bgcolor="#FFFFE1">
				<td>店家名稱:${Food.mapname}</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家地址:${Food.mapku }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>店家電話:${Food.mapnb }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>店家座標位置:${Food.mapxy }</td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>是否為安全店家:${Food.mapcheck }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>分類:${Food.category }</td>
			</tr>
			<tr bgcolor="#F2F4FB">
				<td>照片:<img src="data:image/jpg;base64,${base64String}"
					width="100" height="100"></td>
			</tr>
			<div>
				<input type="text" name="id" value="${param.id}" hidden>
			</div>
		</table>
		<div class="sub">
			<input type="submit" name="submit" value="確認刪除">
		</div>
	</form:form>
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