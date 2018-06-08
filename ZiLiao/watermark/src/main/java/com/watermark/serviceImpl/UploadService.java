package com.watermark.serviceImpl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * description:图片文件上传服务类
 * @author lyb lcass
 *
 */
@Service
public class UploadService {

	public String uploadImage(CommonsMultipartFile image,
			String uploadPath,String realUploadPath){
		InputStream in = null;		//输入流
		OutputStream out = null;	//输出流
		try {
			
			in = image.getInputStream();	//创建输入流对象，指向上传对象
			//创建输出流对象，指向最终要保存的目标文件对象
			String des = realUploadPath+"\\"+image.getOriginalFilename();
			out = new FileOutputStream(des);
			
			byte[] buffer = new byte[1024];
			int len = 0;
			//上传图片
			while((len = in.read(buffer))>0){
				out.write(buffer, 0, len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close(); 	//关闭输入流
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close(); 	//关闭输出流
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		
		return uploadPath+"\\"+image.getOriginalFilename();	//相对路径
	}
}
