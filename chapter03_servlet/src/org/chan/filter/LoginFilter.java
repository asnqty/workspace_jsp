package org.chan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/private/*")
public class LoginFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("로그인 필터 동작 확인중...");
		
		// 1. 로그인 된 클라이언트인지 확인
		// HttpSession 필요 => HttpServletSession 필요
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("id");
		String pw = (String)session.getAttribute("pw");
		
		if(id != null && pw != null) {
			chain.doFilter(request, response);
		}else {
			HttpServletResponse rep = (HttpServletResponse)response;
			// contextpath가 챕터명과 같기에 사용 가능
			String cPath = req.getContextPath();
			rep.sendRedirect(cPath + "/login/loginForm.jsp");
		}
	}
	
	@Override
	// 필터가 끝나고 실행될 함수
	public void destroy() {
		Filter.super.destroy();
	}
}
