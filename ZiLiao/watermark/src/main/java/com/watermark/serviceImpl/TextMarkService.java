package com.watermark.serviceImpl;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.watermark.serviceInfa.MarkService;

@Service
public class TextMarkService implements MarkService {

	@Override
	public String watermark(CommonsMultipartFile image, String uploadPath,
			String realUploadPath) {
		//定义目标文件输出的名称
		String logoFileName = "logo_"+image.getOriginalFilename();
		OutputStream out = null;
		
		
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
			g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
			g.setColor(FONT_COLOR);
			
			//获取文字水印的宽度和高度值
			int width1 = FONT_SIZE*getTextLength(MARK_TEXT);	//文字水印的宽度
			int height1 = FONT_SIZE;				//文字水印的高度
			
			//计算原图和文字水印的宽度和高度之差
			int widthDiff = width - width1;		//宽度之差
			int heightDiff= height - height1;	//高度之差
			int x = X;	//横坐标
			int y = Y;	//纵坐标
			
			//保证文字水印在右下角显示
			if(x < widthDiff){
				x = widthDiff;
			}
			if(y < heightDiff){
				y = heightDiff;
			}
			
			//水印透明度设置
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			//绘制文字水印
			g.drawString(MARK_TEXT, x, y );
			//释放工具
			g.dispose();
			
			//创建文件输出流，指向最终的目标文件
			out = new FileOutputStream(realUploadPath+"\\"+logoFileName);
			
			//5.创建图像编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(out);
			//6.使用图像编码工具类，输出缓存图像到目标文件
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

	//文本长度的处理：文字水印的中英文字符的宽度转换
	public int getTextLength(String text){
		int length = text.length();
		
		for(int i=0;i<text.length();i++){
			String s = String.valueOf(text.charAt(i));
			if(s.getBytes().length>1){	//中文字符
				length++;
			}
		}
		length = length%2 == 0?length/2:length/2+1;  //中文和英文字符的转换
		return length;
	}
}
