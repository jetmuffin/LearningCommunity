<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 添加课程章节</title>
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
			<h2>这是个残次品，js什么的没有写</h2>
			<div class="templatemo-content-widget white-bg">

				<form action="" class="templatemo-login-form" method="post"
					enctype="multipart/form-data">
					<div class="row form-group">
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputName">章节名</label> <input type="text"
								class="form-control" name="title" id="inputName"
								placeholder="请输入章节名" value="${lesson.title}">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-12 form-group">
							<label class="control-label" for="inputNote">章节概要</label>
							<textarea class="form-control" id="inputNote" name="summary"
								rows="3" value="${lesson.summary}"></textarea>
						</div>
					</div>

					<div class="row form-group">
						<div class="col-lg-12 form-group">
							<label class="control-label templatemo-block">选择章节类型</label>
							<div class="margin-right-15 templatemo-inline-block">
								<input type="radio" name="type" id="r4" value="video"> <label
									for="r4" class="font-weight-400"><span></span>视频</label>
							</div>
							<div class="margin-right-15 templatemo-inline-block">
								<input type="radio" name="type" id="r5" value="text" checked>
								<label for="r5" class="font-weight-400"><span></span>文本</label>
							</div>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-12 form-group">
							<label class="control-label" for="inputNote">章节内容</label>
							<textarea class="form-control" id="inputNote" name="content"
								rows="8" value="${lesson.content}"></textarea>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-12">
							<label class="control-label templatemo-block">课程视频</label>
							<!-- <input type="file" name="fileToUpload" id="fileToUpload" class="margin-bottom-10"> -->
							<input type="file" name="video " id="fileToUpload"
								class="filestyle" data-buttonName="btn-primary"
								data-buttonBefore="true" data-icon="false">
							<p>Maximum upload size is 5 MB.</p>
						</div>
					</div>
					<div class="form-group text-right">
						<button type="submit" class="templatemo-blue-button">添加</button>
						<button type="reset" class="templatemo-white-button">重置</button>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/templatemo-script.js"></script>
	<script src="/LearningCommunity/resources/js/backend/course/add.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/bootstrap-filestyle.min.js"></script>
</body>
</html>