package org.chan.service;

import org.chan.dao.MDao;
import org.chan.dao.MDaoImpl;
import org.chan.vo.MVO;

public class MServiceImpl implements MService{
	MDao mDao = MDaoImpl.getInstance();
	// 아이디 점검을 위한 함수
	@Override
	public int validatemId(String mId) {
		return mDao.validatemId(mId);
	}
	
	// 닉네임 점검을 위한 함수
	@Override
	public int validatemName(String mName) {
		return mDao.validatemId(mName);
	}
	
	// 회원 가입을 위한 함수
	@Override
	public int joinMember(MVO mvo) {
		return mDao.joinMember(mvo);
	}
	
	// 로그인을 위한 함수
	@Override
	public String login(MVO mvo) {
		return mDao.login(mvo);
	}
	
	// 회원정보 조회 함수
	@Override
	public MVO getMemberInfo(String mName) {
		return mDao.getMemberInfo(mName);
	}
	
	// 회원정보 수정 전 비밀번호 확인 함수
	@Override
	public int validatemPw(MVO mvo) {
		return mDao.validatemPw(mvo);
	}
	
	// 회원정보 수정
	@Override
	public int updateMember(MVO mvo) {
		return mDao.updateMember(mvo);
	}
	
	// 회원탈퇴
	@Override
	public int deleteMember(String mName) {
		return mDao.deleteMember(mName);
	}
}
