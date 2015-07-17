<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讨论</title>
</head>
<body>

	<c:forEach var="blog" items="${page.list}">
		博客标题：${blog.title}<br />
		博客内容：${blog.content }<br />
		发布者：${blog.user.userName }<br />
		<hr/>
	</c:forEach>
</body>
</html>