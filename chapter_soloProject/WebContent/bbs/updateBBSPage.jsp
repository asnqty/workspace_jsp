<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>게시글 수정</h1>
		<form>
			<table>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" id="title" name="title" value="${bvo.title }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input type="text" id="content" name="content" value="${bvo.content}"></td>
					</tr>
					<tr>
						<td colspan="3" id="btn">
							<button id="updateBBSBtn" type="button">게시글 수정</button>
							<button id="resetUpdateBBSBtn" type="button">다시쓰기</button>
							<button id="moveViewBtn" type="button">게시글로 이동</button>
							<button id="moveAllListBtn" type="button">목록으로 이동</button>
							<input type="hidden" name="cmd" value="updateBBS">
							<input type="hidden" name="b_idx" value="${bvo.b_idx }">
							<input type="hidden" name="pageNum" value="${pageNum }">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/bbs.js"></script>
</html>