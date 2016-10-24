package org.reply.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.reply.domain.Criteria;
import org.reply.domain.ReplyVO;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession session;
	
	private String namespace = "org.reply.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		
		return session.selectList(namespace+".listPage", map);
	}

	@Override
	public int count(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".count", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".addReply", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete", rno);
	}

	@Override
	public ReplyVO confirm() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".confirm");
	}

}
