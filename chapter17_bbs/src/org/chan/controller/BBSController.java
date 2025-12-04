package org.chan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chan.service.BBSService;
import org.chan.service.BBSServiceImpl;
import org.chan.vo.BVO;

@WebServlet("/BBSController")
public class BBSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BBSController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");
		if(cmd == null) cmd = "allList";
		
		boolean isForward = true;
		String path = "";
		BBSService bservice = new BBSServiceImpl();
		List<BVO> list = null;
		
		switch (cmd) {
		case "allList" :
			list = bservice.getList();
			request.setAttribute("list", list);
			path = "bbs/allList.jsp";
			break;
		
		case "insertBBSPage" :
			path = "bbs/insert_page.jsp";
			break;
		}
		
		if(isForward) {
			request.getRequestDispatcher(path).forward(request, response);
		}else {
			response.sendRedirect(path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
