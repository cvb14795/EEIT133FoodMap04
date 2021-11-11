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
							<li class="active"><a href="<c:url value="/Recipe/user/UserInsertRecipe2"/>">新增專屬食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/ViewYourRecipe2"/>">查詢您的食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/showMyfavorites"/>">我的最愛</a></li>
						</ul>
					</div>
				</div>
			</div>
	<div>
		<form id="form">	
			<fieldset>
				<legend>新增</legend>
				<div class="st1">
					<label for="" class="t1">品項:</label>
					<input type="text" id="foodName" name="foodName" required>
				</div>
				<div class="st1">
					<label for="" class="t1">分類:</label>
					<input type="text" id="category" name="category" required>
				</div>
				<div class="st1">
					<label for="" class="t1">食材1:</label>
					<input type="text" id="food1" name="food1" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材2:</label>
					<input type="text" id="food2" name="food2" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材3:</label>
					<input type="text" id="food3" name="food3" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材4:</label>
					<input type="text" id="food4" name="food4" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料1:</label>
					<input type="text" id="sauce1" name="sauce1" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料2:</label>
					<input type="text" id="sauce2" name="sauce2" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料3:</label>
					<input type="text" id="sauce3" name="sauce3" />
				</div>
				<div class="st1">
					<label for="" class="t1">步驟:</label>
					<textarea id="step" name="step" rows="5" cols="33"></textarea>
				</div>
				<div class="st1">
					<img src="" width="450" height="300" alt="請選擇照片"  id="showPic">
				</div>
				<div class="st1">
					<label for="" class="t1">照片:</label>
					<input type="file" name="photo" id="projectImage">
				</div>

			</fieldset>
			<div class="sub">
				<input type="reset" name="reset" value="清除"> 
				<input type="submit" name="submit" value="確認">
				<input type="button" name="submit" value="一鍵輸入" id="btn">
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
		
		$("#btn").on("click",function (e) {
			$("#foodName").val("日式醬油鰹魚片味增湯");
			$("#category").val("湯類");
			$("#food1").val("蔥3條");
			$("#food2").val("洋蔥1顆");
			$("#food3").val("豆腐1盒");
			$("#food4").val("柴魚2包");
			$("#sauce1").val("日式昆布柴魚醬油高湯半鍋");
			$("#sauce2").val("日式淬釀鰹魚醬油3匙");
			$("#sauce3").val("味增1匙、水1/4鍋");
			$("#step").val("1.豆腐切丁、蔥切成蔥花狀、洋蔥切塊。\n"+
						   "2.接著準備日式淬釀鰹魚醬油。\n"+
						   "3.將3匙日式淬釀鰹魚醬油加入鍋中。\n"+
						   "4.準備半鍋的日式昆布柴魚醬油高湯，加水至4分之3鍋"+
						   "5.將1匙味增加入熱水攪拌至無結塊。\n"+
						   "6.將攪拌好的味增倒入鍋中煮滾。\n"+
						   "7.接著依序倒入蔥花，再來倒入洋蔥、豆腐，最後倒入柴魚。\n"+
						   "8.蓋上鍋蓋，轉小火煮5分鐘。\n");
		})
			
		$(function(){
			var src = "<c:url value='/Member/user/${user}/photo'/>";
			userNameMain(src);
		})
		
// 		var x = new FileReader;
// 		var src;
// 		document.forms[1].elements[10].onchange = function() {
// 			x.readAsDataURL(this.files[0]);
// 		}
// 		x.onloadend = function() {
// 			src = this.result;
// 			document.images[0].src = src;;
// 		}

        var img;
		$('#projectImage').change(function() {
            var projectImage = $("#projectImage")[0].files[0];
            var reader = new FileReader;
            reader.onload = function(e) {
                $('#showPic').attr('src', e.target.result);
                img = e.target.result;
            }
            reader.readAsDataURL(projectImage);
        })

		var cookies = document.cookie;
		var userName = cookies.split("user=")[1];
		$("#userName").val(userName);

		var form = document.getElementById("form");
		$("#form").on("submit", function(e){
			/* =====for formData&MultipartFile =====*/
			var formData = new FormData(form);
			
			/* =====for JSON =====*/
			
			var html = "";
			var inputData = $(".st1 input").slice(0, 9);
			var recipeStep = $(".st1 textarea").val();
			// html += + "name" + $("#name").val() + "<br/>";
			for (let i = 0; i < inputData.length; i++) {
				let name = inputData.eq(i).attr("name");
				let value = (inputData.eq(i).val() != "") ? inputData.eq(i).val() : "無";
				html += name+": "+value+"</br>";
			}
			
			//步驟:
			let stepList = recipeStep.split("\n");
			html += "步驟:</br>"
			//照每個步驟換行
			stepList.forEach(element => {
				html += element+"</br>"
			});
			console.log(html);
			
			//改用ajax傳送 棄用原本的form傳送
			e.preventDefault();
			
			Swal.fire({
				title: '您確定要新增嗎？',
				icon: 'question',
				html: html,
// 				imageUrl: src,
				imageUrl: img,
				imageWidth: 400,
				imageHeight: 200,
				showCancelButton: true,
			}).then((result) => {
				if (result.isConfirmed) {
					$.ajax({
						type:"post",
						url:"<c:url value='/Recipe/user/UserInsertToDB'/>",
						data: formData,
		// 				data: json,
		// 				dataType:"json",
		// 				contentType: "application/json; charset=utf-8",
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
								console.log(key);
								console.log(jsonData[key]);
								let val = (jsonData[key] != "") ? jsonData[key] : "無";
								if (!(key == "base64String" || key == "photo")) {
									html1 += key+": "+val;
									html1 += "<br/>";
								} 
							};

							Swal.fire({
								title: '已新增成功！',
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