package org.chan.service;

import java.util.List;

import org.chan.dao.CDao;
import org.chan.dao.CDaoImpl;
import org.chan.vo.BCVO;
import org.chan.vo.CVO;

public class CServiceImpl implements CService{
	CDao cDao = CDaoImpl.getInstance();
	
	// 댓글 작성 함수
	@Override
	public int insertComment(CVO cvo) {
		return cDao.insertComment(cvo);
	}
	
	// 댓글 출력 함수
	@Override
	public List<CVO> getCommentList(int b_idx) {
		return cDao.getCommentList(b_idx);
	}
	
	// 댓글 수정 함수
	@Override
	public int updateComment(CVO cvo) {
		return cDao.updateComment(cvo);
	}
	
	// 댓글 삭제 함수
	@Override
	public int deleteComment(int c_idx) {
		return cDao.deleteComment(c_idx);
	}
	
	// 유저의 댓글을 가져오는 함수
	@Override
	public List<BCVO> getListWithUser(String writer) {
		return cDao.getListWithUser(writer);
	}
}
