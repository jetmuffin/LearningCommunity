<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - ${course.title }</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
<div class="clearfix"></div>
	<div id="subheader">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="/LearningCommunity/course/courses">课程</a></li>
                <li><a href="/LearningCommunity/course/courses?directionId=${course.direction.directionId}&categoryId=0&difficulty=&type=">${course.direction.directionName}</a></li>
                  <li><a href="/LearningCommunity/course/courses?directionId=&categoryId=${course.category.categoryId}&difficulty=&type=">${course.category.categoryName}</a></li>
                <li class="active">${course.title}</li>
            </ol>
            <h1 class="h3 title"><a href="/e/iweb-2015">${course.title}</a></h1>
        </div>
    </div>
    <div id="view-main">
    	<div class="container">
    		<div class="course-summary clearfix">
    			<div class="course-summary-pic"><a href=""><img src="/LearningCommunity/read/photo/${course.courseId }" alt=""></a></div>
    			<div class="course-summary-info">
    				<table class="clearfix">
    					<tr><th>开课时间:</th><td>${course.createTime}</td></tr>
    					<tr><th>课程方向:</th><td>${course.direction.directionName}</td></tr>
    					<tr><th>课程分类:</th><td>${course.category.categoryName }</td></tr>
    					<tr><th>主讲人:</th><td>Left Side Tree官方</td></tr>
    					<tr><th>难度:</th><td><c:if test="${course.difficulty eq 1}">初级</c:if><c:if test="${course.difficulty eq 2}">中级</c:if><c:if test="${course.difficulty eq 3}">高级</c:if></td></tr>
    				</table>
    			</div>
    			<div class="course-summary-statics">
					<div class="statics-group">
						<div class="statics-title">学习人数</div>
						<div class="statics-num"><span>${course.studentNums }</span></div>
					</div>
					<div class="statics-group">
						<div class="statics-title">课程时长</div>
						<div class="statics-num"><span>7</span>h<span>35</span>min</div>
					</div>					
    			</div>
    			<div class="course-summary-more clearfix">
    			<c:if test="${not status.ifLearn}"><button  class="btn btn-primary btn-lg l" id="join-right-now" onclick="javascript:location.href='/LearningCommunity/course/learn/${course.courseId}'">立即学习</button></c:if>
    				<c:if test="${status.ifLearn}"><button  class="btn btn-primary btn-lg l" id="join-right-now" disabled>立即学习</button></c:if>
					<div class="share bdsharebuttonbox bdshare-button-style0-16 r" data-tag="top-share" data-bd-bind="1436963543170">
		                <div class="d">分享课程到：</div>
		                <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		                <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
		                <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
		                <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
		                <a href="#" class="bds_more" data-cmd="more"></a>
            		</div>    				
    			</div>
    		</div>
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
									<li><a href="/LearningCommunity/course/view/lesson/${lesson.lessonId}" target="_blank" class="chapter-item locked">第${status.count}章 ${lesson.title}<span class="lock r"></span></a></li>
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
    					<c:forEach var="course" items="${courses}">
      					<dd class="course-list-relation">
    						<a href="/LearningCommunity/course/view/${course.courseId}" class="course-cover">
    							<img src="/LearningCommunity/read/photo/${course.courseId}" width="108" height="60" alt="">
    							<span class="course-name">${course.title}</span>
    						</a>
    					</dd>  					
    					</c:forEach>
    				</dl>
					<dl>
						<dt>参与的学生</dt>
						<dd>
							<ul class="users">
								<c:forEach var="user" items="${users}" >
									<li><a href="" target="_blank"><img src="/LearningCommunity/read/avatar/${user.userId }" alt=""></a>
									<h3><a href="">${user.userName }</a></h3>
								</c:forEach>
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
		
</body>
</html>