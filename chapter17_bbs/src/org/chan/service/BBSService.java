package org.chan.service;

import java.util.List;

import org.chan.vo.BVO;

public interface BBSService {
	public List<BVO> getList();
	public int getInsertBBS(BVO bvo);
	public BVO getBBS(int b_idx);
	public int removeBBS(int b_idx);
}
