<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>

function writeCheck(){
	if(document.getElementById("write").value == ""){
		alert("내용을 입력해주세요.");
		return document.getElementById("write").focus();
	}
	
	document.writeform.submit();
}


$(document).ready(function(){
	$("#input_check").change(function(){
	    if($("#input_check").is(":checked")){
	    	document.getElementById("input_check_hidden").disabled = true;
	    }else{
	    	document.getElementById("input_check_hidden").disabled = false;
	    }
	});
	
	// pagnation 기능
	$('#data').after('<div id="nav"></div>');
    var rowsShown = 15;
    var rowsTotal = $('#data tbody tr').length;
    var numPages = rowsTotal/rowsShown;
    for(i = 0;i < numPages;i++) {
        var pageNum = i + 1;
        $('#nav').append('<a href="#" rel="'+i+'">'+pageNum+'</a> ');
    }
    $('#data tbody tr').hide();
    $('#data tbody tr').slice(0, rowsShown).show();
    $('#nav a:first').addClass('active');
    $('#nav a').bind('click', function(){

        $('#nav a').removeClass('active');
        $(this).addClass('active');
        var currPage = $(this).attr('rel');
        var startItem = currPage * rowsShown;
        var endItem = startItem + rowsShown;
        $('#data tbody tr').css('opacity','0.0').hide().slice(startItem, endItem).
        css('display','table-row').animate({opacity:1}, 300);
    });
	
	// 더보기 기능
    $('.box').each(function(){
        var content = $(this).children('.textover-cut');
        var content_txt = content.text();
        var content_txt_short = content_txt.substring(0,29)+"...";
        var btn_more = $('<a id="more" style="text-decoration: none" href="javascript:void(0)">▼</a>');

        // box에 ▼ 더보기 버튼 추가(새로운 html 요소 추가)
        $(this).append(btn_more);
        
        // 20글자가 넘을 때 content_txt_short로 교체
        if(content_txt.length >= 20){
            content.html(content_txt_short)
        // 넘지 않으면 더보기 버튼을 가림
        }else{
            btn_more.hide()
        }
        
        btn_more.click(toggle_content);

        function toggle_content(){
            if($(this).hasClass('short')){
                // 접기 상태
                $(this).html('▼');    // this 안의 html 요소(btn_more)
                content.html(content_txt_short);
                $(this).removeClass('short');
            }else{
                // 더보기 상태 -> box에 short 클래스를 추가함.
                $(this).html('▲');
                content.html(content_txt);
                $(this).addClass('short');
            }
        }
    });

    
 // 댓글 기능
    $('.box').each(function(){
    	var reple_able = $(this).children('#reple-able').text();
    	var content_no = $(this).siblings('#t-no').text();
    	var btn_reple = $('<a class="jejugothic" style="text-decoration: none; color:black;" target="test' + content_no + '" href="reple.pknu?rep_no=' + content_no + '&idinfo=${idInfo}"> [댓글]</a>');
    	//var btn_reple = $('<a href="javascript:void(0)">[댓글]</a>');
    	if(reple_able == "Y"){
    		$(this).append(btn_reple);
    		$(this).append($('<span id="table_div" display="none"></span>'));
    		$(this).children('#table_div').append($('<iframe style="height:0;width:0;border:0;border:none" id="test" name="test' + content_no + '" src="" scrolling="yes"></iframe>'));
    		$(this).append($('<form id="rep" method="POST" action="add4.pknu"></form>'));
    	}
    	
    	var reple_textbox = $('<textarea name="rep_data" rows="2" style="width:90%" placeholder="댓글을 써주세요(최대 500자)"></textarea><br/>');
    	var reple_button = $('<button class="btn btn-dark" type="submit">등록</button>');
    	btn_reple.click(reple_page);
    	
    	// $(this)는 a태그. $(this)의 형제 태그인 form 태그에 html 요소를 추가한다.
    	function reple_page(){
    		// 버튼 두번 눌렀을때.
    		if($(this).hasClass('reple_short')){
    			$(this).siblings('#rep').html('');    // h 태그 안의 내용을 없애버린다.
    			$(this).removeClass('reple_short');
    			$(this).siblings('#table_div').children('#test').attr("style", "height:0;width:0;border:0;border:none");
    		}else{
    			// 버튼 한번 눌렀을때.
    			// 댓글 목록 출력
    			$(this).siblings('#table_div').children('#test').attr("style", "width:90%");
    			
    			if("${idInfo}" == ""){
    				$(this).siblings('#rep').append($('<h>댓글을 쓰려면 로그인해주세요.</h>'));
    				$(this).addClass('reple_short');
    			}
    			else{
    				// 댓글 작성 및 등록
        			$(this).siblings('#rep').append($('<input type="hidden" name="rep_id" value="${idInfo}">'));
        			$(this).siblings('#rep').append($('<input type="hidden" name="rep_no" value="' + content_no + '">'));
               		$(this).siblings('#rep').append(reple_textbox);
               		$(this).siblings('#rep').append(reple_button);
                    $(this).addClass('reple_short');
    			}
    			
            }
    	}
    });
    
	/*
    $("#selector>a").on("click", function(){
    	
    	location.href = './list.pknu?type=4';
    		
    })
    */

    	
    // 타이틀 이미지 변경
    var type = document.location.href.split("?")[1];
	if(type == "type=4"){
		$("#header").css({
			"background-image":"url('${pageContext.request.contextPath}/happy.jpg')"
		})
	}
	else if(type == "type=5"){
		$("#header").css({
			"background-image":"url('${pageContext.request.contextPath}/sad.jpg')"
		})
	}
	else if(type == "type=6"){
		$("#header").css({
			"background-image":"url('${pageContext.request.contextPath}/angry.jpg')"
		})
	}
	else if(type == "type=7"){
		$("#header").css({
			"background-image":"url('${pageContext.request.contextPath}/worry.jpg')"
		})
	}
	else if(type == "type=8"){
		$("#header").css({
			"background-image":"url('${pageContext.request.contextPath}/etc.jpg')"
		})
	}
	
	// 추천시 list.pknu로 넘어가지 않게 type 파라미터 전달
	if(document.location.href.indexOf("?") == -1){
		var type_num = 0;
	}
	else{
		var type_num = document.location.href.split("?")[1].split("=")[1];
	}
	
	$('.tiup-a').on("click", function(){
		$(this).children("#up").attr("href", $(this).children("#up").attr("href") + "&type_num=" + type_num);
	})
	
	// 시간 '분' 뒷부분 자르기
	$('.box').each(function(){
		var time = $(this).children('#time');
		var time_txt = time.text();
		var time_txt_short = time_txt.split(":")[0] + ":" + time_txt.split(":")[1];
		time.html(time_txt_short); 
	});
	
	// 삭제 확인메시지
	$('.delEvent').on("click", function(e){
		if(confirm("글을 삭제하시겠습니까?") == true){
			return;
		}
		else{
			e.preventDefault();
		}
	});

	
	
});


</script>
<style>
	@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	@font-face {
    font-family: 'Pretendard-Regular';
    src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    font-weight: 400;
    font-style: normal;
    }

	@font-face {
	    font-family: 'GowunDodum-Regular';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
	    font-weight: normal;
	    font-style: normal;
	}
	
	*{
		font-family: 'Pretendard-Regular';
	}

	.jejugothic { /** 폰트 영문 이름 지정 **/
	 	font-family: 'Jeju Gothic', sans-serif !important;
	 	font-size: 14px;
		}
	
        .footer{
            width : 100%;
            height : 20%;
            background-color : #83CDCB;
        }
        
	
	@media ( max-width :1230px){
		*{
			font-size: 14px;
		}
		body{
	        margin : 20px auto;
            width : 95%;
            text-align : center;
        }
		.body{
            height : 100%;
        }
        .header{
            width : 100%;
            height : 30%;
            background-color : #99D1F7;
            background-image : url("${pageContext.request.contextPath}/basic.png") !important;
            background-size : 100%
        }
        nav a {
        	margin: 0 5px !important;
        }
        
        .content{
            width : 100%;
            background-color: #cef5f9;
        }
        .leftSideBar{
            width : 100%;
            background-color: #bae1ca;
        }
        .table{
        	width: 95%; 
        }
        .table table-striped{
        	width: 95%;
        }
        .tiup{
        	width: 10% !important;
        }
        .tidel{
        	width: 10% !important;
        }
	}
	
	@media ( min-width :1231px){
        .body{
            display : flex;    /* 모바일 환경에서는 flex 없애주기 */
            height : 100%;
        }
        body{
	        margin : 20px auto;
            width : 80%;
            text-align : center;
        }
        .header{
            width : 100%;
            height : 30%;
            background-color : #99D1F7;
            background-image : url("${pageContext.request.contextPath}/cloud.png");
            background-size : 100%
        }
        .content{
            width : 80%;
            background-color: #cef5f9;
        }
        .leftSideBar{
            width : 30%;
            background-color: #bae1ca;
        }
        .table{
        	width: 90%; 
        }
        .table table-striped{
        	width: 90%;
        }
        
	}
	.reples{
		font-family: 'Jeju Gothic', sans-serif;
	}
	
	.write{
		border: 1px solid #dee2e6;
		width: 95%;
        margin: auto;
        padding: 5px;
        border-radius : 10px;
        background-color: white;
	}
	
	/* 메뉴바 스타일 시작*/
	@import url('https://fonts.googleapis.com/css?family=Oswald:500');
	 nav {
		 width: 100%;
		 top: 50px;
		 text-align: center;
	}
	 nav a {
	 	 font-family: 'GowunDodum-Regular';
		 font-weight: 500;
		 text-transform: uppercase;
		 text-decoration: none !important;
		 color: #28597b;
		 margin: 0 15px;
		 font-size: 16px;
		 letter-spacing: 1px;
		 position: relative;
		 display: inline-block;
	}
	 nav a:before {
		 content: '';
		 position: absolute;
		 width: 10%;
		 height: 2px;
		 background: #28597b;
		 top: 90%;
		 animation: out 0.2s cubic-bezier(1, 0, 0.58, 0.97) 1 both;
	}
	 nav a:hover:before {
		 animation: in 0.2s cubic-bezier(1, 0, 0.58, 0.97) 1 both;
	}
	nav a:hover{
		color: #28597b;
	}
	 @keyframes in {
		 0% {
			 width: 0;
			 left: 0;
			 right: auto;
		}
		 100% {
			 left: 0;
			 right: auto;
			 width: 100%;
		}
	}
	 @keyframes out {
		 0% {
			 width: 100%;
			 left: auto;
			 right: 0;
		}
		 100% {
			 width: 0;
			 left: auto;
			 right: 0;
		}
	}
	 @keyframes show {
		 0% {
			 opacity: 0;
			 transform: translateY(-10px);
		}
		 100% {
			 opacity: 1;
			 transform: translateY(0);
		}
	}
	 nav a:nth-child(1) {
		 animation: show 0.2s 0.6s ease 1 both;
	}
	 nav a:nth-child(2) {
		 animation: show 0.2s 0.7s ease 1 both;
	}
	 nav a:nth-child(3) {
		 animation: show 0.2s 0.8s ease 1 both;
	}
	 nav a:nth-child(4) {
		 animation: show 0.2s 0.9s ease 1 both;
	}
	 nav a:nth-child(5) {
		 animation: show 0.2s 1s ease 1 both;
	}
	/* 메뉴바 스타일 끝*/
	
	/* footer 툴팁 설정 */

	.tooltip1 {
	  position: relative;
	}
	
	.tooltip1 .tooltip-text1 {
	  visibility: hidden;
	  background-color: black;
	  color: #fff;
	  border-radius: 6px;
	  position: absolute;
	  padding: 5px 0;
	  font-size: 13px;
	}
	
	.tooltip1:hover .tooltip-text1 {
	  visibility: visible;
	}
	
	/* 툴팁 기본 스타일 설정 끝 */
	
	/* -------------------------- */
	
	/* 툴팁 화살표 기본 스타일 설정 시작 */
	
	.tooltip1 .tooltip-text1::after {
	  content: " ";
	  position: absolute;
	  border-style: solid;
	  border-width: 5px;
	}
	
	/* 툴팁 화살표 기본 스타일 설정 끝 */
	
	/* -------------------------- */
	
	/* 툴팁 방향 설정 시작 */
	
	.tooltip1 .tooltip-top {
	  width: 120px;
	  bottom: 150%;
	  left: 50%;
	  margin-left: -60px;
	}
	
	.tooltip1 .tooltip-top::after {
	  top: 100%;
	  left: 50%;
	  margin-left: -5px;
	  border-color: black transparent transparent transparent;
	}
	
	#more:link{
		color:black
	}
	
	#more:hover {
		color:black
	}
	
	a{
		text-decoration: none !important;
	}
	
    </style>
</head>
<body>
    <div class="header" id="header" style="height: 300px"><br/> <!-- Header영역  -->
    	<img src="${pageContext.request.contextPath}/logo1.png" width="200"; height="200"; style="text-align: left;"/> 
    	<br/><br/>
    	
    	<nav1 class="navbar navbar-light" style="background-color: #e3f2fd; opacity:0.8;">
    		<nav id="selector">
			  <a href="list.pknu?type=4">기쁨/감동</a>
			  <a href="list.pknu?type=5" onclick="titleImage('sad')">슬픔/지침</a>
			  <a href="list.pknu?type=6" onclick="titleImage('angry')">화남/짜증</a>
			  <a href="list.pknu?type=7" onclick="titleImage('worry')">고민/갈등</a>
			  <a href="list.pknu?type=8" onclick="titleImage('etc')">기타</a>
			</nav>
		</nav1>
    	</div>
    <div class="body">
        <div class="leftSideBar"><!-- LeftSideBar영역  -->
	        <div class="info">
	        	<q:if test="${!empty idInfo }">
	        		<br/><img src="${pageContext.request.contextPath}/user-icon.png" width="40px"; height="40px"; style="text-align: left;"/> <br/>
	        		<b>${idInfo }</b>님<br/>
	        		로그인되었습니다<br/>
	        		<button class="btn btn-light" onclick="location.href='./logout.pknu'">로그아웃</button><br/>
	        		<a style="text-decoration: none" href="list.pknu?type=2&id=${idInfo }">내글보기</a><br/>
	        		<hr/>
	        		<div class="write">
				    	<form name=writeform method="POST" action="add2.pknu">
				    		<input type="hidden" name="id" value="${idInfo }"/>
				    		<img src="${pageContext.request.contextPath}/pen.png" width="20px"; height="20px"; style="text-align: left;"/> <br/>
				        	<b style="font-size:20px">글쓰기</b><br/>
				        	<b>오늘 기분</b>은 어때요?<br/>
				        	<select name="emotion" size="1">
				        		<option value="기쁨/감동">기쁨/감동</option>
				        		<option value="슬픔/지침">슬픔/지침</option>
				        		<option value="화남/짜증">화남/짜증</option>
				        		<option value="고민/갈등">고민/갈등</option>
				        		<option value="기타">기타</option>
				        	</select><br/>
				            <br/> <b>무슨 일</b>이 있었나요?(최대 1000자)
				            <textarea id="write" rows="10" style="width:90%" name="data" placeholder="오늘 하루를 기록해주세요"></textarea>
				            <br/> <input id="input_check" type="checkbox" name="reple" value="Y" checked> 댓글 허용
				            <input id="input_check_hidden" type="hidden" name="reple" value="N" disabled>
				            <br/> <button class="btn btn-dark" type="button" onclick="writeCheck()" value="등록">등록</button>
				    	</form>
	        		</div>
	        	</q:if>
	        	<q:if test="${empty idInfo }">
	        		<br/>로그인해주세요<br/>
	        		<button onclick="location.href='./login.pknu'" class="btn btn-dark">로그인</button>
	        		<button onclick="location.href='./remove.pknu'" class="btn btn-light">회원가입</button>
	        	</q:if>
	        </div>
			<br/><br/>
			
        </div>
        
        <div class="content"><!-- Body영역 --><br/>
		    <div class="frame">
				<a href="list.pknu?type=3">오래된순</a> │ <a href="list.pknu?type=0">최신순</a> │ <a href="list.pknu?type=1">인기글</a>
				<table class="table" style="margin:auto; table-layout:fixed; background-color: #EEFCFD">
				<th class="ti" style="width: 10%">번호</th>
		            <q:if test="${idInfo eq 'admin' }">
		            	<th class="ti" style="width: 15%">ID</th>
		            </q:if>
		            <th class="ti" style="width: 15%">기분</th>
		            <th class="ti" style="width: 50%">내용</th>
		            <th class="tiup" style="width: 7%">공감</th>
		            <th class="tidel" style="width: 7%">삭제</th>
				</table>
		        <table id="data" class="table table-striped" style="margin:auto; table-layout:fixed; background-color: #EEFCFD">
		            <q:forEach items="${list }" var="t">
		                <tr>
		                    <td id="t-no" style="width: 10%">${t.no }</td>
		                    <q:if test="${idInfo eq 'admin' }">
		            		<td style="width: 15%">${t.id }</td>
		            	</q:if>
		                    <td style="width: 15%">${t.emotion }</td>
		                    <td class="box" style="width: 50%">
		                    	<div class="textover-cut">${t.data }</div>
		                    	<h id="time" style="color: gray; font-size: 14px;">${t.time }</h><h id="reple-able" style="display:none" >${t.reple }</h>
		                    </td>
		                    <td class="tiup-a" style="width: 7%">${t.up } <a id="up" href="up.pknu?id=${idInfo}&no=${t.no }"><img src="${pageContext.request.contextPath}/like.png" width="15px"; height="15px"; style="text-align: left;"/></a></td>
		                    <td class="tidel-a" style="width: 7%">
		                    	<!-- admin 계정은 모든 글 삭제 가능 -->
		                    	<q:choose>
		                    		<q:when test="${idInfo eq 'admin'}">
		                    			<a class="delEvent" href="del2.pknu?no=${t.no }"><img src="${pageContext.request.contextPath}/trash.png" width="20px"; height="20px"; style="text-align: left;"/></a>
		                    		</q:when>
		                    		<q:otherwise>
		                    			<q:if test="${ t.id eq idInfo }">
		                    				<a class="delEvent" href="del2.pknu?no=${t.no }"><img src="${pageContext.request.contextPath}/trash.png" width="20px"; height="20px"; style="text-align: left;"/></a>
		                    			</q:if>
		                    		</q:otherwise>
		                    	
		                    	</q:choose>
		                    	
		                    </td>
		                </tr>
		            </q:forEach>
		        </table>
		        <br/>
		
		    </div>
        
        </div>
	</div>
	

    <div class="footer">
	  <h class="tooltip1">
	  	<span class="tooltip-text1 tooltip-top">구성원 : 박성경<br/>(개인 팀)</span>
	  	<b>제작팀명</b> 
	  </h>
	  <h class="tooltip1">
	  	<span class="tooltip-text1 tooltip-top">뜻 : 정보통신공학과<br/> 약자</span>
	  	ice
	  </h>
    	│ <b>Email</b> p8yft8903@gmail.com <br/>
    	Since 2022.02.10 │ 멀티캠퍼스X부경대학교 │ <b>도움</b>
    	<h class="tooltip1">
	  	<span class="tooltip-text1 tooltip-top">아이디어, 디자인 도움</span>
	  	박지민
	  </h>
    </div>
</body>
</html>