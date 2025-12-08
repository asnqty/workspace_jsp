package org.chan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chan.service.CommentService;
import org.chan.service.CommentServiceImpl;
import org.chan.vo.CVO;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 분기 판단 cmd
		String cmd = request.getParameter("cmd");
		
		// 비동기 처리를 위한 객체들
		ObjectMapper objectMapper = null;	// JSON과 java 객체를 변환
		String jsonString = null;	// JSON으로 직렬화 된 데이터를 담는 용도
		PrintWriter out = response.getWriter();	// 응답 객체
		JSONObject obj = new JSONObject();	// 응답으로 보내줄 객체
		
		// DB 데이터를 다루기 위한 객체들
		CVO cvo = null;
		CommentService cservice = new CommentServiceImpl();
		
		switch(cmd) {
		case "insertComment" :
			cvo = new CVO();
			cvo.setWriter(request.getParameter("writer"));
			cvo.setPw(request.getParameter("pw"));
			cvo.setContent(request.getParameter("content"));
			cvo.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			cvo.setIp(Inet4Address.getLocalHost().getHostAddress());
			
			// 메소드 insertComment(cvo)
			// 아이디 insert_commnet
			cservice.insertComment(cvo);
			obj.put("result", "success");
			break;
			
		// 댓글 출력
		case "commList" :
			int b_idx = Integer.parseInt(request.getParameter("b_idx"));
			List<CVO> cList = cservice.getCommList(b_idx);
			// java에서 javascript 영역으로 데이터를 보내기에 json 타입으로 파싱해서 보냄
			objectMapper = new ObjectMapper();
			jsonString = objectMapper.writeValueAsString(cList);
			
			obj.put("cList", jsonString);
			break;
			
		// 댓글 삭제
		case "removeComm" :
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			cservice.removeComm(c_idx);
			obj.put("result", "success");
			break;
		}
		
		out.print(obj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
