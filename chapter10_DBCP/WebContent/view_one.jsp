<%@page import="org.chan.ex.MemberVO"%>
<%@page import="org.chan.ex.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO vo = dao.getUserInfoById(id);
	pageContext.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="index.jsp"/>
	
	<br> <hr> <br>
	
	<!-- if문을 통해 성공적으로 데이터를 받아왔을때와 아닐때를 구분해서 <h1>태그 내용을 출력 -->
	<h1>${vo.id}의 데이터</h1>
	<table>
		<thead>
			<tr>
				<th>회원번호</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
				<th>가입일자</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<!-- EL표기법에 truthy와 faulty가 존재하여 vo 객체가 null이라면 empty로 검증이 가능함 -->
				<c:when test="${vo.idx == 0}">
					<tr>
						<td  colspan="7">member 데이터가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
						<tr>
							<td>${vo.idx}</td>
							<td>${vo.id}</td>
							<td>${vo.pw}</td>
							<td>${vo.name}</td>
							<td>${vo.age}</td>
							<td>${vo.addr}</td>
							<td>${vo.reg_date}</td>
						</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>