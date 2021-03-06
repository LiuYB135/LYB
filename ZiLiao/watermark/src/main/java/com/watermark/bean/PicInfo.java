package com.watermark.bean;

import org.springframework.stereotype.Component;

/**
 * description:模型类
 * @author lyb lcass
 *
 */
@Component
public class PicInfo {
	private String imageURL;		//图片的返回路径
	private String logoImageURL;	//添加水印后的图片的返回路径
	
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getLogoImageURL() {
		return logoImageURL;
	}
	public void setLogoImageURL(String logoImageURL) {
		this.logoImageURL = logoImageURL;
	}
	
	
	
}
