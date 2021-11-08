function validate(formElem) {
    var elem = document.getElementsByClassName(formElem);

    // HTMLCollection不能用for in
    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("blur", exitInputEvent);
    }

    function exitInputEvent(event) {
        var formElemID = this.id;
        var inputVal = document.getElementById(formElemID).value;
        console.log(`"You selected ID: '${formElemID}' !"`);
        console.log(`"Input value: '${inputVal}' !"`);

        var result = validateInput(formElemID, inputVal);
        console.log(result.status, formElemID, result.message);
        setValidateStatus(result.status, formElemID, result.message);
    }

    function validateInput(inputType, val) {
        /* 使用regExp做資料驗證 */
        var msg = "錯誤";
        var isPassed = false;
        switch (inputType) {
            case "name":
                // 姓名必須全為中文
                var isAllZh = true;
                // 2中文字以上 且unicode在中文範圍 #see: https://en.wikipedia.org/wiki/CJK_Unified_Ideographs_(Unicode_block)
                let zhRegExp = /[\u4E00-\u9FFF]/;

                if (val.length < 2) {
                    msg = "至少2字元以上";
                    break;
                }
                for (let i = 0; i < val.length; i++) {
                    var char = val.charAt(i);
                    if (!zhRegExp.test(char)) {
                        isAllZh = false;
                        msg = "姓名必須全為中文";
                        break;
                    }
                }
                if (isAllZh) {
                    isPassed = true;
                    msg = "正確";
                }
                break;

            case "phone":
                let isPhone = false;
                let phoneRegExp = /[0]{1}[9]{1}[0-9]{8}/;

                if (phoneRegExp.test(val)) {
                    isPhone = true;
                    isPassed = true;
                    msg = "正確";
                    console.log("pass");
                } else {
                    msg = "不符合輸入格式(09開頭且為10碼數字)"
                }
                break;
            case "address":
                isPassed = true;
                msg = "正確";
                break;

            case "password":
            case "account":
                let isEng, isNum, isTooShort = false;
                // 在英文範圍
                let engRegExp = /[A-Za-z]/;
                // 在數字範圍
                let numRegExp = /[0-9]/;

                if (val.length < 6) {
                    isTooShort = true;
                    console.log("too short");
                    msg = "該欄位至少須6字元以上";
                    break;
                }

                for (let i = 0; i < val.length; i++) {
                    var char = val.charAt(i);

                    if (engRegExp.test(char)) {
                        isEng = true;
                        console.log("pass1");
                    } else if (numRegExp.test(char)) {
                        isNum = true;
                        console.log("pass2");
                    }
                }
                // 有任一英文＆任一數字就給過
                if (isEng && isNum) {
                    isPassed = true;
                    msg = "正確";
                    console.log("passed!");
                    break;
                } else {
                    // 沒過情況. 英數字其中一個沒有
                    if (!isEng && !isNum) {
                        msg = "須包含英數字";
                    }
                }
                break;
            case "email":
                let isEmail = false;
                // ^: 開頭須符合格式
                // $: 結尾須符合格式
                let emailRegExp = /^[A-Za-z0-9_]+\@[A-Za-z0-9]+\.[A-Za-z0-9]+$/;

                if (emailRegExp.test(val)) {
                    isEmail = true;
                    isPassed = true;
                    msg = "正確";
                    console.log("pass");
                } else {
                    msg = "請輸入正確的電子郵件格式";
                }
                break;
            case "passwordConfirm":
                if ($("#password").val() == $("#passwordConfirm").val()) {
                    isPassed = true;
                    msg = "正確";
                    console.log("pass");
                } else {
                    msg = "密碼不相符";
                }
                break;
            case "ID":
                let IDRegExp = /^[A-Z][1,2][0-9][A-Za-z0-9]/;
                if (IDRegExp.test(val.toUpperCase())) {
                    isPassed = true;
                    msg = "正確";
                    console.log("pass");
                } else {
                    msg = "身分證格式錯誤";
                }
                break;
        }
        // 驗證regexp 設訊息 設element
        return { status: isPassed, message: msg };
    }

    function setValidateStatus(status, inputType, msg) {
        var $msgElem = $("#" + inputType + "CheckMsg");

        // $("#"+inputType + "CheckMsg").children().remove();

        if (status) {
            $msgElem.removeClass("incorrect");
            $msgElem.addClass("correct");
            $msgElem.find(".fa-times-circle").remove();
            $msgElem.text(msg);
            $msgElem.append("<i class='fa far fa-check-circle'></i>");
            // imgElem.classList.toggle("fa");
            // imgElem.classList.toggle("far");
            // imgElem.classList.toggle("fa-check-circle");

        } else {
            $msgElem.removeClass("correct");
            $msgElem.addClass("incorrect");
            $msgElem.find(".fa-check-circle").remove();
            $msgElem.text(msg);
            $msgElem.append("<i class='fa far fa-times-circle'></i>");
            // imgElem.classList.toggle("fa");
            // imgElem.classList.toggle("far");
            // imgElem.classList.toggle("fa-times-circle");
        }
        console.log($msgElem.text());
    }
}



/*
status=true;
inputType="name"
msg="test"
var msgElem = document.getElementById(inputType + "CheckMsg");
var imgElem = document.createElement("i");
console.log("before: ", msgElem.children);

if (status) {
    msgElem.classList.remove("incorrect");
    msgElem.classList.add("correct");
    imgElem.classList.toggle("fa");
    imgElem.classList.toggle("far");
    imgElem.classList.toggle("fa-check-circle");

} else {
    msgElem.classList.remove("correct");
    msgElem.classList.add("incorrect");
    imgElem.classList.toggle("fa");
    imgElem.classList.toggle("far");
    imgElem.classList.toggle("fa-times-circle");
}
msgElem.appendChild(imgElem);
console.log("after: ", msgElem.children);
*/