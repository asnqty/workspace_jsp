package org.chan.service;

import java.util.List;

import org.chan.dao.BDao;
import org.chan.dao.BDaoImpl;
import org.chan.vo.BVO;

public class BBSServiceImpl implements BBSService{
	private BDao bdao = BDaoImpl.getInstance();
	
	@Override
	public List<BVO> getList() {
		return bdao.getList();
	}
}
