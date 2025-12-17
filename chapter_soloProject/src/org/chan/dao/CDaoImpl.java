package org.chan.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.chan.mybatis.config.DBService;
import org.chan.vo.BCVO;
import org.chan.vo.CVO;

public class CDaoImpl implements CDao{
	private static CDaoImpl instance = null;
	private CDaoImpl() {}
	public static CDaoImpl getInstance() {
		if(instance == null) {
			instance = new CDaoImpl();
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
	
	// 댓글 작성 함수
	@Override
	public int insertComment(CVO cvo) {
		int result = getSqlSession().insert("insert_comment", cvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 댓글 출력 함수
	@Override
	public List<CVO> getCommentList(int b_idx) {
		return getSqlSession().selectList("get_comment_list", b_idx);
	}
	
	// 댓글 수정 함수
	@Override
	public int updateComment(CVO cvo) {
		int result = getSqlSession().update("update_comment", cvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 댓글 삭제 함수
	@Override
	public int deleteComment(int c_idx) {
		int result = getSqlSession().delete("delete_comment", c_idx);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 유저의 댓글을 가져오는 함수
	@Override
	public List<BCVO> getListWithUser(String writer) {
		return getSqlSession().selectList("get_commentlist_with_user", writer);
	}
}