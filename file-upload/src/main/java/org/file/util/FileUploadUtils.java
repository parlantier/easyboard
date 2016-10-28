package org.file.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class FileUploadUtils {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadUtils.class);

	public static String uploadUtil(String uploadPath, String originalFileName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();
		// 파일명 중복방지를위해 랜덤한숫자를 덧붙여줌
		String savedName = uid.toString() + "_" + originalFileName;
		// 디렉토리를 날짜에 맞게 생성하고 해당 경로를 반환함
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);

		// 원본 파일저장
		FileCopyUtils.copy(fileData, target);

		// 이미지타입인지 아닌지 검사하기위해 포멧명을 추출
		String formatName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		// 이미지타입일 경우 makethumbnail함수 실행
		// 아닐경우 makeIcon 메서드 실행
		if (MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			// 문자열 치환용도의 함수
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		// 업로드한 파일명을 반환
		return uploadedFileName;
	}

	// 디렉토리 생성 메서드
	public static String calcPath(String uploadPath) {

		// 년-월-일 별로 디렉토리 경로를 지정해줄 문자열생성
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);

		return datePath;
	}

	// 실제 디렉토리 생성 함수
	public static void makeDir(String uploadPath, String... paths) {

		// 하위디렉토리가 이미 존재한다면 return
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		// 년-월-일 별로 디렉토리생성
		for (String path : paths) {

			File dirPath = new File(uploadPath + path);

			// 해당디렉토리가 존재하지않는다면 생성
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}

	}

	// 썸네일 처리를하는 함수
	public static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		// BufferedImage 메모리상의 이미지를 의미하는 객체
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		// 정해진 크기에맞게 사이즈를 지정해줌
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		// 썸네일 이름을 설정 파일명앞에 s_붙어줌
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		// 썸네일이름을가진 파일객체생성
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		// 썸네일 생성코드
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// 문자열 치환용도의 메서드
	private static String makeIcon(String uploadPath, String path, String fileName) {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
