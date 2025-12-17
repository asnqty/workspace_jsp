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
		<h1>마이페이지</h1>
		<form>
			<input type="hidden" id="pageNum" value="1">
			<input type="hidden" name="cmd" value="123">
		</form>
		<table>
			<thead>
				<tr>
					<td colspan="5">
						<button id="moveUpdateMemberPageBtn" type="button">회원 정보 수정</button>
						<button id="deleteMemberBtn" type="button">회원 탈퇴</button>
						<button id="moveAllListBtn" type="button">목록으로 이동</button>
						<input type="hidden" id="mName" value="${sessionScope.mName}">
					</td>
				</tr>
			</thead>
			<thead>
				<tr>
					<td colspan="5">작성한 글</td>
				</tr>
				<tr>
					<th>번호</th> 
					<th>제목</th> 
					<th>작성자</th> 
					<th>조회수</th> 
					<th>날짜</th> 
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty list}">
							<c:forEach varStatus="vs" var="bvo" items="${list }">
								<tr>
									<td>${bvo.b_idx }</td>
									<td>
										<a href="BController?cmd=view&b_idx=${bvo.b_idx}&pageNum=1">
										${bvo.title }
										</a>
									</td>
									<td>${bvo.writer }</td>
									<td>${bvo.hit }</td>
									<td>${bvo.reg_date }</td>
								</tr>
							</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">작성한 글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<table>
			<thead>
				<tr>
					<td colspan="5">작성한 댓글</td>
				</tr>
				<tr>
					<th>글 번호</th> 
					<th>글 제목</th> 
					<th>댓글 내용</th> 
					<th>날짜</th> 
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty clist}">
							<c:forEach varStatus="vs" var="bcvo" items="${clist }">
								<tr>
									<td>${bcvo.b_idx }</td>
									<td>
										<a href="BController?cmd=view&b_idx=${bcvo.b_idx}&pageNum=1">
										${bcvo.title }
										</a>
									</td>
									<td>${bcvo.content }</td>
									<td>${bcvo.reg_date }</td>
								</tr>
							</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">작성한 댓글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	
</body>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/member.js"></script>
</html>