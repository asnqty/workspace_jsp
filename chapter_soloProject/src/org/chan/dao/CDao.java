package org.chan.dao;

import java.util.List;

import org.chan.vo.BCVO;
import org.chan.vo.CVO;

public interface CDao {
	// 댓글 작성 함수
	public int insertComment(CVO cvo);
	
	// 댓글 출력 함수
	public List<CVO> getCommentList(int b_idx);
	
	// 댓글 수정 함수
	public int updateComment(CVO cvo);
	
	// 댓글 삭제 함수
	public int deleteComment(int c_idx);
	
	// 유저의 댓글을 가져오는 함수
	public List<BCVO> getListWithUser(String writer);
}
