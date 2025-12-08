package org.chan.dao;

import java.util.List;

import org.chan.vo.CVO;

public interface CDao {
	public int insertComment(CVO cvo);
	public List<CVO> getCommList(int b_idx);
	public int removeComm(int c_idx);
}
