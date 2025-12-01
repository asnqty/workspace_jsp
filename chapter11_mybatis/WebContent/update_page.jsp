<%@page import="org.chan.ex.MemberVO"%>
<%@page import="org.chan.ex.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO updatevo = new MemberVO();
	updatevo.setId(id);
	updatevo.setPw(pw);
	MemberVO vo = dao.getUpdateView(updatevo);
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
	
	<h1>${vo.id}의 정보 수정</h1>
	<form method="post">
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
								<td><input type="text" name="name" value="${vo.name}" style="width: 50px"></td>
								<td><input type="number" name="age" value="${vo.age}" style="width: 30px"></td>
								<td><input type="text" name="addr" value="${vo.addr}" style="width: 30px"></td>
								<td>${vo.reg_date}</td>
								<td colspan="7"><input type="button" value="수정하기" onclick="update(this.form)"> </td>
								<input type="hidden" name="id" value="${vo.id}">
								<input type="hidden" name="pw" value="${vo.pw}">
							</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
	function update(f){
		if(!f.name.value){
			alert("수정할 이름을 입력해주세요.")
			f.name.focus();
			return;
		}
		if(!f.age.value){
			alert("수정할 나이를 입력해주세요.")
			f.age.focus();
			return;
		}
		if(!f.addr.value){
			alert("수정할 주소를 입력해주세요.")
			f.addr.focus();
			return;
		}
		f.action = 'update.jsp';
		f.submit();
	}
</script>
</html>