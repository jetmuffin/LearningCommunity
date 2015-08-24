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
					${lesson.content}
			</div>
			<a href="#" id="discussBtn">在线讨论</a>
		</div>
		  <div id="editor" class=" clearfix l">
    <div id="laboratory">
      <iframe src="http://121.248.199.29:8000/" id="java-lab" frameborder="0"></iframe>
    </div>
  </div>
  </div>
      <div class="chat-box" id="chatroom">
        <div class="chat-header">
            在线讨论 <span id="room"></span> ( <span id="status"></span>)
            <span id="close"> × </span>
        </div>
        <div class="chat-body">
            <div class="chat-friend-list">
                <ul id="friend-list">
                </ul>
            </div>
            <div class="chat-content">
                <div id="chat-board" class="chat-board">

                </div>
                <div class="chat-control">
                    <a id="emoji" class="control-icon"></a>
                    <div id="emojiWrapper"></div>
                    <label for="sendImage" class="imageLable" title="send image">
                        <a title="send image" class="sendImage control-icon"></a>
                        <input id="sendImage" type="file" value="image"/>
                    </label>
                    <a id="clearBtn" title="clear screen" class="control-icon"></a> 
                    <div class="to">
                        发送给:
                        <select name="" id="sendTo">
                            <option value="all">所有人</option>
                        </select>
                    </div>
                    <div style="height:8px"></div>
                    <textarea id="msg-textarea"></textarea>
                    <button class="btn btn-blue" id="sendBtn">发送</button>
                    <div id="send-notice">按Ctrl+Enter发送</div>                    
                </div>
            </div>
        </div>
    </div>
</body>
	<script src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script src="/LearningCommunity/resources/js/socketio/socket.io.js"></script>
	<script src="/LearningCommunity/resources/js/socketio/hichat.js"></script>
	
	<script>
		$(function(){
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
</html>