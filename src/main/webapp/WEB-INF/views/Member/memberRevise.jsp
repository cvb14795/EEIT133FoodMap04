<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>會員資料修改</title>
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
		integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
		crossorigin="anonymous" />
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/memberRegister.css">
	<link rel="stylesheet" href="../css/sweetalert2-9.17.2.css">
	<script src="../js/jquery-3.6.0.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/validate.js"></script>
	<script src="../js/sweetalert2-9.17.2.js"></script>
	<style>
		img {
			width: 400px;
			height: 400px;
		}
	</style>
</head>

<body>
	<div class="container">
		<!-- 除非要上傳檔案 否則不要用enctype="multipart/form-data" 會抓不到參數-->
		<form:form id="form" action="" modelAttribute="member" method="POST">
<%-- 		enctype="multipart/form-data"> --%>
			<!-- HiddenHttpMethodFilter轉PUT -->
			<input type="hidden" name="_method" value="PUT"/>
			<fieldset>
				<legend>註冊表單</legend>
				<div class="st1">
					<label for="name" class="t1">姓名：</label>
					<form:input path="name" id="name" class="formElem" />
					<label for="name" id="nameCheckMsg" class="validation"></label><br>
					<span class="hint">(1.不可空白 2.至少兩個字以上 3.必須全部為中文字)</span>
				</div>
				<div class="st1">
					<label for="account" class="t1">帳號：</label>
					<form:input path="account" id="account" class="formElem" disabled="true" />
					<label for="account" id="accountCheckMsg" class="validation"></label><br>
					<span class="hint">(1.不可空白 2.至少6個字且必須包含英數字)</span>
				</div>
				<div class="st1">
					<label for="password" class="t1">密碼：</label>
					<input type="password" name="password" id="password" class="formElem"/>
					<label for="password" id="passwordCheckMsg" class="validation"></label><br>
					<span class="hint">(1.不可空白 2.至少6個字且必須包含英數字)</span>
				</div>
				<div class="st1">
					<label for="passwordConfirm" class="t1">確認密碼：</label>
					<input type="password" name="passwordConfirm" id="passwordConfirm" class="formElem"/>
					<label for="passwordConfirm" id="passwordConfirmCheckMsg" class="validation"></label><br>
				</div>
				<div class="st1">
					<label for="ID" class="t1">身分證字號：</label>
					<form:input type="text" path="id" id="ID" class="formElem"/>
					<label for="ID" id="IDCheckMsg" class="validation"></label><br>
				</div>
				<div class="st1">
					<label for="email" class="t1">電子郵件：</label>
					<form:input type="text" path="email" id="email" class="formElem" />
					<label for="email" id="emailCheckMsg" class="validation"></label><br>
				</div>
				<div class="st1">
					<label for="address" class="t1">地址：</label>
					<form:input type="text" path="address" id="address" class="formElem" />
					<label for="address" id="addressCheckMsg" class="validation"></label><br>
				</div>
				<div class="st1">
					<label for="phone" class="t1">電話：</label>
					<form:input type="text" path="phone" id="phone" class="formElem" maxlength="10"/>
					<label for="name"id="phoneCheckMsg" class="validation"></label><br>
					<span class="hint">(格式：0987654321之十碼數字)</span>
				</div>
				<div class="st1">
					<label for="image" class="t1">會員頭像：</label>
					<img src="data:image/jpg;base64,<c:out value='${base64String}'/>" />
				</div>
				<div class="st1">
					<label for="image" class="t1">上傳頭像：</label>
					<img src="" id="uploadImg"/>
					<input type="hidden" id="imgBase64String" name="imgBase64String" value="">
 				</div>
				<div class="submitBtnContainer">
					<input type="submit" value="送出">
				</div>
			</fieldset>
		</form:form>
		<div class="st1">
			<button id="uploadBtn">上傳頭像</button>
		</div>
	</div>

	<script>
		$("#uploadImg").hide();
		$("#uploadBtn").on("click", async function(){
			const { value: file } = await Swal.fire({
			  title: '請選擇上傳頭像',
			  input: 'file',
			  inputAttributes: {
			    'accept': 'image/*',
			    'aria-label': 'Upload your profile picture'
			  }
			})

			if (file) {
			  const reader = new FileReader()
			  reader.onload = (e) => {
			    Swal.fire({
			      title: 'Your uploaded picture',
			      imageUrl: e.target.result,
			      imageAlt: 'The uploaded picture'
			    })
				// 顯示圖片
				$("#uploadImg").attr("src", e.target.result);
			    $("#uploadImg").show();
				// 儲存base64值供上傳進member
				$("#imgBase64String").val(e.target.result.split(";base64,")[1]);
			  }
			  reader.readAsDataURL(file)
			}
		})
	
		var account = $("#account").val();
// 		取name為form的值
// 		var form = document.forms.namedItem("form");
		var $form = $("#form");
		$form.attr("action", "./user/"+account);
		
		validate("formElem");
		
		$form.on("submit", function (e) {
			e.preventDefault();
// 			給multipart/form-data使用
// 			var data = new FormData(form);
			Swal.fire({
				position: 'center',
				icon: 'info',
				title: '提交',
				html: "送出中，請稍候...",
				allowOutsideClick: false,
                onBeforeOpen: () => {
    				Swal.showLoading()
				},
				showConfirmButton: false,
			})
			console.log("送出...");
			var invalid = $(".checkMsg").hasClass("incorrect");
			if (!invalid) {
				console.log("資料正確，送出表單");
				$.ajax({
					type: $form.attr("method"),
					url: $form.attr("action"),
					data: $form.serialize(),
					success: function (data) {
						console.log("修改成功");
						console.log(data);
						swal.close();
						Swal.fire({
							position: 'center',
							icon: 'success',
							title: '修改成功',
							html: "修改成功，將在3秒後回首頁...",
							timer: 3000,
							timerProgressBar: true,
							showConfirmButton: false,
						})
						location.href = '<c:url value="/Home"/>';
					}
				})
			}
		})
	</script>
</body>

</html>