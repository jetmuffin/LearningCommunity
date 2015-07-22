<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - ${course.title}</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/run.css">
<link rel="stylesheet" href="/LearningCommunity/resources/js/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="/LearningCommunity/resources/css/lab-html.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="run" class="clearfix">
		<div id="text" class="l">
			<div class="textbody rich-text">
				${course.content}
			</div>
		</div>
	<div id="editor" class=" clearfix l">
		<div id="laboratory"></div>
	</div>
	</div>
	<script src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script src="/LearningCommunity/resources/js/bootstrap.min.js"></script>
	<script src="/LearningCommunity/resources/js/codemirror/lib/codemirror.js"></script>
	<script src="/LearningCommunity/resources/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script src="/LearningCommunity/resources/js/codemirror/mode/css/css.js"></script>
	<script src="/LearningCommunity/resources/js/codemirror/mode/javascript/javascript.js"></script>
	<script src="/LearningCommunity/resources/js/lab-html.js"></script>
</body>
</html>