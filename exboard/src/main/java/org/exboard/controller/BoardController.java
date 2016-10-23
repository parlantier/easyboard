package org.exboard.controller;

import javax.inject.Inject;

import org.exboard.domain.BoardVO;
import org.exboard.domain.Criteria;
import org.exboard.domain.PageMaker;
import org.exboard.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public void write(){
		
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri")Criteria cri 
			,Model model)throws Exception{
		
		logger.info("list페이지 요청");
		logger.info("cri: "+cri.toString());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.getCount());
		
		logger.info("pageMaker: " + pageMaker.toString());
		
		model.addAttribute("list", service.listPage(cri));
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String register(RedirectAttributes rttr
						, BoardVO vo)throws Exception{
		
		logger.info("글저장");
		service.register(vo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}

	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void readGET(@RequestParam("bno")int bno
						,@ModelAttribute("cri") Criteria cri
						,Model model)throws Exception{
		logger.info("read페이지호출");
		model.addAttribute(service.read(bno));
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(Model model
						, @ModelAttribute("cri") Criteria cri
						, int bno){
		
		logger.info("수정페이지호출");
		model.addAttribute(service.read(bno));
		
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modifyPOST(RedirectAttributes rttr
							, BoardVO vo
							, Criteria cri)throws Exception{
		
		logger.info("수정기능실행");
		service.modify(vo);
		rttr.addFlashAttribute("msg", "SUCCESS");
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/board/read";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String removePOST(RedirectAttributes rttr,
							Criteria cri,int bno)throws Exception{
		
		logger.info("삭제기능 호출");
		service.remove(bno);
		rttr.addFlashAttribute("msg", "deleted");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/board/list";
	}
}
