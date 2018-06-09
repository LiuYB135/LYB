package q.qq;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;


/*使用这个类需要导入的maven依赖
 * 
 * <dependency>
<groupId>com.baidu.aip</groupId>
<artifactId>java-sdk</artifactId>
<version>4.3.1</version>
</dependency>
*/

public class baiduaiTool {
    
	public static AipFace GetAipFace(String APP_ID,String API_KEY,String SECRET_KEY) {
		
		AipFace client = new AipFace(APP_ID, API_KEY,SECRET_KEY);
		return client;
	}
	/**
	 * 使用这个类的前提是有那个Img2Base64Util类
	 * 根据需要去调整类里面对应的参数，对options进行修改
	 * @param client -传入一个AipFace类
	 * @param base64ofimage -图片路径
	 * @param groupIdList -要识别的用户组数组， 用逗号分隔，上限20个
	 * @return -返回类型是jsonobject，要取什么自己再取
	 */
	
	public static JSONObject search(AipFace client,String image,String groupIdList) {
		  // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	 /*   options.put("user_id", "233451");*/
	    options.put("max_user_num", "1");
	    
	    Img2Base64Util base=new Img2Base64Util();

		   base.getImgStr(image);
	    String image2 =base.getImgStr(image);
	    String imageType = "BASE64";
	    String groupIdList2 = groupIdList;//用户组；人脸库里面有用户组，用户组里面有用户
	    
	    // 人脸搜索
	    JSONObject res = client.search(image2, imageType, groupIdList2, options);
        return res;
	}
	
	public static JSONObject addUser(AipFace client,String image,String groupId,String userId) {
		
	  	 HashMap<String, String> options = new HashMap<String, String>();
 	    options.put("user_info", "3");
 	    options.put("quality_control", "NORMAL");
 	    options.put("liveness_control", "LOW");
 	
	    Img2Base64Util base=new Img2Base64Util();
		   
 	    String image2 = base.getImgStr(image);
 	    String imageType = "BASE64";
 	    String groupId2 = groupId;
 	    String userId2 = userId;
 	    
 	    // 人脸注册
 	    JSONObject res = client.addUser(image2, imageType, groupId2, userId2, options);
 	   return res;
		
		
	}
	
	
	
	
	
	
	
	
	
}
