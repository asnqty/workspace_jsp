<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/insertbbsPage.css">
</head>
<body>
	<jsp:include page="/member/main.jsp"/> <br>
	<div>
		<h1>게시글 작성</h1>
		<form>
			<table>
				<tbody>
					<tr>
						<td>
							<input type="hidden" id="writer" name="writer" value="${sessionScope.mName }">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="10" cols="80" name="content" placeholder="내용을 입력하세요"></textarea></td>
					</tr>
					<tr>
						<td colspan="3" id="btn">
							<button id="insertbbsBtn" type="button">게시글 저장</button>
							<button id="insertbbsResetBtn" type="button">리셋</button>
							<button id="moveAllListBtn" type="button">목록으로 이동</button>
							<input type="hidden" name="cmd" value="insertbbs">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/bbs.js"></script>
</html>