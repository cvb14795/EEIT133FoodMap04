<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
<script src='<c:url value="/js/bootstrap.js"/>'></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>

<script>
	$(function() {
		adminAuth();
	})
</script>
<title>${title}</title>
<style>
        p {
            font-size: 20px;
        }
        fieldset {
            width: 500px;
            border: 1px solid #acd6ff;
            border-radius: 20px;
            margin:0 auto;
        }
        .st1 {
            width: 450px;
            border-bottom: 1px dashed #d0d0d0;
            /* 表格間距 */
            margin: 20px;
            /* 線條與表格的距離 */
            padding-bottom: 10px;
        }

        legend {
            font-size: 24px;
            color: #0066cc;
            margin-top:20px;
            margin-left: 50px;
        }

        .t1 {
            width: 100px;
            float: left;
            text-align: right;
        }


        .sub {
            width: 450px;
            margin: 20px;
            /* 置中 */
            text-align: center;
            margin:0 auto;
        }
    </style>
    <script>
	function chk(){
		var cnt = 0;
		
		// 針對性別
		var gender = document.getElementsByName('gender');
		for (let i=0;i<gender.length;i++){
			if (gender[i].checked){
				cnt++;
			}	
		}
		
		
		//針對各選項
		var foreign = document.getElementsByName('abroad');
		for (let i=0;i<foreign.length;i++){
			if (foreign[i].checked){
				cnt++;
			}	
		}
		
		var move = document.getElementsByName('moving');
		for (let i=0;i<move.length;i++){
			if (move[i].checked){
				cnt++;
			}	
		}
		
		var family = document.getElementsByName('family');
		for (let i=0;i<family.length;i++){
			if (family[i].checked){
				cnt++;
			}	
		}

		var fever = document.getElementsByName('fever');
		for (let i=0;i<fever.length;i++){
			if (fever[i].checked){
				cnt++;
			}	
		}
		
		var vaccine = document.getElementsByName('vaccine');
		for (let i=0;i<vaccine.length;i++){
			if (vaccine[i].checked){
				cnt++;
			}	
		}
		
		if (cnt>5){
			return true
		} else {
			alert('需作答完整!');
			return false
		}	
	}
</script>
</head>
<body>
	<input type="hidden" name="isAdmin" id="isAdmin" value="${isAdmin}">
	<input type="hidden" name="userName" id="userName" value="${user}">
	<input type="hidden" name="label" id="label" value="0">

	<div class="memberDetail">
		<div class="text-right">
			會員： <span id="userNameContainer"></span>
		</div>
		<div class="text-right">
			身分： <span id="isAdminContainer"></span>
		</div>
	</div>

<form action='controller' method='post' name='send' onsubmit="return chk();">
        <fieldset>
        	       	
        	<legend>個人資料</legend>
            <div class='str1'>

                <p>姓名:
                     <label><input type='text' autofocus autocomplete='off'
						placeholder='請輸入姓名' id='name' name='name' required></label>
                </p>

                <p> 性別:
                    <label> <input type='radio' name='gender' value='男'>男 </label>
                    <label> <input type='radio' name='gender' value='女'>女 </label>
                    <label> <input type='radio' name='gender' value='其他'>其他 </label>
                </p>



                <p>身分證字號:
                    <label><input type='text' name='id' placeholder='請輸入身份證字號' maxlength='10'
                            pattern='^[A-Z]{1}[1-2]{1}[0-9]{8}$' required></label>
                </p>


				
                <p>生日:
                	<label><input type='text' name='birth' id='birthday' placeholder='輸入格式(yyyymmdd)' maxlength='8' required 
                	pattern='((\d{3}[1-9]|\d{2}[1-9]\d|\d[1-9]\d{2}|[1-9]\d{3})(((0[13578]|1[02])(0[1-9]|[12]\d|3[01]))|((0[469]|11)(0[1-9]|[12]\d|30))|(02(0[1-9]|[1]\d|2[0-8]))))|(((\d{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229)'></label>
                </p>

                <p>聯絡電話: <label><input type='text' name='phone' required></label></p>

            </div>
        </fieldset>
        <fieldset>
            <legend>接觸史調查</legend>
            <div class='str1'>
                <p>最近14天是否有出國紀錄:</p>
                <label><input type='radio' name='abroad' value='1'>是</label>
                <label><input type='radio' name='abroad' value='0'>否</label>
                <p>最近14天是否有跨縣市移動:</p>
                <label><input type='radio' name='moving' value='1'>是</label>
                <label><input type='radio' name='moving' value='0'>否</label>
                <p>同住親友是否有收到居家隔離通知單:</p>
                <label><input type='radio' name='family' value='1'>是</label>
                <label><input type='radio' name='family' value='0'>否</label>
            </div>
        </fieldset>
        <fieldset>
            <legend>身體狀況調查</legend>
            <div class='str1'>

                <p>過去 14 天是否有發燒、咳嗽或呼吸急促症狀？（已服藥者亦須勾選「是」）</p>
                <label><input type='radio' name='fever' value='1'>是 </label>
                <label><input type='radio' name='fever' value='0'>否 </label>
                <p>是否有接種過疫苗</p>
                <label><input type='radio' name='vaccine' value='1'>是 </label>
                <label><input type='radio' name='vaccine' value='0'>否 </label>


            </div>

        </fieldset>


        <div class='sub'>
            <input type='submit' value='送出'>
            <input type='reset' value='清除'>
        </div>


    </form>
</body>
</html>