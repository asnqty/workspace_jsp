package org.chan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.service.CService;
import org.chan.service.CServiceImpl;
import org.chan.vo.CVO;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/CController")
public class CController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 분기 판단용 cmd
		String cmd = request.getParameter("cmd");
		
		// 비동기 처리를 위한 객체들
		ObjectMapper objectMapper = null;	// JSON과 java 객체를 변환
		String jsonString = null;	// JSON으로 직렬화 된 데이터를 담는 용도
		PrintWriter out = response.getWriter();	// 응답 객체
		JSONObject obj = new JSONObject();	// 응답으로 보내줄 객체
		
		// DB 데이터를 다루기 위한 객체들
		CVO cvo = null;
		CService cservice = new CServiceImpl();
		
		// 댓글 작성자와 로그인 된 유저 정보 비교
		HttpSession session = request.getSession();
		String mName = (String) session.getAttribute("mName");
		
		switch(cmd) {
		// 댓글 작성
		case "insertComment":
			cvo = new CVO();
			cvo.setWriter(request.getParameter("writer"));
			cvo.setContent(request.getParameter("content"));
			cvo.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			obj.put("result", cservice.insertComment(cvo));
			break;
		
		// 댓글 출력
		case "showCommentList":
			int b_idx = Integer.parseInt(request.getParameter("b_idx"));
			List<CVO> cList = cservice.getCommentList(b_idx);

			List<Map<String, Object>> resultList = new ArrayList<>();

			// 댓글의 작성자와 로그인 된 유저의 닉네임을 비교하여 댓글의 수정 삭제 버튼을 보일지 말지 결정하기 위한
			// isMine 필드가 CVO에는 없어서 map에 담아서 정보를 보내는 과정
		    for (CVO cvo1 : cList) {
		        Map<String, Object> map = new HashMap<>();
		        map.put("c_idx", cvo1.getC_idx());
		        map.put("writer", cvo1.getWriter());
		        map.put("content", cvo1.getContent());
		        map.put("reg_date", cvo1.getReg_date().toString());

		        boolean isMine = mName != null && mName.equals(cvo1.getWriter());
		        map.put("isMine", isMine);

		        resultList.add(map);
		    }
		    obj.put("cList", resultList);
		    break;
		    
		// 댓글 수정
		case "updateComment":
			cvo = new CVO();
			cvo.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
			cvo.setContent(request.getParameter("content"));
			obj.put("result", cservice.updateComment(cvo));
			break;
			
		// 댓글 수정
		case "deleteComment":
			int c_idx = Integer.parseInt(request.getParameter("c_idx"));
			obj.put("result", cservice.deleteComment(c_idx));
			break;
		}
		
		out.print(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
