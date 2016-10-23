package org.exboard.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.exboard.domain.Criteria;
import org.exboard.domain.ReplyVO;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession session;

	private final String namespace = "org.exboard.mapper.ReplyMapper";

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception{

		Map<String, Object> map = new HashMap();
		map.put("bno", bno);
		map.put("cri", cri);
		return session.selectList(namespace+".listPage", map);
	}

	public int count(Integer bno) throws Exception{

		return session.selectOne(namespace+".count", bno);
	}

	public void create(ReplyVO vo) throws Exception{

		session.insert(namespace+".create", vo);
	}

	public void update(ReplyVO vo) throws Exception{

		session.update(namespace+".update", vo);
	}

	public void delete(Integer bno) throws Exception{

		session.update(namespace+".delete", bno);
	}

}