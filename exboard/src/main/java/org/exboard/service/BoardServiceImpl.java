package org.exboard.service;

import java.util.List;

import javax.inject.Inject;

import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;
import org.exboard.persistence.BoardDAO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public List<BoardVO> listPage(Criteria cri) {
		// TODO Auto-generated method stub
		return dao.listPage(cri);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	@Override
	public List<BoardVO> listAll() {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public void register(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public BoardVO read(Integer bno) {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void remove(Integer bno) {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}
	
	

}
