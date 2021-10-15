<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>重設密碼</title>
<link rel="stylesheet" href="../css/memberRegister.css">
<script src="../js/validate.js"></script>
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/sweetalert2.all.js"></script>
</head>
<body>
	<form>
		<fieldset>
			<legend>重設密碼</legend>
			<div class="st1">
				<!-- 				<label for="" class="t1">舊密碼：</label> -->
				<!-- 				<input type="password" name="oldPassword" id="account" class="formElem" placeholder="請輸入最近一次記得的舊密碼"> -->
				<!-- 				<label for="account" id="accountCheckMsg"></label><br> -->
			</div>
			<div class="st1">
				<label for="" class="t1">新密碼：</label> <input type="password"
					name="password" id="password" class="formElem" placeholder="請輸入新密碼">
				<label for="password" id="passwordCheckMsg"></label><br>
			</div>
			<div class="st1">
				<label for="" class="t1">確認新密碼：</label> <input type="password"
					name="passwordConfirm" id="passwordConfirm" class="formElem"
					placeholder="請重複輸入密碼以確認"> <label for="passwordConfirm"
					id="passwordConfirmCheckMsg"></label><br>
			</div>
			<div class="submitBtnContainer">
				<input type="submit" value="送出">
				<!-- <button type="button" id="authorize-button">Google帳號認證</button> -->
			</div>
		</fieldset>
	</form>

	<script>
		validate("formElem");
		//仍會送出 待DEBUG
		$("form").submit(function (e) {
            $form = $(this);
            $.ajax({
				type: "POST",
				url: "./resetPassword",
				data: $form.serialize(),
				success: function (data) {
					console.log("提交成功");
				    Swal.fire({
				        position: 'center',
				        icon: 'success',
				        title: '提交成功',
				        html: "修改密碼成功！",
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
				        html: "連線錯誤，請稍後再試！",
				    })
				}
			})
            
            var invalid = $(".checkMsg").hasClass("incorrect");
			if (invalid) {
				return false;
			}
			return true;
        })
	</script>
	<!-- <script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script> -->
</body>
</html>