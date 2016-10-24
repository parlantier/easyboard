package org.reply.persistence;

import java.util.List;

import org.reply.domain.Criteria;
import org.reply.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

	public int count(Integer bno) throws Exception;

	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;

	public void delete(Integer rno) throws Exception;
	
	public ReplyVO confirm()throws Exception;
	
}
