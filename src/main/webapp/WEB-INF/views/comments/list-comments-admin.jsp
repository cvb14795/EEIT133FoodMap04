<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>評論區-管理者頁面</title>
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/cs-skin-elastic.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/admin.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<style>
body {
	font-size: 20px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: white;
	line-height: 1.5em;
}

/* .header {
	background: #FFBB77;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
} */

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
	margin: auto;
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
	<!-- left panel -->
	<aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">
            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="<c:url value='/'/>"><i class="menu-icon fa fa-laptop"></i>首頁</a>
                    </li>
                    <li class="menu-title">功能總覽</li><!-- /.menu-title -->
                    
                    <li>
                        <a href="<c:url value='/Food/FoodMap'/>"> <i class="menu-icon ti-email"></i>商家資訊 </a>
                    </li>
                    <li>
                        <a href=<c:url value='/Coupon/frontpage'/>> <i class="menu-icon ti-email"></i>防疫專區 </a>
                    </li>
                    <li>
                        <a href="<c:url value='/Event'/>"> <i class="menu-icon ti-email"></i>活動報名 </a>
                    </li>
                    <li>
                        <a href="<c:url value='/comment/admin-list'/>"> <i class="menu-icon ti-email"></i>評論專區 </a>
                    </li>
                    <li>
                        <a href="<c:url value='/Recipe/admin'/>"> <i class="menu-icon ti-email"></i>營養資訊 </a>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>帳號功能</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-sign-in"></i><a href="<c:url value='/Member/Login'/>">登入</a></li>
                            <li><i class="menu-icon fas fa-sign-out-alt"></i><a href="<c:url value='/Member/Logout'/>">登出</a></li>
                            <li><i class="menu-icon fa fa-sign-in"></i><a href="<c:url value='/Member/Register'/>">管理者註冊</a></li>
                            <li><i class="menu-icon fa fa-paper-plane"></i><a href="<c:url value='/Member/ForgetPassword'/>">忘記密碼</a></li>
                            <!-- 另外做一頁 修改所有會員  -->
                            <li><i class="menu-icon fa fa-sign-in"></i><a href="<c:url value='/Member/Revise'/>">修改所有會員資料</a></li>
                        </ul>
                    </li>
<!--                     <li class="menu-item-has-children dropdown"> -->
<!--                         <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-th"></i>Forms</a> -->
<!--                         <ul class="sub-menu children dropdown-menu"> -->
<!--                             <li><i class="menu-icon fa fa-th"></i><a href="forms-basic.html">Basic Form</a></li> -->
<!--                             <li><i class="menu-icon fa fa-th"></i><a href="forms-advanced.html">Advanced Form</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                </ul>
            </div><!-- /.navbar-collapse -->
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
                            <h1>想食What<strong>!</strong></h1>
                        </div>
                    </a>
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
	
	<!-- list every comments here -->
	
	<div class="container">

	<h3>評論區-管理者頁面</h3>
	<hr><br>
		
	
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
				<th>編號</th>
				<th>姓名</th>
				<th>評分</th>
				<th>評論</th>
				<th>評論時間</th>
				<th>刪除</th>
			</tr>
		</thead>	
		<tbody>
			<c:forEach items="${comments}" var="tempComment">
				<tr>
					<td>${tempComment.id}</td>
					<td>${tempComment.userName}</td>
					<!-- <td th:text="${tempComment.score}" />  -->
					
					<td><img src="<c:url value='/image/star${tempComment.score}.png'/>" /></td>
					<td>${tempComment.userComment}</td>
					<td>${tempComment.userDate}</td>
					
					<td>
											
						<!-- Add "delete" button/link -->
						
						<a href="<c:url value='/comments/admin-delete/${tempComment.id}'/>"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('確定要刪除嗎？'))) return false">
							刪除					
						</a> 
						
						
						
						 
						 <!-- <button class="btn btn-danger btn-sm" name="delete" type="submit" id="deleteComment">刪除</button>
						   -->
					
						
						
						
					</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
		
	
	<!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="<c:url value='/js/admin/js/main.js'/>"></script>
    <script>
	 $("#searchForm").attr("action", "<c:url value='/comments/admin-search'/>")
	</script>
	
	
	
	
</body>
</html>