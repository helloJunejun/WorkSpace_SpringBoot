<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 메인페이지</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">
</head>
<body>
<a href="/"> <img src="/img/logo.png"> </a>


<!-- 실습 : 컨트롤러 구현
   회원가입 : joinForm.jsp이동
   로그인 : loginFrom.jsp이동
 -->
 
 <c:choose>
 	<c:when test="${empty memberId || empty dto}">
		<a href = "joinForm">회원가입</a>
		<a href = "loginForm">로그인</a>
 	</c:when>
 	
 	<c:otherwise>
 		<div>로그정보: ${memberId}[${dto.grade}]</div>
 		<a href = "logout">로그아웃</a>
		<a href = "myInfo">내정보조회</a>
		
		
		
 	</c:otherwise>
 </c:choose>
 
<h3>메인페이지</h3>
<c:if test="${not empty message}">
	결과메세지 : ${message}
</c:if>

</body>
</html>