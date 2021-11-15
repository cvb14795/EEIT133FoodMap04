<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Spring Boot WebSocket Chat Application</title>
<link rel="stylesheet" href="<c:url value='/css/chat.css'/>" />
<style>
* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

html, body {
    height: 100%;
    overflow: hidden;
}

body {
    margin: 0;
    padding: 0;
    font-weight: 400;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 1rem;
    line-height: 1.58;
    color: #333;
    background-color: #f4f4f4;
    height: 100%;
}

body:before {
    height: 50%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background: #008080;
    content: "";
    z-index: 0;
}

.username-page-container {
    background: #fff;
    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
    border-radius: 2px;
    width: 100%;
    max-width: 500px;
    display: inline-block;
    margin-top: 42px;
    vertical-align: middle;
    padding: 35px 55px 35px;
    min-height: 250px;
    position: absolute;
    top: 50%;
    left: 0;
    right: 0;
    margin: 0 auto;
    margin-top: -160px;
}
</style>
</head>
<body>
    <noscript>
        <h2>Sorry! Your browser doesn't support Javascript</h2>
    </noscript>

    <!-- 進入頁面 -->
    <div id="username-page">
        <div class="username-page-container">
            <h1 class="title">輸入名稱</h1>
            
            <form id="usernameForm" name="usernameForm">
                <div class="form-group popup">
                    <input type="text" id="name" placeholder="輸入名稱..."
                        autocomplete="off" class="form-control popup" />
                    <span class="popuptext" id="hint">請輸入名稱</span>
                </div>
                <div class="form-group">
                    <button type="submit" class="accent username-submit">開始聊天</button>
                </div>
            </form>
        </div>
    </div>

    <!-- 聊天室頁面 -->
    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="chat-header">
                <h2>想食What 線上文字客服中心</h2>
            </div>
            <div class="connecting">Connecting...</div>
            <ul id="messageArea">

            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="輸入訊息..."
                            autocomplete="off" class="form-control" />
                        <button type="submit" class="primary">送出<i class="pe-7s-paper-plane"></i></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
	
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="<c:url value='/js/chat.js?version=1'/>"></script>
</body>
</html>