package com.movielog.domain;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class fileUtil {
	
	private static final String filePath = "C:\\summernote_image\\";
	
	
	public static String updateImg(MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		String userimg = "";
		
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				userimg = storedFileName;
			}
		}
		return userimg;
	}
	

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
