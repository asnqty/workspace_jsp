<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>include1.jsp 페이지</h1>
	
	<br> <hr> <br>
	
	<!-- include1.jsp 페이지에 include2.jsp 페이지를 포함 -->
	
	<!-- 1. include 지시어 -->
	<%@ include file="Ex01_include2.jsp" %>
	
	<!-- 2. include 액션 태그 -->
	<jsp:include page="Ex01_include2.jsp">
		<jsp:param name="name" value="김씨"/>
		<jsp:param name="age" value="20"/>
		<jsp:param name="addr" value="서울"/>
	</jsp:include>
</body>
</html>