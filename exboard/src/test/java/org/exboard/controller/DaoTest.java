package org.exboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;
import org.exboard.domain.PageMaker;
import org.exboard.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

public class DaoTest {

	@Inject
	private BoardDAO dao;
	
	private static final Logger logger =
			LoggerFactory.getLogger(DaoTest.class);
	
	@Test
	public void paging(){
		
		Criteria cri = new Criteria();
		cri.setPage(13);
		cri.setPerPageNum(10);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(dao.getCount());
		List<BoardVO> list = dao.listPage(cri);
		logger.info(pageMaker.toString());
		for(BoardVO vo : list){
			logger.info("vo["+vo.getBno()+"]"+vo.toString());
		}
		
		
	}
	
	
	/*@Test
	public void listAll(){
		
		List<BoardVO> list = dao.listAll();
		
		for(BoardVO vo : list){
			logger.info("vo["+vo.getBno()+"] : "+vo.toString());
		}
	}*/
	
	/*	@Test
	public void insert(){
		
		BoardVO vo = new BoardVO();
		vo.setTitle("갑동이");
		vo.setWriter("김작가");
		vo.setContent("웹툰");
		
		dao.insert(vo);
	}
	
	@Test
	public void read(){
		
		BoardVO vo = dao.read(2);
		logger.info("read: "+vo.toString());
	}*/
	
	/*@Test
	public void update(){
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setTitle("검은그림자");
		vo.setContent("롤롤");
		dao.update(vo);
	}
	
	@Test
	public void delete(){
		
		dao.delete(4);
	}*/
	
}
