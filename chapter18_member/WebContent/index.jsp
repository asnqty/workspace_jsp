<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.member}">
			<!-- 클릭하면 마이 페이지로 이동하는 버튼 -->
			<button id="myPageBtn">마이페이지</button>
			<!-- 클릭하면 로그 아웃을 하는 버튼 -->
			<button id="logoutBtn">로그아웃</button>
		</c:when>
		<c:otherwise>
			<!-- 클릭하면 로그인 페이지로 이동하는 버튼 -->
			<button id="loginBtn">로그인</button>
		</c:otherwise>
	</c:choose>
	<!-- 클릭하면 회원가입 페이지로 이동하는 버튼 -->
	<button id="joinBtn">회원가입</button>
</body>
<script type="text/javascript" src="./js/main.js"></script>
</html>