<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 적용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- 폰트 적용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<!-- css 적용 -->
<link rel="stylesheet" href="css/style.css">

<script> <!--writeForm.jsp의 제약조건과 동일하나 게시판 작성시의 비밀번호와 현재 비밀번호가 다르면 false 추가-->
function writeSave(){
	var pass = '<c:out value="${article.pw}"/>'; //자바스크립트는 el태그사용
	var regexp = /^[0-9]*$/
	v = document.writeForm.pw.value;
	if( !regexp.test(v) ) {
		alert("비밀번호는 숫자만 입력하세요");
		document.writeForm.pw.focus();
		return false;
	}
	if(document.writeForm.pw.value!=pass){
		alert("비밀번호가 다릅니다");
		return false;
	}
	if(document.writeForm.id.value==""){
		alert("아이디를 입력하세요");
		document.writeForm.id.focus();
		return false;
	}
	if(document.writeForm.pw.value==""){
		alert("비밀번호를 입력하세요");
		document.writeForm.pw.focus();
		return false;
	}
	if(document.writeForm.title.value==""){
		alert("제목을 입력하세요");
		document.writeForm.title.focus();
		return false;
	}
	if(document.writeForm.content.value==""){
		alert("내용을 입력하세요");
		document.writeForm.content.focus();
		return false;
	}
	if(document.writeForm.uploadFile.value==""){
		alert("파일을 넣으세요");
		document.writeForm.uploadFile.focus();
		return false;
	}
}
</script>
<title>content</title>
</head>
<body  class="bg-success p-2 text-dark bg-opacity-25">
<div class= "mt-4">
<section class="text-center">
	<h1><a href="${pageContext.request.contextPath}/list.do">FILE 사전</a></h1>
	<form method = "post" name="writeForm" action="${pageContext.request.contextPath}/updatePro.do" enctype="multipart/form-data"  onsubmit="return writeSave()">
		<input type="hidden" name="num" value = "${article.num}">
		<input type="hidden" name="fname" value = "${article.fname}">
		
		<table style= "margin: 0 auto;">
			<tr>
				<td><input type="file" name="uploadFile"></td>
				<td>작성자: ${article.id} </td>
			</tr>
			<tr>
				<td>제목: <input type="text" name="title" value="${article.title}"> </td>
				<td>작성자 비밀번호: <input type="password" name="pw"></td>
			</tr>
			<tr>
				<td colspan="2">
				<textarea name="content" cols="40" rows="8">${article.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" class="btn btn-outline-secondary mt-3 px-7 py-7">
				</td>
			</tr>
		</table>
	</form>
</section>
</div>
</body>
</html>