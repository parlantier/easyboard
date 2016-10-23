/*package org.exboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.exboard.domain.Criteria;
import org.exboard.domain.PageMaker;
import org.exboard.domain.ReplyVO;
import org.exboard.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="replies")
public class ReplyController{

	@Inject
	private ReplyService service;

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@RequestMapping(value="/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> ListPage(@PathVariable("bno") Integer bno
													   ,@PathVariable("page") Integer page){

		logger.info("listPage...get");
		ResponseEntity<Map<String, Object>> entity = null;
		try{
			PageMaker pageMaker = new PageMaker();
			Criteria cri = new Criteria();
			
			cri.setPage(page);
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.count(bno));
			
			Map<String, Object> map = new HashMap<>();
			List<ReplyVO> list = service.listPage(bno, cri);
			
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			 e.printStackTrace();
			 entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 필요한값 vo 리턴값 httpstatus
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		
		ResponseEntity<String> entity = null;
		try{
			service.register(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
		}catch(Exception e){
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyPUT(@RequestBody ReplyVO vo){
		
		ResponseEntity<String> entity = null;
		try{
			service.modify(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(){
		
		ResponseEntity<String> entity = null;
		try{
			
		}catch(Exception){
		
	}
	

}*/
