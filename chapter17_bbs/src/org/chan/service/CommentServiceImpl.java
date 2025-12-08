package org.chan.service;

import java.util.List;

import org.chan.dao.CDao;
import org.chan.dao.CDaoImpl;
import org.chan.vo.CVO;

public class CommentServiceImpl implements CommentService{
	private CDao cdao = CDaoImpl.getInstance();
	
	// 댓글 입력
	@Override
	public int insertComment(CVO cvo) {
		return cdao.insertComment(cvo);
	}
	
	// 댓글 출력
	@Override
	public List<CVO> getCommList(int b_idx) {
		return cdao.getCommList(b_idx);
	}
	
	// 댓글 삭제
	@Override
	public int removeComm(int c_idx) {
		return cdao.removeComm(c_idx);
	}
}
