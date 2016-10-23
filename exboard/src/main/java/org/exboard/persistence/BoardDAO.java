package org.exboard.persistence;

import java.util.List;

import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;

public interface BoardDAO {

	public List<BoardVO> listAll();
	
	public List<BoardVO> listPage(Criteria cri);
	
	public int getCount();
	
	public void insert(BoardVO vo);
	
	public BoardVO read(Integer bno);
	
	public void update(BoardVO vo);
	
	public void delete(Integer bno);
	
	
	
}
