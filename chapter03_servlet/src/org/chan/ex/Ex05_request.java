package org.chan.ex;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex05_request")
public class Ex05_request extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Ex05_request() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobbies");
		
		String hobbies2 = "";
		for (int i = 0; i < hobbies.length; i++) {
			if(i == hobbies.length - 1) {
				hobbies2 += hobbies[i];				
			}else {
				hobbies2 += hobbies[i] + ", ";				
			}
		}
		
		System.out.println("전달된 아이디는 " + id + "입니다.");
		System.out.println("전달된 비밀번호는 " + pw + "입니다.");
		System.out.println("전달된 이름은 " + name + "입니다.");
		System.out.println("전달된 이메일은 " + email + "입니다.");
		System.out.println("전달된 성별은 " + gender + "자입니다.");
		System.out.println("전달된 취미는 " + hobbies2 + "입니다.");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
