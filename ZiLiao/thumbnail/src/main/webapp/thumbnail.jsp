<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>图片展示</title>
  </head>
  <body>
	<h4>图片展示</h4>
	<hr/>
	<table width="100%">
		<tr>
			<td width="50%" align="center">
				<img src="${pageContext.request.contextPath }${imageUrl}" width="500">
			</td>
			<td width="50%" align="center">
				<img src="${pageContext.request.contextPath }${thumImageUrl}" >
			</td>
		</tr>
	</table>
  </body>
</html>
