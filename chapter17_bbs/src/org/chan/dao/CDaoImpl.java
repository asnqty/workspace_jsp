package org.chan.dao;

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
}
