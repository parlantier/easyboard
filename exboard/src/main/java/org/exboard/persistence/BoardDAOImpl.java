package org.exboard.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.exboard.mapper.BoardMapper";
	
	@Override
	public List<BoardVO> listAll() {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listAll");
	}

	@Override
	public BoardVO read(Integer bno) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer bno) {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete", bno);
	}

	@Override
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		session.insert(namespace+".create", vo);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".listPage", cri);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".count");
	}

}
