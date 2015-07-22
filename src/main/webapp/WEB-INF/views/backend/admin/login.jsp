<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 登录</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="/LearningCommunity/resources/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="/LearningCommunity/resources/css/templatemo-style.css" />
</head>
	<body class="light-gray-bg">
			<c:if test="${not empty loginMsg}">
			<div class="templatemo-content-widget  templatemo-login-widget templatemo-register-widget pink-bg">
                <i class="fa fa-times"></i>                
                <p class="margin-bottom-0">${loginMsg}</p>
              </div>
              </c:if>
              
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <div class="square"></div>
	          <h1>LST Admin</h1>
	        </header>
	        <form action="login" method="post" class="templatemo-login-form">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<input type="text" class="form-control" name="email" placeholder="请输入帐号">           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input type="password" class="form-control"  name="password" placeholder="请输入密码">           
		          	</div>	
	        	</div>	          	
	          	<div class="form-group">
				    <div class="checkbox squaredTwo">
				        <input type="checkbox" id="c1" name="cc" />
						<label for="c1"><span></span>记住我</label>
				    </div>				    
				</div>
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">登录</button>
				</div>
	        </form>
		</div>
		<div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
			<p>不记得密码了？ <strong><a href="#" class="blue-text">找回密码</a></strong></p>
		</div>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	</body>
</html>