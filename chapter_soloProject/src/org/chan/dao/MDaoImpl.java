package org.chan.dao;

import org.apache.ibatis.session.SqlSession;
import org.chan.mybatis.config.DBService;
import org.chan.vo.MVO;

public class MDaoImpl implements MDao{
	private static MDaoImpl instance = null;
	private MDaoImpl() {}
	public static MDaoImpl getInstance() {
		if(instance == null) {
			instance = new MDaoImpl();
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
	
	// 아이디 중복 확인을 위한 함수
	@Override
	public int validatemId(String mId) {
		return getSqlSession().selectOne("validate_mId", mId);
	}
	
	// 닉네임 중복 확인을 위한 함수
	@Override
	public int validatemName(String mName) {
		return getSqlSession().selectOne("validate_mName", mName);
	}
	
	// 회원 가입을 위한 함수
	@Override
	public int joinMember(MVO mvo) {
		int result = getSqlSession().insert("join_member", mvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 로그인을 위한 함수
	@Override
	public String login(MVO mvo) {
		return getSqlSession().selectOne("login", mvo);
	}
	
	// 회원정보 조회 함수
	@Override
	public MVO getMemberInfo(String mName) {
		return getSqlSession().selectOne("get_member_info", mName);
	}
	
	// 회원정보 수정 전 비밀번호 확인 함수
	@Override
	public int validatemPw(MVO mvo) {
		return getSqlSession().selectOne("validate_mPw", mvo);
	}
	
	// 회원정보 수정 함수
	@Override
	public int updateMember(MVO mvo) {
		int result = getSqlSession().update("update_member", mvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 회원탈퇴 함수
	@Override
	public int deleteMember(String mName) {
		int result = getSqlSession().delete("delete_member", mName);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
}
