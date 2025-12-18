package org.chan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.service.BService;
import org.chan.service.BServiceImpl;
import org.chan.service.CService;
import org.chan.service.CServiceImpl;
import org.chan.service.MService;
import org.chan.service.MServiceImpl;
import org.chan.vo.BCVO;
import org.chan.vo.BVO;
import org.chan.vo.MVO;


@WebServlet("/MController")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MController() {
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
				
		// MService를 사용하기 위한 객체
		MService mservice = new MServiceImpl();
		
		// BService를 사용하기 위한 객체
		BService bservice = new BServiceImpl();
		
		// CService를 사용하기 위한 객체
		CService cservice = new CServiceImpl();
		
		// MService에 파라미터를 전달하기 위한 MVO 객체
		MVO mvo = null;

		// 로그인 확인을 위한 session 객체
		HttpSession session = request.getSession();
		
		String mName = null;
		
		switch(cmd) {
		// 로그인 페이지로 이동
		case "moveLoginPage":
			path = "member/loginPage.jsp";
			break;
		// 회원가입 페이지로 이동
		case "moveJoinPage":
			path = "member/joinPage.jsp";
			break;
		// 회원가입
		case "joinMember":
			mvo = new MVO();
			mvo.setmId(request.getParameter("mId"));
			mvo.setmPw(request.getParameter("mPw"));
			mvo.setmName(request.getParameter("mName"));
			mservice.joinMember(mvo);
			isForward = false;
			path = "MController?cmd=moveLoginPage";
			break;
		// 로그인
		case "login":
			session.invalidate();
			session = request.getSession();
			mvo = new MVO();
			mvo.setmId(request.getParameter("mId"));
			mvo.setmPw(request.getParameter("mPw"));
			String result = mservice.login(mvo);
			if(result != null) {
				session.setAttribute("mName", result);
				isForward = false;
				path = "BController?cmd=allList";
			}
			else if(result == null) {
				isForward = false;
				path = "MController?cmd=moveLoginPage";
			}
			break;
		// 마이 페이지로 이동
		case "moveMyPage":
			String writer = (String) session.getAttribute("mName");
			// bservice에서 회원이 작성한 글 전부 가져오기
			List<BVO> list = bservice.getListWithUser(writer);
			request.setAttribute("list", list);
			// cservice에서 회원이 작성한 댓글 전부 가져오기
			List<BCVO> clist = cservice.getListWithUser(writer);
			request.setAttribute("clist", clist);
			path = "member/myPage.jsp";
			break;
		// 로그아웃
		case "logout":
			session.invalidate();
			isForward = false;
			path = "BController?cmd=allList";
			break;
		// 회원정보 수정 페이지로 이동
		case "moveUpdateMemberPage":
			mName = (String) session.getAttribute("mName");
			mvo = mservice.getMemberInfo(mName);
			request.setAttribute("mvo", mvo);
			path = "member/updateMemberPage.jsp";
			break;
		// 회원정보 수정
		case "updateMember":
			mvo = new MVO();
			mvo.setmId(request.getParameter("mId"));
			mvo.setmPw(request.getParameter("newmPw"));
			mvo.setmName(request.getParameter("newmName"));
			int result2 = mservice.updateMember(mvo);
			if(result2 == 1) {
				session.removeAttribute("mName");
				session.setAttribute("mName", request.getParameter("newmName"));
			}
			isForward = false;
			path = "MController?cmd=moveMyPage";
			break;
		// 회원탈퇴
		case "deleteMember":
			mName = (String) session.getAttribute("mName");
			mservice.deleteMember(mName);
			session.invalidate();
			isForward = false;
			path = "BController?cmd=allList";
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
