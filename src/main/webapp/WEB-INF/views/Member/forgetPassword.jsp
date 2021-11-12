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
<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/validate.js"></script>
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/sweetalert2.all.js"></script>

<style>
	#exampleModalLabel {
		font-size:18px;
		color:#00555f;
		line-height:1;
		margin:0 0 18px;
		font-family:"Helvetica Neue", Helvetica, Roboto, Arial, "Lucida Grande", "PingFang TC", "蘋果儷中黑", "Apple LiGothic Medium", "微軟正黑體", sans-serif;
		font-weight:normal
	}

</style>
</head>
<body>
	<form>
		<fieldset>
			<legend>發送重設密碼驗證信</legend>
			<button type="button" id="oneClick" onclick="autoInput()">一鍵輸入</button>
			<div class="st1">
				<label for="account" class="t1">帳號：</label> <input type="text"
					name="account" id="account" placeholder="請輸入註冊時的帳號"> <label
					for="account" id="accountCheckMsg"></label><br>
			</div>
			<div class="st1">
				<label for="email" class="t1">信箱：</label> <input type="text"
					name="email" id="email" placeholder="請輸入註冊時的信箱"> <label
					for="email" id="emailCheckMsg"></label><br>
			</div>
			<div class="submitBtnContainer">
				<!-- Button trigger modal -->
				<input type="submit" class="btn btn-primary" 
				data-toggle="modal" data-target="#dialogLabel" value="送出" />	
				<!-- <button type="button" id="authorize-button">Google帳號認證</button> -->
			</div>
		</fieldset>
	</form>


	<!-- Modal -->
	<!--
  	「data-backdrop="static"」 鎖定背景，點擊背景時不自動關閉視窗
  	「fade」 淡入、淡出的轉場效果
  	「modal-lg」視窗大小，如modal-lg、modal-md、modal-sm
  	「data-dismiss="modal"」 關閉視窗
  	「data-keyboard="true"」 是否用ESC鍵關閉，預設為true
	-->
	<div class="dialogify">
		<div class="modal fade" id="dialogLabel" tabindex="-1" role="dialog"
			aria-hidden="true" data-backdrop="static" data-keyboard="true">
			<div class="modal-dialog modal-md" role="document">
				<div class="modal-content">
					<div class="modal-header">
					<span class="modal-title" id="exampleModalLabel">資料核對正確後，將寄出信件</span>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">回到登入頁面</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<script>
		//預設此視窗為隱藏
		$("#dialogLabel").modal('hide');
        $("#dialogLabel .modal-footer button").on("click", function () {
			location.href = "./Login";
		});
	
        $("form").submit(function (e) {
        	$("#dialogLabel").modal('show');
        	
            $form = $(this);
           	console.log("ajax送郵件");
           	$("#dialogLabel .modal-body").html("帳號："+ $("#account").val() +"<br>信箱："+  $("#email").val());
//            	alert("帳號："+ $("#account").val() +"\n信箱："+  $("#email").val());
           	
            $.ajax({
                type: "POST",
                url: "./ForgetPassword",
                data: $form.serialize(),
                success: function (data) {
                	console.log("提交成功");
//                     Swal.fire({
//                         position: 'center',
//                         icon: 'success',
//                         title: '提交成功',
//                         html: "請至信箱 " + $("#email").val() + " 收取驗證信！",
//                     }).then((result) => {
//                         if (result.isConfirmed) {
//                             location.href = "../Home";
//                         }
//                     })
                },
                error: function (params) {
                	console.log("提交失敗");
//                     Swal.fire({
//                         position: 'center',
//                         icon: 'error',
//                         title: '提交失敗',
//                         html: "信箱 " + $("#email").val() + " 錯誤<br/>或者帳戶名稱 "+ $("#account").val() +"錯誤！",
//                     })
                }
             })
            e.preventDefault();
        })
        
		function autoInput(){
			$("#account").val("foodmap04")
			$("#email").val("foodmap04@gmail.com")
		}
    </script>
	<!-- <script src="https://apis.google.com/js/client.js?onload=handleClientLoad"></script> -->
</body>
</html>