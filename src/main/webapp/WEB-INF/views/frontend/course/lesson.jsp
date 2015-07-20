<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - ${course.title}</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
  <link href="/LearningCommunity/resources/js/videojs/video-js.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div id="learn">
		<div class="bg-wrapper">
			<div class="video-box">
				<video id="learn-video" class="video-js vjs-default-skin" controls preload="none" width="1200" height="533"
				      poster="http://video-js.zencoder.com/oceans-clip.png"
				      data-setup="{}">
				    <source src="/LearningCommunity/resources/js/videojs/1.mp4" type='video/mp4' />
    			<p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>
 			 </video>			
			</div>
		</div>

	</div>
	<div id="view-main">
		<div class="container">
			<div class="course-content">
				<div class="content-area">
					<div class="course-menu">
						<ul class="course-menu clearfix">
							<li><a class="ui-tabs-active" id="learnOn" href=""><span>章节</span></a></li>
							<li><a id="commentOn" href=""><span>评论</span></a></li>
							<li><a id="qaOn" href=""><span>问答</span></a></li>
						</ul>
					</div>
					<div class="course-chapter-list">
						<div class="chapter">
							<!--  <a  onclick="collapseChapter(this)"><h3><strong>这是章节的标题哦</strong><span id="collapse-btn">-</span></h3></a> -->
							<ul>
								<c:forEach var="lesson" items="${lessons}" varStatus="status">
									<li><a href="" target="_blank" class="chapter-item locked">第${status.count}章
											${lesson.title}<span class="lock r"></span>
									</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="sidebar">
					<dl class="course-desc clear-fix">
						<dt>课程介绍</dt>
						<dd>${course.description }</dd>
					</dl>
					<dl class="course-relation clear-fix">
						<dt>相关课程</dt>
						<dd class="course-list-relation">
							<a href="" class="course-cover"> <img src="" width="108"
								height="60" alt=""> <span class="course-name">恶化呵呵大</span>
							</a>
						</dd>
						<dd class="course-list-relation">
							<a href="" class="course-cover"> <img src="" width="108"
								height="60" alt=""> <span class="course-name">恶化呵呵大</span>
							</a>
						</dd>
						<dd class="course-list-relation">
							<a href="" class="course-cover"> <img src="" width="108"
								height="60" alt=""> <span class="course-name">恶化呵呵大</span>
							</a>
						</dd>
					</dl>
					<dl>
						<dt>参与的学生</dt>
						<dd>
							<ul class="users">
								<li><a href="" target="_blank"><img src="" alt=""></a>
									<h3>
										<a href="">呵呵</a>
									</h3></li>
								<li><a href="" target="_blank"><img src="" alt=""></a>
									<h3>
										<a href="">呵呵</a>
									</h3></li>
								<li><a href="" target="_blank"><img src="" alt=""></a>
									<h3>
										<a href="">呵呵</a>
									</h3></li>
							</ul>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
		<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
		<script src="/LearningCommunity/resources/js/frontend/course/main.js"></script>
		
  <!-- video.js must be in the <head> for older IEs to work. -->
  <script src="/LearningCommunity/resources/js/videojs/video.js"></script>

  <script>
    videojs.options.flash.swf = "video-js.swf";
  </script>
</body>
</html>