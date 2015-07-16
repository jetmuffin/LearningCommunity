<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题详情</title>
</head>
<body>

标题：${question.title}
<br/>
描述：${question.content }
<br/>
标签：${question.tag }
<br/>
阅读数：${question.readNums }
<br/>
回答数：${question.answerNums }
<br/>
回复：


</body>
</html>