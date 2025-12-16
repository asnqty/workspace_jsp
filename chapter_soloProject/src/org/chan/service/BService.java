package org.chan.service;

import java.util.List;

import org.chan.model.Criteria;
import org.chan.vo.BVO;

public interface BService {
	// 페이징 게시글 가져오기
		public List<BVO> getListWithPaging(Criteria cri);
		
	// 전체 게시글 수 파악
	public int getTotalCount();
	
	// 게시글 작성
	public int insertbbs(BVO bvo);
	
	// 회원이 작성한 게시글 가져오기
	public List<BVO> getListWithUser(String writer);
	
	// 게시글 내용 출력
	public BVO getbbs(int b_idx);
	
	// 게시글 수정
	public int updatebbs(BVO bvo);
	
	// 게시글 삭제
	public int deletebbs(int b_idx);
}
