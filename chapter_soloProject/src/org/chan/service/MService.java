package org.chan.service;

import org.chan.vo.MVO;

public interface MService {
	// 아이디 중복 확인을 위한 함수
	public int validatemId(String mId);
	
	// 닉네임 중복 확인을 위한 함수
	public int validatemName(String mName);
	
	// 회원 가입을 위한 함수
	public int joinMember(MVO mvo);
	
	// 로그인을 위한 함수
	public String login(MVO mvo);
	
	// 회원 정보 수정을 위한 회원 정보 조회 함수
	public MVO getMemberInfo(String mName);
	
	// 회원정보 수정 전 비밀번호 확인 함수
	public int validatemPw(MVO mvo);
	
	// 회원정보 수정 함수
	public int updateMember(MVO mvo);
	
	// 회원탈퇴 함수
	public int deleteMember(String mName);
}
