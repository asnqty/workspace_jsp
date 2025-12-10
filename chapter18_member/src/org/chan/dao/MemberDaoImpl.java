package org.chan.dao;

import org.apache.ibatis.session.SqlSession;
import org.chan.mybatis.config.DBService;
import org.chan.vo.MemberVO;

public class MemberDaoImpl implements MemberDao{
	private static MemberDaoImpl instance = null;
	private MemberDaoImpl() {}
	public static MemberDaoImpl getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}
	
	private static SqlSession sqlsession = null;
	private synchronized static SqlSession getSqlSession() {
		if(sqlsession == null) {
			sqlsession = DBService.getFactory().openSession(false);
		}
		return sqlsession;
	}
	
	// 아이디를 검증하기 위한 메소드
	@Override
	public int validateId(String mId) {
		int result = getSqlSession().selectOne("validate_id", mId);
		return result;
	}
	
	// 회원가입을 위한 메소드
	@Override
	public int insertMember(MemberVO mvo) {
		int result = getSqlSession().insert("insert_member", mvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 로그인시 기존 회원 확인을 위한 메소드
	@Override
	public MemberVO doLogin(MemberVO mvo) {
		MemberVO member = getSqlSession().selectOne("do_login", mvo);
		return member;
	}
}
