<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl -->
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
<a href = "joinForm">회원가입</a>
<a href = "loginForm">로그인</a>
<a href = "memberList">전체회원조회</a>
<h3>결과창</h3>

<h3>메세지</h3>
<c:if test="${not empty message}">
	${message}
</c:if>

<c:if test="${not empty loginId}">
	${loginId}
</c:if>

</body>
</html>