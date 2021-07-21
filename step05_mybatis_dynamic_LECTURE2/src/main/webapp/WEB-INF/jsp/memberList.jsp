<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
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

<!-- 다중조건검색 조각페이지 삽입 -->
<jsp:include page="inc/multipleCondition.jsp" />

<h3>전체회원조회</h3>
<table>
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>휴대폰</th>
		<th>이메일</th>
		<th>가입일</th>
		<th>등급</th>
		<th>마일리지</th>
		<th>담당자</th>
	</tr>
	
	<!-- 검색조건에 해당하는 결과가 없는 경우 메세지 출력 -->
	<c:if test="${not empty message}">
		<tr>
			<th colspan=10>${message}</th>
		</tr>
	</c:if>
	
	<c:forEach var="dto" items="${list}" varStatus="status">
		<tr>
			<!-- status.index => 0시작, status.count => 1시작 -->
			<th>${status.count}</th>
			<th><a href="memberDetail?memberId=${dto.memberId}" title="${dto.name}님 회원상세조회">${dto.memberId}</a></th>
			<th>${fn:substring(dto.memberPw, 0, 2)}<c:forEach begin="2" end="${fn:length(dto.memberPw)}" step="1">*</c:forEach> 
			</th>
			<th>${dto.name}</th>
			<th>${dto.mobile}</th>
			<th>${dto.email}</th>
			<th>${dto.entryDate}</th>
			<th>
			${dto.grade}
			<c:choose>
				<c:when test="${dto.grade eq 'G'}">[일반회원]</c:when>
				<c:when test="${dto.grade eq 'S'}">[우수회원]</c:when>
				<c:when test="${dto.grade eq 'A'}">[관리자]</c:when>
			</c:choose>
			</th>
			<th class="align_right">
			<!-- Edge currecy 통화기호 깨짐 type="currency | number | percent", currencySymbol="$" -->
			<fmt:formatNumber type="currency" value="${dto.mileage}" />
			</th>
			<th>${dto.manager}</th>
		</tr>
	</c:forEach>
</table>
</body>
</html>