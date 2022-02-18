<%@ page contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

<%  
	// 세션 정보를 서블릿으로 획득
	request.setCharacterEncoding("UTF-8");
	String res = (String)session.getAttribute("CAPTCHAres");
%>


function joinCheck(){
	if("<%= res %>" != "success"){
		alert("CAPTCHA 인증을 완료해주세요.");
		return document.getElementById("captcha").focus();
	}
	if(document.getElementById("id").value == ""){
		alert("아이디를 입력해주세요.")
		return document.getElementById("id").focus();
	}
	if(document.getElementById("password").value == ""){
		alert("비밀번호를 입력해주세요.")
		return document.getElementById("password").focus();
	}
	if(document.getElementById("phonenum").value == ""){
		alert("휴대폰 번호를 입력해주세요.")
		return document.getElementById("phonenum").focus();
	}
	if(isNaN(parseInt(document.getElementById("phonenum").value)) == true){
		alert("휴대폰 번호 형식이 잘못되었습니다.")
		return document.getElementById("phonenum").focus();
	}
	if(document.getElementById("phonenum").value.length != 11){
		alert("휴대폰 번호 형식이 잘못되었습니다.")
		return document.getElementById("phonenum").focus();
	}
	if(document.getElementById("id").value.search(/\s/) != -1){
		alert("아이디에 공백이 포함될 수 없습니다.")
		return document.getElementById("id").focus();
	}
	if(document.getElementById("password").value.search(/\s/) != -1){
		alert("비밀번호에 공백이 포함될 수 없습니다.")
		return document.getElementById("password").focus();
	}
	if(document.getElementById("password").value != document.getElementById("pwcheck").value){
		alert("비밀번호와 비밀번호 확인 입력이 서로 다릅니다.")
		return document.getElementById("password").focus();
	}
	
	
	document.joinform.submit();
}

</script>

<style>
@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
}
	
* {
	 font-family: 'Pretendard-Regular';
	 font-size: 16px;
	 font-weight: 300;
	 margin: 0;
}
 html, body {
	 height: 100vh;
	 width: 100vw;
	 margin: 0 0;
	 display: flex;
	 align-items: flex-start;
	 justify-content: flex-start;
	 background: #f3f2f2;
}
 h4 {
	 font-size: 24px;
	 font-weight: 600;
	 color: #000;
	 opacity: 0.85;
}
 label {
	 font-size: 12.5px;
	 color: #000;
	 opacity: 0.8;
	 font-weight: 400;
}
 form {
	 padding: 40px 30px;
	 background: #fefefe;
	 display: flex;
	 flex-direction: column;
	 align-items: flex-start;
	 padding-bottom: 20px;
	 width: 300px;
}
 form h4 {
	 margin-bottom: 20px;
	 color: rgba(0, 0, 0, .5);
}
 form h4 span {
 	font-size: 40px;
	 color: rgba(0, 0, 0, 1);
	 font-weight: 700;
}
 form p {
	 line-height: 155%;
	 margin-bottom: 5px;
	 font-size: 14px;
	 color: #000;
	 opacity: 0.65;
	 font-weight: 400;
	 max-width: 200px;
	 margin-bottom: 40px;
}
 a.discrete {
	 color: rgba(0, 0, 0, .4);
	 font-size: 14px;
	 border-bottom: solid 1px rgba(0, 0, 0, .0);
	 padding-bottom: 4px;
	 margin-left: auto;
	 font-weight: 300;
	 transition: all 0.3s ease;
	 margin-top: 40px;
}
 a.discrete:hover {
	 border-bottom: solid 1px rgba(0, 0, 0, .2);
}
 button {
	 -webkit-appearance: none;
	 width: auto;
	 min-width: 100px;
	 border-radius: 24px;
	 text-align: center;
	 padding: 15px 40px;
	 margin-top: 5px;
	 background-color: #83cdcb;
	 color: #fff;
	 font-size: 14px;
	 margin-left: auto;
	 font-weight: 500;
	 box-shadow: 0px 2px 6px -1px rgba(0, 0, 0, .13);
	 border: none;
	 transition: all 0.3s ease;
	 outline: 0;
}
 button:hover {
	 transform: translateY(-3px);
	 box-shadow: 0 2px 6px -1px rgba(182, 157, 230, .65);
}
 button:hover:active {
	 transform: scale(0.99);
}
 input {
	 font-size: 16px;
	 padding: 20px 0px;
	 height: 56px;
	 border: none;
	 border-bottom: solid 1px rgba(0, 0, 0, .1);
	 background: #fff;
	 width: 280px;
	 box-sizing: border-box;
	 transition: all 0.3s linear;
	 color: #000;
	 font-weight: 400;
	 -webkit-appearance: none;
}
 input:focus {
	 border-bottom: solid 1px #83cdcb;
	 outline: 0;
	 box-shadow: 0 2px 6px -8px rgba(182, 157, 230, .45);
}
 .floating-label {
	 position: relative;
	 margin-bottom: 10px;
	 width: 100%;
}
 .floating-label label {
	 position: absolute;
	 top: calc(50% - 7px);
	 left: 0;
	 opacity: 0;
	 transition: all 0.3s ease;
	 padding-left: 44px;
}
 .floating-label input {
	 width: calc(100% - 44px);
	 margin-left: auto;
	 display: flex;
}
 .floating-label .icon {
	 position: absolute;
	 top: 0;
	 left: 0;
	 height: 56px;
	 width: 44px;
	 display: flex;
}
 .floating-label .icon svg {
	 height: 30px;
	 width: 30px;
	 margin: auto;
	 opacity: 0.15;
	 transition: all 0.3s ease;
}
 .floating-label .icon svg path {
	 transition: all 0.3s ease;
}
 .floating-label input:not(:placeholder-shown) {
	 padding: 28px 0px 12px 0px;
}
 .floating-label input:not(:placeholder-shown) + label {
	 transform: translateY(-10px);
	 opacity: 0.7;
}
 .floating-label input:valid:not(:placeholder-shown) + label + .icon svg {
	 opacity: 1;
}
 .floating-label input:valid:not(:placeholder-shown) + label + .icon svg path {
	 fill: #83cdcb;
}
 .floating-label input:not(:valid):not(:focus) + label + .icon {
	 animation-name: shake-shake;
	 animation-duration: 0.3s;
}
 @keyframes shake-shake {
	 0% {
		 transform: translateX(-3px);
	}
	 20% {
		 transform: translateX(3px);
	}
	 40% {
		 transform: translateX(-3px);
	}
	 60% {
		 transform: translateX(3px);
	}
	 80% {
		 transform: translateX(-3px);
	}
	 100% {
		 transform: translateX(0px);
	}
}
 .session {
	 display: flex;
	 flex-direction: row;
	 width: auto;
	 height: auto;
	 margin: auto;
	 background: #fff;
	 border-radius: 4px;
	 box-shadow: 0px 2px 6px -1px rgba(0, 0, 0, .12);
}
 

</style>
</head>
<body>
		  <div class="session">
		  		<q:if test="${CAPTCHAres ne 'success' }">
				      <form method="POST" action="test910">
				      		<img src="image.jsp?fname=${fname }"/>
							<input type="hidden" name="key" value="${key }"/>
							<input placeholder="CAPTCHA 문자를 입력해주세요" type="text" name="captcha" id="captcha"/>
							<input type="submit"/>
				      </form>
				</q:if>
			    <form action="add3.pknu" method="POST" class="log-in" name="joinform" autocomplete="off"> 
			      <h4>오늘 <span>회원가입</span> 어때?</h4>
			      <p>함께하게 되어 기쁩니다!</p>
			      <div class="floating-label">
			        <input placeholder="아이디" type="text" name="id" id="id" autocomplete="off">
			        <label for="id">아이디:</label>
			      </div>
			      <div class="floating-label">
			        <input placeholder="비밀번호" type="password" name="pw" id="password" autocomplete="off">
			        <label for="password">비밀번호:</label>
			        <div class="icon">
			          <?xml version="1.0" encoding="UTF-8"?>
			          <svg enable-background="new 0 0 24 24" version="1.1" viewBox="0 0 24 24" xml:space="preserve"  xmlns="http://www.w3.org/2000/svg">
					<style type="text/css">
					  .st0{fill:none;}
					  .st1{fill:#010101;}
					</style>
					    <rect class="st0" width="24" height="24"/>
					    <path class="st1" d="M19,21H5V9h14V21z M6,20h12V10H6V20z"/>
					    <path class="st1" d="M16.5,10h-1V7c0-1.9-1.6-3.5-3.5-3.5S8.5,5.1,8.5,7v3h-1V7c0-2.5,2-4.5,4.5-4.5s4.5,2,4.5,4.5V10z"/>
					    <path class="st1" d="m12 16.5c-0.8 0-1.5-0.7-1.5-1.5s0.7-1.5 1.5-1.5 1.5 0.7 1.5 1.5-0.7 1.5-1.5 1.5zm0-2c-0.3 0-0.5 0.2-0.5 0.5s0.2 0.5 0.5 0.5 0.5-0.2 0.5-0.5-0.2-0.5-0.5-0.5z"/>
					</svg>
			        </div>
			      </div>
			      <div class="floating-label">
			        <input placeholder="비밀번호 확인" type="password" id="pwcheck" autocomplete="off">
			        <label for="password">비밀번호 확인:</label>
			      </div>
			      
			      <div class="floating-label">
			        <input placeholder="휴대폰 번호('-'은 제외합니다)" type="text" name="phone" id="phonenum" autocomplete="off">
			        <br/>
			        <label for="id">휴대폰 번호:</label>
			      </div>
			      <button type="button" value="check" onclick="joinCheck()">회원가입</button>
					
			    </form>
		 </div>
</body>

</html>