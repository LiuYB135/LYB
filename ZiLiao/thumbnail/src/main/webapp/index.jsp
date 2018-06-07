<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>====上传文件====</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  </head>
  <body>
    <h2>--图片上传--</h2>
    <hr/>
    <div align="center">
    	<form id="upload_form" enctype="multipart/form-data" method="POST" 
    		  action="${pageContext.request.contextPath }/thumbnail">
    		<h2>请选择上传图片</h2>
    		<div>
    			<input type="file" name="image" id="image"/><br/><br/>
    			<input type="submit" value="上传" />
    		</div>
    	</form>
    </div>
  </body>
</html>
