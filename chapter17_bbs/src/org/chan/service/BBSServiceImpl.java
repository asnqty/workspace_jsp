package org.chan.service;

import java.util.List;

import org.chan.dao.BDao;
import org.chan.dao.BDaoImpl;
import org.chan.model.Criteria;
import org.chan.vo.BVO;

public class BBSServiceImpl implements BBSService{
	private BDao bdao = BDaoImpl.getInstance();
	
	// 모든 게시글 보기
//	@Override
//	public List<BVO> getList() {
//		return bdao.getList();
//	}
	
	// 페이징 게시글
	@Override
	public List<BVO> getListWithPaging(Criteria cri) {
		return bdao.getListWithPaging(cri);
	}
	
	// 전체 게시글 수 가져오기
	@Override
	public int getTotalRecordCount() {
		return bdao.getTotalRecordCount();
	}
	
	// 게시글 작성
	@Override
	public int getInsertBBS(BVO bvo) {
		return bdao.getInsertBBS(bvo);
	}
	
	// 게시글 내용 보기
	@Override
	public BVO getBBS(int b_idx) {
		return bdao.getBBS(b_idx);
	}
	
	// 게시글 삭제
	@Override
	public int removeBBS(int b_idx) {
		return bdao.removeBBS(b_idx);
	}
	
	// 게시글 수정
	@Override
	public int updateBBS(BVO bvo) {
		return bdao.updateBBS(bvo);
	}
	
	// 조회수 증가
	@Override
	public int updateHit(BVO bvo) {
		return bdao.updateHit(bvo);
	}
}
