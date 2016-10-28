package org.file.controller;

import org.file.util.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value="/file/*")
@Controller
public class UploadController {

	
	public String uploadPath = "C:\\zzz\\upload";
	
	
	
	private static final Logger logger =
			LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model) throws Exception{
		
		logger.info(file.getOriginalFilename());
		logger.info(file.getContentType());
		logger.info(""+file.getSize());
		
		String uploadFile = 
				FileUploadUtils.uploadUtil(uploadPath, file.getOriginalFilename(), file.getBytes());
		model.addAttribute("msg","SUCCESS");
		logger.info(uploadFile);
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST)
	public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
		
		ResponseEntity<String> entity = null;
		try{
		String uploadFileName = 
				FileUploadUtils.uploadUtil(
						uploadPath, file.getOriginalFilename(), file.getBytes());

		entity = new ResponseEntity<>(uploadFileName, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
