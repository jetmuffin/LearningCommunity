<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 添加用户</title>
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
					<li><a href="users">用户列表</a></li>
					<li><a href="addUser" class="active">添加用户</a></li>
					<li><a href="login.html">Sign in form</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div class="templatemo-content-container">
			<div class="templatemo-content-widget white-bg">
				<h2 class="margin-bottom-10">添加新用户</h2>
				<p>Here goes another form and form controls.</p>
				<form action="index.html" class="templatemo-login-form"
					method="post" enctype="multipart/form-data">
					<div class="row form-group">
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputEmail">邮箱</label> <input type="email"
								class="form-control" id="inputEmail"
								placeholder="admin@company.com">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputUsername">昵称</label> <input type="text"
								class="form-control" id="inputUsername" placeholder="Admin">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputNewPassword">密码</label> <input
								type="password" class="form-control" id="inputNewPassword">
						</div>
						<div class="col-lg-8 col-md-8 form-group">
							<label for="inputConfirmNewPassword">确认密码</label>
							<input type="password" class="form-control"
								id="inputConfirmNewPassword">
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
</body>
</html>