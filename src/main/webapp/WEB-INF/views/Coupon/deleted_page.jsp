<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cf.cvb14795.Coupon.model.bean.QuestionnaireBean"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="google-signin-client_id"
		content="196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com"> -->
<style>
.box1 {
	border: 1px solid;
	height: 300px;
	padding: 20px;
}

td, th {
	border: 1px solid;
	width: 200px;
	text-align: center;
}

table {
	border: 1px;
	border-collapse: collapse;
}

 img.user-avatar {
	  width:40px;
	 }
</style>

<script>
	$(function() {
		adminAuth();
	})
	
</script>
<title>管理員頁面</title>

<!-- favicon -->
<link rel="shortcut icon" type="image/png" href="<c:url value='/image/user/favicon.png'/>">
<!-- google font -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
<!-- fontawesome -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" -->
<!-- 	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" -->
<!-- 	crossorigin="anonymous" /> -->
<link rel="stylesheet" href="<c:url value='/css/user/css/all.min.css'/>">
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
<!-- bootstrap -->
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
<!-- vegas -->
<link rel="stylesheet" href="<c:url value='/css/vegas.css'/>"/>

<!-- <link href="./test/pre.css" rel="stylesheet" /> -->
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<!-- 暗黑模式 -->
<script src="https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.min.js"></script>
<script src="<c:url value='/js/jquery-3.6.0.js"'/>"></script>
<script src="<c:url value='/js/bootstrap.js'/>"></script>
<script src="<c:url value='/js/lineLogin.js'/>"></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>
<%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
<!-- aboutUs -->
<!-- <script src="./js/aboutUs.js"></script> -->

<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->



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
	<div class="top-header-area " id="sticker">
  <div class="container">
   <!-- logo -->
   <div class="site-logo">
    <a href="<c:url value='/'/>">
     <img src="<c:url value='/image/user/logo.png'/>" alt="" width="65%" height="65%">
    </a>
   </div>
   <!-- end logo -->
  </div>
  <div>
   <div class="row">
    <div class="col-xl-12 col-lg-12 col-sm-12 text-center">
     <div class="main-menu-wrap">
      <!-- menu start -->
      <nav class="main-menu navbar navbar-expand-lg">
       <ul>
        <li><a href="<c:url value='/'/>">首頁</a></li>
        <li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
        <li class="current-list-item"><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
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
<!--           <li> -->
<!--            <a> -->
<!--             淺色模式 -->
<!--  <!--            <label for="checkbox-switch" style="">淺色模式</label> --> 
<!--             <input type="checkbox" id="checkbox-switch" class="checkbox-switch" /> -->
<!--            </a> -->
<!--           </li> -->
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
						<p>防疫生活 人人有責</p>
						<h1>防疫專區</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
<!-- 	<div class="memberDetail"> -->
<!-- 		<div class="text-right"> -->
<!-- 			會員： <span id="userNameContainer"></span> -->
<!-- 		</div> -->
<!-- 		<div class="text-right"> -->
<!-- 			身分： <span id="isAdminContainer"></span> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<!-- 防疫專區 -->
		<form action='admincontroller' method='post'>
		<div>	
			<h1 style="margin:30px 10px 15px 10px">管理者操作功能</h1>	
		</div>
		<div>
			<p  style="font-weight:bold;font-size:20px">
				<label><input type='radio' name='action' value='R' style="margin:30px 0px 15px 10px">查詢疫苗接種者</label>
				<label><input type='radio' name='action' value='U' style="margin:30px 0px 15px 10px">發放</label>
				<label><input type='radio' name='action' value='B' style="margin:30px 0px 15px 10px">撤回</label>
				<label><input type='radio' name='action' value='D' style="margin:30px 0px 15px 10px">刪除</label>
			</p>
		</div>
		<div style="margin:30px 10px 15px 10px">
			<h6>	( 註: 撤回與刪除:為方便測試時使用，並非正式功能。</h6>
			<h6>        刪除: 功能為刪除測試帳號 caterpillar123 ) </h6>
		</div>

		<div style="margin-bottom:30px;">
			<input type='submit' value='送出'> 
			<input type="button" style="width:88px;height:55px;border-radius:30px;border:3px solid #fd7e14;font-size:15px;background-color:#FF8000;font-weight:bold"
				value="首頁" onclick="location.href='<c:url value="/Coupon/frontpage"/>'">
			<input type="button" style="width:90px;height:55px;border-radius:30px;border:3px solid #fd7e14;font-size:15px;background-color:#FF8000;font-weight:bold"
				value="管理折價券" onclick="location.href='<c:url value="/Coupon/adminforCoupon"/>'">
		</div>

	</form>

	<br>
	<br>
	<div style="display:flex;margin:20px;">			
		<h4>刪除成功</h4>
	</div>
	
	<!-- end 防疫專區 -->

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
							<li><a href="" id="lineLoginHref"><input type="button"
									class="lineLogin"
									style="width: 151px; height: 44px; border: none" /></a></li>
							<li><img src="<c:url value='/image/lineFoodMap04QR.png'/>" alt=""></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6">
					<div class="footer-box pages">
						<h2 class="widget-title">導覽</h2>
						<ul>
							<li><a href="<c:url value='/'/>">首頁</a></li>
							<li><a href="<c:url value='/aboutUs'/>">關於我們</a></li>
							<li><a href="<c:url value='/Food/Fooddex'/>">商家資訊</a></li>
							<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
							<li><a href="<c:url value='/Event/'/>">活動總覽</a></li>
							<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
							<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
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
	
	<script>
		$(function() {
			userNameMain();

			const cookies = document.cookie;
			console.log("cookies:'" + cookies + "'");
			let userName = cookies.split("user=")[1];   //帳號
			$("#admin").on("click", function(e){
				if(userName != undefined){
					location.href="<c:url value='/Coupon/admin' />";
				} else {
					alert("請先登入!")
					location.href = "<c:url value='/Member/Login'/>";						
				}
			})
// 			 $("#header").vegas({
// 	             slides: [
// 	                 { src: "./image/question.jpeg" },
// 	                 { src: "./image/salad.jpg" },
// 	                 { src: "./image/food1.jpeg" },
// 	                 { src: "./image/food2.jpeg" },
// 	                 { src: "./image/food3.jpeg" },
// 	                 { src: "./image/coupon.jpg" }
// 	             ],
// 	             transition: ['blur', 'zoomOut', 'swirlLeft'],
// 	             delay: 5000
// 	        });
			 $(".wrap").vegas({
				 overlay: true,
				 transition: 'fade', 
				 transitionDuration: 2000,
				 delay: 6000,
				 color: 'red',
				 animation: 'random',
				 animationDuration: 8000,
				 slides: [
					 { src: "./image/question.jpeg" },
					 { src: "./image/salad.jpg" },
					 { src: "./image/food1.jpeg" },
					 { src: "./image/food2.jpeg" },
					 { src: "./image/food3.jpeg" },
					 { src: "./image/coupon.jpg" }
				 ],
			});
		})
	</script>
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
	<!-- vegas js -->
	<script src="<c:url value='/js/vegas.js'/>"></script>
	<%-- // 		var src = "<c:url value='/Member/user/${user}/photo'/>"; --%>
<!-- // 		userNameMain(src); -->
<!-- 	</script> -->
	
</body>
</html>