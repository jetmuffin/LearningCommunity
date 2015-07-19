<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 查看课程</title>
<link href="/LearningCommunity/resources/font/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/templatemo-style.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="../common/sidebar.jsp"></jsp:include>
	<!-- Main content -->
	<div class="templatemo-content col-1 light-gray-bg">
		<div class="templatemo-top-nav-container">
			<div class="row">
				<nav class="templatemo-top-nav col-lg-12 col-md-12">
				<ul class="text-uppercase">
					<li><a href="/LearningCommunity/manage/course/courses"
						class="active">课程列表</a></li>
					<li><a href="/LearningCommunity/manage/category/categories">分类列表</a></li>
					<li><a href="/LearningCommunity/manage/direction/directions">方向列表</a></li>
				</ul>
				</nav>
			</div>
		</div>

		<!-- Content -->
		<div class="templatemo-content-container">
			<c:if test="${not empty lessonMsg}">
				<div class="alert alert-info templatemo-content-widget" role="alert">
					<i class="fa fa-times"></i>
					<p>${lessonMsg}</p>
				</div>
			</c:if>
			<div class="templatemo-content-widget white-bg">
				<h2 class="margin-bottom-10 blue-text">${course.title}</h2>
				<div class="row info-group">
					<h3>课程描述：</h3>
					<p>${course.description}</p>
				</div>
				<div class="row info-group">
					<h3>课程方向：</h3>
					<p>${course.direction.directionName}</p>
				</div>
				<div class="row info-group">
					<h3>课程类别：</h3>
					<p>${course.category.categoryName}</p>
				</div>
				<div class="row info-group">
					<h3>课程图片：</h3>
					<img src="/LearningCommunity/read/photo/${course.courseId}" alt="" />
				</div>
				<div class="info-group">
					<h3>章节列表：</h3>
					<div class="templatemo-content-widget no-padding">
						<div class="panel panel-default table-responsive">
							<table
								class="table table-striped table-bordered templatemo-user-table">
								<thead>
									<tr>
										<td><a href="" class="white-text templatemo-sort-by">#
												<span class="caret"></span>
										</a></td>
										<td><a href="" class="white-text templatemo-sort-by">章节名
												<span class="caret"></span>
										</a></td>
										<td><a href="" class="white-text templatemo-sort-by">章节描述
												<span class="caret"></span>
										</a></td>
										<td><a href="" class="white-text templatemo-sort-by">编辑
												<span class="caret"></span>
										</a></td>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="lesson" varStatus="status" items="${lessons}">
										<tr>
											<td>${status.count }</td>
											<td>${lesson.title}</td>
											<td>${lesson.summary}</td>
											<td class="course-td"><a
												href="/LearningCommunity/manage/course/editlesson/${lesson.lessonId}"
												class="templatemo-edit-btn">编辑</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="info-group">
					<a
						href="/LearningCommunity/manage/course/addlesson/${course.courseId}"
						class="templatemo-edit-btn">添加课程</a>
				</div>

			</div>
		</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/templatemo-script.js"></script>
</body>
</html>