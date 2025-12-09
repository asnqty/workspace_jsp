// 게시글 삽입 페이지 이동 함수
function moveInsertPage(){
    location.href = 'BBSController?cmd=insertBBSPage';
}
// 목록으로 이동하는 함수
function view_all(){
	let pageNum = new URLSearchParams(location.search).get("pageNum");
	let amount = new URLSearchParams(location.search).get("amount");
	
	if(pageNum == null || amount == null){
		pageNum = 1;
		amount = 5;
	}
	
	let sendData = '&pageNum=' + pageNum + '&amount=' + amount;
    location.href = 'BBSController?cmd=allList' + sendData;
}
// 게시글 등록
function insert(f){
    if(f.writer.value == ''){
        alert("작성자는 필수로 입력해야 합니다.");
        f.writer.focus();
        return;
    }
    if(f.title.value == ''){
        alert("제목은 필수로 입력해야 합니다.");
        f.title.focus();
        return;
    }
    if(f.content.value == ''){
        alert("내용은 필수로 입력해야 합니다.");
        f.content.focus();
        return;
    }
    f.action = 'BBSController';
    f.submit();
}
// 게시글 삭제 함수
function removeBBS(b_idx){
    if(confirm('해당 게시글을 삭제하시겠습니까?')){
		let pageNum = new URLSearchParams(location.search).get("pageNum");
		let amount = new URLSearchParams(location.search).get("amount");
		let sendData = '&pageNum=' + pageNum + '&amount=' + amount;
        location.href = 'BBSController?cmd=remove&b_idx='+b_idx+sendData;
    }
}
// 게시글 수정 페이지로 이동
function updatePage(){
	let pageNum = new URLSearchParams(location.search).get("pageNum");
	let amount = new URLSearchParams(location.search).get("amount");
	let sendData = '&pageNum=' + pageNum + '&amount=' + amount;
    location.href = 'BBSController?cmd=updatePage' + sendData;
}
// 게시글 수정
function update(f){
    if(f.title.value == ''){
        alert("제목은 필수로 입력해야 합니다.");
        f.title.focus();
        return;
	}
	if(f.content.value == ''){
        alert("내용은 필수로 입력해야 합니다.");
        f.content.focus();
        return;
    }
	let pageNum = new URLSearchParams(location.search).get("pageNum");
	let amount = new URLSearchParams(location.search).get("amount");
	let sendData = '?pageNum=' + pageNum + '&amount=' + amount;
    f.action = 'BBSController'+sendData;
    f.submit();
}
// 페이지 버튼 클릭 이벤트
// jsp에서 링크를 바로 넣지 않고 페이지 버튼에 클릭 이벤트를 부여하는 이유
// jsp와 js의 역할을 구분하기 위함 (jsp는 값과 출력 화면만, js가 함수의 역할)
document.querySelectorAll('.page-nation li a').forEach(aEle=> {
    aEle.addEventListener('click', (e)=>{
        e.preventDefault();
        
        // href 속성에서 값 꺼내서 콘솔에 출력
        // e.target, e.currentTarget도 가능
        let pageNum = aEle.getAttribute("href");
        let sendData = 'cmd=allList&pageNum=' + pageNum + '&amount=5';
        location.href = 'BBSController?' + sendData;        
    });
});

// 게시글을 볼 때 pageNum과 amount와 cmd 총 3개의 정보를 담아서 보냄
document.querySelectorAll('.output tr td a').forEach(aEle=>{
    aEle.addEventListener('click', (e)=>{
        e.preventDefault();
        let pageNum = new URLSearchParams(location.search).get("pageNum");

        let b_idx = aEle.getAttribute("href");

		if(pageNum == null){
			pageNum = 1;
			amount = 5;
		}
        let sendData = 'cmd=view&b_idx=' + b_idx + '&pageNum=' + pageNum + '&amount=5';
        location.href = 'BBSController?' + sendData;
    });
});