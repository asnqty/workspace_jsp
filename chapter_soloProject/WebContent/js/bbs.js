/* ----------------- form 관련 요소들 ----------------- */
const f = document.forms[0];


// 버튼 요소들 가져오기
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', () => {
		// 버튼을 구분하기 위한 필드 type
		let type = btn.id;

		// 게시글 작성 페이지로 이동 버튼
		if (type === 'moveInsertbbsBtn') {
			moveInsertbbs();
		}
		// 게시글 작성 버튼
		if (type === 'insertbbsBtn') {
			insertbbs(f);
		}
		// 게시글 작성 리셋 버튼
		if (type === 'insertbbsResetBtn') {
			f.reset();
		}
		// 메인 페이지로 이동 버튼
		if (type === 'moveAllListBtn') {
			moveAllList();
		}
		// 게시글 수정 페이지로 이동 버튼
		if (type === 'moveUpdateBBSPageBtn') {
			moveUpdateBBSPage();
		}
		// 게시글 수정
		if (type === 'updateBBSBtn') {
			updateBBS();
		}
		// 게시글로 돌아가기
		if (type === 'moveViewBtn') {
			moveView();
		}
		// 게시글 수정 페이지 리셋
		if (type === 'resetUpdateBBSBtn') {
			f.reset();
		}
		// 게시글 삭제
		if (type === 'deleteBBSBtn') {
			deleteBBS();
		}
	});
});

// 메인 페이지로 이동
function moveAllList() {
	f.action = 'BController'
	f.cmd.value = 'allList';
	f.submit();
}

// 게시글 작성 페이지로 이동
function moveInsertbbs() {
	location.href = 'BController?cmd=moveInsertbbsPage';
}

// 게시글 작성
function insertbbs(f) {
	let title = f.title;
	let content = f.content;

	if (title.value == '') {
		alert("제목을 작성해주세요");
		return;
	}
	if (content.value == '') {
		alert("내용을 작성해주세요");
		return;
	}

	f.action = 'BController'
	f.submit();
}

// 게시글을 볼 때 pageNum과 cmd, pageNum 총 3개의 정보를 담아서 보냄
/*document.querySelectorAll('.output tr td a').forEach(aEle=>{
    aEle.addEventListener('click', (e)=>{
        e.preventDefault();

		const form = document.createElement("form");
		form.method = "post";
		form.action = "BController";
		
		let pageNum = document.querySelector("#pageNum");
		const input = document.createElement("input");
		input.type = "hidden";
		input.name = "pageNum";
		if(pageNum == null){
			input.value = 1;			
		}else{
			input.value = pageNum.value;
		}
		
        let b_idx = aEle.getAttribute("href");
		const input2 = document.createElement("input");
		input2.type = "hidden";
		input2.name = "b_idx";
		input2.value = b_idx;
		
		const input3 = document.createElement("input");
        input3.type = "hidden";
        input3.name = "cmd";
        input3.value = "view";
		
        form.appendChild(input);
        form.appendChild(input2);
        form.appendChild(input3);

		document.body.appendChild(form);
		form.submit();
    });
});*/

// 페이지 버튼 클릭 이벤트
document.querySelectorAll('.page-nation li a').forEach(aEle => {
	aEle.addEventListener('click', (e) => {
		e.preventDefault();

		const form = document.createElement("form");
		form.method = "post";
		form.action = "BController";

		let pageNum = aEle.getAttribute("href");
		const input = document.createElement("input");
		input.type = "hidden";
		input.name = "pageNum";
		input.value = pageNum;

		const input2 = document.createElement("input");
		input2.type = "hidden";
		input2.name = "cmd";
		input2.value = "allList";

		form.appendChild(input);
		form.appendChild(input2);

		document.body.appendChild(form);
		form.submit();
	});
});

// 검색한 후 페이지 버튼 클릭 이벤트
document.querySelectorAll('.page-nations li a').forEach(aEle => {
	aEle.addEventListener('click', (e) => {
		e.preventDefault();

		const form = document.createElement("form");
		form.method = "get";
		form.action = "BController";

		let pageNum = aEle.getAttribute("href");
		const input = document.createElement("input");
		input.type = "hidden";
		input.name = "pageNum";
		input.value = pageNum;

		const input2 = document.createElement("input");
		input2.type = "hidden";
		input2.name = "cmd";
		input2.value = "searchbbs";
		
		let searchType = document.querySelector("#searchType")
		const input3 = document.createElement("input")
		input3.type = "hidden";
		input3.name = "searchType";
		input3.value = searchType.value;
		
		let keyword = document.querySelector("#keyword")
		const input4 = document.createElement("input")
		input4.type = "hidden";
		input4.name = "keyword";
		input4.value = keyword.value;

		form.appendChild(input);
		form.appendChild(input2);
		form.appendChild(input3);
		form.appendChild(input4);

		document.body.appendChild(form);
		form.submit();
	});
});

// 게시글 수정 페이지로 이동
function moveUpdateBBSPage() {
	f.action = 'BController'
	f.submit();
}

// 게시글로 돌아가기
function moveView() {
	location.href = 'BController?cmd=view&b_idx=' + f.b_idx.value + '&pageNum=' + f.pageNum.value;
}

// 게시글 수정
function updateBBS() {
	f.action = 'BController'
	f.submit();
}

// 게시글 삭제
function deleteBBS() {
	if (confirm("게시글을 삭제하시겠습니까?")) {
		f.action = 'BController'
		f.cmd.value = 'deleteBBS';
		f.submit();
	}
}