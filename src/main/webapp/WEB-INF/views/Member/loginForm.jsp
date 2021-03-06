<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="google-signin-client_id"
	content="532249608397-co7fll33vdrk1l991fsco6ko85jrlpi1.apps.googleusercontent.com">
<title>用戶登入</title>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
<link rel="stylesheet" href="<c:url value='/css/login.css'/>">
<link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
<style>
#my-signin2 {
	display: flex;
	justify-content: center;
}
</style>
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<script src="<c:url value='/js/bootstrap.js'/>"></script>
<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
<script src="<c:url value='/js/lineLogin.js'/>"></script>
<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->
</head>
<body>
 	<script src="https://accounts.google.com/gsi/client" async defer></script>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<!--           <div class="fadeIn first"> -->
			<!--             <img src="" id="icon" alt="User Icon" /> -->
			<!--           </div>  -->

			<!-- Login Form -->
			<form action="./Login" method="post" id="form" >
				<input type="text" id="account" name="account" placeholder="請輸入帳號">
				<input type="password" id="password" name="password" placeholder="請輸入密碼" autocomplete="on">
               	<input type="submit" id="loginBtn" value="登入">
				<input type="button" id="oneClick" value="一鍵輸入" onclick="autoInput()" style="background-color:#F28123">
			</form>

			<div id="my-signin2"></div>
			<div id="g_id_onload"
			     data-client_id="532249608397-co7fll33vdrk1l991fsco6ko85jrlpi1.apps.googleusercontent.com"
			     data-context="signin"
			     data-ux_mode="popup"
			     data-login_uri="/FoodMap04/api/oauth2callback/google"
			     data-auto_prompt="false">
			</div>
			
			<div class="g_id_signin"
			     data-type="standard"
			     data-shape="rectangular"
			     data-theme="outline"
			     data-text="signin_with"
			     data-size="large"
			     data-locale="zh-TW"
			     data-logo_alignment="left"
			     style="display:flex;justify-content:center">
			</div>
			<a href="" id="lineLoginHref">
				<input type="button" class="lineLogin" style="width: 151px; height: 44px; border: none" />
			</a>
			<!-- Remind Password -->
			<div id="formFooter">
				<a class="underlineHover" href="./ForgetPassword">忘記密碼?</a>
			</div>

		</div>
	</div>

	<script>
		setLineOAuthUrl();
        var loginBtn = document.getElementById("loginBtn");
        var $account = $("#account");
        var $form = $("#form");

        $form.on("submit", function (e) {
            console.log("偵測到使用者登入請求");

            Swal.fire({
                position: 'center',
                icon: 'info',
                title: '登入',
                html: "登入中，請稍候...",
                timer: 3000,
                timerProgressBar: true,
                showConfirmButton: false,
                // title: "登入",
                // text: "登入中，請稍候...",
                // type: "info",
                // confirmButtonColor: "#3085d6",
                // confirmButtonText: "確定",
                // confirmButtonClass: "btn btn-success",
                // buttonsStyling: false
            })

            // 傳送登入資料
            $.ajax({
                // type: "POST",
                type: $form.attr("method"),
                // url: "./Login",
                url: $form.attr("action"),
                data: $form.serialize(),
                success: function (data) {
		            console.log("登入成功");
                    
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '登入成功',
                        html: "您好，" + $account.val() + "！",
                        timer: 3000,
                        timerProgressBar: true,
                        showConfirmButton: false,
                    })
                    location.href = '<c:url value="/Home"/>';
                },
                error: function(result) {
		            console.log("登入失敗");
                	
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: '登入失敗',
                        html: "帳戶名稱錯誤或密碼錯誤！",
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    })
                }
            })

            return false;
        })

        // loginBtn.addEventListener("click", loginCheck);


    </script>

	<script>
        var id_token;
//         function onSuccess(googleUser) {
//         	var profile = googleUser.getBasicProfile();
//         	console.log('Logged in as: ' + profile.getName());
//         	console.log('Image URL: ' + profile.getImageUrl());
//         	console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
//             //用於傳送到後端的token
//         	id_token = googleUser.getAuthResponse().id_token;
//             console.log('id_token: ' + id_token);            
//         }

//         function onFailure(error) {
//             console.log(error);
//         }

//         function renderButton() {
//             gapi.signin2.render('my-signin2', {
//                 'scope': 'profile email',
//                 'width': 240,
//                 'height': 50,
//                 'longtitle': false,
//                 'theme': 'light',
//                 'onsuccess': onSuccess,
//                 'onfailure': onFailure
//             });
//         }
//         function signOut() {
//     	    var auth2 = gapi.auth2.getAuthInstance();
//     	    auth2.signOut().then(function () {
//     	        console.log('User signed out.');
//     	    });
//     	}

        function autoInput(){
        	$("#account").val("eeit133group4")
        	$("#password").val("eeit133group4")
        }
        
    </script>
</body>
</html>