<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>登出成功</title>
</head>

<body>
    <p>您好，已經將您登出！</p>
    <p><span id="count"></span>秒後跳轉回首頁... <span id="countDown"></span></p><br/>
    或<a href="../Home">點擊此處</a>立刻返回首頁

    <script>
        var time = 3;
        document.getElementById("count").innerHTML = time;
        function redirectCountDown() {
            var cd = document.getElementById("countDown");

            if (time <= 0) {
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