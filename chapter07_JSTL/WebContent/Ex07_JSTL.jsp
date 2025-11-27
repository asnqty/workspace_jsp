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
	<c:set var="animals" value="사자,호랑이;사슴,곰;이구아나^뱀"/>
	
	<c:forTokens items="${animals}" delims=",;^" var="animal">
		${animal} <br>
	</c:forTokens>
</body>
</html>