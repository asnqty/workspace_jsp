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
	<jsp:include page="/member/main.jsp"/> <br>
	<div>
		<h1>${bvo.b_idx }번 게시글</h1>
		<form method="post">
			<table class="bbs">
				<tbody>
					<tr>
						<th>작성자</th>
						<td>
							${bvo.writer }
							<input type="hidden" name="pageNum" value="${pageNum}">
							<input type="hidden" name="b_idx" value="${bvo.b_idx }">
							<input type="hidden" name="cmd" value="moveUpdatePage">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">${bvo.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">${bvo.content }</td>
					</tr>
					<c:choose>
						<c:when test="${bvo.writer eq sessionScope.mName}">
							<tr>
								<td colspan="4" id="btn">
									<button id="moveUpdateBBSPageBtn" type="button">게시글 수정하기</button>
									<button id="deleteBBSBtn" type="button">게시글 삭제하기</button>
									<button id="moveAllListBtn" type="button">목록으로 이동</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4" id="btn">
									<button id="moveAllListBtn" type="button">목록으로 이동</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</form>
		
		<c:choose>
			<c:when test="${not empty sessionScope.mName}">
				<form method="post">
					<table class="insertComment">
						<tbody>
							<tr>
								<th>댓글 내용</th>
								<td colspan="3">
									<textarea name="content" rows="3" cols="80" placeholder="댓글을 입력하세요."></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" id="btn">
									<button id="insertCommentBtn" type="button">댓글작성</button>
									<button id="resetCommentBtn" type="button">다시작성</button>
									<input type="hidden" name="writer" value="${sessionScope.mName}">
									<input type="hidden" name="b_idx" value="${bvo.b_idx }"/>  
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</c:when>
		</c:choose>
		
		<form >
			<table class="viewComment">
				<thead>
					<tr>
						<th>작성자</th>
						<th>내용</th>
						<th>작성일</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody id="commBody">
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/comment.js"></script>
</html>