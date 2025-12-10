/* ----------------- form 관련 요소들 ----------------- */

// 전달 받은 첫 번째 폼 : 아이디, 비밀번호, 비밀번호 확인 입력 값
const f = document.forms[0];

/* ----------------- 함수 -----------------*/

// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;

    // 로그인 버튼을 눌렀을 때 실행되는 함수
    if(type === 'loginBtn'){
      // 로그인 함수 실행
      login();
    }
    // 메인 버튼을 눌렀을 때 실행되는 함수
    else if(type === 'mainBtn'){
      // 메인 페이지로 갈 수 있게 cmd를 담아 컨트롤러로 보냄
      location.href = `MemberController?cmd=mainPage`;
    }
  });
});

// 로그인 함수
function login(){
  // 1. 아이디 및 비밀번호 빈 값 검증
  // 아이디가 mId인 input의 값 가져오기
  let mId = f.mId;

  // 아이디가 입력되지 않았을 때 실행되는 함수
  if(mId.value == ''){
    // 아이디를 입력할 수 있게 경고
    alert("아이디를 입력하세요.");
    return;
  }

  // 비밀번호가 mPw인 input의 값 가져오기
  let mPw = f.mPw;

  // 비밀번호가 입력되지 않았을 때 실행되는 함수
  if(mPw.value == ''){
    // 비밀번호를 입력할 수 있게 경고
    alert("비밀번호를 입력하세요.");
    return;
  }

  // 2. mId, mPw, cmd 데이터 json으로 변환 후 전송
  let formData = new FormData(f);
  let jsonData = JSON.stringify(Object.fromEntries(formData.entries()));

  // 컨트롤러로 아이디와 비밀번호와 cmd가 담긴 json 객체를 보냄 
  fetch('MemberAsyncController', {
     	method : 'POST',
     	// body = 실어 보낼 데이터
     	body : jsonData,
		// headers = 요청에 대한 추가 정보
      	headers : {
        	'Content-type' : 'application/json; charset=UTF-8'
      	}
	})
    .then(response => response.json())
    .then(data =>{
      // 아이디와 비밀번호가 일치하는 회원이 존재하여 success를 리턴 받으면 실행되는 함수
      if(data.result === "success"){
		console.log("성공");
      }
      // 아이디와 비밀번호가 일치하는 회원이 존재하지 않아 fail을 리턴 받으면 실행되는 함수
      else if(data.result === "fail"){
		console.log("실패");
      }
    })  
    .catch(err => console.log(err))

  // 3. 정보가 있으면 
  //  1) 쿼리 실행 결과를 세션에 저장 속성 이름 = "member"
  //  2) obj.put("result", "success")
  // 4. 정보가 없으면
  //  1) obj.put("result", "fail")
}