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
	
	// 페이징 게시글 가져오기
	@Override
	public List<BVO> getListWithPaging(Criteria cri) {
		return getSqlSession().selectList("get_list_with_paging", cri);
	}
	
	// 전체 게시글 수 파악
	@Override
	public int getTotalCount() {
		return getSqlSession().selectOne("get_total_count");
	}
	
	// 게시글 작성
	@Override
	public int insertbbs(BVO bvo) {
		int result = getSqlSession().insert("insert_bbs", bvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 회원이 작성한 게시글 가져오기
	@Override
	public List<BVO> getListWithUser(String writer) {
		return getSqlSession().selectList("get_list_with_user", writer);
	}
	
	// 게시글 내용 출력
	@Override
	public BVO getbbs(int b_idx) {
		return getSqlSession().selectOne("get_bbs", b_idx);
	}
	
	// 게시글 수정
	@Override
	public int updatebbs(BVO bvo) {
		int result = getSqlSession().update("update_bbs", bvo);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 게시글 삭제
	@Override
	public int deletebbs(int b_idx) {
		int result = getSqlSession().delete("delete_bbs", b_idx);
		if(result > 0) {
			getSqlSession().commit();
		}
		return result;
	}
	
	// 작성자 페이징
	@Override
	public List<BVO> searchbbsWithWriter(Criteria cri) {
		return getSqlSession().selectList("search_bbs_with_writer", cri);
	}
	
	// 작성자 게시글 수
	@Override
	public int searchbbsWithWriterCount(String keyword) {
		return getSqlSession().selectOne("search_bbs_with_writer_count", keyword);
	}
	
	// 제목 페이징
	@Override
	public List<BVO> searchbbsWithTitle(Criteria cri) {
		return getSqlSession().selectList("search_bbs_with_title", cri);
	}
		
	// 제목 게시글 수
	@Override
	public int searchbbsWithTitleCount(String keyword) {
		return getSqlSession().selectOne("search_bbs_with_title_count", keyword);
	}
	
	// 내용 페이징
	@Override
	public List<BVO> searchbbsWithContent(Criteria cri) {
		return getSqlSession().selectList("search_bbs_with_content", cri);
	}
	
	// 내용 게시글 수
	@Override
	public int searchbbsWithContentCount(String keyword) {
		return getSqlSession().selectOne("search_bbs_with_content_count", keyword);
	}
}
