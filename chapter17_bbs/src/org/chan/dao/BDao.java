package org.chan.dao;

import java.util.List;

import org.chan.model.Criteria;
import org.chan.vo.BVO;

public interface BDao {
//	public List<BVO> getList();
	public List<BVO> getListWithPaging(Criteria cri);
	public int getInsertBBS(BVO bvo);
	public BVO getBBS(int b_idx);
	public int removeBBS(int b_idx);
	public int updateBBS(BVO bvo);
	public int updateHit(BVO bvo);
}
