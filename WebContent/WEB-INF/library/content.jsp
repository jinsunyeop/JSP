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

<title>content</title>
</head>
<body  class="bg-success p-2 text-dark bg-opacity-25">
<div class= "mt-4">
<section class="text-center">
	<h1><a href="${pageContext.request.contextPath}/list.do">FILE 사전</a></h1>
		<table style= "margin: 0 auto;">
			<tr>
				<td>
				<a href ="${pageContext.request.contextPath}/download.do?fileName=${article.fname}"> <!-- 파일명을 파라미터로 가지고 download.do로  -->
				${article.fname}</a>
				</td>
				<td>작성자: ${article.id} </td>
			</tr>
			<tr>
				<td colspan="2">제목: ${article.title} </td>
			</tr>
			<tr>
				<td colspan="2">
				<p>${article.content}</p>
				</td>
			</tr>
			<button class="btn btn-outline-secondary mt-3 mx-3 px-7 py-7">
				<a href="${pageContext.request.contextPath}/list.do">목록</a>
			</button>
			<button class="btn btn-outline-secondary mt-3 mx-3 px-7 py-7">
				<a href="${pageContext.request.contextPath}/update.do?num=${article.num}">수정</a>
			</button>
			<button class="btn btn-outline-secondary mt-3 mx-3 px-7 py-7">
				<a href="${pageContext.request.contextPath}/delete.do?num=${article.num}">삭제</a>
			</button>
			
			
		</table>
</section>
</div>
</body>
</html>