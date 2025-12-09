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
		// 서버에서 String을 던지면 json 대신 text로 사용
        .then(response => response.json())
        .then(data =>{
			console.log(data);
			showCommList();	
		})
        .catch(err => console.log(err));
}

// 댓글 출력
function showCommList(){
    // cmd와 b_idx를 던져줘야 함
    // javascript / sessio은 서버 영역
    let b_idx = new URLSearchParams(location.search).get("b_idx");

    let sendData = `cmd=commList&b_idx=${b_idx}`

    let msg = ``;

    fetch('CommentController?' + sendData)   // 경로, 데이터를 담음
        .then(response => response.json())
        .then(data =>{
			let cList = JSON.parse(data.cList);
			
			cList.forEach(cvo =>{
                msg += `<tr>`;
                msg += `<td>${cvo.c_idx}</td>`;
                msg += `<td>${cvo.writer}</td>`;
                msg += `<td>${cvo.content}</td>`;
                msg += `<td>${myTime(cvo.reg_date)}</td>`;
                msg += `<td><button type="button" onclick="removeComm(${cvo.c_idx})">삭제</button></td>`;
                msg += `</tr>`;
            });

            if(msg == ''){
                msg += `<tr>`
                msg += `<td colspan="5">댓글이 없습니다. </td>`
                msg += `</tr>`
            }

            document.querySelector("#commBody").innerHTML = msg;
            
		})
        .catch(err => console.log(err));
}
showCommList();

// unixTimeStamp to date
function myTime(unixTimeStamp){
    // 1. 밀리초로 넘어오면 1000으로 나눠준다.
    let myDate = new Date(unixTimeStamp);

    let date = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
    return date;
}

// 댓글 삭제
function removeComm(c_idx) {
	if(confirm("댓글을 삭제하시겠습니까?")){
		fetch('CommentController?cmd=removeComm&c_idx=' + c_idx)
	        .then(response => response.json())
	        .then(data =>{
				console.log(data);
				showCommList();	
			})
	     	.catch(err => console.log(err));
	}
}