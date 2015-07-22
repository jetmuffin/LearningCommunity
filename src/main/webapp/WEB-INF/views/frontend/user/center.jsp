<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 个人中心</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
		<div id="subheader" >
		<div class="container">
			<ol class="breadcrumb">
				<li class="active">个人中心</li>
			</ol>
		</div>
	</div>
	
	<div id="usercenter">
	
		<div class="container">

			<div class="sidebar l">
			<div class="profile-header media clearfix">
				<a href="" class="l user-avatar"><img src="/LearningCommunity/read/avatar/${user.userId}" class="avatar-128" alt="" /></a>
				<div class="media-body">
                        <h4 class="media-heading">${user.userName}</h4>
                  </div>
			</div>			
			<div class="profile clearfix">
				<ul class="profile-rank">
					<li><strong>${user.rank}</strong><span class="text-muted">等级</span></li>
					<li><strong>${user.integral }</strong><span class="text-muted">积分</span></li>
				</ul>
			</div>
			<ul class="nav">
				<li><a href="#course" onclick="javascript:loadContent()">我的课程</a></li>
				<li><a href="#question" onclick="javascript:loadContent()">我的讨论</a></li>
				<li><a href="#blog" onclick="javascript:loadContent()">我的文章</a></li>
				<li><a href="#setting" onclick="javascript:loadContent()">个人设置</a></li>
			</ul>
			</div>
			<div class="usercenter-main r">
			<div id="course" class="my-list">
				<div class="main-title">
					<h1>我的课程</h1>
				</div>
				<div class="course-tool-bar clearfix">
			        <div class="tool-left l">
			            <a href="" class="sort-item active">正在学</a>
			            <a href="" class="sort-item ">已学完</a>
			        </div>
			        </div>
				<div class="course-list">
					<div class="all-course-list">
						<c:forEach var="course" items="${courses}">
							<div class="course-item">
								<div class="widget-course">
									<a href="/LearningCommunity/course/view/${course.courseId}"><img src="/LearningCommunity/read/photo/${course.courseId }" class="widget-course-banner" alt=""></a>
								<div class="widget-course-info">
	            					<h2 class="h4 title"><a href="">${course.title}</a></h2>
	            					<ul class="widget-course-meta">
						                <li class="course-desc">${course.description}</li>
						                <li class="course-student-number">学生人数：${course.studentNums}</li>
						            </ul>
	                            </div>									
								</div>
							</div>						
						</c:forEach>
					</div>					
				</div>			
				</div>
				<div id="question"  class="my-list" style="display:none">
				<div class="main-title" >
					<h1>我的讨论</h1>
				</div>
				<div class="course-tool-bar clearfix">
			        <div class="tool-left l">
			            <a href="" class="sort-item active">按浏览数</a>
			            <a href="" class="sort-item ">按回复数</a>
			        </div>
			        </div>
				<div class="course-list" >
					<div class="all-course-list">
						<c:forEach var="question" items="${questions}">
						<div class="question-item clearfix">
							<a href="/LearningCommunity/question/view/${question.questionId}"
								target="_blank"
								class="replynumber  <c:if test="${question.answerNums gt 0}">hasanswer</c:if> <c:if test="${question.answerNums eq 0}">noanswernum</c:if>">
								<div class="ys l">
									<div class="number">
										<span>${question.answerNums }</span>
									</div>
									<div>回答</div>
								</div>
								<div class="browsenum l">
									<div class="number">
										<span>${question.readNums }</span>
									</div>
									<div>浏览</div>
								</div>
							</a>
							<div class="question-title">
								<a
									href="/LearningCommunity/question/view/${question.questionId}">${question.title}</a>
							</div>
							<div class="question-footer clearfix">
								<div class="question-time l">${question.time}</div>
								<div class="question-tag-list l">
									<c:forEach var="tag" items="${st.stringSplit(question.tag)}">
										<a href="" target="_blank" class="list-tag">${tag}</a>
									</c:forEach>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>					
				</div>	
				</div>
				<div id="blog"  class="my-list" style="display:none">
				<div class="main-title" >
					<h1>我的文章</h1>
				</div>
				<div class="course-tool-bar clearfix">
			        <div class="tool-left l">
			            <a href="" class="sort-item active">按浏览数</a>
			            <a href="" class="sort-item ">按回复数</a>
			        </div>
			        </div>
				<div class="course-list"  >
					<div class="all-course-list">
						<c:forEach var="blog" items="${blogs}">
								<div class="blog-item clearfix">
							<div class="blog-title">
								<a href="/LearningCommunity/blog/view/${blog.blogId}">${blog.title}</a>
							</div>
							<div class="blog-summary">
								<p>kingshard简介 kingshard是一个由Go开发高性能MySQL
									Proxy项目，kingshard在满足基本的读写分离的功能上，致力于简化MySQL分库分表操作；能够让DBA通过kingshard轻松平滑地实现MySQL数据库扩容。
									主要功能： {代...</p>
							</div>
							<div class="blog-footer clearfix">
								<div class="blog-author l">
									<a href=""><img
										src="/LearningCommunity/read/avatar/${blog.user.userId }"
										class="avatar-20" alt="">${blog.user.userName}</a>
								</div>
								<div class="blog-time l">${blog.time}</div>
								<div class="blog-tag-list l">
									<c:forEach var="tag" items="${st.stringSplit(blog.tag)}">
										<a href="" target="_blank" class="list-tag">${tag}</a>
									</c:forEach>
								</div>
								<div class="blog-addon r">
									<span>浏览 ${blog.readNums }</span> <span>评论 ${blog.commentNums }</span>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>					
				</div>	
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
		<script>
			function loadContent(){
				$('.my-list').hide();
				var hash = window.location.hash;
				$(hash).show();
				console.log(hash);
			}
		</script>
</body>
</html>