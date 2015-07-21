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
				<a href="" class="l user-avatar"><img src="/LearningCommunity/read/avatar/${user.avatar}" class="avatar-128" alt="" /></a>
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
				<li><a href="">我的课程</a></li>
				<li><a href="">我的讨论</a></li>
				<li><a href="">我的文章</a></li>
				<li><a href="">个人设置</a></li>
			</ul>
			</div>
			<div class="usercenter-main r">
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
						<c:forEach var="course" items="${page.list}">
							<div class="course-item">
								<div class="widget-course">
									<a href="/LearningCommunity/course/view/${course.courseId}"><img src="/LearningCommunity/read/photo/${course.courseId }" class="widget-course-banner" alt=""></a>
								<div class="widget-course-info">
	            					<h2 class="h4 title"><a href="">${course.title}</a></h2>
	            					<ul class="widget-course-meta">
						                <li class="course-desc">${course.description}</li>
						                <li class="course-student-number">学生人数：${course.studentNums}</li>
						            </ul>
	                                <a href=""  id="join-right-now" class="btn btn-primary btn-sm">立即学习</a>
	                               
	                            </div>									
								</div>
							</div>						
						</c:forEach>
					</div>					
				</div>			        
			</div>
		</div>
	</div>

</body>
</html>