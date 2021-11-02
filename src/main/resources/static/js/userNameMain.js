function userNameMain() {
    // 將所有checkbox-switch改成IOS風格
    var el = document.querySelector('.checkbox-switch');
    var nav = document.querySelector("nav");
    var mySwitch = new Switch(el, {
        size: 'small',
        checked: false,
        onChange: function () {
            // 					darkmode.toggle();
            nav.classList.toggle("navbar-default");
            nav.classList.toggle("navbar-dark");
            nav.classList.toggle("bg-dark");

            if (mySwitch.getChecked()) {
                console.log("nav:深色模式");
                $(".navbar").css("background-color", "#ffc078");
                $("ul.nav li a").css("color", "#333")
                $("ul.nav li a:hover").css("color", "#EA7500")
                $("ul.nav li a:hover").css("background-color", "#333")
                $("label[for='checkbox-switch']").text("深色模式")
            } else {
                console.log("nav:淺色模式");
                $(".navbar").css("background-color", "#333");
                $("ul.nav li a").css("color", "#ffc078");
                $("ul.nav li a:hover").css("color", "#333")
                $("ul.nav li a:hover").css("background-color", "#EA7500")
                $("label[for='checkbox-switch']").text("淺色模式")
            }
        }
    });


    // 先關閉new完後預設自動轉暗黑模式的動作
    // 			darkmode.toggle();

    // 獲取使用者資訊
    const cookies = document.cookie;
    console.log("cookies:" + cookies);
    let userName = cookies.split("user=")[1];
    if (userName === undefined) {
        console.log("使用者名稱:未定義 ");
        // 未登入狀態下(登出後)顯示登入按鈕及註冊按鈕
        $("#loginNavBtn").show();
        $("#registerNavBtn").show();
        // 未登入狀態下(登出後)隱藏登出按鈕及編輯按鈕
        $("#logoutNavBtn").hide();
        $("#editNavBtn").hide();
        // 未登入顯示為Guest
        $("#user").text("Guest");
        console.log(document.getElementById("user").outerHTML);
    } else {
        console.log(`使用者名稱:${userName}`);
        // 已登入狀態下(登入後)隱藏登入按鈕及註冊按鈕
        $("#loginNavBtn").hide();
        $("#registerNavBtn").hide();
        // 已登入狀態下(登入後)顯示登出按鈕及編輯按鈕
        $("#logoutNavBtn").show();
        $("#editNavBtn").show();
        // 登入後顯示用戶名稱
        $("#user").text(userName);
    }
}