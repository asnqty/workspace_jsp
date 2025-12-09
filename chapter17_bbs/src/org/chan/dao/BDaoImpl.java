package org.chan.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.chan.model.Criteria;
import org.chan.mybatis.config.DBService;
import org.chan.vo.BVO;

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
	
	// 모든 게시글 보기
//	@Override
//	public List<BVO> getList() {
//		return getSqlSession().selectList("bbs_select_all");
//	}
	
	// 페이징 게시글
	@Override
	public List<BVO> getListWithPaging(Criteria cri) {
		return getSqlSession().selectList("bbs_select_all_with_paging", cri);
	}
	
	// 전체 게시글 수 가져오기
	@Override
	public int getTotalRecordCount() {
		return getSqlSession().selectOne("total_count_of_bbs");
	}
	
	// 게시글 작성
	@Override
	public int getInsertBBS(BVO bvo) {
		int result = getSqlSession().insert("insert_bbs", bvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 게시글 내용 보기
	@Override
	public BVO getBBS(int b_idx) {
		return getSqlSession().selectOne("bbs_by_idx", b_idx);
	}
	
	// 게시글 삭제
	@Override
	public int removeBBS(int b_idx) {
		int result = getSqlSession().delete("delete_bbs", b_idx);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 게시글 수정
	@Override
	public int updateBBS(BVO bvo) {
		int result = getSqlSession().update("update_bbs", bvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 조회수 증가
	@Override
	public int updateHit(BVO bvo) {
		int result = getSqlSession().update("update_hit", bvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
}









