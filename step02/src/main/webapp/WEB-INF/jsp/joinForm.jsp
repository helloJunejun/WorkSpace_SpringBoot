<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입페이지</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">
</head>
<body>
<a href="/"> <img src="/img/logo.png"> </a>

<a href = "joinForm">회원가입</a>
<a href = "loginForm">로그인</a>
<h3>회원가입페이지</h3>

<form action = "join" method="post">
   아이디
   <input type ="text" name="memberId" autofocus="autofocus" />
   비밀번호
   <input type ="password" name="memberPw" autofocus="autofocus" />
   이름
   <input type ="text" name="name" autofocus="autofocus" />
   휴대폰
   <input type ="text" name="mobile" autofocus="autofocus" />
   이메일
   <input type ="text" name="email" autofocus="autofocus" />
   <input type ="submit" value="가입" />
   <input type ="reset" value="취소" />



</form>
</body>
</html>