<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 完善用户信息</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
<div class="clearfix"></div>
	<div id="subheader">
        <div class="container">
            <ol class="breadcrumb">
                <li class="active">完善个人信息</li>
            </ol>
        </div>
    </div>
    <div id="complete">
    	<div class="container">
    		<form action="" id="info-form" class="clearfix" method="post" enctype="multipart/form-data">
    			<div class="avatar-wrap l">
    				<div class="avatar-inner clearfix">
    					<img src="/LearningCommunity/resources/images/default.png" alt="" id="user-avatar-thumb">
    				</div>
    				<a href="" class="btn btn-default btn-lg avatar-btn" >上传头像</a>
    				<input type="file" name="avatar" id="upload"  onChange="loadImageFile(this)";>
    			</div>
    			<div class="file-wrap l">
    				<div class="file-form-group">
    					<label for="userName">昵称</label>
    					<input type="text" id="userName" name="userName">
    				</div>
    				<div class="file-form-group">
    					<label for="gender">性别</label>
    					<label><input type="radio" hidefocus="true" value="未知" checked="checked" name="gender">保密</label>
    					<label><input type="radio" hidefocus="true" value="男" checked="checked" name="gender">男</label>
    					<label><input type="radio" hidefocus="true" value="女" checked="checked" name="gender">女</label>        					    					
    				</div>    
    				<div class="file-form-group">
    					<label for="city">城市</label>
    					<input type="text" id="city" name="city">
    				</div>  
    				<div class="file-form-group">
    					<label for="motto">座右铭</label>
    					<textarea id="motto" name="motto"></textarea> 
    				</div>      				  					
    				<div class="file-form-group">
    					<button class="btn btn-primary btn-lg" id="complete-submit">完成</button>
    				</div>	
    			</div>
    		</form>
    	</div>
    </div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
    	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script src="/LearningCommunity/resources/js/frontend/course/main.js"></script>
</body>
</html>