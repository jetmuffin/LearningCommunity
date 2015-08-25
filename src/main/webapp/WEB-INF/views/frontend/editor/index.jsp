<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 在线运行</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
  <link href="/LearningCommunity/resources/js/videojs/video-js.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
		
	<div id="lab" class="clearfix">
		<div class="left">
			<div class="header">选择语言</div>
			<ul class="nav">
				<li ><a onclick="javascript:loadEditor(this)" lang="html">HTML</a></li>
				<li><a onclick="javascript:loadEditor(this)" lang="java">Java</a></li>
				<li><a onclick="javascript:loadEditor(this)" lang="cpp">C & C++</a></li>
				<li><a href="#more" data="more">更多语言支持敬请期待</a></li>
			</ul>
		</div>
		<div class="right">
			<iframe src="/LearningCommunity/editor/html" id="java-lab" frameborder="0"></iframe>
		</div>
	</div>
		<jsp:include page="../common/chatroom.jsp"></jsp:include>
    <div class="elevator">
    <a class="elevator-msg"  id="discussBtn"></a>
    </div>
	<script src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
		<script src="/LearningCommunity/resources/js/socketio/socket.io.js"></script>
	<script src="/LearningCommunity/resources/js/socketio/hichat.js"></script>
	<script>
		function loadEditor(d){
			var language = $(d).attr("lang");
			var url;
			switch(language)
			{
			case "html": url = "/LearningCommunity/editor/html";
			  break;
			case "java":url = "http://localhost:8000";
				break;
			case "cpp":url = "http://localhost:8001";
			  break;
			default: url = "/LearningCommunity/editor/html";
			}
			 $('iframe').attr("src",url);
		}
		
		$(function(){
			user = {
					'uid': '${loginUser.userName}',
					'avatar': '/LearningCommunity/read/avatar/' + ${loginUser.userId}
			};
			room = {
					'room_id': 23214
			};
			 var width = document.body.clientWidth,
			 		discussBtn = $('#discussBtn'),
			 		chatroom = $('#chatroom'),
			 		closeBtn = $("#close");
			 
			 discussBtn.click(function(){
				 chatroom.css('left',(width-740)/2);
				 chatroom.fadeIn();
			 });
			 
			 closeBtn.click(function(){
				 chatroom.fadeOut();
			 });
		});
	</script>
</body>
</html>