package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

/**
 * 该工具类实现了以下两个功能：
 * 1.将xml形式的数据转化为Map形式的数据
 * 2.将Bean对象封装的数据转换为Xml形式的数据
 * 
 * 需要引入的包：
 * 1.dom4j
 * 2.xstream
 * @author LYB
 *
 */
public class Changes {
	
	/**
	 * 将xml形式的数据转换成Map<String,String>形式
	 * @param request	携带着XML文件数据的HttpServletRequest请求
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream in = request.getInputStream();
		//通过输入流获取doc
		Document doc = reader.read(in);
		//获取其root标签
		Element root = doc.getRootElement();
		
		//获取root的子标签
		List<Element> list = root.elements();
		
		for (Element e : list) {
			//将xml的标签作为key，其文本作为value
			map.put(e.getName(), e.getText());
		}
		in.close();
		
		return map;
	}
	
	/**
	 * 将一个Bean对转化为XML的形式字符串
	 * @param ob
	 * @return
	 */
	public static String textMessageToXml(Object obj){
		XStream xstream = new XStream();
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}
	
}
