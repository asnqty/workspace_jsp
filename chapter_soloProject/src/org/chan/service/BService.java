package org.chan.service;

import java.util.List;

import org.chan.model.Criteria;
import org.chan.vo.bbsVO;

public interface BService {
	// 페이징 게시글 가져오기
		public List<bbsVO> getListWithPaging(Criteria cri);
		
	// 전체 게시글 수 파악
	public int getTotalCount();
}
