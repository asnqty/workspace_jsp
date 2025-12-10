package org.chan.service;

import org.chan.dao.MemberDao;
import org.chan.dao.MemberDaoImpl;
import org.chan.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	// MemberDao를 사용하기 위한 객체 생성
	private MemberDao mDao = MemberDaoImpl.getInstance();
	
	// 아이디를 검증하기 위한 메소드
	@Override
	public int validateId(String mId) {
		return mDao.validateId(mId);
	}
	
	// 회원가입을 위한 메소드
	@Override
	public int insertMember(MemberVO mvo) {
		return mDao.insertMember(mvo);
	}
	// 로그인시 기존 회원 확인을 위한 메소드
	@Override
	public MemberVO doLogin(MemberVO mvo) {
		return mDao.doLogin(mvo);
	}
}
