// 댓글 작성
function insert_comment(f){
    if(f.writer.value == ""){
        alert(`작성자를 입력해주세요.`);
        f.writer.focus();
        return;
    }
    if(f.pw.value == ""){
        alert(`비밀번호를 입력해주세요.`);
        f.pw.focus();
        return;
    }
    if(f.content.value == ""){
        alert(`내용을 입력해주세요.`);
        f.content.focus();
        return;
    }

    let formData = new FormData(f);

    // 직렬화
    let serializedData = new URLSearchParams(formData).toString();

    // json
    // let jsonData = JSON.stringify(Object.fromEntries(formData.entries()));

    // console.log(serializedData);
    // console.log(jsonData);
    
    
    fetch('CommentController?' + serializedData)   // 경로, 데이터를 담음
        .then(response => response.json())
        .then(data =>{console.log(data);})
        .catch(err => console.log(err));
}