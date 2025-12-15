package org.chan.service;

import java.util.List;

import org.chan.dao.BDao;
import org.chan.dao.BDaoImpl;
import org.chan.model.Criteria;
import org.chan.vo.bbsVO;

public class BServiceImpl implements BService{
	BDao bDao = BDaoImpl.getInstance();
	
	// 페이징 게시글 가져오기
	@Override
	public List<bbsVO> getListWithPaging(Criteria cri) {
		return bDao.getListWithPaging(cri);
	}
	
	// 전체 게시글 수 파악
	@Override
	public int getTotalCount() {
		return bDao.getTotalCount();
	}
}
