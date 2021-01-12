<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>회원정보</h1>
회원번호 : ${dto.usernum }<br>
ID : ${dto.id }<br>
이름 :  ${dto.name }<br>
닉네임 :  ${dto.nickname }<br>
전화번호 :  ${dto.tell }<br>
주소 :  ${dto.address }<br>
가입일 :   ${dto.joined }<br>
<br>
<br>
<a href="main.jsp">메인으로 가기</a><br>
<a href="updateui.do?id=${dto.id}">정보수정</a>
&nbsp;&nbsp;
<a href="delete.do?id=${dto.id}" id="del">회원탈퇴</a>


<script type="text/javascript">
$("#del").click(function(event){
	event.preventDefault();
	choice = confirm("정말로 삭제하시겠습니까?");
	href = $(this).attr("href");
	
	if (choice) {
		location.assign(href);
	}
	
	
});

</script>


</body>
</html>