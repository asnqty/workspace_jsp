<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/updatePage.css">
</head>
<body>
	<div>
		<h1>회원정보 수정</h1>
		<form>
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" id="mId" name="mId" value="${mvo.mId}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>현재 비밀번호</th>
						<td><input type="password" id="mPw" name="mPw"></td>
						<td><button id="validatemPwBtn" type="button">비밀번호 확인</button></td>
					</tr>
					<tr>
						<th>새 비밀번호</th>
						<td><input type="password" id="newmPw" name="newmPw"></td>
					</tr>
					<tr>
						<th>닉네임 변경</th>
						<td><input type="text" id="newmName" name="newmName" value="${mvo.mName}"></td>
					</tr>
					<tr>
						<td colspan="3" id="btn">
							<button id="updateMemberBtn" type="button">회원정보 수정</button>
							<button id="moveMyPageBtn" type="button">마이페이지로 이동</button>
							<input type="hidden" name="cmd" value="updateMember">
							<input type="hidden" name="mName" value="${mvo.mName}">
							<input type="hidden" id="pageNum" value="1">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/member.js"></script>
</html>