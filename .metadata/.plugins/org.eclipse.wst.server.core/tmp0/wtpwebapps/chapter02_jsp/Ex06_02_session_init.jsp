<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션의 모든 정보 삭제 (초기화)
	session.invalidate();
	response.sendRedirect("Ex06_01_session.jsp");
%>