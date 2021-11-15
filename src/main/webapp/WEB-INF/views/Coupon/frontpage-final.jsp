<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="google-signin-client_id"
		content="196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com"> -->
<style>
	.rs {
		font-size:30px;
		text-align:center;
	}
	
	.rst{
		font-size:20px;
		text-align:center;
	}
	.mhw {
		text-align:center;
		width:150px;height:50px;
		background-color:#fd7e14;
		border:3px solid #fd7e14;
		float:right;
		margin: 8px; 
		padding: 5px;
		font-weight:bold;
		border-radius: 10px;
	}
	.mhw:hover{
		background-color:#f17612;
		border:3px solid #f17612;
	}
	.mhw a  {
		font-size: 20px;
	}
	.mhw a:hover {
		text-decoration: none;
	}
	.mhw:active{
		background-color:#e95714;
		border:3px solid #e95714;
	}
	.main {
		padding-left: 150px;
		padding-right: 150px;
	}
	 img.user-avatar {
	  width:40px;
	 }
</style>
<title>防疫專區</title>

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
<%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
<!-- aboutUs -->
<!-- <script src="./js/aboutUs.js"></script> -->

<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->

<script>
	/* 待完工 */
	//接收登入狀態並alert
	/* 待完工 */

	// function signOut() {
	//     var auth2 = gapi.auth2.getAuthInstance();
	//     auth2.signOut().then(function () {
	//         console.log('User signed out.');
	//     });
	// }
	$('document').ready(function() {
		$('#tabs a').click(function(e) { //當按下 id=tabs 中的超連結時
			e.preventDefault(); //阻止事件向上提升 (處理一次)    
			var url = $(this).attr("data-url"); //取得 data-url 屬性值
			// var href = this.hash; //取得 href 值
			// var pane = $(this); //轉成 DOM 物件
			// 跳轉至外部頁面
			location.href = url;
		});
	});
	
</script>
<script type="text/javascript">

	//國內檢驗總計
	var result = {
	    //檢驗人數
	    peopleTotal:"",
	    //檢驗件數
	    caseTotal: "",
	    //資料更新時間 (yyyy/mm/d)
	    updateTime: "",
	    
	    //總通報(總送驗 )
	    comunicatedTotal: "",
	    //總排除
	    excludedTotal: "",
	    //總確診
	    confirmedTotal: "",
	    //死亡
	    dead: "",
	    //日排除
	    excluded: "",
	    //日通報(送驗)
	    comunicated: "",
	    //日確診
	    confirmed: ""
	    
	};
	
	$.ajax({
	    url: "https://covid19dashboard.cdc.gov.tw/dash3",
	    type: 'GET',
	    success: function (data) {
	        console.log(data)
	        result.comunicatedTotal = data[0]["送驗"];
	        result.excludedTotal = data[0]["排除"];
	        result.confirmedTotal = data[0]["確診"];
	        result.dead = data[0]["死亡"];        
	        result.comunicated = data[0]["昨日送驗"];
	        result.excluded = data[0]["昨日排除"];
	        result.confirmed = data[0]["昨日確診"];
	             
	    }
	    
	});
	$.ajax({
	    url: "https://covid19dashboard.cdc.gov.tw/dash7",
	    type: 'GET',
	    success: function (data) {
	        console.log(data)
	        result.peopleTotal = data[0]["檢驗人數"];
	        result.caseTotal = data[0]["檢驗件數"];
	        result.updateTime = data[0]["資料更新時間"];
	    }
	    
	});
	
	
	window.onload = function(){
		// 國內檢驗總計
		document.getElementById('result11').innerHTML = result.peopleTotal;
		document.getElementById('result12').innerHTML = result.caseTotal;
		document.getElementById('result13').innerHTML = result.updateTime;
		
		//疫情監測(總數)
		document.getElementById('result21').innerHTML = result.comunicatedTotal;
		document.getElementById('result22').innerHTML = result.excludedTotal;
		document.getElementById('result23').innerHTML = result.confirmedTotal;
		document.getElementById('result24').innerHTML = result.dead;
		
		//疫情監測(每日)
		document.getElementById('result31').innerHTML = result.comunicated;
		document.getElementById('result32').innerHTML = result.excluded;
		document.getElementById('result33').innerHTML = result.confirmed;
		
	}
	
</script>

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
        <li  class="current-list-item"><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
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
	
	<!-- 防疫專區 -->
		
	<div class="main mt-150 mb-150">
		<div>
			<div style="display:flex;margin:10px;font-weight:bold;">
				<div style="margin:20px 40px 10px 20px;"><div class="rst" style="font-size:30px;text-align:center">國內疫情總計數據</div></div>
				<div style="margin:10px 50px 10px 50px;"><div class="rst">總通報人數:<div id="result21" class="rs">無資料</div></div></div>
				<div style="margin:10px 50px 10px 50px;"><div class="rst">總排除人數:<div id="result22" class="rs">無資料</div></div></div>
				<div style="margin:10px 50px 10px 50px;"><div class="rst">總確診人數:<div id="result23" class="rs">無資料</div></div></div>			 
				<div style="margin:10px 50px 10px 50px;"><div class="rst">總死亡人數:<div id="result24" class="rs">無資料</div></div></div>				 
			</div>
			<div style="display:flex; margin:20px;font-weight:bold;">
				<div style="margin:20px 50px 10px 50px;"><div class="rst" style="font-size:30px;text-align:center">今日數據</div></div>
				<div style="margin:10px 100px 10px 150px;"><div class="rst">通報數:<div id="result31" class="rs">無資料</div></div></div>
				<div style="margin:10px 100px 10px 100px;"><div class="rst">排除數:<div id="result32" class="rs">無資料</div></div></div>
				<div style="margin:10px 70px 10px 70px;"><div class="rst" >確診人數:<div id="result33" class="rs" style="border:3px solid #F75000;">無資料</div></div></div>			 
			</div>
			
			<div style="display:flex;margin:20px;font-weight:bold;">
				<div style="margin:20px 10px 10px 10px;"><div class="rst" style="font-size:30px;text-align:center">國內檢驗總計</div></div>
				<div><div class="rst" style="margin:10px 70px 10px 120px;">累計人次:<div id="result11" class="rs">無資料</div></div></div>
				<div><div class="rst" style="margin:10px 100px 10px 100px;">累計件數:<div id="result12" class="rs">無資料</div></div></div>
				<div><div class="rst" style="margin:10px 70px 50px 70px;">更新時間:<div id="result13" class="rs">無資料</div></div></div>
 
			</div>
		</div>		
			
	
	
		<div style="width:550px;height:500px;float:right;">
			<div style="text-align:center;width:550px;height:50px;"><h2>疫情相關資訊</h2></div>
			<div class="container">
				<div class="row align-items-center">
					<div class="mhw"><a role="button" href='https://sites.google.com/cdc.gov.tw/2019ncov/taiwan' target="_blank" style='color:white'>疫情監測</a></div>
					<div class="mhw"><a role="button" href='https://www.mohw.gov.tw/mp-1.html' target="_blank"  style='color:white'>衛福部</a></div>
					<div class="mhw"><a role="button" href='https://www.cdc.gov.tw/Category/Page/vleOMKqwuEbIMgqaTeXG8A' target="_blank" style='color:white'>病毒介紹</a></div>
				</div>
				<div class="row align-items-center">
					<div class="mhw"><a role="button" href='https://www.cdc.gov.tw/Category/List/SI4DlKBGujaYVJVSVKmwJw' target="_blank" style='color:white'>疫苗資訊</a></div>	
					<div class="mhw"><a role="button" href='https://www.cdc.gov.tw/Category/MPage/epjWGimoqASwhAN8X-5Nlw' target="_blank" style='color:white'>疫苗簡介</a></div>	
					<div class="mhw"><a role="button" href='https://www.cdc.gov.tw/Category/Page/9mcqWyq51P_aYADuh3rTBA' target="_blank" style='color:white'>接種對象</a></div>	
		
				</div>
				<div class="row align-items-center">
					<div class="mhw"><a role="button" href='https://1922.gov.tw/' target="_blank" style='color:white'>疫苗預約平台</a></div>	
					<div class="mhw"><a role="button" href='https://www.mohw.gov.tw/np-15-1.html' target="_blank" style='color:white'>最新消息</a></div>	
					<div class="mhw"><a role="button" href='https://covid19.mohw.gov.tw/ch/np-5187-205.html' target="_blank" style='color:white'>紓困4.0</a></div>	
				</div>
				<div style="text-align:center;width:550px;height:50px;float:right;margin-bottom:20px;font-weight:bold;"> <h5>* 防疫專線: 1922， 國外民眾可撥打: +886-800-001922 </h5></div>
			</div>			
		</div>		
	<div>
		<div>			
			<br>
			<h2>歡迎大家來填問卷</h2>
			<input type="button" value="問卷" id='questionnaire'
			style="width:88px;height:60px;border-radius:30px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;font-weight:bold;"
			onclick="location.href='<c:url value="/Coupon/questionnaire" />'">
		</div>
			<br>	
		<div>
			
			<h2>管理員管理</h2>
			<input type="button" id="admin" 
			style="width:88px;height:60px;border-radius:30px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;font-weight:bold;"
			 value="管理" /> 
			<%-- 	onclick="location.href='<c:url value='/Coupon/admin' />'"> --%>
		</div>		
			<br>
		<div style="margin-bottom:20px;">	
			<h2>回美食地圖首頁</h2>
			<input type="button" value="首頁"
			style="width:88px;height:60px;border-radius:30px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;font-weight:bold;"
			onclick="location.href='<c:url value='/' />'">		
		</div>		
	</div>
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
	<!-- 	<script> -->
<%-- // 		var src = "<c:url value='/Member/user/${user}/photo'/>"; --%>
<!-- // 		userNameMain(src); -->
<!-- 	</script> -->
</body>
</html>