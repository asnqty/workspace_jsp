// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;
	
	// 로그인 페이지로 이동하는 버튼
	if(type === "moveLoginPageBtn"){
		moveLoginPage();
	}
	// 회원가입 페이지로 이동하는 버튼
	if(type === "moveJoinPageBtn"){
		moveJoinPage();
	}
	// 회원가입 페이지에서 회원 가입을 완료하는 버튼
	if(type === "joinBtn"){
		join();
	}
	// 회원가입 페이지에서 입력 내용을 리셋하는 버튼
	if(type === "joinResetBtn"){
		f.reset();
	}
  });
});

// 로그인 페이지로 이동
function moveLoginPage(){
	location.href = 'MController?cmd=moveLoginPage';
}

// 회원가입 페이지로 이동
function moveJoinPage(){
	location.href = 'MController?cmd=moveJoinPage';
}

// 회원 가입
function 