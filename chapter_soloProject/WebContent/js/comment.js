/* ----------------- form 관련 요소들 ----------------- */
const cf = document.forms[1];

// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    // 버튼을 구분하기 위한 필드 type
    let type = btn.id;
	
	// 댓글 작성
    if(type === 'insertCommentBtn'){
      insertComment();
	  cf.reset();
    }
	// 댓글 리셋
    if(type === 'resetCommentBtn'){
      cf.reset();
    }
  });
});

function insertComment(){
	let writer = cf.writer;
	let content = cf.content;
	let b_idx = cf.b_idx;
	
	if(content.value == ""){
        alert(`내용을 입력해주세요.`);
        f.content.focus();
        return;
    }

	const params = {
   		cmd : 'insertComment',
    	writer : writer.value,
		content : content.value,
		b_idx : b_idx.value
  	}
	
	const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');

	fetch(`CController?${queryString}`)
    .then(response => response.json())
    .then(data =>{
		console.log(data.result)
		showCommentList();
	})  
    .catch(err => console.log(err))
}

// 댓글 출력 함수
function showCommentList(){
	const params = {
   		cmd : 'showCommentList',
		b_idx : f.b_idx.value
  	}

	const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');
	
	fetch(`CController?${queryString}`)
    .then(response => response.json())
    .then(data =>{
		console.log(data);
		
		let cList = data.cList;
    	let msg = '';


    	cList.forEach(cvo =>{
        	msg += `<tr>`;
        	msg += `<td>${cvo.writer}<input type="hidden" name="c_idx" value="${cvo.c_idx}"</td>`;
        	msg += `<td>${cvo.content}</td>`;
        	msg += `<td>${myTime(cvo.reg_date)}</td>`;

        	// ⭐ 본인 댓글만 버튼 출력
        	if(cvo.isMine){
           		msg += `<td>
                        	<button type="button" onclick="updateComment(${cvo.c_idx})">수정</button>
                  		</td>`;
				msg += `<td>
           				 	<button type="button" onclick="deleteComment(${cvo.c_idx})">삭제</button>
                  		</td>`;
        	} else {
            	msg += `<td></td>`;
            	msg += `<td></td>`;
        	}

        	msg += `</tr>`;
    });

    document.querySelector("#commBody").innerHTML = msg;
	})  
    .catch(err => console.log(err))
}
showCommentList();

// unixTimeStamp to date
function myTime(unixTimeStamp){
    // 1. 밀리초로 넘어오면 1000으로 나눠준다.
    let myDate = new Date(unixTimeStamp);

    let date = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
    return date;
}

// 댓글 수정
function updateComment(c_idx){
	ContentInput = prompt("수정할 댓글의 내용을 입력해주세요.");
	if(ContentInput != null){
		const params = {
	   		cmd : 'updateComment',
	    	c_idx : c_idx,
			content : ContentInput
	  	}
	
		const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');
	
		fetch(`CController?${queryString}`)
	    .then(response => response.json())
	    .then(data =>{
			showCommentList();
		})  
	    .catch(err => console.log(err))
	}
}

// 댓글 삭제
function deleteComment(c_idx){
	if(confirm("댓글을 삭제하시겠습니까?")){
		
		const params = {
	   		cmd : 'deleteComment',
	    	c_idx : c_idx,
	  	}
	
		const queryString = Object.keys(params).map(key => encodeURIComponent(key) + "=" + encodeURIComponent(params[key])).join('&');
	
		fetch(`CController?${queryString}`)
	    .then(response => response.json())
	    .then(data =>{
			showCommentList();
		})  
	    .catch(err => console.log(err))
	}
}