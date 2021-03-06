<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.work.dto.Member" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css/common.css"></style>
<title>회원가입페이지</title>
<link type = "text/css" rel = "stylesheet" href ="/css/common.css">
</head>
<body>
<a href = "/"><img src ="/img/logo.png"></a>
<c:choose>
   <c:when test = "${empty memberId || empty dto}">
      <a href = "joinForm">회원가입</a>
      <a href = "loginForm">로그인</a>
   </c:when>
   
   <c:otherwise> 
         <div>로그인 정보 : ${memberId}[${dto.grade}]</div>
         <a href = "logout">로그아웃</a>
         <a href = "myInfo">내정보조회</a>
         
         <!-- 관리자 권한 : 전체회원조회 서비스 제공-->
         <c:if test="${grade == 'A'}">
            [관리자]<a href = "memberList">전체회원조회</a>
         </c:if>
   </c:otherwise>
</c:choose>

<%-- <jsp:include page="inc/multipleCondition.jsp" /> --%>


<h3>회원전체조회</h3>
<c:if test="${not empty dto}">
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
      
      <!-- 검색조건에 해당하는 결과가 없는 경우 메세지 출력 -->
      <c:if test="${not empty message}">
         <tr>
         <th colspan=10>${message}</th>
         </tr>
      </c:if>
      
      <c:forEach var="dto" items="${list}">
         <tr>
            <!-- dto참조변수명.멤버변수명 => dto.getter() 수행결과 출력  -->
            <td>${dto.memberId}</td>
            <td>${dto.memberPw}</td>
            <td>${dto.name}</td>
            <td>${dto.mobile}</td>
            <td>${dto.email}</td>
            <td>${dto.entryDate}</td>
            <td>
            ${dto.grade}
            <c:choose>
               <c:when test="${dto.grade eq 'G'}">[일반회원]</c:when>
               <c:when test="${dto.grade eq 'S'}">[우수회원]</c:when>
               <c:when test="${dto.grade eq 'A'}">[관리자회원]</c:when>
            </c:choose>
            </td>
            
            <td>${dto.mileage}</td>
            <td>${dto.manager}</td>
         </tr>
      </c:forEach>
   </table>
</c:if>

<c:if test="${empty dto}">
   <h3>등록 회원이 없습니다.</h3>
</c:if>
</body>
</html>