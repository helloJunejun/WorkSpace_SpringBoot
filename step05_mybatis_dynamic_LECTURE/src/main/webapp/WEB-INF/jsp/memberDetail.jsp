<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세조회</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">
</head>
<body>
<a href="/"><img src="/img/logo.png"></a>

<c:choose>
	<c:when test="${empty memberId || empty dto}">
		<a href="joinForm">회원가입</a>
		<a href="loginForm">로그인</a>
	</c:when>
	
	<c:otherwise>
		<div>로그정보: ${memberId}[${dto.grade}]</div>
		<a href="logout">로그아웃</a>
		<a href="myInfo">내정보조회</a>
		
		<!-- 관리자 권한 전체회원조회 서비스 제공 -->
		<c:if test="${grade == 'A'}">
			[관리자] <a href="memberList">전체회원조회</a>
		</c:if>
		
	</c:otherwise>
</c:choose>

<h3>회원상세조회</h3>
<table>
	<tr>
		<th>아이디</th>
		<td>${dto.memberId}</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>${fn:substring(dto.memberPw, 0, 2)}<c:forEach begin="2" end="${fn:length(dto.memberPw)}" step="1">*</c:forEach></td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td>${dto.mobile}</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${dto.email}</td>
	</tr>
	<tr>
		<th>가입일</th>
		<td>${dto.entryDate}</td>
	</tr>
	<tr>
		<th>등급</th>
		<td>
		${dto.grade}
		<c:choose>
			<c:when test="${dto.grade eq 'G'}">[일반회원]</c:when>
			<c:when test="${dto.grade eq 'S'}">[우수회원]</c:when>
			<c:when test="${dto.grade eq 'A'}">[관리자]</c:when>
		</c:choose>
		</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td><fmt:formatNumber type="currency" value="${dto.mileage}" /></td>
	</tr>
	<tr>
		<th>담당자</th>
		<td>
			<c:choose>
				<c:when test="${not empty dto.manager}">${dto.manager}</c:when>
				<c:otherwise>미배정</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
</body>
</html>