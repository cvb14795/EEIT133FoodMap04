<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>忘記密碼</title>
<link rel="stylesheet" href="../css/memberRegister.css">
<script src="../js/validate.js"></script>
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/sweetalert2.all.js"></script>
</head>
<body>
	<form>
		<fieldset>
			<legend>發送重設密碼驗證信</legend>
			<div class="st1">
				<label for="account" class="t1">帳號：</label>
				<input type="text" name="account" id="account" placeholder="請輸入註冊時的帳號">
				<label for="account" id="accountCheckMsg"></label><br>
			</div>
			<div class="st1">
				<label for="email" class="t1">信箱：</label> <input type="text"
					name="email" id="email" placeholder="請輸入註冊時的信箱"> <label
					for="email" id="emailCheckMsg"></label><br>
			</div>
			<div class="submitBtnContainer">
				<input type="submit" value="送出">
				<!-- <button type="button" id="authorize-button">Google帳號認證</button> -->
			</div>
		</fieldset>
	</form>

	<script>
        $("form").submit(function (e) {
            $form = $(this);
            Swal.fire({
                position: 'center',
                icon: 'info',
                title: '正在寄出郵件...',
                didOpen: () => {
                    Swal.showLoading();
                },
                html: "正在發送重設密碼確認信，請稍候...",
                // timer: 5000,
                // timerProgressBar: true,
                //預設為有確認按鈕
                showConfirmButton: false,
                allowOutsideClick: false
            }).then((result) => {
                $.ajax({
                    type: "POST",
                    url: "./ForgetPassword",
                    data: $form.serialize(),
                    success: function (data) {
                    	console.log("提交成功");
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: '提交成功',
                            html: "請至信箱 " + $("#email").val() + " 收取驗證信！",
                        }).then((result) => {
                            if (result.isConfirmed) {
                                location.href = "../Home";
                            }
                        })
                    },
                    error: function (params) {
                    	console.log("提交失敗");
                        Swal.fire({
                            position: 'center',
                            icon: 'error',
                            title: '提交失敗',
                            html: "信箱 " + $("#email").val() + " 錯誤<br/>或者帳戶名稱 "+ $("#account").val() +"錯誤！",
                        })
                    }
                })
            })
            e.preventDefault();
        })

    </script>
	<!-- <script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script> -->
</body>
</html>