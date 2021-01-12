<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>회원정보수정</h1>

<form action="update.do" method="post">
ID : <input name="id" value="${dto.id}" readonly ><br>
PW : <input type="password" name="pw1"><br>
PW(확인) : <input type="password" name="pw2"><br>
NAME : <input name="name" value="${dto.name}"><br>
NICKNAME : <input name="nickname" value="${dto.nickname}"><br>
TELL : <input type="tell" name="tell" value="${dto.tell}"><br>
ADDRESS : <input type="address" name="address" value="${dto.address}"><br>
<input type="submit" value="수정완료">
</form>
</body>
</html>