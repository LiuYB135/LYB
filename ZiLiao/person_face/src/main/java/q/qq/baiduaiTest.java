package q.qq;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
/*百度ai的人脸识别SDK类*/
public class baiduaiTest {
	 public static final String APP_ID = "11341839 ";
	 public static final String API_KEY = "hechRUbiXXtFluoCuwm6L88W";
	 public static final String SECRET_KEY = "V09dMtmSsG1RqDtjLLBUSKqNC1Z6dx0T";
		public static void main(String[] args) {
		      baiduaiTool baiduai=new baiduaiTool();
		      AipFace client=baiduai.GetAipFace(APP_ID, API_KEY, SECRET_KEY);
		      String image="G:\\10.jpg";
		    /*  JSONObject json=baiduai.addUser(client, image, "programming", "6");*/
		      JSONObject json=baiduai.search(client, image, "programming");
		      System.out.println(json.toString(2));
		      
		}
	
	
}
