package org.chan.service;

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
}
