<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<!-- 부트스트랩 적용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- 폰트 적용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<!-- css 적용 -->
<link rel="stylesheet" href="css/style.css">

<meta charset="UTF-8">
<script>
function deleteSave(){
	var pass = '<c:out value="${article.pw}"/>'; //자바스크립트는 el태그사용
	if(document.deleteForm.pw.value!=pass){
		alert("비밀번호가 다릅니다");
		return false;
	}
}
</script>
<title>삭제</title>
</head>
<body  class="bg-success p-2 text-dark bg-opacity-25">
<div class= "mt-4">
<section class="text-center">
	<h1><a href="${pageContext.request.contextPath}/list.do">FILE 사전</a></h1>
	<form method = "post" name="deleteForm" action="${pageContext.request.contextPath}/deletePro.do" onsubmit="return deleteSave()">
	<input type="hidden" name="num" value = "${article.num}">
	<input type="hidden" name="fname" value = "${article.fname}">
	
		<table style= "margin: 0 auto;">
			<tr>
				<td>비밀번호: <input type="password" name="pw"> </td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="삭제" class="btn btn-outline-secondary mt-3 px-7 py-7">
				</td>
			</tr>
		</table>
</body>
</html>