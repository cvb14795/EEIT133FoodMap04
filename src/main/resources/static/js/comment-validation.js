// check name

function checkname() {
  let thename = document.getElementById("name").value;
  thenameLen = thename.length;
  let sp = document.getElementById("idsp");

  if (thename == "") {
    sp.innerHTML = "<img src='../images/error.png'>不可空白";
  } else if (thenameLen > 10) {
    sp.innerHTML = "<img src='../images/error.png'>不可超過10字";
  } else {
    sp.innerHTML = "<img src='../images/correct.png'>";
  }
}

// check comment area
function checkcommentarea() {
  let thecommentarea = document.getElementById("commentarea").value;
  thecommentareaLen = thecommentarea.length;
  let sp = document.getElementById("idsp3");

  if (thecommentarea == "") {
    sp.innerHTML = "<img src='../images/error.png'>不可空白";
  } else if (thecommentareaLen > 100) {
    sp.innerHTML = "<img src='../images/error.png'>不可超過100字";
  } else {
    sp.innerHTML = "<img src='../images/correct.png'>";
  }
}

function validateForm() {
  let theLengthError = [];

  // error fields
  let theErrorFields = [];

  // comment form
  let commentForm = document.forms["commentForm"];

  // check name
  let userName = commentForm["userName"].value.trim();

  let thename = document.getElementById("name").value;
  let thenameLen = thename.length;

  if (thenameLen > 10) {
    theLengthError.push("姓名 ");
  }

  if (userName == "") {
    theErrorFields.push("姓名 ");
  }

  // check score
  let score = commentForm["score"].value.trim();
  if (score == "") {
    theErrorFields.push("評分 ");
  }

  // check comment area
  let userComment = commentForm["userComment"].value.trim();
  let thecommentarea = document.getElementById("commentarea").value;
  let thecommentareaLen = thecommentarea.length;

  if (thecommentareaLen > 100) {
    theLengthError.push("評論區 ");
  }

  if (userComment == "") {
    theErrorFields.push("評論區 ");
  }

  if (theErrorFields.length > 0) {
    alert("表單未完成：" + theErrorFields + " 不可空白");
    return false;
  } else if (theLengthError.length > 0) {
    alert("請檢查以下區塊：" + theLengthError + "格式不正確");
    return false;
  }
}
