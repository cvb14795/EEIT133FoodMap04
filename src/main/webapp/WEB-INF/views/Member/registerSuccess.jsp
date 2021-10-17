<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>註冊成功</title>
    <link rel="stylesheet" href="../css/registerSuccess.css">
</head>

<body>
    <jsp:useBean id="member" class="cf.cvb14795.member.bean.Member" scope="request" />
    <p>請確認以下資料</p>
    <table>
        <thead>
            <th>姓名</th>
            <th>帳號</th>
            <th>地址</th>
            <th>手機</th>
        </thead>
        <tbody>
            <tr>
                <td>${member.name}<br/>
                    <jsp:getProperty name="member" property="name" />
                </td>
                <td>
                    <jsp:getProperty name="member" property="account" />
                </td>
                <td>
                    <jsp:getProperty name="member" property="address" />
                </td>
                <td>
                    <jsp:getProperty name="member" property="phone" />
                </td>
            </tr>
        </tbody>
    </table>

    <br>
    
	<table>
		<thead>
			<tr>
				<th>頭像</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<img src="data:image/${imgExt};base64,${base64String}">
				</td>
			</tr>
		</tbody>
	</table>
	
	<br>

    <button id="confirmBtn" value="確定">確定</button>

    <script>
        var confirmBtn = document.getElementById("confirmBtn");
        confirmBtn.addEventListener("click", function (e) {
            // 點擊確定後回到首頁
            location.href = "../Home" ;
        })
    </script>
</body>

</html>