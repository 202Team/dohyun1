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
ID : <input name="id"><button>중복체크</button><span></span><br>
PW : <input type="password" name="pw1"><br>
PW(확인) : <input type="password" name="pw2"><br>
NAME : <input name="name"><br>
NICKNAME : <input name="nickname"><br>
TELL : <input type="tell" name="tell" ><br>
ADDRESS : <input type="address" name="address" ><br>
<input type="submit" value="가입">
</form>

<script type="text/javascript">
$(document).ready(function(){
	
    $("button").click(function(event) {
        event.preventDefault();
        
        var idv = $("input[name='id']").val();
        
        $.ajax({
           type: 'get',
           url: 'idcheck',
           data: {
              id : idv
           },
           dataType: 'text',
           success: function(result) {
           	console.log(result);
              $("span").text(result);
           },
           error: function(request, status, error) {
              console.log(error);
           }
           
        });
             
        
     });
	
	$("input[type='submit']").click(function(){
		var pw1 = $("input[name='pw1']").val();
		var pw2 = $("input[name='pw2']").val();
		
		if (pw1 != pw2) {
			event.preventDefault();
			
			$("input=[name'pw2']").focus();
			${"input[name='pw2']"}.select();
			
			return;
		}
		
	});
	
	
	
	
});



</script>

</body>
</html>