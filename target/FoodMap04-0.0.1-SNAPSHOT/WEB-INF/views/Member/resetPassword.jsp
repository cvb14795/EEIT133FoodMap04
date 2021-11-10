<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form id="form" action="./ResetPassword" method="POST">
		<fieldset>
			<legend>重設密碼</legend>
			<div class="st1">
				<!-- 				<label for="" class="t1">舊密碼：</label> -->
				<!-- 				<input type="password" name="oldPassword" id="account" class="formElem" placeholder="請輸入最近一次記得的舊密碼"> -->
				<!-- 				<label for="account" id="accountCheckMsg"></label><br> -->
			</div>
			<div class="st1">
				<label for="" class="t1">新密碼：</label>
				<input type="password" name="password" id="password" class="formElem" placeholder="請輸入新密碼">
				<label for="password" id="passwordCheckMsg"></label><br>
			</div>
			<div class="st1">
				<label for="" class="t1">確認新密碼：</label>
				<input type="password" name="passwordConfirm" id="passwordConfirm" class="formElem"
					placeholder="請重複輸入密碼以確認">
					<label for="passwordConfirm" id="passwordConfirmCheckMsg"></label><br>
			</div>
			<div class="submitBtnContainer">
				<input type="submit" value="送出">
				<!-- <button type="button" id="authorize-button">Google帳號認證</button> -->
			</div>
		</fieldset>
	</form>

	<script>
		validate("formElem");
		var $form = $("#form");
		$form.on("sumbit", function (e) {
			e.preventDefault();
			// 檢查輸入是否正確
			var invalid = $(".checkMsg").hasClass("incorrect");
			console.log("invalid:"+invalid);
			if (!invaild){
	            $.ajax({
					type: $form.attr("method"),
					url: $form.attr("action"),
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
					        	//將使用者登出(防止被其他人惡意變更密碼)
			                    location.href = '<c:url value="/Member/Logout"/>';
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
			} else {
				Swal.fire({
			        position: 'center',
			        icon: 'error',
			        title: '錯誤',
			        html: "請填寫所有欄位!",
			    })
			}
        })
	</script>
	<!-- <script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script> -->
</body>
</html>