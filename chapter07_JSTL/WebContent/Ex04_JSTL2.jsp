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
		1. JSTL을 이용하여 평균 변수 생성 (avg, grade)
		
		2. JSTL을 이용하여 합, 불 출력 60이상 합격 (pass) 
		
		3. 출력 데이터
		국어 : 00점
		영어 : 00점
		수학 : 00점
		평균 : 00점
		학점 : A
		합격여부 : 합격
	 -->
	<c:set var="kor" value="${param.kor + 0}"/>
	<c:set var="eng" value="${param.eng + 0}"/>
	<c:set var="mat" value="${param.mat + 0}"/>
	<c:set var="evg" value="${(kor + eng + mat) / 3}"/>
	
	<c:choose>
		<c:when test="${evg >= 90}">
			<c:set var="grade" value="A"/>
		</c:when>
		<c:when test="${evg >= 80}">
			<c:set var="grade" value="B"/>
		</c:when>
		<c:when test="${evg >= 70}">
			<c:set var="grade" value="C"/>
		</c:when>
		<c:when test="${evg >= 60}">
			<c:set var="grade" value="D"/>
		</c:when>
		<c:otherwise>
			<c:set var="grade" value="F"/>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${evg >= 60}">
			<c:set var="pass" value="합격"/>
		</c:when>
		<c:otherwise>
			<c:set var="pass" value="불합격"/>
		</c:otherwise>
	</c:choose>
	
	국어 점수 	: ${kor}점 <br>
	영어 점수 	: ${eng}점 <br>
	수학 점수 	: ${mat}점 <br>
	평균 점수 	: ${evg}점 <br>
	학점 		: ${grade} <br>
	합격여부	: ${pass}
</body>
</html>