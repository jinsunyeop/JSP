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

<title>파일을 한번 올려보자</title>
</head>
<body  class="bg-success p-2 text-dark bg-opacity-25">
<div class= "mt-4">
<section class="text-center">
	<h1><a href="${pageContext.request.contextPath}/list.do">FILE 사전</a></h1> <!-- list.jsp로 가는 링크 -->
	
	<c:if test="${writecount==0}">	<!--ListAction에서 req.setAttribute를 통해 가져온 writecount(글 개수가 0이라면)-->
	<table style= "margin: 0 auto;">
		<tr><td>아무런 글이 없다</td></tr>
		<tr><td><a href="${pageContext.request.contextPath}/write.do">글쓰기</a></td></tr>
	</table>
	</c:if>
	
	
	
	<c:if test="${writecount>0}"> <!--ListAction에서 req.setAttribute를 통해 가져온 writecount > 0(글이 있다면) -->
	<table style= "margin: 0 auto;  border: 1px solid;" class="mt-4">
		<tr>
			<th>번 호</th>
			<th>제 목</th>
			<th>파일명</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성시간</th>
		</tr>
		<c:forEach var="article" items="${articleList}"> <!-- req.setAttribute를 통해 가져온 articleList에 Dto를 반복문으로 뽑아서 보여줌 -->
		<tr>
			<td>${article.num}</td>
			<td>${article.title}</td>
			<td><a href= "${pageContext.request.contextPath}/content.do?num=${article.num}">${article.fname}</a></td> <!--파일명을 누르면 그 객체의 num을 파라미터로 가지고 content.jsp로 감 -->
			<td>${article.id}</td>
			<td>${article.count}</td>
			<td>${article.date}</td>
		</tr>
		
		</c:forEach>
		
		<button class="btn btn-outline-secondary mt-3 px-7 py-7">
			<a href="${pageContext.request.contextPath}/write.do">글쓰기</a> <!-- 글쓰기 버튼을 누르면 writeForm.jsp로 감 -->
		</button>
		
		<div class= "mt-4">
		<c:forEach var="p" begin="1" end="${page}">
		<a href ="${pageContext.request.contextPath}/list.do?pageNum=${p}">[${p}]</a>
		</c:forEach>
		</div>
		

		
	</table>
	</c:if>
	
</section>
</div>
</body>
</html>