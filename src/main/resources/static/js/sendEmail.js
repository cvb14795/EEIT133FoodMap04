
var $form = $("#form");
const CLIENT_ID = "196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com";
const DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/gmail/v1/rest"];
const SCOPE = "https://www.googleapis.com/auth/userinfo.email " +
    "https://www.googleapis.com/auth/userinfo.profile " +
    "https://www.googleapis.com/auth/gmail.send ";
const API_KEY = "AIzaSyAZ9QTUwXbcsHp5Fx_LbD-HVbPMw8uzBig";

function handleClientLoad() {
    gapi.client.setApiKey(API_KEY);
    gapi.load('client:auth2', initClient);
    window.setTimeout(checkAuth, 10);
}

function initClient() {
    console.log("initClient");
    gapi.client.init({
        clientId: CLIENT_ID,
        discoveryDocs: DISCOVERY_DOCS,
        scope: SCOPE
    })
}

function checkAuth() {
    console.log("checkAuth");
    gapi.auth.authorize({
        apiKey: API_KEY,
        client_id: CLIENT_ID,
        scope: SCOPE,
        immediate: true
    }, handleAuthResult);
}

function handleAuthResult(authResult) {
    if (authResult && !authResult.error) {
        $('#authorize-button').remove();
        console.log("Success: 使用者已登入 載入Gmail API成功");
    } else {
        console.log("Error: 使用者未登入 載入Gmail API失敗");
        $('#authorize-button').on('click', function () {
            handleAuthClick();
        });
    }
}

function handleAuthClick() {
    // gapi.auth.authorize({
    //     client_id: CLIENT_ID,
    //     scope: SCOPE,
    //     immediate: false
    // }, handleAuthResult);
    // return false;
    gapi.auth2.getAuthInstance().signIn();
}

// To: Mary Smith
// Subject: Saying Hello

//符合郵件規範的字串(含收件者、主旨、內容)
/* ==========以下為郵件內容原文=========== 
 To: cvb14795 @gmail.com
 Subject: Gmail API 郵件測試

 測試測試123
 ==========以上為郵件內容原文=========== */

// function login(params) {
//     var params = {
//         "clientid": CLIENT_ID,
//         "callback": callbackfunc,
//         "discoveryDocs": DISCOVERY_DOCS,
//         "scope": 'https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile'
//     };
//     gapi.auth.signIn(myParams);
// }
var $form = $("form");
$form.on("submit", function () {
    // gmail.users.messages.send({
    //     'userId': userId,
    //     'resource': {
    //         'raw': base64EncodedEmail
    //     }
    // });
    sendEmail();
    console.log("送出成功");
});


function sendEmail() {
    var testStr = "您好，" + document.getElementById('account').value + "！" + "\r\n" +
        "<a href='http://localhost:8080/FoodMap04/Member/ForgetPassword?method=reset'>請點擊以下連結修改您的密碼</a>";

    sendMessage(
        {
            'To': document.getElementById('email').value,
            'From': 'me',
            // 'Subject': document.getElementById('inputText').value
            'Subject': "Gmail API 郵件測試"
        },
        // content
        testStr,
        callback
    );
}

function callback() {
    console.log("callback");
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '提交成功',
        html: "請至信箱 " + $("#email").val() + " 收取驗證信！",
        // timer: 5000,
        // timerProgressBar: true,
        //預設為有確認按鈕
        // showConfirmButton: false,
    })
    // location.href = "../Home
}

function sendMessage(headers_obj, message, callback) {
    //內容
    var email = '';
    for (var header in headers_obj)
        email += header += ": " + headers_obj[header] + "\r\n";
    email += "\r\n" + message;
    console.log("email:\n " + email);
    //轉 base64
    var base64EncodedEmail = btoa(unescape(encodeURIComponent(email)));
    console.log("base64EncodedEmail:\n" + base64EncodedEmail);
    var request = gapi.client.gmail.users.messages.send({
        'userId': 'me',
        'resource': {
            'raw': base64EncodedEmail
        }
    });
    return request.execute(callback);
}

// window.callbackfunc = function callbackfunc(result) {
//     console.log("callback result: " + result);
// }

var mailStr = "VG86IGN2YjE0Nzk1QGdtYWlsLmNvbQpTdWJqZWN0OiBHbWFpbCBBUEkg6YO15Lu25ris6KmmCgrmuKzoqabmuKzoqaYxMjM=";