package org.chan.service;

import java.util.List;
import java.util.Set;

import org.chan.dao.BDao;
import org.chan.dao.BDaoImpl;
import org.chan.model.Criteria;
import org.chan.vo.BVO;

public class BServiceImpl implements BService{
	BDao bDao = BDaoImpl.getInstance();
	
	// 페이징 게시글 가져오기
	@Override
	public List<BVO> getListWithPaging(Criteria cri) {
		return bDao.getListWithPaging(cri);
	}
	
	// 전체 게시글 수 파악
	@Override
	public int getTotalCount() {
		return bDao.getTotalCount();
	}
	
	// 게시글 작성
	@Override
	public int insertbbs(BVO bvo) {
		return bDao.insertbbs(bvo);
	}
	
	// 회원이 작성한 게시글 가져오기
	@Override
	public List<BVO> getListWithUser(String writer) {
		return bDao.getListWithUser(writer);
	}
	
	// 게시글 출력
	@Override
	public BVO getbbs(int b_idx) {
		return bDao.getbbs(b_idx);
	}
	
	// 게시글 수정
	@Override
	public int updatebbs(BVO bvo) {
		return bDao.updatebbs(bvo);
	}
	
	// 게시글 삭제
	@Override
	public int deletebbs(int b_idx) {
		return bDao.deletebbs(b_idx);
	}
	
	// 작성자 페이징
	@Override
	public List<BVO> searchbbsWithWriter(Criteria cri) {
		return bDao.searchbbsWithWriter(cri);
	}
	
	// 작성자 게시글
	@Override
	public int searchbbsWithWriterCount(String keyword) {
		return bDao.searchbbsWithWriterCount(keyword);
	}
	
	// 제목 페이징
	@Override
	public List<BVO> searchbbsWithTitle(Criteria cri) {
		return bDao.searchbbsWithTitle(cri);
	}
	
	// 제목 게시글
	@Override
	public int searchbbsWithTitleCount(String keyword) {
		return bDao.searchbbsWithTitleCount(keyword);
	}
	
	// 내용 페이징
	@Override
	public List<BVO> searchbbsWithContent(Criteria cri) {
		return bDao.searchbbsWithContent(cri);
	}
	
	// 내용 게시글
	@Override
	public int searchbbsWithContentCount(String keyword) {
		return bDao.searchbbsWithContentCount(keyword);
	}
	
	// 조회수 증가
	@Override
	public int increaseHit(int b_idx, Set<Integer> viewSet) {
		
		// 조회수를 증가시킬지 말지의 변수
		boolean increaseHit = true;
		// 조회수 증가를 시켰는지 아닌지에 대한 결과를 반환하기 위한 변수
		int result = 0;
		
		// 전달받은 set에 같이 전달받은 글 번호가 있다면 조회수 증가 X
		if(viewSet.contains(b_idx)) {
			increaseHit = false;
		}
		// 없다면 조회수 증가 O, set에 글 번호 추가
		else {
			viewSet.add(b_idx);
		}
		// increaseHit가 true면 조회수를 증가시키는 함수 실행
		if(increaseHit) {
			result = bDao.increaseHit(b_idx);
		}
		
		// 조회수를 증가시켰다면 1을 반환, 아니라면 0을 반환
		return result;
	}
}
