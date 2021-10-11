<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成功</title>
<style type="text/css">
header {
	background:#9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<header>
	<h1>Success!</h1>
	</header>
	<script>
		Swal.fire({
			position : 'center',
			icon : 'success',
			title : '即將回到首頁...',
			showConfirmButton : false,
			timer : 3000
		})
		setTimeout("location.href = './UserStartingPage.jsp'",3000);
		
	</script>
</body>

</html>