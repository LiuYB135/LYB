<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传图片 -- watermark</title>
	<meta http-equiv="Content-type"  content="text/html;charset=UTF-8">
  </head>
  <body>
	<h4>上传图片</h4>
	<hr/>
	<form name="form1" action="${pageContext.request.contextPath }/watermark"
		method="post" enctype="multipart/form-data">
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<input type="file" name="image"/><br/>
		<br/>
		<input type="submit" value="上传图片" />
	</form>
  </body>
</html>
