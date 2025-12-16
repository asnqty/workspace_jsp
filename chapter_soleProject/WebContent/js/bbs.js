/* ----------------- form 관련 요소들 ----------------- */
const f = document.forms[0];


// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;
	
	// 게시글 작성 페이지로 이동 버튼
    if(type === 'moveInsertbbsBtn'){
      moveInsertbbs();
    }
	// 게시글 작성 버튼
    if(type === 'insertbbsBtn'){
      insertbbs(f);
    }
	// 게시글 작성 리셋 버튼
    if(type === 'insertbbsResetBtn'){
      f.reset();
    }
	// 메인 페이지로 이동 버튼
    if(type === 'moveAllListBtn'){
      moveAllList();
    }
  });
});

// 메인 페이지로 이동
function moveAllList(){
	location.href = 'BController?cmd=allList';
}

// 게시글 작성 페이지로 이동
function moveInsertbbs(){
	location.href = 'BController?cmd=moveInsertbbsPage';
}

// 게시글 작성
function insertbbs(f){
	let writer = f.writer;
	let title = f.title;
	let content = f.content;
	
	if(title.value == ''){
		alert("제목을 작성해주세요");
		return;
	}
	if(content.value == ''){
		alert("내용을 작성해주세요");
		return;
	}
	
	f.action = 'BController'
	f.submit();
}