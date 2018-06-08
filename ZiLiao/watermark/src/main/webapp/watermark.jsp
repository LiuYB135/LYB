<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>展示水印图片 -- watermark</title>
	<meta http-equiv="Content-type"  content="text/html;charset=UTF-8">
  </head>
  <body>
	<table width="99%" align="center">
		<c:forEach items="${picInfo }" var="item" >
		<tr>
			<td width="50%" align="center">
				<img src="${pageContext.request.contextPath }${item.imageURL }" width="550" />
			</td>
			<td width="50%" align="center">
				<img src="${pageContext.request.contextPath }${item.logoImageURL }" width="550" />
			</td>
		</tr>
		</c:forEach>
	</table>
  </body>
</html>
