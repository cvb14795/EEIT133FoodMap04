<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登入成功</title>
</head>

<body>
    <p>您好，您現在登入的帳戶為：${user}</p>
    <p>5秒後跳轉回首頁... <span id="countDown"></span></p>

    <script>
        var time = 5;
        function redirectCountDown() {
            var cd = document.getElementById("countDown");

            if (time == 0) {
                clearInterval(this);
                location.href = "./Home";
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