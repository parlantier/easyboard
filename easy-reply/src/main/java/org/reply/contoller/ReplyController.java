package org.reply.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.reply.domain.Criteria;
import org.reply.domain.PageMaker;
import org.reply.domain.ReplyVO;
import org.reply.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/replies/*")
@RestController
public class ReplyController {

	@Inject
	private ReplyService service;

	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("bno") Integer bno,
			@PathVariable("page") Integer page, Model model) throws Exception {

		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.count(bno));
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageMaker", pageMaker);
			map.put("list", service.listPage(bno, cri));
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ResponseEntity<String> addReply(@RequestBody ReplyVO vo){
		
		ResponseEntity<String> entity = null;
		try{
			service.addReply(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
		}catch(Exception e){
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	@RequestMapping(value="/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modifyReply(@RequestBody ReplyVO vo
										, @PathVariable("rno")Integer rno){
		
		ResponseEntity<String> entity = null;
		try{
			vo.setRno(rno);
			service.modify(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}	

	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeReply(@PathVariable("rno")Integer rno){
		
		ResponseEntity<String> entity = null;
		try{
			service.remove(rno);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
}
