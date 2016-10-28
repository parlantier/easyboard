package org.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.file.util.FileUploadUtils;
import org.file.util.MediaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST,
					produces = "text/plain;charset=UTF-8")
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
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8")
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
	
	// 전송된 파일 표시하기
	// HttpHeaders객체를 생성하여 head값을 알맞게 지정한후 byte[]배열을
	// IOUtils.toByteArray(in) 메서드를 써서 보내준다
	@ResponseBody
	@RequestMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		// 파일명: 년/월/일/파일명 형태로 받음
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME : " + fileName);
		try{
			// 파일 확장자 추출
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			// 미디어타입을 지정하고 getMediaType메서드를 써서 알맞는 값을 리턴받음
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			// 헤더를 지정하는 객체 생성
			HttpHeaders headers = new HttpHeaders();
			
			// 해당파일에 스트림생성
			in = new FileInputStream(uploadPath + fileName);
			
			// 미디어 타입검사 
			if(mType != null){
				// 이미지 타입일경우 컨텐츠 타입을 미디어타입으로 지정해줌
				headers.setContentType(mType);
			}else{
				// 이미지 타입이아닐경우 파일이름앞에 랜덤id를 추출해서 
				// 파일이름을 가져옴
				// 컨텐츠타입을 APPLICATION_OCTET_STREAM으로 지정 
				// content-disposition값을 attachment file=파일명 으로지정
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename:\""+
				 new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			
			// IOUtils.toByteArray를 써서 해당파일 byte[]를 전송해줌
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
													headers,
													HttpStatus.CREATED);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		
		return entity;
	}
	
	// format추출해서 미디어파일과 일반파일을 분기시킨다음
	// 미디어파일일경우 파일이름을 알맞게 추출해서 원본파일과 썸네일파일을 삭제시킨다
	@ResponseBody
	@RequestMapping(value="/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){
		
		logger.info("delete file: " + fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		// 미디어 타입일경우 원본파일 삭제한다음
		// 썸네일 파일도 삭제
		if(mType != null){
			
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar))
			.delete();
			
		}
		
		new File(uploadPath + fileName.replace('/', File.separatorChar))
		.delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
}
