package com.thumbnail;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


	@RequestMapping("/thumbnail")
	public ModelAndView thumbnail(@RequestParam("image") CommonsMultipartFile file,HttpSession session)throws Exception{
		
		//上传后图片的路径（相对路径）
		String uploadPath = "\\images";
		//转化为在服务器上的绝对路径
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		/*realUploadPath=F:\apache-tomcat-8.0.46\webapps\thumbnail\images*/
		
		//原图在服务器上的相对路径信息（其中将文件上传）
		String imageUrl = uploadService.uploadImage(file, uploadPath, realUploadPath);
		//缩略图在服务器上的访问路径（其中将文件上传）
		String thumImageUrl = thumbnailService.thumbnail(file, uploadPath, realUploadPath);
		
		
		ModelAndView mv = new ModelAndView();
		//设置返回的参数信息
		mv.addObject("imageUrl",imageUrl);
		mv.addObject("thumImageUrl",thumImageUrl);
		//thumbnail:缩略图的名字
		mv.setViewName("thumbnail");
		
		return mv;
		
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello test!!";
	}
	
	
}
