package com.watermark.serviceImpl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.watermark.serviceInfa.MarkService;

@Service
public class MoreImageMarkService implements MarkService{

	@Override
	public String watermark(CommonsMultipartFile image, String uploadPath,
			String realUploadPath) {
		//定义目标文件输出的名称
		String logoFileName = "logo_"+image.getOriginalFilename();
		OutputStream out = null;
		
		String logoPath = realUploadPath+"/"+LOGO;	//logo水印文件的具体路径
		
		try {
			//1.创建图片缓存对象
			Image image2 = ImageIO.read(image.getInputStream());	//解码原图
			int width = image2.getWidth(null);		//获取原图的宽度
			int height = image2.getHeight(null);	//获取原图的高度
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
			
			//2.创建java绘图工具
			Graphics2D g = bufferedImage.createGraphics();
			
			//3.使用绘图工具对象将原图绘制缓存对象中
			g.drawImage(image2, 0, 0, width, height, null);
			
			//4.使用绘图工具将水印（文字/图片）绘制到缓存图片对象		
			//创建logo文件,使用ImageIO流来读取文件
			File logo = new File(logoPath);	
			Image imageLogo = ImageIO.read(logo);
			
			//获取文字水印的宽度和高度值
			int width1 = imageLogo.getWidth(null);	//文字树荫的宽度
			int height1 = imageLogo.getHeight(null);//文字水印的高度
			
			//水印透明度设置
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			
			//旋转图片(30°)
			g.rotate(Math.toRadians(30),bufferedImage.getWidth()/2,bufferedImage.getHeight()/2);
			
			//设置水印的坐标
			int x= -width/2;
			int y= -height/2;
			
			while(x < width*1.5){
				y = -height/2;
				while(y < height*1.5){
					//绘制图片水印
					g.drawImage(imageLogo, x, y,null);
					y += height1 + 200;
				}
				x += width1 + 200;	//水印之间的间隔设为200
			}	
			//释放工具
			g.dispose();
			
			//创建文件输出流，指向最终的目标文件
			out = new FileOutputStream(realUploadPath+"\\"+logoFileName);
			
			//5.创建图像编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(out);
			//6.使用图像帮吗工具类，输出缓存图像到目标文件
			en.encode(bufferedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return uploadPath+"\\"+logoFileName;
	}

}
