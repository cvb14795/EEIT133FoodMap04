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
	<script src="../js/jquery-3.6.0.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/validate.js"></script>
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
		<form:form action="./Revise" modelAttribute="member" method="POST" enctype="multipart/form-data">
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
					<form:input type="file" path="imgBytes" id="image"/>
				</div>
				<div class="submitBtnContainer">
					<input type="submit" value="送出">
				</div>
			</fieldset>
		</form:form>
	</div>

	<script>
		validate("formElem");
		$("form").submit(function (e) {
			var invalid = $(".checkMsg").hasClass("incorrect");
			if (invalid) {
				return false;
			}
			return true;

		})
	</script>
</body>

</html>