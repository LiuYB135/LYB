package com.thumbnail;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thumbnail.service.ThumbnailService;
import com.thumbnail.service.UploadService;

@Controller
@RequestMapping("/")
public class ThumbnailAction {
	@Autowired
	private UploadService uploadService;
	@Autowired
	private ThumbnailService thumbnailService;
	
	public UploadService getUploadService() {
		return uploadService;
	}

	public ThumbnailService getThumbnailService() {
		return thumbnailService;
	}



	@RequestMapping("/thumbnail")
	public ModelAndView thumbnail(@RequestParam("image") CommonsMultipartFile file,HttpSession session)throws Exception{
		
		System.out.println("============="+file);
		
		ModelAndView mv = new ModelAndView();
		//上传后图片的路径（相对路径）
		String uploadPath = "/images";
		//转化为在服务器上的绝对路径
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		System.out.println(realUploadPath);
		
		//原图在服务器上的相对路径信息
		String imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath);
		//缩略图在服务器上的访问路径
		String thumImageUrl = thumbnailService.thumbnail(file, uploadPath, realUploadPath);
		
		//设置返回的参数信息
		mv.addObject("imageUrl",imageUrl);
		mv.addObject("thumImageUrl",thumImageUrl);
		//thumbnail:缩略图的名字
		mv.setViewName("thumbnail");
		
		return mv;
		
	}
	
}
