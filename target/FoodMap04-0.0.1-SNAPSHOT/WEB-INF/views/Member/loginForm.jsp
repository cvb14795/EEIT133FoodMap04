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
	content="196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com">
<title>用戶登入</title>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/sweetalert2-9.17.2.css">
<style>
#my-signin2 {
	display: flex;
	justify-content: center;
}
</style>
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/sweetalert2-9.17.2.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton"
	async defer></script>
</head>
<body>
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
			</form>

			<div id="my-signin2"></div>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover" href="./ForgetPassword">忘記密碼?</a>
			</div>

		</div>
	</div>

	<script>
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
        function onSuccess(googleUser) {
            console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
        }

        function onFailure(error) {
            console.log(error);
        }

        function renderButton() {
            gapi.signin2.render('my-signin2', {
                'scope': 'profile email',
                'width': 240,
                'height': 50,
                'longtitle': true,
                'theme': 'dark',
                'onsuccess': onSuccess,
                'onfailure': onFailure
            });
        }
    </script>
</body>
</html>