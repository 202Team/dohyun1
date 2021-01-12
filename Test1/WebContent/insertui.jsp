<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>회원가입</h1>
<form action="insert.do" method="post">
ID : <input name="id"><br>
PW : <input type="password" name="pw1"><br>
PW(확인) : <input type="password" name="pw2"><br>
NAME : <input name="name"><br>
NICKNAME : <input name="nickname"><br>
TELL : <input type="tell" name="tell" ><br>
ADDRESS : <input type="address" name="address" ><br>
<input type="submit" value="가입">
</form>

</body>
</html>