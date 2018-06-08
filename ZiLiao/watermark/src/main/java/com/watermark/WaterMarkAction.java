package com.watermark;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.watermark.bean.PicInfo;
import com.watermark.serviceImpl.ImageMarkService;
import com.watermark.serviceImpl.MoreImageMarkService;
import com.watermark.serviceImpl.MoreTextMarkService;
import com.watermark.serviceImpl.TextMarkService;
import com.watermark.serviceImpl.UploadService;

@Controller
public class WaterMarkAction{
	
	@Autowired
	private UploadService uploadService;	//上传文件服务类
	@Autowired
	private TextMarkService textMarkService;//文字水印类
	@Autowired
	private ImageMarkService imageMarkService;	//图片水印类
	@Autowired
	private MoreTextMarkService moreTextMarkService;	//多文字水印类
	@Autowired
	private MoreImageMarkService moreImageMarkService;	//多图像水印类
	
	/**
	 * 请求处理方法：负责接收图片上传以及水印添加的请求(上传单个文件)
	 * @return
	 */
	@RequestMapping("/watermark")
	public ModelAndView watermark(@RequestParam("image") CommonsMultipartFile[] image,HttpSession session){
		//相对路径
		String uploadPath = "\\images";
		//文件上传路径是基于相对路径获取的
		String realUploadPath = session.getServletContext().getRealPath(uploadPath);
		
		List<PicInfo> picInfo = new ArrayList<PicInfo>();	//创建数据模型
		if(image != null && image.length > 0){
			for(int i=0;i<image.length;i++){
				if(image[i] != null && image[i].getSize()>0){
					PicInfo pi = new PicInfo();
					
					//上传图片
					String imageURL = uploadService.uploadImage(image[i],uploadPath, realUploadPath);
					pi.setImageURL(imageURL);
					
					//加水印并上传
//					String logoImageURL = imageMarkService.watermark(image[i], uploadPath, realUploadPath);
//					String logoImageURL = textMarkService.watermark(image[i], uploadPath, realUploadPath);
					String logoImageURL = moreTextMarkService.watermark(image[i], uploadPath, realUploadPath);
//					String logoImageURL = moreImageMarkService.watermark(image[i], uploadPath, realUploadPath);
					pi.setLogoImageURL(logoImageURL);
					
					
					picInfo.add(pi);
				}
			}
		}
		
		
		//添加信息
		ModelAndView mv = new ModelAndView();
		mv.addObject("picInfo",picInfo);
		mv.setViewName("watermark");
		
		return mv;
	}		
}
