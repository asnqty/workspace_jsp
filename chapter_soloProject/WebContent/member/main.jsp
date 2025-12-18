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
	<div>
		<c:choose>
			<c:when test="${not empty sessionScope.mName}">
				<button id="moveMyPageBtn" type="button">마이페이지</button>
				<button id="logoutBtn" type="button">로그아웃</button>
			</c:when>
			<c:otherwise>
				<button id="moveLoginPageBtn" type="button">로그인</button>
				<button id="moveJoinPageBtn" type="button">회원가입</button>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script type="text/javascript" src="js/member.js"></script>
</html>