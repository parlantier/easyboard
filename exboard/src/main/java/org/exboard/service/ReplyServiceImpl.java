package org.exboard.service;

import java.util.List;

import javax.inject.Inject;

import org.exboard.domain.Criteria;
import org.exboard.domain.ReplyVO;
import org.exboard.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl{

	@Inject
	private ReplyDAO dao;

	public List<ReplyVO> listPage(Integer bno,Criteria cri)throws Exception{

		return dao.listPage(bno, cri);
	}

	public int count(Integer bno)throws Exception{

		return dao.count(bno);
	}

	public void register(ReplyVO vo)throws Exception{

		dao.create(vo);
	}

	public void modify(ReplyVO vo)throws Exception{

		dao.update(vo);
	}

	public void remove(Integer bno)throws Exception{

		dao.delete(bno);
	}

}