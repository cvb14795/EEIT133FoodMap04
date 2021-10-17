<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>註冊失敗</title>
</head>

<body>
    <p>您註冊的的帳戶：${user} 已存在<br>請更換其他帳號名稱重試！</p>
    <p>5秒後跳轉回首頁... <span id="countDown"></span></p>

    <script>
        var time = 5;
        function redirectCountDown() {
            var cd = document.getElementById("countDown");

            if (time == 0) {
                clearInterval(this);
                location.href = "../Home";
            } else {
                // 更新倒數秒數
                cd.innerHTML = time;
            }

            time--;
        }
        setInterval(redirectCountDown, 1000);

    </script>
</body>

</html>