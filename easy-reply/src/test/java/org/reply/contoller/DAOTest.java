package org.reply.contoller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reply.domain.Criteria;
import org.reply.domain.PageMaker;
import org.reply.domain.ReplyVO;
import org.reply.persistence.ReplyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DAOTest {

	@Inject
	private ReplyDAO dao;
	
	private static final Logger logger =
						LoggerFactory.getLogger(DAOTest.class);
	
	
	
	
	
	
}
