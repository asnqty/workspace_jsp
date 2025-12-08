package org.chan.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.chan.mybatis.config.DBService;
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
	
	// 댓글 입력
	@Override
	public int insertComment(CVO cvo) {
		int result = getSqlSession().insert("insert_comment", cvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 댓글 출력
	@Override
	public List<CVO> getCommList(int b_idx) {
		return getSqlSession().selectList("commList", b_idx);
	}
	
	// 댓글 삭제
	@Override
	public int removeComm(int c_idx) {
		int result = getSqlSession().delete("delete_comment", c_idx);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
}
