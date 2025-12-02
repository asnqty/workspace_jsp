<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 파일을 업로드 할 때 get을 쓰면 URL에 다 담지 못해 post 방식 사용, 인코딩을 거쳐야 함 -->
		<form action="upload.jsp" method="post" enctype="multipart/form-data">
			<p>업로더 <input type="text" name="uploader"> </p>
			<p>첨부파일 <input type="file" name="filename"> </p>
			<input type="submit" value="업로드">
		</form>
	</div>
</body>
</html>