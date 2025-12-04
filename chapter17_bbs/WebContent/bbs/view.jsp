<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/view.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>${bvo.b_idx }번 게시글</h1>
		<form method="post">
			<table class="bbs">
				<tbody>
					<tr>
						<th>작성자</th>
						<td>${bvo.writer }</td>
						<th>IP</th>
						<td>${bvo.ip }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">${bvo.title }</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<c:choose>
								<c:when test="${bvo.filename eq null }">
									첨부파일 없음
								</c:when>
								<c:otherwise>
									<a id="download" href="${bvo.filename }">${bvo.filename }</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">${bvo.content }</td>
					</tr>
					<tr>
						<td colspan="4" id="btn">
							<input type="button" value="게시글 수정하기" onclick="updatePage()">&nbsp;&nbsp;
							<input type="button" value="게시글 삭제하기" onclick="removeBBS(${bvo.b_idx})">&nbsp;&nbsp;
							<input type="button" value="목록으로 이동" onclick="view_all()">
							<input type="hidden" name="b_idx" value="${bvo.b_idx }">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<br/><hr/><br/>
		<!-- 댓글 입력 폼 -->
		
		<br/><hr/><br/>
		<!-- 댓글 출력 폼 -->
		
	</div>

</body>
<script type="text/javascript" src="js/bbs.js"></script>
</html>