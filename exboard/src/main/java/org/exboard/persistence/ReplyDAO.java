package org.exboard.persistence;

import java.util.List;

import org.exboard.domain.Criteria;
import org.exboard.domain.ReplyVO;

public interface ReplyDAO{

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

	public int count(Integer bno) throws Exception;

	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;

	public void delete(Integer bno) throws Exception;

}