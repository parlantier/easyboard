package org.exboard.service;

import java.util.List;

import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;

public interface BoardService {

	public List<BoardVO> listAll();
	
	public void register(BoardVO vo);
	
	public BoardVO read(Integer bno);
	
	public void modify(BoardVO vo);
	
	public void remove(Integer bno);
	
	public List<BoardVO> listPage(Criteria cri);
	
	public int getCount();
	
}
