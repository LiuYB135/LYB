<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改图书</title>
</head>
<body>
    <form action="/book/uodate" method="post">
        <input type="hidden" name="id"  value="${book.id}" />
        图书名称：<input type="text" name="name" value="${book.name}" /><br/>
        图书作者：<input type="text" name="name" value="${book.auther}" /><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>