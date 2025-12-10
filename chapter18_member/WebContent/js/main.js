// 페이지의 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;
    // 버튼별로 controller에 전달할 데이터를 담는 필드 sendData
    let sendData = '';

    // id가 myPageBtn인 버튼의 sendData에 데이터 담기
    if(type === 'myPageBtn'){
      sendData = 'cmd=myPage';
    }
    // id가 loginBtn인 버튼의 sendData에 데이터 담기
    else if(type === 'loginBtn'){
      sendData = 'cmd=loginPage';
    }
    // id가 joinBtn인 버튼의 sendData에 데이터 담기
    else if(type === 'joinBtn'){
      sendData = 'cmd=joinPage';
    }

    // 버튼을 눌렀을 때 sendData의 데이터를 갖고 controller로 이동하는 링크 
    location.href = '/chapter18_member/MemberController?' + sendData;
  });
});