<%@page import="org.chan.db.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="index.jsp"/>
	
	<br> <hr> <br>
	
	<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		conn = DBConnect.getConnection();
		String sql = "select * from member where id=? and pw=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		rs = ps.executeQuery();
	}catch(Exception e){
		
	}
	%>
	
	<h1>회원의 데이터</h1>
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
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${empty vo}">
					<tr>
						<td colspan="7">해당 회원이 없습니다. </td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>${vo.idx }</td>
						<td>${vo.id }</td>
						<td>${vo.pw }</td>
						<td>${vo.name }</td>
						<td>${vo.age }</td>
						<td>${vo.addr }</td>
						<td>${vo.regdate }</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="7">
						<input type="button" value="수정" onclick="update(this.form)">
						<input type="reset" value="다시 작성">
					</th>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
<script type="text/javascript">
	function update(f){
		if(!f.pw.value ||
			!f.age.value ||
			!f.name.value ||
			!f.addr.value){
			alert("수정할 내용을 모두 입력하세요.");
			return;
		}
		f.action = 'update.jsp';
		f.submit();
	}
</script>
</html>






