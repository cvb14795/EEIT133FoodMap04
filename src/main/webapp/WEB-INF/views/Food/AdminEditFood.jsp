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
<title>店家資料修改</title>
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
	background: #536e75;
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
	margin: auto;
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
		<h1>修改店家資料</h1>
	</header>
	<form id="form" modelAttribute="updataFood" method="POST"
		enctype="multipart/form-data">
		<%--     <form id="form"> --%>
		<fieldset>
			<legend>店家資訊</legend>
			<div class="st1">
				<label for="" class="t1">店家名稱:</label>
				<%--                 <form:input id="" path="name"/> --%>
				<input id="name" name="mapname" value="${updataFood.mapname}" />
			</div>
			<div class="st1">
				<label for="" class="t1">店家地址:</label>
				<%--                 <form:input id="" path="category"/> --%>
				<input id="name" name="mapku" value="${updataFood.mapku}" />
			</div>
			<div class="st1">
				<label for="" class="t1">店家電話:</label>
				<%--                 <form:input id="" path="food1"/> --%>
				<input id="name" name="mapnb" value="${updataFood.mapnb}" />
			</div>
			<div class="st1">
				<label for="" class="t1">座標位置:</label>
				<%--                 <form:input id="" path="food2"/> --%>
				<input id="name" name="mapxy" value="${updataFood.mapxy}" />
			</div>
			<div class="st1">
				<label for="" class="t1">安全店家:</label>
				<%--                 <form:input id="" path="food3"/> --%>
				<input id="name" name="mapcheck" value="${updataFood.mapcheck}" />
			</div>
			<div class="st1">
				<label for="" class="t1">分類:</label>
				<%--                 <form:input id="" path="food4"/> --%>
				<input id="name" name="category" value="${updataFood.category}" />
			</div>
			<div class="st1">
				<label for="" class="t1">照片:</label> <img id="src"
					src="data:image/jpg;base64,${base64String}" width="100"
					height="100"> <input type="text" id="" name="filename"
					value="${base64String}" hidden>
			</div>
			<div>
				<%--              	<form:input  id="id" path="id" hidden="true"/> --%>
				<input type="number" id="id" name="id" value="${updataFood.id}"
					hidden>
			</div>
		</fieldset>
		<div class="sub">
			<input type="submit" id="send" name="submit" value="儲存">
		</div>
	</form>

	<script>
    	var form = document.getElementById("form");
    	$("#form").on("submit", function(e){
			/* =====for formData&MultipartFile =====*/
			var formData = new FormData(form);
			
			/* =====for JSON =====*/
			
			var html = "";
			var inputData = $(".st1 input").slice(0, 9);
			// html += + "name" + $("#name").val() + "<br/>";
			for (let i = 0; i < inputData.length; i++) {
				let name = inputData.eq(i).attr("name");
				let value = (inputData.eq(i).val() != "") ? inputData.eq(i).val() : "無";
				if(inputData.eq(i).attr("name") != "filename"){
				html += name+": "+value+"</br>";
				}
			}
			console.log(html);
			
			//改用ajax傳送 棄用原本的form傳送
			e.preventDefault();
			
			Swal.fire({
				title: '您確定要儲存嗎？',
				icon: 'question',
				html: html,
				imageUrl: $("#src").attr("src"),
				imageWidth: 400,
				imageHeight: 200,
				showCancelButton: true,
			}).then((result) => {
				if (result.isConfirmed) {
					let url = "<c:url value='/Food/admin/AdminViewFood/id=" + ${updataFood.id} +"'/>"
					console.log(url)
					$.ajax({
						type:"post",
						url:url,
						data: formData,
						contentType: false, //required
						processData: false, // required
						/*一定要加*/
						mimeType: 'multipart/form-data', //有圖片就要加這行
						/*一定要加*/
						success: function(data){
							var jsonData = JSON.parse(data);
							console.log("Success:" + "\nID:" +jsonData.id + "\nName:" +jsonData.name) ;

							var html1 = "";
							for (const key in jsonData) {
								let val = (jsonData[key] != "") ? jsonData[key] : "無";
								if (!(key == "base64String" || key == "filename")) {
									console.log(key);
									console.log(jsonData[key]);
									html1 += key+": "+val;
									html1 += "<br/>";
								} 
							};

							Swal.fire({
								title: '已儲存成功！',
								icon: 'success',
								html: html1,
								imageUrl: 'data:image;base64,'+jsonData.base64String,
								imageWidth: 400,
								imageHeight: 200,
							})
							setTimeout("window.location.pathname = 'FoodMap04/Food/admin'",1500);
						},
						error: function(e){
							console.log(e);
						}
					})
				} else if (result.dismiss === Swal.DismissReason.cancel) {
					Swal.fire({
						icon: 'error',
						title: '已取消送出請求',
						text: '您的變更將不會被儲存!'
					})
				}
			})
		})
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