<%@page import="java.util.Arrays"%>
<%@page import="org.chan.bean.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// DTO 파일을 이용하여 데이터 저장, 사용
	MemberDTO dto = new MemberDTO();
	dto.setId("admin");
	dto.setPw("1234");
	dto.setName("김씨");
	dto.setAge(50);
	dto.setAddr("서울");
	dto.setGender("남자");
	String[] h = {"게임", "수면"};
	dto.setHobbies(h);
	String[] l = {"삼겹살", "햄버거", "라면"};
	dto.setLikeFoods(l);
	String[] dl = {"김치", "오이"};
	dto.setDislikeFoods(dl);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>아이디 : <%=dto.getId() %></li>
		<li>비밀번호 : <%=dto.getPw() %></li>
		<li>이름 : <%=dto.getName() %></li>
		<li>나이 : <%=dto.getAge() %></li>
		<li>주소 : <%=dto.getAddr() %></li>
		<li>성별 : <%=dto.getGender() %></li>
		<li>
			취미 : 
				<%for(int i=0; i<dto.getHobbies().length; i++){%> 
					<%=dto.getHobbies()[i] %>
				<%}%>
		</li>
		<li>좋아하는 음식 : <%=Arrays.toString(dto.getLikeFoods()) %></li>
		<li>싫어하는 음식 : 
			<%
				for(String dislikefoods : dto.getDislikeFoods()){
					out.print(dislikefoods);
				}
			%></li>
	</ul>
</body>
</html>