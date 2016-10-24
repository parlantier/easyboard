package org.reply.service;

import java.util.List;

import org.reply.domain.Criteria;
import org.reply.domain.ReplyVO;


public interface ReplyService {

	public List<ReplyVO> listPage(Integer bno,Criteria cri)throws Exception;

	public int count(Integer bno)throws Exception;

	public void addReply(ReplyVO vo)throws Exception;

	public void modify(ReplyVO vo)throws Exception;

	public void remove(Integer rno)throws Exception;	
}
