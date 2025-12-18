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
		<h1>로그인</h1>
		<form>
			<table>
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" id="mId" name="mId">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" id="mPw" name="mPw"></td>
					</tr>
					<tr>
						<td colspan="3" id="btn">
							<button id="loginBtn" type="button">로그인</button>
							<button id="loginResetBtn" type="button">리셋</button>
							<button id="moveJoinPageBtn" type="button">회원가입으로 이동</button>
							<button id="moveAllListBtn" type="button">목록으로 이동</button>
							<input type="hidden" name="cmd" value="login">
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