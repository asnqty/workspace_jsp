package org.chan.service;

import java.util.List;

import org.chan.dao.BDao;
import org.chan.dao.BDaoImpl;
import org.chan.model.Criteria;
import org.chan.vo.BVO;

public class BServiceImpl implements BService{
	BDao bDao = BDaoImpl.getInstance();
	
	// 페이징 게시글 가져오기
	@Override
	public List<BVO> getListWithPaging(Criteria cri) {
		return bDao.getListWithPaging(cri);
	}
	
	// 전체 게시글 수 파악
	@Override
	public int getTotalCount() {
		return bDao.getTotalCount();
	}
	
	// 게시글 작성
	@Override
	public int insertbbs(BVO bvo) {
		return bDao.insertbbs(bvo);
	}
	
	// 회원이 작성한 게시글 가져오기
	@Override
	public List<BVO> getListWithUser(String writer) {
		return bDao.getListWithUser(writer);
	}
	
	// 게시글 출력
	@Override
	public BVO getbbs(int b_idx) {
		return bDao.getbbs(b_idx);
	}
	
	// 게시글 수정
	@Override
	public int updatebbs(BVO bvo) {
		return bDao.updatebbs(bvo);
	}
	
	// 게시글 삭제
	@Override
	public int deletebbs(int b_idx) {
		return bDao.deletebbs(b_idx);
	}
}
