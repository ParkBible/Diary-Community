<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="q" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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

</style>
</head>
<body>
	<q:if test="${empty rep }">
		<h>아직 댓글이 없어요 :)</h>
	</q:if>
	
	<table id="data" class="table table-striped" style="margin:auto; table-layout:fixed; background-color: #EEFCFD">
	<q:forEach items="${rep }" var="t">
		<tr>
			<td width="20%"><h style="font-weight:bold">${t.rep_id }</h></td>
			<td>${t.rep_data }
			<q:choose>
		   		<q:when test="${idinfo eq 'admin'}">
		   			[<a href="reple_del.pknu?reple_no=${t.reple_no }&rep_no=${num }">X</a>]
		   		</q:when>
		   		<q:otherwise>
		   			<q:if test="${t.rep_id eq idinfo }">
		   			[<a href="reple_del.pknu?reple_no=${t.reple_no }&rep_no=${num }">X</a>]
		   			</q:if>
		   		</q:otherwise>
   			</q:choose>
			</td>
		</tr>
	</q:forEach>
	</table>
</body>
</html>