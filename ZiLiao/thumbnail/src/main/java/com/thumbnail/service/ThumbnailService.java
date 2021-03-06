package com.thumbnail.service;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class ThumbnailService {

	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	
	public String thumbnail(CommonsMultipartFile file,String uploadPath,String realUploadPath){
		
		try {
			//缩略图在服务器上的路径
			String des = realUploadPath+"\\thum_"+file.getOriginalFilename();
			/*des=F:\apache-tomcat-8.0.46\webapps\thumbnail\image\thum_微信图片_20180605222732.jpg*/
			
		
			//通过原图的输入流，获得原图的数据信息，;keepAspectRatio(false):不强制进行等比缩略;指定大小;toFile(des):保存到服务器上
			Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*\images\thum_微信图片_20180605222732.jpg*/
		//缩略图的访问路径
		return uploadPath + "\\thum_" + file.getOriginalFilename();
	}
	
}
