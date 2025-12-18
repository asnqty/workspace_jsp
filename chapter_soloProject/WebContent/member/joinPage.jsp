<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div>
		<h1>회원가입</h1>
		<form>
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td><input type="text" id="mId" name="mId"></td>
						<td><button id="mIdCkBtn" type="button">아이디 중복확인</button></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="mPw" name="mPw"></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input type="text" id="mName" name="mName"></td>
						<td><button id="mNameCkBtn" type="button">닉네임 중복확인</button></td>
					</tr>
					<tr>
						<td colspan="3" id="btn">
							<button id="joinBtn" type="button">회원가입</button>
							<button id="joinResetBtn" type="button">리셋</button>
							<button id="moveAllListBtn" type="button">목록으로 이동</button>
							<input type="hidden" name="cmd" value="joinMember">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/bbs.js"></script>
<script type="text/javascript" src="js/member.js"></script>
</html>