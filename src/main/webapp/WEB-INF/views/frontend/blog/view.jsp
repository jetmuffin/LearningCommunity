<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客详情</title>
</head>
<body>

	标题：${blog.title}
	<br /> 描述：${blog.content }
	<br /> 标签：${blog.tag }
	<br /> 阅读数：${blog.readNums }
	<br /> 回答数：${blog.commentNums }
	<br /> 回复：

	<c:forEach var="comment" items="${comments}">
	${comment.userName} 回复：${comment.content}
	<br />
	</c:forEach>


</body>
</html>