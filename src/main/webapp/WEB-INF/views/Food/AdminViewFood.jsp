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
<title>店家資料查詢</title>
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

th {
	text-overflow: ellipsis;
	white-space: nowrap;
}

td {
	border: 1px solid black;
	text-align: center;
}

thead {
	text-align: center;
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
                    <a class="navbar-brand hidden" href="./"><img src="images/logo2.png" alt="Logo"></a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
                </div>
            </div>
            <div class="top-right">
                <div class="header-menu">
                    <div class="header-left">
                        <button class="search-trigger"><i class="fa fa-search"></i></button>
                        <div class="form-inline">
                            <form class="search-form">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                                <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                            </form>
                        </div>

                        <!-- 小鈴鐺通知 -->
                        <div class="dropdown for-notification">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-bell"></i>
                                <span class="count bg-danger">87</span>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="notification">
                                <p class="red">You have 3 Notification</p>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-check"></i>
                                    <p>Server #1 overloaded.</p>
                                </a>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-info"></i>
                                    <p>Server #2 overloaded.</p>
                                </a>
                                <a class="dropdown-item media" href="#">
                                    <i class="fa fa-warning"></i>
                                    <p>Server #3 overloaded.</p>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle active" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="menu-icon fa fa-sign-in"></i><span class="memberDetail" id="userNameContainer"></span>
                            <img class="user-avatar rounded-circle" src="<c:url value='/Member/user/${user}/photo'/>" alt="User Avatar">
                        </a>

                        <div class="user-menu dropdown-menu">
                            <a class="nav-link" href="#"><i class="fa fa- user"></i>My Profile</a>
                            <a class="nav-link" href="#"><i class="fa fa-power -off"></i>Logout</a>
                        </div>
                    </div>

                </div>
            </div>
        </header>
	</div>
	<header>
		<h1>店家資料查詢</h1>
	</header>
	<form>
		<input type="button" onclick="history.back()" value="首頁">
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
							<td><img src="data:image/jpg;base64,${imgList.get(i)}"
								width="100" height="100"></td>
							<td><a id="edit"
								href="./AdminViewFood/id=${lists.get(i).id}">修改</a> <a
								id="delete" href="./AdminDeleteFood?id=${lists.get(i).id}">刪除</a></td>
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
	<script type="text/javascript">
		
	</script>
</body>
</html>