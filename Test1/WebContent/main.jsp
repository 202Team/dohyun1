<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>Main Page</h1>
<c:choose>
	<c:when test="${empty login }">
		<a href="loginui.do">로그인</a><br>
		<a href="insertui.do">회원가입</a><br>
	</c:when>
	<c:otherwise>
		${login.id} 님, 환영합니다 <a href="logout.do">로그아웃</a><br>
	</c:otherwise>

</c:choose>


<a href="boardmove.do">게시판가기</a><br>
<a href="read.do">회원정보</a>
</body>
</html>