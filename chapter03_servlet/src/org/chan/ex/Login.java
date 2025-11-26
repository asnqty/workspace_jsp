package org.chan.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// jsp에서는 기본 객체로 session이 존재하지만 java에는 존재하지 않아 request 객체로부터 정보를 받아 객체를 새로 만들어 줘야 한다.
		HttpSession session = request.getSession();
		
		session.setAttribute("id", id);
		session.setAttribute("pw", pw);
		
		response.sendRedirect("/chapter03_servlet/login/showUserInfo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
