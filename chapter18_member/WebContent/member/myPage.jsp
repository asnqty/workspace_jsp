<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<!-- c:if를 이용해 session에 정보가 없다면 session이 만료됐다 경고 등 처리 가능 -->
	${member.mId} <br>
	${member.mPw} <br>
</body>
</html>