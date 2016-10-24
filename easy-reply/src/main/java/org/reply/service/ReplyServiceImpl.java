package org.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.reply.domain.Criteria;
import org.reply.domain.ReplyVO;
import org.reply.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.count(bno);
	}

	@Override
	public void addReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.create(vo);
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void remove(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(rno);
	}

}
