package org.chan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.model.Criteria;
import org.chan.model.PageDTO;
import org.chan.service.BService;
import org.chan.service.BServiceImpl;
import org.chan.vo.BVO;

@WebServlet("/BController")
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
		List<BVO> list = null;
		
		// 글 번호를 받기 위한 객체
		int b_idx;
		
		// paging을 위한 객체
		String pageNum = "";
		int parsePageNum = 0;
		Criteria cri = new Criteria();
		cri.setAmount(10);
		BVO bvo = null;
		
		// 로그인 확인을 위한 session 객체
		HttpSession session = request.getSession();
		
		int total = 0;
		PageDTO pdto = null;
		
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
			total = bservice.getTotalCount();
			
			// pateDTO 객체 생성
			pdto = new PageDTO(cri, total);
						
			// 게시글 및 페이징 객체를 request객체로 전달
			request.setAttribute("pageNum", parsePageNum);
			request.setAttribute("list", list);
			request.setAttribute("pageMaker", pdto);

			path = "bbs/allList.jsp";
			break;
		
		// 게시글 작성 페이지로 이동
		case "moveInsertbbsPage":
			path = "bbs/insertbbsPage.jsp";
			break;
		
		// 게시글 작성
		case "insertbbs":
			bvo = new BVO();
			bvo.setWriter(request.getParameter("writer"));
			bvo.setTitle(request.getParameter("title"));
			bvo.setContent(request.getParameter("content"));
			
			bservice.insertbbs(bvo);
			
			path = "BController?cmd=allList";
			break;
			
		// 게시글 내용 보기
		case "view" :
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			bvo = bservice.getbbs(b_idx);
			request.setAttribute("bvo", bvo);
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			path = "bbs/view.jsp";
			break;
		
		// 게시글 내용 수정 페이지로 이동
		case "moveUpdatePage":
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			bvo = bservice.getbbs(b_idx);
			request.setAttribute("bvo", bvo);
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			path = "bbs/updateBBSPage.jsp";
			break;
			
		// 게시글 수정
		case "updateBBS":
			bvo = new BVO();
			bvo.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			bvo.setTitle(request.getParameter("title"));
			bvo.setContent(request.getParameter("content"));
			request.setAttribute("pageNum", request.getParameter("pageNum"));
			bservice.updatebbs(bvo);
			path = "BController?cmd=view";
			break;
			
		// 게시글 삭제
		case "deleteBBS":
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			bservice.deletebbs(b_idx);
			isForward = false;
			path = "BController?cmd=allList";
			break;
			
		// 게시글 검색
		case "searchbbs":
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
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");
			if(keyword == "") {
				isForward = false;
				path = "BController?cmd=allList";
			}
			else {
				cri.setKeyword(keyword);
				switch(searchType) {
				case "writer":
					list = bservice.searchbbsWithWriter(cri);
					total = bservice.searchbbsWithWriterCount(keyword);
					pdto = new PageDTO(cri, total);
					break;
				case "title":
					list = bservice.searchbbsWithTitle(cri);
					total = bservice.searchbbsWithTitleCount(keyword);
					pdto = new PageDTO(cri, total);
					break;
				case "content":
					list = bservice.searchbbsWithContent(cri);
					total = bservice.searchbbsWithContentCount(keyword);
					pdto = new PageDTO(cri, total);
					break;
				}
				request.setAttribute("searchType", searchType);
				request.setAttribute("keyword", keyword);
				request.setAttribute("pageNum", parsePageNum);
				request.setAttribute("list", list);
				request.setAttribute("pageMaker", pdto);
				
				path = "bbs/searchList.jsp";
			}
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
