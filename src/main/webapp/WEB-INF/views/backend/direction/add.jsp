<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 添加方向</title>
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
					<li><a href="courses">课程列表</a></li>
					<li><a href="categories">分类列表</a></li>
					<li><a href="directions"   class="active">方向列表</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div class="templatemo-content-container">
			<div class="templatemo-content-widget white-bg">
				<h2 class="margin-bottom-10">添加新课程</h2>
				<form action="addDirection" class="templatemo-login-form"
					method="post" enctype="multipart/form-data">
					<div class="row form-group">
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputName">方向名</label> <input type="text"
								class="form-control" id="inputName"  name="directionName" placeholder="请输入课程名">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-12 form-group">
							<label class="control-label" for="inputNote">方向描述</label>
							<textarea class="form-control"  name="directionDesc" id="inputNote" rows="3"></textarea>
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
		    <script type="text/javascript" src="/LearningCommunity/resources/js/bootstrap-filestyle.min.js"></script> 
</body>
</html>