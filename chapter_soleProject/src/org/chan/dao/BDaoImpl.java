package org.chan.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.chan.model.Criteria;
import org.chan.mybatis.config.DBService;
import org.chan.vo.bbsVO;

public class BDaoImpl implements BDao{
	private static BDaoImpl instance = null;
	private BDaoImpl() {}
	public static BDaoImpl getInstance() {
		if(instance == null) {
			instance = new BDaoImpl();
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
	
	// 페이징 게시글 가져오기
	@Override
	public List<bbsVO> getListWithPaging(Criteria cri) {
		return getSqlSession().selectList("get_list_with_paging", cri);
	}
	
	// 전체 게시글 수 파악
	@Override
	public int getTotalCount() {
		return getSqlSession().selectOne("get_total_count");
	}
}
