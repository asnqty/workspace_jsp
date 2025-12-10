package org.chan.service;

import org.chan.vo.MemberVO;

public interface MemberService {
	//아이디를 검증하기 위한 메소드
	public int validateId(String mId);
	
	// 회원가입을 위한 메소드
	public int insertMember(MemberVO mvo);
	
	// 로그인시 기존 회원 확인을 위한 메소드
	public MemberVO doLogin(MemberVO mvo);
}
