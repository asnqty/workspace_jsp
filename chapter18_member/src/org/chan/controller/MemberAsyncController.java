package org.chan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chan.service.MemberService;
import org.chan.service.MemberServiceImpl;
import org.chan.vo.MemberVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/MemberAsyncController")
public class MemberAsyncController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberAsyncController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// fetch를 통해 비동기로 데이터를 받는 컨트롤러
		// 쿼리스트링으로 넘어오는 데이터는 파라미터로 받을 수 있지만, json 데이터는 그렇지 못하기 때문에
		// 두 방식에 따라서 처리를 하는 방식이 바뀌게 된다.
		
		// 쿼리 스트링으로 들어오는 cmd를 저장하기 위한 방식
		// controller로 전송된 데이터중 cmd의 값을 받아 switch문에서 기능을 구분
		String cmd = request.getParameter("cmd");
		
		// 비동기를 처리하기 위한 과정
		// json과 java 객체들을 변환할 때 사용
		ObjectMapper objectMapper = null;
		// json으로 직렬화 된 데이터를 담는 용도
		String jsonString = null;
		// 응답 객체
		PrintWriter out = response.getWriter();
		// 응답 객체에 보내줄 객체
		JSONObject obj = new JSONObject();
		// json 데이터를 저장하기 위한 객체
		StringBuilder sb = new StringBuilder();
		// json 데이터가 들어온 객체
		BufferedReader reader = request.getReader();
		String line;
		
		// 1. json 데이터를 StringBuilder에 저장
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		// 데이터는 sb에 담겨진 상태이기 때문에 검증은 sb.toString()으로 문자열을 출력해서 가능
		
		// 2. json 데이터를 자바 객체로 저장
		if(!sb.toString().isEmpty()) {
			try {
				obj = (JSONObject)new JSONParser().parse(sb.toString());
			}catch(ParseException e) {
				e.printStackTrace();
			}
		}
		
		// json으로 들어오는 cmd 파라미터는 obj 객체 안에 key, value 형태로 저장되어 있음
		if(cmd == null) {
			// json으로 들어오는 cmd를 저장하기 위한 방식
			cmd = (String)obj.get("cmd");
		}
		
		// service를 사용하기 위한 service 객체 생성
		MemberService mservice = new MemberServiceImpl();
		// service에 데이터를 전송하기 위한 데이터를 담는 객체 mvo 생성
		MemberVO mvo = null;
		
		// 로그인 상태 확인을 위한 session 생성
		HttpSession session = request.getSession();
		
		// controller의 기능을 마치고 이동할 경로를 담을 필드 path 생성
		String path = "";
		
		// 전달받은 cmd로 기능을 구분해서 실행
		switch(cmd) {
		// 아이디를 검증하기 위한 코드
		case "validateId":
			// 전달받은 mId 파라미터의 값을 저장
			String mId = request.getParameter("mId");
			// validateId 메소드에 mId를 매개 변수로 던져주어 실행, 그 결과를 obj에 result라는 파라미터로 저장
			// 중복된 아이디가 있다면 1을 리턴, 없다면 0을 리턴 받아서 저장
			obj.put("result", mservice.validateId(mId));
			break;
		
		// 회원가입을 하기 위한 코드
		case "join":
			// json을 통해 전달 받은 아이디, 비밀번호, 이름, 이메일을 vo 객체에 저장
			mvo = new MemberVO();
			mvo.setmId((String)obj.get("mId"));
			mvo.setmPw((String)obj.get("mPw"));
			mvo.setmName((String)obj.get("mName"));
			mvo.setmEmail((String)obj.get("mEmail"));
			// insertMember 메소드에 mvo 객체를 매개 변수로 던져주어 실행, 그 결과를 obj에 result라는 파라미터로 저장
			// insert에 성공하면 1을 리턴, 실패하면 0을 리턴 받아서 저장
			// obj 객체에 정보가 담겨 있으므로, 비워준 뒤 result를 저장
			obj.clear();
			obj.put("result", mservice.insertMember(mvo));
			break;
			
		// 로그인을 하기 위한 코드
		case "login":
			// json을 통해 전달받은 아이디, 비밀번호를 vo 객체에 저장
			mvo = new MemberVO();
			mvo.setmId((String)obj.get("mId"));
			mvo.setmPw((String)obj.get("mPw"));
			// doLogin 메소드에 mvo 객체를 매개 변수로 던져주어 실행, 그 결과를 MemberVO 객체 member에 저장
			MemberVO member	= mservice.doLogin(mvo);
			
			// obj 객체에 정보가 담겨 있으므로, 비워줌
			obj.clear();
			// member 객체에 데이터가 있다면 session에 저장하고 obj의 result에 success 저장
			if(member != null) {
				session.setAttribute("member", member);
				obj.put("result", "success");
			}
			// member 객체에 데이터가 없다면 session에 저장하지 않고 obj의 result에 fail 저장
			else if(member == null) {
				obj.put("result", "fail");
			}
		}
		
		out.print(obj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
