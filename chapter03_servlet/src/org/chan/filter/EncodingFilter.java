package org.chan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("EncodingFilter doFilter() 동작중...");
		
		// 인코딩을 해줌
		if(request.getCharacterEncoding() == null){
			request.setCharacterEncoding(encoding);
		}
		
		// request와 response를 원래 가던 곳으로 보내는 메소드
		chain.doFilter(request, response);
	}
}
