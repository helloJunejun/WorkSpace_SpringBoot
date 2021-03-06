<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">

</head>
<body>
	<a href="/"><img src="/img/logo.png"></a>
	<c:choose>
		<c:when test="${empty memberId}">
		<a href="joinForm">회원가입</a>
	<a href="loginForm">로그인</a>
		</c:when>
		<c:otherwise>
		<div>로그인정보 : ${memberId}[${grade}]</div>
		<a href="logout">로그아웃</a>
		<a href="myInfo">내정보조회</a>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${grade eq 'A' }">
		[관리자]<a href="memberList">전체회원조회</a>
	</c:if>

</body>
</html>