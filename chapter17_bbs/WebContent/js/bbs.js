// 게시글 삽입 페이지 이동 함수
function moveInsertPage(){
    location.href = 'BBSController?cmd=insertBBSPage';
}
// 목록으로 이동하는 함수
function view_all(){
    location.href = 'BBSController?cmd=allList';
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
    f.action = 'BBSController'
    f.submit();
}