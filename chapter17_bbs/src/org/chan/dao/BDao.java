package org.chan.dao;

import java.util.List;

import org.chan.model.Criteria;
import org.chan.vo.BVO;

public interface BDao {
	// 모든 게시글 보기
//	public List<BVO> getList();
	
	// 페이징 게시글
	public List<BVO> getListWithPaging(Criteria cri);
	
	// 전체 게시글 수 가져오기
	public int getTotalRecordCount();
	
	// 게시글 작성
	public int getInsertBBS(BVO bvo);
	
	// 게시글 내용 보기
	public BVO getBBS(int b_idx);
	
	// 게시글 삭제
	public int removeBBS(int b_idx);
	
	// 게시글 수정
	public int updateBBS(BVO bvo);
	
	// 조회수 증가
	public int updateHit(BVO bvo);
}
