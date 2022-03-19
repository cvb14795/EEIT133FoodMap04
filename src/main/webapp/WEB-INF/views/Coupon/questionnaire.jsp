<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="google-signin-client_id"
		content="532249608397-co7fll33vdrk1l991fsco6ko85jrlpi1.apps.googleusercontent.com"> -->
<style>
	p {
		font-size: 100px;
		text-align: center;
		
	}
	
	fieldset {
		width: 1500px;
		border: 5px solid #FF8000;
		border-radius: 20px;
		text-align: center;
		margin: 0 auto;
		display:flex;		
		
	}
	
	.str1 {
/* 		text-align: center; */
		width: 450px;
		border-bottom: 1px dashed #d0d0d0;
		/* 表格間距 */
		margin:0px auto;
		/* 線條與表格的距離 */
		padding-bottom: 5px;
	}
	
	legend {
		color: #0066cc;
 		margin: 0 auto; 
		text-align: center;
		font-weight:bold;
		font-size: 50px;
	}
	
	.t1 {
		width: 100px;
		float: left;
		text-align: right;
	}
	
	.sub {
		width: 500px;
/* 		margin: 20px; */
		/* 置中 */
		text-align: center;
		margin: 0 auto;
		font-size:20px;
	}
	
	.pd1 {
		font-size:20px;
		margin:10px;
		font-weight:bold;
	}

	.inputstyle {
		font-size:20px;
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
<script src='<c:url value="/js/memberAuth.js"/>'></script>

<%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
<!-- aboutUs -->
<!-- <script src="./js/aboutUs.js"></script> -->

<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->
<script>
	$(function() {
		adminAuth();
		// 將所有checkbox-switch改成IOS風格
		var el = document.querySelector('.checkbox-switch');
		var nav = document.querySelector("nav");
		var mySwitch = new Switch(el, {
			size: 'small',
			checked:false,
			onChange: function(){
// 					darkmode.toggle();
				nav.classList.toggle("navbar-default");
				nav.classList.toggle("navbar-dark");
				nav.classList.toggle("bg-dark");

				if (mySwitch.getChecked()) {
					console.log("nav:深色模式");
					$(".navbar").css("background-color", "#ffc078");
					$("ul.nav li a").css("color", "#333")
					$("ul.nav li a:hover").css("color", "#EA7500")
					$("ul.nav li a:hover").css("background-color", "#333")
					$("label[for='checkbox-switch']").text("深色模式")
				} else {
					console.log("nav:淺色模式");
					$(".navbar").css("background-color", "#333");
					$("ul.nav li a").css("color", "#ffc078");
					$("ul.nav li a:hover").css("color", "#333")
					$("ul.nav li a:hover").css("background-color", "#EA7500")
					$("label[for='checkbox-switch']").text("淺色模式")
				}
			}														
		});
		// 獲取使用者資訊
		const cookies = document.cookie;
		console.log("cookies:" + cookies);
		let userName = cookies.split("user=")[1];
		if (userName === undefined) {
			console.log("使用者名稱:未定義 ");
			// 未登入狀態下(登出後)顯示登入按鈕及註冊按鈕
			$("#loginNavBtn").show();
			$("#registerNavBtn").show();
			// 未登入狀態下(登出後)隱藏登出按鈕及編輯按鈕
			$("#logoutNavBtn").hide();
			$("#editNavBtn").hide();
			// 未登入顯示為Guest
			$("#user").text("Guest");
			console.log(document.getElementById("user").outerHTML);
		} else {
			console.log(`使用者名稱:${userName}`);
			// 已登入狀態下(登入後)隱藏登入按鈕及註冊按鈕
			$("#loginNavBtn").hide();
			$("#registerNavBtn").hide();
			// 已登入狀態下(登入後)顯示登出按鈕及編輯按鈕
			$("#logoutNavBtn").show();
			$("#editNavBtn").show();
			// 登入後顯示用戶名稱
			$("#user").text(userName);
		}
	})
</script>
<style>
header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
.search-wrap{
	border: 1px solid #ddd;
	color: #051922;
	padding: 15px;
	width: 100%;
	border-radius: 5px;
	font-size: 15px;
	width:500px;
	margin-bottom:20px;
	margin-left:20px;
}
</style>
<script>
	
	function chk() {
		var cnt = 0;

		// 針對性別
		var gender = document.getElementsByName('gender');
		for (let i = 0; i < gender.length; i++) {
			if (gender[i].checked) {
				cnt++;
			}
		}

		//針對各選項
		var foreign = document.getElementsByName('abroad');
		for (let i = 0; i < foreign.length; i++) {
			if (foreign[i].checked) {
				cnt++;
			}
		}

		var move = document.getElementsByName('moving');
		for (let i = 0; i < move.length; i++) {
			if (move[i].checked) {
				cnt++;
			}
		}

		var family = document.getElementsByName('family');
		for (let i = 0; i < family.length; i++) {
			if (family[i].checked) {
				cnt++;
			}
		}

		var fever = document.getElementsByName('fever');
		for (let i = 0; i < fever.length; i++) {
			if (fever[i].checked) {
				cnt++;
			}
		}

		var vaccine = document.getElementsByName('vaccine');
		for (let i = 0; i < vaccine.length; i++) {
			if (vaccine[i].checked) {
				cnt++;
			}
		}

		if (cnt > 5) {
			return true
		} else {
			alert('需作答完整!');
			return false
		}
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
	
	<!-- 防疫專區 -->		

	<div class="memberDetail">
		<div class="text-right">
			會員： <span id="userNameContainer"></span>
		</div>
		<div class="text-right">
			身分： <span id="isAdminContainer"></span>
		</div>
	</div>

	<div style="display:flex;margin:20px;">
	<form action='qcontroller' method='post' name='send' onsubmit="return chk();">
 		
		<fieldset>

			<legend><h2 style="font-weight:bold;">個人資料</h2></legend>
			<div class='str1'>

				<div class='pd1'>
					姓名:
					<input type='text' autofocus autocomplete='off' placeholder='請輸入姓名' id='realname'
                            name='name' required>
<!--                             <label></label> -->
                </div>

				<div class='pd1'>
					性別:
						<label> <input id='gender' type='radio' name='gender' value='男'>男</label> 
						<label> <input id='gender' type='radio' name='gender' value='女'>女</label> 
						<label> <input id='gender' type='radio' name='gender' value='其他'>其他</label>
					
				</div>



				<div class='pd1'>
					身分證字號: 
					<label><input type='text' name='id' id='idcode'
						placeholder='請輸入身份證字號' maxlength='10'
						pattern='^[A-Z]{1}[1-2]{1}[0-9]{8}$' required></label>
				</div>



				<div class='pd1'>
					生日:<label><input type='text' name='birth' id='birthday'
						placeholder='輸入格式(yyyymmdd)' maxlength='8' required
						pattern='((\d{3}[1-9]|\d{2}[1-9]\d|\d[1-9]\d{2}|[1-9]\d{3})(((0[13578]|1[02])(0[1-9]|[12]\d|3[01]))|((0[469]|11)(0[1-9]|[12]\d|30))|(02(0[1-9]|[1]\d|2[0-8]))))|(((\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)'></label>
				</div>

				<div class='pd1'>
					聯絡電話: <label><input id='phone' class="inputstyle" type='text' name='phone' required></label>
				</div>

			</div>
		</fieldset>
		<fieldset>
			<legend><h2 style="font-weight:bold;">接觸史調查</h2></legend>
			<div class='str1'>
				<div class='pd1'>最近14天是否有出國紀錄:
					<div>
						<label><input class="inputstyle" id='abroad' type='radio' name='abroad' value='1'>是</label>		
						<label><input class="inputstyle" id='abroad' type='radio' name='abroad' value='0'>否</label>
					</div>
				</div>
				<div class='pd1'>最近14天是否有跨縣市移動:
					<div>
						<label><input class="inputstyle" type='radio' id='moving' name='moving' value='1'>是</label>
						<label><input class="inputstyle" type='radio' id='moving' name='moving' value='0'>否</label>
					</div>
				</div>
				<div class='pd1'>同住親友是否有收到居家隔離通知單:
					<div>
						<label><input  type='radio' id='family' name='family' value='1'>是</label>
						<label><input  type='radio' id='family' name='family' value='0'>否</label>
					</div>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend><h2 style="font-weight:bold;">身體狀況調查</h2></legend>
			<div class='str1'>

				<div class='pd1'>過去 14 天是否有發燒、咳嗽或呼吸急促症狀？（已服藥者亦須勾選「是」）:
					<div>
		 				<label><input type='radio' id='fever' name='fever' value='1'>是</label> 
		 				<label><input type='radio' id='fever' name='fever' value='0'>否 </label> 
	 				</div>
 				</div> 
 				<div class='pd1'>是否有接種過疫苗:
	 				<div>
		 				<label><input type='radio' id='vaccine' name='vaccine' value='1'>是 </label> 
		 				<label><input type='radio' id='vaccine' name='vaccine' value='0'>否 </label>
	 				</div>
				</div> 

			</div> 

 		</fieldset> 


		<div class='sub'>
			<input type='submit' style="border-radius: 10px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;color:white;" value='送出'>
			<input type='reset' style="width:88px;height:60px;border-radius:30px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;color:white;font-weight:bold;"  value='清除'>
			<input type="button" style="width:88px;height:60px;border-radius:30px;border:3px solid #fd7e14;font-size:20px;background-color:#FF8000;color:white;font-weight:bold;" name="submit" value="一鍵" id="btn">
		</div>

 	</form> 
 	
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
	
	<script>
		$("#btn").on("click",function (e) {
		   $("#realname").val("王小明");
		   $("#idcode").val("H134029606");
		   $("#birthday").val("19980901");
		   $("#phone").val("0968676882");  
		})
	</script>
	<%-- // 		var src = "<c:url value='/Member/user/${user}/photo'/>"; --%>
<!-- // 		userNameMain(src); -->
<!-- 	</script> -->
</body>
</html>