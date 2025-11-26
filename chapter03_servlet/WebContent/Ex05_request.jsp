<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Ex05_request">
		<div>아이디 <input type="text" name="id"></div>
		<div>비밀번호 <input type="password" name="pw"></div>
		<div>이름 <input type="text" name="name"></div>
		<div>이메일 <input type="text" name="email"></div>
		<div>
			성별
			<input type="radio" name="gender" value="남" checked="checked">남
			<input type="radio" name="gender" value="여">여
		</div>
		<div>
			취미
			<input type="checkbox" name="hobbies" value="영화">영화
			<input type="checkbox" name="hobbies" value="음악">음악
			<input type="checkbox" name="hobbies" value="독서">독서
			<input type="checkbox" name="hobbies" value="없음">없음
		</div>
		<div>
			<input type="button" value="전송" onclick="send(this.form)">
		</div>
	</form>
</body>
<script type="text/javascript">
	function send(f) {
		if(f.id.value == ''){
			alert("아이디는 필수로 입력해야 합니다.");
			f.id.focus();
			return;
		}
		if(f.pw.value == ''){
			alert("비밀번호는 필수로 입력해야 합니다.");
			f.pw.focus();
			return;
		}
		if(f.name.value == ''){
			alert("이름은 필수로 입력해야 합니다.");
			f.name.focus();
			return;
		}
		if(f.email.value == ''){
			alert("이메일은 필수로 입력해야 합니다.");
			f.email.focus();
			return;
		}
		let checkedCount = document.querySelectorAll('input[name="hobbies"]:checked');
		if(checkedCount.length === 0){
			alert("취미는 최소 하나 선택해야 합니다.")
			return;
		}
		// f.hobbies는 input 4개를 가진 NodeList라서 길이가 항상 4이다.
		// 따라서 원하는 결과를 내기 위해서는 f.hobbies의 배열을 돌며 checked가 된 값이 있나 확인을 한 후
		// checked가 되어 있다면 외부에서 만든 변수의 값을 변화시켜준 후 그 값이 초기값인지 변화됐는지를 물어봐야 한다.
//		if(f.hobbies.length == 0){
//			alert("취미는 최소 하나 선택해야 합니다.");
//			return;
//		}
//		f.submit();
	}
</script>
</html>