package com.watermark.serviceInfa;

import java.awt.Color;
import java.awt.Font;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface MarkService {
	
	public static final String MARK_TEXT = "晨~光";
	
	public static final String FONT_NAME = "微软雅黑";
	public static final int FONT_STYLE = Font.BOLD;		//黑体
	public static final int FONT_SIZE = 120;				//文字大小
	public static final Color FONT_COLOR = Color.red;	//文字颜色
	public static final int X = 10;		//文字坐标
	public static final int Y = 10;
	
	public static float ALPHA = 0.3F;				//文字水印透明度
	public static final String LOGO = "logo.jpg";	//图片形式水印
	
	
	
	public String watermark(CommonsMultipartFile image,String uploadPath,String realUploadPath);
}
