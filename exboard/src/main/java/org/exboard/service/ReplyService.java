package org.exboard.service;

import java.util.List;

import org.exboard.domain.Criteria;
import org.exboard.domain.ReplyVO;

public interface ReplyService{

	public List<ReplyVO> listPage(Integer bno,Criteria cri)throws Exception;

	public int count(Integer bno)throws Exception;

	public void register(ReplyVO vo)throws Exception;

	public void modify(ReplyVO vo)throws Exception;

	public void remove(Integer bno)throws Exception;	

}