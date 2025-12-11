package org.chan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// controller로 전송된 데이터중 cmd의 값을 받아 switch문에서 기능을 구분
		String cmd = request.getParameter("cmd");
		// 만약 전송된 cmd가 없다면 메인 페이지로 자동으로 이동하게 cmd를 초기화
		if(cmd == null) {
			cmd = "mainPage";
		}
		
		// controller의 기능을 마치고 이동할 경로를 담을 필드 path 생성
		String path = "";
		
		// 전달받은 cmd로 기능을 구분해서 실행
		switch(cmd) {
			// cmd가 mainPage일 경우 실행될 코드
			case "mainPage" :
				path = "index.jsp";
				break;
			// cmd가 myPage일 경우 실행될 코드
			case "myPage" :
				path = "member/myPage.jsp";
				break;
			// cmd가 loginPage일 경우 실행될 코드
			case "loginPage" :
				path = "member/loginPage.jsp";
				break;
			// cmd가 joinPage일 경우 실행될 코드
			case "joinPage" :
				path = "member/joinPage.jsp";
				break;
			// cmd가 logout일 경우 실행될 코드
			case "logout":
				HttpSession session = request.getSession();
				session.removeAttribute("member");
				path = "index.jsp";
				break;
		}
		
		// 모든 기능을 마치고 설정된 링크가 담긴 path 필드로 이동
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
