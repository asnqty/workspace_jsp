package org.chan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chan.service.MService;
import org.chan.service.MServiceImpl;
import org.chan.vo.MVO;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/MAsyncController")
public class MAsyncController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MAsyncController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 분기 판단용 cmd
		String cmd = request.getParameter("cmd");
		
		// MService를 사용하기 위한 객체
		MService mservice = new MServiceImpl();
		
		// 아이디와 닉네임 중복 여부 확인 후 결과 판단을 위한 객체
		int result = 0;
				
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
		
		// 매개변수로 던져줄 MVO 객체
		MVO mvo = null;
		
		switch(cmd) {
		// 회원가입 시도시 아이디 검증
		case "validatemId":
			String mId = request.getParameter("mId");
			// validateId 메소드에 mId를 매개 변수로 던져주어 실행, 그 결과를 obj에 result라는 파라미터로 저장
			// 중복된 아이디가 있다면 1을 리턴, 없다면 0을 리턴 받아서 저장
			obj.put("result", mservice.validatemId(mId));
			break;
		// 회원가입 시도시 닉네임 검증
		case "validatemName":
			String mName = request.getParameter("mName");
			// validateId 메소드에 mId를 매개 변수로 던져주어 실행, 그 결과를 obj에 result라는 파라미터로 저장
			// 중복된 아이디가 있다면 1을 리턴, 없다면 0을 리턴 받아서 저장
			obj.put("result", mservice.validatemName(mName));
			break;
		// 회원정보 수정시 비밀번호 확인
		case "validatemPw":
			mvo = new MVO();
			mvo.setmPw(request.getParameter("mPw"));
			mvo.setmName(request.getParameter("mName"));
			obj.put("result", mservice.validatemPw(mvo));
			break;
		}
		
		out.print(obj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
