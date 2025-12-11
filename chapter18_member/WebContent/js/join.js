// 정규식과 비동기를 이용해 검증을 거치고, 회원가입을 진행

/* ----------------- form 관련 요소들 ----------------- */

// 전달 받은 첫 번째 폼 : 아이디, 비밀번호, 비밀번호 확인 입력 값
const f = document.forms[0];

// 입력 받은 아이디
const mIdValidState = document.querySelector("#mIdValidState");
// 입력 받은 비밀번호
const mPwValidState = document.querySelector("#mPwValidState");
// 입력 받은 비밀번호 확인
const mPwReValidState = document.querySelector("#mPwReValidState");

// 회원가입 전 검증이 됐는지 체크하기 위한 용도, 모두 true가 되면 회원가입 성공
let idCk=pwCk=pwReCk=nameCk=emailCk = false;

/* ----------------- 정규식 ----------------- */

const regExpId = /^[a-z]+[0-9a-z]{3,12}$/;   // 아이디 검증 정규식
const regExpPw = /^[0-9a-zA-Z]{8,16}$/;      // 비밀번호 검증 정규식
const regExpName = /^[가-힣a-zA-Z]{2,12}$/;   // 이름 검증 정규식 
const regExpEmail = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;   // 이메일 검증 정규식

/* ----------------- 함수 -----------------*/

// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;

    // 중복확인 버튼을 눌렀을 때 실행되는 함수
    if(type === 'duplicateCkBtn'){
      // 아이디 중복을 확인하는 함수 실행
      validateId();
    }
    // 회원가입 버튼을 눌렀을 때 실행되는 함수
    else if(type === 'joinBtn'){
      join();
    }
    // 다시작성 버튼을 눌렀을 때 실행되는 함수
    else if(type === 'resetBtn'){
      // 폼을 초기화 하는 함수
      f.reset();
    }
    // 목록 버튼을 눌렀을 때 실행되는 함수
    else if(type === 'mainBtn'){
      // 메인 페이지로 갈 수 있게 cmd를 담아 컨트롤러로 보냄
      location.href = `MemberController?cmd=mainPage`;
    }
  });
});

// 데이터 검증 완료 함수
function validated(inputTarget, resultState, comment){
   inputTarget.classList.add("is-valid");
   inputTarget.classList.remove("is-invalid");
   if(resultState){
      resultState.classList.add("valid-feedback");
      resultState.classList.remove("invalid-feedback");
      comment ? 
         resultState.innerHTML = comment : 
            resultState.innerHTML = '' ;
   }
}
// 데이터 검증 미완료 함수
function invalidate(inputTarget, resultState, comment){
   inputTarget.classList.remove("is-valid");
   inputTarget.classList.add("is-invalid");
   if(resultState){
      resultState.classList.remove("valid-feedback");
      resultState.classList.add("invalid-feedback");
      comment ? 
         resultState.innerHTML = comment : 
            resultState.innerHTML = '' ;
   }
}
// 검증 스타일 초기화 함수
function Initialization(inputTarget, resultState){
   inputTarget.classList.remove("is-valid");
   inputTarget.classList.remove("is-invalid");
   if(resultState){
      resultState.classList.remove("valid-feedback");
      resultState.classList.remove("invalid-feedback");
      resultState.innerHTML = '';
   }
}

// 아이디 중복을 확인하는 함수
function validateId(){
  // 아이디가 mId인 input의 값 가져오기
  let target = f.mId;

  // 아이디가 입력되지 않았을 때 실행되는 함수
  if(target.value == ''){
    // 아이디가 입력되지 않았으므로 검증 스타일을 초기화 하는 함수 실행
    Initialization(target, mIdValidState);
    // 아이디를 입력할 수 있게 경고
    alert("아이디를 입력하세요.");
    // 아이디가 비어 있으므로 idCk를 false로 초기화
    idCk = false;
    return;
  }
  // 아이디가 입력 됐을 때 검증식에 맞게 입력했는지 확인하는 과정
  else if(!regExpId.exec(target.value)){
    // 형식에 맞지 않은 값이 입력되면 input 창 아래에 표시
    invalidate(target, mIdValidState, "형식에 맞지 않은 아이디입니다.")
    // 아이디가 적절한 형식이 아니므로 idCk를 false로 초기화
    idCk = false;
    return;
  }

  // 자바 스크립트에서는 객체에 파라미터를 key, value 형식으로 담음
  const params = {
    cmd : 'validateId',
    mId : target.value
  }

  // params 객체에 담긴 key를 모두 꺼내 'key=value' 형태로 변경, key가 여러개면 사이에 &을 넣어서 연결하여 queryString 형식으로 가공
  const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');

  // 컨트롤러로 아이디와 cmd가 담긴 queryString을 보냄
  fetch(`MemberAsyncController?${queryString}`)
    .then(response => response.json())
    .then(data =>{
      // 중복된 아이디가 있어 1을 리턴 받으면 실행되는 함수
      if(data.result == 1){
        // 중복된 아이디가 있어 사용할 수 없으므로 input 창 아래에 표시
        invalidate(target, mIdValidState, "중복된 아이디입니다.");
        // 중복된 아이디가 있어 사용할 수 없으므로 idCk를 false로 초기화
        idCk = false;
      }
      // 중복된 아이디가 없어 0을 리턴 받으면 실행되는 함수
      else if(data.result == 0){
        // 사용할 수 있는 아이디이므로 input 창 아래에 표시
        validated(target, mIdValidState, "사용 가능한 아이디입니다.");
        // 사용할 수 있는 아이디이므로 idCk를 true로 변경
        idCk = true;
      }
    })  
    .catch(err => console.log(err))
};

// 비밀번호 입력 검증 (실시간으로 검증을 하기 때문에 함수를 사용하지 않고 요소에 이벤트를 검)
f.mPw.addEventListener('keyup', e =>{
  // mPw를 target으로 지정해서 target 객체에 담음
  let target = e.currentTarget;

  // 비밀번호가 비어있을 때 실행되는 함수
  if(target.value == ''){
    // 비밀번호가 입력되지 않았으므로 검증 스타일을 초기화 하는 함수 실행
    Initialization(target, mPwReValidState);
    // 비밀번호가 비어있으므로 pwCk를 false로 초기화
    pwCk = false;
  }
  // 비밀번호가 형식에 맞을 때 실행되는 함수
  else if(regExpPw.exec(target.value)){
    // 비밀번호를 사용할 수 있으므로 input 창 아래에 표시
    validated(target, mPwValidState);
    // pwCk를 true로 변경
    pwCk = true;
  }
  // 비밀번호가 형식에 맞지 않을 때 실행되는 함수
  else if(!regExpPw.exec(target.value)){
    // 비밀번호가 형식에 맞지 않아 사용할 수 없으므로 input 창 아래에 표시
    invalidate(target, mPwValidState, "올바른 형식이 아닙니다.");
    // 사용할 수 없는 비밀번호이므로 pwCk를 false로 초기화
    pwCk = false;
  }
});

// 비밀번호 확인 검증 (비밀번호와 마찬가지로 실시간으로 검증)
f.mPwRe.addEventListener('keyup', e=>{
  // mPwRe를 target으로 지정해서 target 객체에 담음
  let target = e.currentTarget;
  
  // 비밀번호 확인이 비어있을 때 실행되는 함수
  if(target.value == ''){
    // 비밀번호 확인이 입력되지 않았으므로 검증 스타일을 초기화 하는 함수 실행
    Initialization(target, mPwReValidState);
    // 비밀번호 확인이 비어있으므로 pwReCk를 false로 초기화
    pwReCk = false;
  }
  // 비밀번호와 비밀번호 확인이 일치하지 않을 때 실행되는 함수
  else if(target.value !== f.mPw.value){
    // 비밀번호와 비밀번호 확인이 일치하지 않으므로 input 창 아래에 표시
    invalidate(target, mPwReValidState, "비밀번호가 일치하지 않습니다.");
    // 비밀번호와 비밀번호 확인이 일치하지 않으므로 pwReCk를 false로 초기화.
    pwReCk = false;
  }
  // 비밀번호와 비밀번호 확인이 일치할 때 실행되는 함수
  else if(target.value === f.mPw.value) {
    // 비밀번호와 비밀번호 확인이 일치하므로 input 창 아래에 표시
    validated(target, mPwReValidState);
    // 비밀번호와 비밀번호 확인이 일치하므로 pwReCk를 true로 변경
    pwReCk = true;
  }
});

// 이름 입력 검증
f.mName.addEventListener('input', e=>{
  // mName을 target으로 지정해서 target 객체에 담음
  let target = e.currentTarget;

  // 이름이 비어있을 때 실행되는 함수
  if(target.value == ''){
    // 이름이 입력되지 않았으므로 검증 스타일을 초기화 하는 함수 실행
    Initialization(target);
    // 이름이 비어있으므로 nameCk를 false로 초기화
    nameCk = false;
  }
  // 이름이 형식에 맞지 않을 때 실행되는 함수
  if(!regExpName.exec(target.value)){
    // 이름이 형식에 맞지 않으므로 input 창 변경
    invalidate(target);
    // 이름이 형식에 맞지 않으므로 nameCk를 false를 초기화
    nameCk = false;
  }
  // 이름이 형식에 맞을 때 실행되는 함수
  if(regExpName.exec(target.value)){
    // 이름이 형식에 맞으므로 input 창 변경
    validated(target);
    // 이름이 형식에 맞으므로 nameCk를 true로 변경
    nameCk = true;
  }
});

// 이메일 입력 검증
f.mEmail.addEventListener('input', e=>{
  // mEamil을 target으로 지정해서 target 객체에 담음
  let target = e.currentTarget;

  // 이메일이 비어있을 때 실행되는 함수
  if(target.value == ''){
    // 이메일이 입력되지 않았으므로 검증 스타일을 초기화 하는 함수 실행
    Initialization(target);
    // 이메일이 비어있으므로 emailCk를 false로 초기화
    emailCk = false;
  }
  // 이메일이 형식에 맞지 않을 때 실행되는 함수
  else if(!regExpEmail.exec(target.value)){
    // 이메일이 형식에 맞지 않으므로 input 창 변경
    invalidate(target);
    // 이메일이 형식에 맞지 않으므로 emailCk를 false로 초기화
    emailCk = false;
  }
  // 이메일이 형식에 맞을 때 실행되는 함수
  else if(regExpEmail.exec(target.value)){
    // 이메일이 형식에 맞으므로 input 창 변경
    validated(target);
    // 이메일이 형식에 맞으므로 emailCk를 true로 변경
    emailCk = true;
  }
});

// 회원가입
function join(){
  // post 방식 - json 전달
  // 1. 데이터 검증 완료
  // Ck들중 하나라도 false라면 실행 모두 true라면 다음 단계로 넘어감
  if(!idCk || !pwCk || !pwReCk || !nameCk || !emailCk){
    alert('입력 내용을 확인해주세요.');
    return;   
  }

  // 2. form 데이터들을 json으로 변경
  let formData = new FormData(f);
  let jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
  
  // 3. fetch로 데이터 통신
  // post 방식으로 데이터를 보호하여 전송
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
        // insert에 성공하여 1을 리턴 받은 경우 실행
        if(data.result == 1){
          // 회원가입 성공 알림
          alert("회원가입이 성공했습니다.");
          // 메인 페이지로 이동
          location.href = 'MemberController?cmd=mainPage';
        // insert에 실패하여 0을 리턴 받은 경우 실행
        }else{
          // 회원가입 실패 알림
          alert("회원가입이 실패했습니다.");
        }
    })
    .catch(err => console.log(err));
}