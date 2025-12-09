package org.chan.controller;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.model.Criteria;
import org.chan.model.FileDownload;
import org.chan.model.PageDTO;
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
		String open = null;	// 세션 정보 저장
		
		// 페이징을 위한 cri 파라미터 변수
		String pageNum = "";
		String amount = "";
		int parsePageNum = 0;
		int parseAmount = 0;
		Criteria cri = new Criteria();
		
		switch (cmd) {
		// 모든 게시글 보기
		case "allList" :
			// 조회수를 증가시키는 변수
			open = (String)session.getAttribute("open");
			if(open != null) {
				session.removeAttribute("open");
			}
			
			pageNum = request.getParameter("pageNum");
			amount = request.getParameter("amount");
			
			if(pageNum != null && amount != null) {
				// 파라미터를 잘 전달 받으면 적용
				parsePageNum = Integer.parseInt(pageNum);
				parseAmount = Integer.parseInt(amount);
			}else {
				// 파라미터를 전달 받지 못하면 기본 값으로 초기화
				parsePageNum = 1;
				parseAmount = 5;
			}
			
			cri.setPageNum(parsePageNum);
			cri.setAmount(parseAmount);
			
			// 페에징 게시글 수 가져오기
//			list = bservice.getList();
			list = bservice.getListWithPaging(cri);
			
			// 전체 게시글 수 가져오기
			int total = bservice.getTotalRecordCount();
			// ID : total_count_of_bbs
			
			// pateDTO 객체 생성
			PageDTO pdto = new PageDTO(cri, total);
			
			// 게시글 및 페이징 객체를 request객체로 전달
			request.setAttribute("list", list);
			request.setAttribute("pageMaker", pdto);
			
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
			// 게시글을 가져오는 로직
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			// 1. 메소드를 통해 데이터 받아오기 - getBBS
			// 2. mapper와 연동 id : bbs_by_idx
			// 3. 가져온 데이터 session에 저장. 저장 이름은 "bvo"
			// 4. bbs/view.jsp로 이동
			bvo = bservice.getBBS(b_idx);
			
			// 조회수 증가 로직
			// 1. 상세 페이지에 접근시 세션에 정보를 저장
			// 2. 세션이 만료되기 전까지 조회수의 증가를 더 이상 하지 않는다. (새로고침 등으로 조회수 증가 방지)
			// 3. 메인 화면(allList.jsp)로 이동하게 되면 세션을 만료
			open = (String)session.getAttribute("open");
			if(open == null) {
				session.setAttribute("open", "yes");
				int hit = bvo.getHit() + 1;
				bvo.setHit(hit);
				bservice.updateHit(bvo);
				// 매퍼 아이디 update_hit
			}
			
			session.setAttribute("bvo", bvo);
			path = "bbs/view.jsp";
			break;
			
		// 게시글 삭제
		case "remove" :
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			bservice.removeBBS(b_idx);
			isForward = false;
			parsePageNum = Integer.parseInt(request.getParameter("pageNum"));
			parseAmount = Integer.parseInt(request.getParameter("amount"));
			path = "BBSController?cmd=allList" + "&pageNum=" + parsePageNum + "&amount=" + parseAmount;
			break;
			
		// 게시글 수정 페이지로 이동
		case "updatePage" :
			path = "bbs/update_page.jsp";
			break;
			
		// 게시글 수정
		case "update" :
			bvo = new BVO();
			bvo.setB_idx(Integer.parseInt(mr.getParameter("b_idx")));
			bvo.setTitle(mr.getParameter("title"));
			bvo.setPw(mr.getParameter("pw"));
			bvo.setContent(mr.getParameter("content"));
			
			File newFile = mr.getFile("filename");			// 새 첨부 파일
			String oldFile = mr.getParameter("oldfile");	// 기존 첨부 파일
			if(newFile != null) {	
				// 새 첨부 파일이 있을 때
				if(oldFile != null) {
					// 기존 파일이 있을 때
					File removeFile = new File(realPath+"/"+oldFile);
					if(removeFile.exists()) {
						// 누군가가 물리적으로 기존 파일을 삭제 했는지 파악, 안했으면 기존 파일 삭제
						removeFile.delete();
					}
				}
				bvo.setFilename(newFile.getName());
			}else {
				// 새 첨부 파일이 없을 때
				if(oldFile != null) {
					// 기존 파일이 있을 때
					bvo.setFilename(oldFile);
				}else {
					// 기존 파일이 없을 때
					bvo.setFilename("");
				}
			}
			bservice.updateBBS(bvo);
			isForward = false;
			
			parsePageNum = Integer.parseInt(request.getParameter("pageNum"));
			parseAmount = Integer.parseInt(request.getParameter("amount"));
			path = "BBSController?cmd=view&b_idx=" + bvo.getB_idx() + "&pageNum=" + parsePageNum + "&amount=" + parseAmount;
			break;
			
		// 파일 다운로드
		case "download" :
			FileDownload fd = new FileDownload();
			fd.doDownload(request, response);
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
