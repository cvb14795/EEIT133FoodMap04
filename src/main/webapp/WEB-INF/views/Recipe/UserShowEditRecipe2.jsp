<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- title -->
	<title>食譜規劃</title>
	<!-- favicon -->
	<link rel="shortcut icon" type="image/png" href="<c:url value='/image/user/favicon.png'/>">
	<!-- google font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
	<!-- fontawesome -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/all.min.css'/>">
	<!-- bootstrap -->
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
	<!-- owl carousel -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/owl.carousel.css'/>">
	<!-- magnific popup -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/magnific-popup.css'/>">
	<!-- animate css -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/animate.css'/>">
	<!-- mean menu css -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/meanmenu.min.css'/>">
	<!-- main style -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/main.css'/>">
	<!-- responsive -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/responsive.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
	<!-- sweetalert -->
	<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
	<!-- 開關改成IOS風格(左右滑動按鈕) -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
	<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<style>
/* body { */
/* 	font-size: 20px; */
/* 	font-family: Arial, Helvetica, sans-serif; */
/* 	background-color: white; */
/* 	line-height: 1.5em; */
/* } */

/* .insertForm { */
/* 	width: 90%; */
/* 	margin: auto; */
/* 	padding: 10px; */
/* } */

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

img.user-avatar {
	width:40px;
}
</style>
</head>
<body>
	<!--PreLoader-->
	<div class="loader">
		<div class="loader-inner">
			<div class="circle"></div>
		</div>
	</div>
	<!--PreLoader Ends-->
	
	<!-- header -->
	<div class="top-header-area" id="sticker">
<!-- 		<div class="container"> -->
		<div>
			<div class="row">
				<div class="col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- logo -->
						<div class="site-logo">
							<a href="<c:url value='/'/>">
								<img src="<c:url value='/image/user/logo.png'/>" alt="">
							</a>
						</div>
						<!-- logo -->

						<!-- menu start -->
						<nav class="main-menu navbar navbar-expand-lg">
							<ul>
								<li class="current-list-item"><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
								<li><a href="<c:url value='/Event/'/>">活動總覽</a>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
								<li><a href="<c:url value='/Shop/'/>">美食商城</a>
								<li>
									<div class="header-icons">
										<!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
										<li><a><img class="user-avatar rounded-circle" src=""/> 會員：<span id="user"></span></a>
											<ul class="sub-menu">
												<li id="editNavBtn"><a
													href="<c:url value='/Member/Revise'/>"><i
														class="fas fa-edit"></i> 修改個人資料</a></li>
												<li id="registerNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Register'/>"><i class="fas fa-user-plus"></i>
														註冊會員</a></li>
												<li id="loginNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Login'/>"><i class="fas fa-sign-in-alt"></i>
														登入</a></li>
												<li id="logoutNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Logout'/>"><i class="fas fa-sign-out-alt"></i>
														登出</a></li>
											</ul>
										</li>
										
										<li>
											<div class="header-icons">
											  <a class="shopping-cart" href="<c:url value="/Recipe/user/showMyfavorites"/>">
											  	<i class="fas fa-heart"></i>
											  </a>
											  <a class="shopping-cart" href="<c:url value='/Shop/Cart'/>">
											  	<i class="fas fa-shopping-cart"></i>
											  </a>
											</div>
										</li>
<!-- 										<li> -->
<!-- 											<a> -->
<!-- 												淺色模式 -->
<!-- 	<!-- 											<label for="checkbox-switch" style="">淺色模式</label> --> 
<!-- 												<input type="checkbox" id="checkbox-switch" class="checkbox-switch" /> -->
<!-- 											</a> -->
<!-- 										</li> -->
									</div>
								</li>
							</ul>
						</nav>
						<a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
						<div class="mobile-menu"></div>
						<!-- menu end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end header -->

	<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>新增專屬食譜</p>
						<h1>食譜規劃</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
				<div class="col-md-12">
					<div class="product-filters">
						<ul>
<!-- 							<li class="active" data-filter="*">官方食譜</li> -->
							<li><a href="<c:url value="/Recipe/user"/>">官方食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/UserViewMembersRecipe2"/>">所有會員食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/UserInsertRecipe2"/>">新增專屬食譜</a></li>
							<li class="active"><a href="<c:url value="/Recipe/user/ViewYourRecipe2"/>">查詢您的食譜</a></li>
							<li data-filter=".lemon">我的最愛</li>
						</ul>
					</div>
				</div>
			</div>
	<div class="insertForm">
		<form id="form">
<%--     <form id="form"> --%>
        <fieldset>
            <legend>官方食譜</legend>
            <input type="hidden" name="userName" value="${updateRecipe.userName}">
            <div class="st1">
                <label for="" class="t1">品項:</label>
                <input id="name" name="foodName" value="${updateRecipe.foodName}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">分類:</label>
                <input id="name" name="category" value="${updateRecipe.category}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材1:</label>
                <input id="name" name="food1" value="${updateRecipe.food1}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材2:</label>
                <input id="name" name="food2" value="${updateRecipe.food2}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材3:</label>
                <input id="name" name="food3" value="${updateRecipe.food3}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材4:</label>
                <input id="name" name="food4" value="${updateRecipe.food4}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料1:</label>
                <input id="name" name="sauce1" value="${updateRecipe.sauce1}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料2:</label>
                <input id="name" name="sauce2" value="${updateRecipe.sauce2}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料3:</label>
                <input id="name" name="sauce3" value="${updateRecipe.sauce3}"/>
            </div>
            <div class="st1">
					<label for="" class="t1">步驟:</label>
					<textarea name="step" rows="5" cols="33">${updateRecipe.step}</textarea>
			</div>
            <div class="st1">
                <label for="" class="t1">照片:</label>
                
                <img id="src" src="data:image/jpg;base64,${base64String}" width="100" height="100" >
                <input type="text" id="" name="photo" value="${base64String}" hidden>
            </div>
            <div>
                <input type="number" id="id" name="id" value="${updateRecipe.id}" hidden>
            </div>
        </fieldset>
        <div class="sub">
            <input type="submit" id="send" name="submit" value="儲存">
        </div>
    </form>
	</div>
	
	<!-- footer -->
	<div class="footer-area">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-12">
					<div class="footer-box about-widget">
						<h2 class="widget-title">網站特色</h2>
						<p>
							1.此平台除了能快速找出使用者想要的美食資訊<br />也會提供google map地圖來讓使用者了解與美食的距離。
						</p>
						<p>
							2.人們在購買東西時，都希望會有折扣，因此平台也會有特約餐廳的優惠券<br />以及針對已接種疫苗之民眾的專屬優惠券。
						</p>
						<p>3.打造適合自己的專屬食譜，實現健康生活。</p>
						<p>4.平台定期舉辦一些會員活動，活絡會員間的感情。</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="footer-box">
						<h2 class="widget-title">Line 智慧客服</h2>
						<ul>
							<li>
								<a href="" id="lineLoginHref">
									<input type="button"class="lineLogin" style="width: 151px; height: 44px; border: none" />
								</a>
			  				</li>
							<li><img src="<c:url value='/image/lineFoodMap04QR.png'/>"alt=""></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6">
					<div class="footer-box pages">
						<h2 class="widget-title">導覽</h2>
						<ul>
							<li><a href="<c:url value='/'/>">首頁</a></li>
							<li><a href="<c:url value='/aboutUs'/>">關於我們</a></li>
							<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
							<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
							<li><a href="<c:url value='/Event/'/>">活動總覽</a></li>
							<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
							<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
							<li><a href="<c:url value='/Shop'/>">美食商城</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end footer -->
	
	<!-- copyright -->
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-12">
					<p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>,  All Rights Reserved.</p>
				</div>
				<div class="col-lg-6 text-right col-md-12">
					<div class="social-icons">
						<ul>
							<li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-linkedin"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end copyright -->

	<!-- jquery -->
	<script src="<c:url value='/js/user/js/jquery-1.11.3.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
	<!-- bootstrap -->
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
	<!-- count down -->
	<script src="<c:url value='/js/user/js/jquery.countdown.js'/>"></script>
	<!-- isotope -->
	<script src="<c:url value='/js/user/js/jquery.isotope-3.0.6.min.js'/>"></script>
	<!-- waypoints -->
	<script src="<c:url value='/js/user/js/waypoints.js'/>"></script>
	<!-- owl carousel -->
	<script src="<c:url value='/js/user/js/owl.carousel.min.js'/>"></script>
	<!-- magnific popup -->
	<script src="<c:url value='/js/user/js/jquery.magnific-popup.min.js'/>"></script>
	<!-- mean menu -->
	<script src="<c:url value='/js/user/js/jquery.meanmenu.min.js'/>"></script>
	<!-- sticker js -->
	<script src="<c:url value='/js/user/js/sticker.js'/>"></script>
	<!-- main js -->
	<script src="<c:url value='/js/user/js/main.js'/>"></script>
	<!-- userNameMain js -->
	<script src="<c:url value='/js/userNameMain.js'/>"></script>
	<!-- sweetalert js -->
	<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
	
	<script type="text/javascript">
		$(function(){
			var src = "<c:url value='/Member/user/${user}/photo'/>";
			userNameMain(src);
		})
		
		var form = document.getElementById("form");
		
    	$("#form").on("submit", function(e){
			/* =====for formData&MultipartFile =====*/
			var formData = new FormData(form);
			
			/* =====for JSON =====*/
			
			var html = "";
			var inputData = $(".st1 input").slice(0, 10);
			var recipeStep = $(".st1 textarea").val();
			// html += + "name" + $("#name").val() + "<br/>";
			for (let i = 0; i < inputData.length; i++) {				
				let name = inputData.eq(i).attr("name");
				let value = (inputData.eq(i).val() != "") ? inputData.eq(i).val() : "無";
				if (name  != "photo"){
					html += name+": "+value+"</br>";					
				}
			}
			//步驟:
			let stepList = recipeStep.split("\n");
			html += "步驟:</br>"
			//照每個步驟換行
			stepList.forEach(element => {
				html += element+"</br>"
			});
			console.log(html)
			
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
					let url = "<c:url value='/Recipe/user/ViewYourRecipe2/" + ${updateRecipe.id} +"'/>"
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
								if (!(key == "base64String" || key == "photo")) {
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
							setTimeout("window.location.pathname = 'FoodMap04/Recipe/user/ViewYourRecipe2'",1500);
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
	
</body>
</html>