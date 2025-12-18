/* ----------------- form 관련 요소들 ----------------- */
const mf = document.forms[0];

let mIdCk=mNameCk=mPwCk = false;

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
		join(mf);
	}
	// 회원가입 페이지에서 입력 내용을 리셋하는 버튼
	if(type === "joinResetBtn"){
		mf.reset();
	}
	// 아이디 중복확인 버튼
	if(type === "mIdCkBtn"){
		validatemId();
	}
	// 회원가입 페이지에서 입력 내용을 리셋하는 버튼
	if(type === "mNameCkBtn"){
		validatemName();
	}
	// 로그인 버튼
	if(type === "loginBtn"){
		login();
	}
	// 회원가입 페이지에서 입력 내용을 리셋하는 버튼
	if(type === "loginResetBtn"){
		mf.reset();
	}
	// 마이페이지로 이동하는 버튼
	if(type === "moveMyPageBtn"){
		moveMyPage();
	}
	// 로그아웃 버튼
	if(type === "logoutBtn"){
		logout();
	}
	// 마이페이지에서 회원 정보를 수정하는 페이지로 이동하는 버튼
	if(type === "moveUpdateMemberPageBtn"){
		moveUpdateMemberPage();
	}
	// 회원정보 수정시 비밀번호 확인 버튼
	if(type === "validatemPwBtn"){
		validatemPw();
	}
	// 회원정보 수정 버튼
	if(type === "updateMemberBtn"){
		updateMember();
	}
	// 마이페이지에서 회원 탈퇴를 하는 버튼
	if(type === "deleteMemberBtn"){
		deleteMember();
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
function join(mf){
	let mId = mf.mId;
	let mPw = mf.mPw;
	let mName = mf.mName;
	
	if(mId.value.trim() === ""){
		alert("아이디를 입력해주세요");
		return;
	}
	if(mPw.value.trim() === ""){
		alert("비밀번호를 입력해주세요");
		return;
	}
		if(mName.value.trim() === ""){
		alert("닉네임을 입력해주세요");
		return;
	}
	
	if(mIdCk == true && mNameCk == true){
		mf.action = 'MController?cmd=joinMember'
		mf.submit();
	}
	else{
		alert("아이디와 닉네임의 중복확인을 해주세요.")
	}
}

// 중복된 아이디 여부 확인 함수
function validatemId(){
	let target = mf.mId;
	console.log(target.value);
	if(target.value.trim() === ""){
		alert("아이디를 입력해주세요.")
	}
	else{
		const params = {
	   		cmd : 'validatemId',
	    	mId : target.value
	  	}
	
		const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');
	
		fetch(`MAsyncController?${queryString}`)
	    .then(response => response.json())
	    .then(data =>{
	      if(data.result == 1){
			alert("중복된 아이디입니다.")
			mIdCk = false;
	      }
			else if (data.result == 0){
					alert("사용할 수 있는 아이디입니다.");
					mIdCk = true;
				}
	    })  
	    .catch(err => console.log(err))
	}
}

// 중복된 닉네임 여부 확인 함수
function validatemName(){
	let target = mf.mName;
	if(target.value.trim() === ""){
		alert("닉네임을 입력해주세요.")
	}
	else{
	const params = {
		cmd : 'validatemName',
		mName : target.value
  	}

	const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');

	fetch(`MAsyncController?${queryString}`)
    .then(response => response.json())
    .then(data =>{
      if(data.result == 1){
		alert("중복된 닉네임입니다.")
		mNameCk = false;
		return;
      }
			else if (data.result == 0){
				alert("사용할 수 있는 닉네임입니다.");
				mNameCk = true;
			}
    })  
    .catch(err => console.log(err))
	}
}

// 로그인
function login(){
	let mId = f.mId;
	let mPw = f.mPw;
	
	if(mId.value.trim() === ""){
		alert("아이디를 입력해주세요");
		return;
	}
	if(mPw.value.trim() === ""){
		alert("비밀번호를 입력해주세요");
		return;
	}
	
	f.action = 'MController'
	f.submit();
}

// 마이페이지로 이동
function moveMyPage(){
	location.href = 'MController?cmd=moveMyPage'
}

// 로그아웃
function logout(){
	 location.href = 'MController?cmd=logout'
}

// 회원정보 수정 페이지로 이동
function moveUpdateMemberPage(){
	location.href ='MController?cmd=moveUpdateMemberPage'
}

// 회원정보 수정 전 현재 비밀번호 확인
function validatemPw(){
	let target = mf.mPw;
	let target2 = mf.mName;
	
	const params = {
   		cmd : 'validatemPw',
    	mPw : target.value,
		mName : target2.value
  	}

	const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');

	fetch(`MAsyncController?${queryString}`)
    .then(response => response.json())
    .then(data =>{
      if(data.result == 1){
		alert("비밀번호가 검증되었습니다.")
		mPwCk = true;
		return;
      }
		else if (data.result == 0){
				alert("비밀번호가 일치하지 않습니다.");
				mPwCk = false;
			}
    })  
    .catch(err => console.log(err))
}

// 회원정보 수정
function updateMember(){
	if(mPwCk == false){
		alert("비밀번호 확인을 해주세요.")
		return;
	}
	else if(mPwCk == true){
		mf.action = 'MController'
		mf.submit();
	}
}


// 회원탈퇴
function deleteMember(){
	mPwInput = prompt("회원탈퇴를 원하신다면 비밀번호를 입력해주세요.");
	if(mPwInput != null){
		let target = document.querySelector('#mName');
		
		const params = {
	   		cmd : 'validatemPw',
	    	mPw : mPwInput,
			mName : target.value
	  	}
	
		const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');
	
		fetch(`MAsyncController?${queryString}`)
	    .then(response => response.json())
	    .then(data =>{
	      if(data.result == 1){
			location.href = 'MController?cmd=deleteMember'
			return;
	      }
			else if (data.result == 0){
					alert("비밀번호가 일치하지 않습니다.");
				}
	    })  
	    .catch(err => console.log(err))
	}
}