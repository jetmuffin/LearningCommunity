<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 消息箱</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li class="active">消息箱</li>
			</ol>
		</div>
	</div>
	<div id="notification">
		<div class="container">
			<div class="stream-list l">
					<jsp:include page="${notify_module}.jsp"></jsp:include>
			</div>
			<div class="widget-messages r">
				<a href="/LearningCommunity/user/notification/friends" class="message-item">好友申请<span class="badge">1</span></a>
				<a href="/LearningCommunity/user/notification/letter" class="message-item">消息列表</a>
				<a href="/LearningCommunity/user/notification/write" class="message-item">写站内信</a>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>