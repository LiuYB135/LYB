package com.thumbnail.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class UploadService {

	//文件上传，并返回文件在服务器上的路径
	public String uploadImage(CommonsMultipartFile file,String uploadPath,String realUploadPath){
		InputStream in = null;
		OutputStream out = null;
		
		try {
			//获取输入流信息
			in = file.getInputStream();
			
			//目标路径=服务器的绝对路径+文件名称
			String des = realUploadPath+"/"+file.getOriginalFilename();
			System.out.println(des);
			//输出流 指向 目标文件的路径
			out = new FileOutputStream(des);
			
			byte[] buffer = new byte[1024];
			int len = 0;
			//通过输入流读取字节，字节大小大于0，说明有内容
			while((len = in.read(buffer))>0){
				//输出流通过循环写出
				out.write(len);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭流
			if(in != null){
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		//返回目标路径
		return realUploadPath+"\\"+file.getOriginalFilename();
	}
	
}
