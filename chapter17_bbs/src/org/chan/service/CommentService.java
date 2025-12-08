package org.chan.service;

import java.util.List;

import org.chan.dao.CDao;
import org.chan.vo.CVO;

public interface CommentService {
	public int insertComment(CVO cvo);
	public List<CVO> getCommList(int b_idx);
	public int removeComm(int c_idx);
}
