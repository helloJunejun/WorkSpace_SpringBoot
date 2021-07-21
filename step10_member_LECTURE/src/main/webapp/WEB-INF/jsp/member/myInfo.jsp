<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibMenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체회원조회</title>
<link type="text/css" rel="stylesheet" href="/resource/css/common.css">
</head>
<body>
<jsp:include page="../inc/logoMenu.jsp" />
<jsp:include page="../inc/mainMenu.jsp" />


<h3>내 정보 조회</h3>
<table>
	<tr>
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
	

	
		<tr>
			<td>${dto.memberId}</td>
			<td>${dto.memberPw}</td>
			<td>${dto.name}</td>
			<td>${dto.mobile}</td>
			<td>${dto.email}</td>
			<td>${dto.entryDate}</td>
			<td>${dto.grade}</td>
			<td>${dto.mileage}</td>
			<td>${dto.manager}</td>
		</tr>

</table>

<jsp:include page="../inc/footerMenu.jsp" />
</body>
</html>