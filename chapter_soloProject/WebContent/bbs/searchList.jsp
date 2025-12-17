<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/member/main.jsp"/> <br>
	<div>
		<h1>게시판</h1>
		<input type="hidden" id="pageNum" value="${pageNum }">
		<input type="hidden" id="searchType" value="${searchType }">
		<input type="hidden" id="keyword" value="${keyword }">
		<table>
			<c:choose>
				<c:when test="${not empty sessionScope.mName}">
					<thead>
						<tr>
							<td colspan="5"><button id="moveInsertbbsBtn" type="button">게시글 작성</button></td>
						</tr>
					</thead>
				</c:when>
			</c:choose>
			<thead>
				<tr>
					<th>번호</th> 
					<th>제목</th> 
					<th>작성자</th> 
					<th>조회수</th> 
					<th>날짜</th> 
				</tr>
			</thead>
			<tbody class="output">
				<c:choose>
					<c:when test="${not empty list}">
							<c:forEach varStatus="vs" var="bvo" items="${list }">
								<tr>
									<td>${bvo.b_idx }</td>
									<td>
										<a href="BController?cmd=view&b_idx=${bvo.b_idx}&pageNum=1">
											${bvo.title }
										</a>
									</td>
									<td>${bvo.writer }</td>
									<td>${bvo.hit }</td>
									<td>${bvo.reg_date }</td>
								</tr>
							</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">작성된 글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<div class="page-wrap">
	   <ul class="page-nations">
	      <c:if test="${pageMaker.prev }">
	         <li class="previous">
	            <a href="${pageMaker.startPage-1}"> &lt; </a>
	         </li>
	      </c:if>
	      <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
	         <li>
	            <a href="${num}" class="${pageMaker.cri.pageNum == num ? 'active' : '' }"> ${num } </a>
	         </li>
	      </c:forEach>
	      <c:if test="${pageMaker.next }">
	         <li><a href="${pageMaker.endPage+1}"> &gt; </a></li>
	      </c:if>
	   </ul>
	</div>		
	
	<form method="get" action="BController">
    <input type="hidden" name="cmd" value="searchbbs">

    <select name="searchType">
        <option value="writer"
            <c:if test="${param.searchType == 'writer'}">selected</c:if>>
            작성자
        </option>
        <option value="title"
            <c:if test="${param.searchType == 'title'}">selected</c:if>>
            제목
        </option>
        <option value="content"
            <c:if test="${param.searchType == 'content'}">selected</c:if>>
            내용
        </option>
    </select>

    <input type="text"
           name="keyword"
           value="${param.keyword}"
           placeholder="검색어 입력">

    <button type="submit">검색</button>
</form>
</body>
<script type="text/javascript" src="js/bbs.js"></script>
</html>