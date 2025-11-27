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
	<!-- 
		1. 기본 forEach 사용 예제
		최소 값, 최대 값, 최소 값 ~ 최대 값 화면에 출력
		최소, 최대 값을 변수 저장
	 -->
	 <c:choose>
	 	<c:when test="${param.num1 lt param.num2}">
	 		<c:forEach var="i" begin="${param.num1}" end="${param.num2}" step="1">
	 			${i} <br>
	 		</c:forEach>
	 	</c:when>
	 	<c:otherwise>
	 		<c:forEach var="i" begin="${param.num2}" end="${param.num1}" step="1">
	 			${i} <br>
	 		</c:forEach>
	 	</c:otherwise>
	 </c:choose>
	 
	 <!-- 2. 향상 forEach를 이용하여 음식 종류들 출력 -->
	 <%pageContext.setAttribute("FOODS", request.getParameterValues("foods")); %>
	 <c:forEach var="foods" items="${FOODS}">
	 	${foods} <br>
	 </c:forEach>
	 <!-- 
	 	<c:forEach var="foods" items="${paramValues.foods}">
	 		${foods} <br>
	 	</c:forEach>
	  -->
</body>
</html>