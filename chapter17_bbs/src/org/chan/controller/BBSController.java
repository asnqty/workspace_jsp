package org.chan.controller;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.service.BBSService;
import org.chan.service.BBSServiceImpl;
import org.chan.vo.BVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/BBSController")
public class BBSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BBSController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// 파일 업로드
		String realPath = request.getServletContext().getRealPath("/upload");
		MultipartRequest mr = null;
		
		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");
		if(cmd == null) { 
			// 파일 업로드 시 일반 request에서 받아올 수 없기 때문에 mr 객체 생성해서 파라미터를 받아온다.
			mr = new MultipartRequest(request, realPath, 1024 * 1024 * 10, "utf-8", new DefaultFileRenamePolicy());
			cmd = mr.getParameter("cmd");
		}
		
		boolean isForward = true;
		String path = "";
		BBSService bservice = new BBSServiceImpl();
		List<BVO> list = null;
		BVO bvo = null;
		HttpSession session = request.getSession();
		int b_idx;
		
		switch (cmd) {
		// 모든 게시글 보기
		case "allList" :
			list = bservice.getList();
			request.setAttribute("list", list);
			path = "bbs/allList.jsp";
			break;
		
		// 게시글 작성 페이지로 이동
		case "insertBBSPage" :
			path = "bbs/insert_page.jsp";
			break;
			
		// 게시글 작성
		case "insertBBS" :
			// 파라미터들을 꺼내서 vo에 저장
			// vo를 DB까지 전달
			bvo = new BVO();
			bvo.setWriter(mr.getParameter("writer"));
			bvo.setTitle(mr.getParameter("title"));
			bvo.setPw(mr.getParameter("pw"));
			bvo.setContent(mr.getParameter("content"));
//			bvo.setIp(request.getRemoteAddr());	// IPv6
			bvo.setIp(Inet4Address.getLocalHost().getHostAddress()); // IPv4
			
			// 첨부 파일 유무에 따라서 filename 값을 결정
			if(mr.getFile("filename") != null) {
				bvo.setFilename(mr.getFilesystemName("filename"));
			}else {
				bvo.setFilename("");
			}
			// getInsertBBS 메소드를 이용하여 실행
			// mapper와의 연계 ID = insert_bbs
			bservice.getInsertBBS(bvo);
			// insert를 마치고 가지고 갈 데이터가 없기에 리다이렉트 방식으로 이동 포워드로 이동해도 어차피 주소는 controller이다.
			isForward = false;
			path = "BBSController?cmd=allList";
			break;
			
		// 게시글 내용 보기
		case "view" :
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			// 1. 메소드를 통해 데이터 받아오기 - getBBS
			// 2. mapper와 연동 id : bbs_by_idx
			// 3. 가져온 데이터 session에 저장. 저장 이름은 "bvo"
			// 4. bbs/view.jsp로 이동
			bvo = bservice.getBBS(b_idx);
			session.setAttribute("bvo", bvo);
			path = "bbs/view.jsp";
			break;
			
		// 게시글 삭제
		case "remove" :
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			bservice.removeBBS(b_idx);
			isForward = false;
			path = "BBSController?cmd=allList";
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
