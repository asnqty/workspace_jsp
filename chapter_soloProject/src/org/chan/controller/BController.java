package org.chan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chan.model.Criteria;
import org.chan.model.PageDTO;
import org.chan.service.BService;
import org.chan.service.BServiceImpl;
import org.chan.vo.bbsVO;

@WebServlet("/bbsController")
public class BController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 분기 판단용 cmd
		String cmd = request.getParameter("cmd");
		
		// forward 방식으로 이동 여부를 결정하기 위한 객체
		boolean isForward = true;
		
		// controller의 기능을 마치고 이동할 경로를 담은 객체
		String path = "";
		
		// BService를 사용하기 위한 객체
		BService bservice = new BServiceImpl();
		
		// select한 결과가 여럿일 때 받아오기 위한 List
		List<bbsVO> list = null;
		
		// paging을 위한 객체
		String pageNum = "";
		int parsePageNum = 0;
		Criteria cri = new Criteria();
		cri.setAmount(10);
		
		switch(cmd) {
		case "allList":
			pageNum = request.getParameter("pageNum");
			
			// pageNum 값을 URL을 통해 받아왔다면 그 값을 저장
			if(pageNum != null) {
				parsePageNum = Integer.parseInt(pageNum);
			}
			// pageNum 값을 URL을 통해 받지 못했다면 1페이지로 이동
			else {
				parsePageNum = 1;
			}
			
			// cri 객체에 parsePageNum 저장
			cri.setPageNum(parsePageNum);

			// 페이징 게시글 수 가져오기
			list = bservice.getListWithPaging(cri);
			
			// 전체 게시글 수 파악
			int total = bservice.getTotalCount();
			
			// pateDTO 객체 생성
			PageDTO pdto = new PageDTO(cri, total);
						
			// 게시글 및 페이징 객체를 request객체로 전달
			request.setAttribute("list", list);
			request.setAttribute("pageMaker", pdto);

			path = "bbs/allList.jsp";
			break;
		}
		
		// forward 방식으로 이동할지 redirect 방식으로 이동할지 여부 결정
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
