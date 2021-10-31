function adminAuth() {
    // 獲取登入帳號 並顯示於該標籤
    let userName = $("#userName").val();
    $("#userNameContainer").text(userName);
    // 判斷是否具有管理員權限(它是string 不能使用if(isAdmin))
    let isAdmin = $("#isAdmin").val();
    if(isAdmin === "true"){
        $("#isAdminContainer").text("管理者");
        // 若是管理員則只顯示管理員功能 並隱藏使用者功能
        $(".userScope").hide();
        $(".adminScope").show();
    } else if ((isAdmin === "false")){
        $("#isAdminContainer").text("一般會員");
        // 若是使用者則只顯示使用者能操作的功能 並隱藏管理員功能
        $(".userScope").show();
        $(".adminScope").hide();
    }
}