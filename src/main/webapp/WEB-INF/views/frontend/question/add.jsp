<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 提问</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="">讨论</a></li>
				<li class="active">提问</li>
			</ol>
		</div>
	</div>
    <div id="ask">
      <div class="container">
        <h4>提问题</h4>
        <form action="" method="post">
          <input id="myTitle" type="text" name="title" required=""  class="tagClose input-lg"  name="title" placeholder="标题：一句话说清你遇到的问题" value="">
          <input type="text" placeholder="标签，如：HTML" data-role="tagsinput" name="tag">
          <textarea id="content" name="content" rows="120" cols="60"></textarea>
          <button type="submit" class="r btn btn-primary btn-lg" id="btn-ask">发布问题</button>
        </form>
      </div>
    </div>
    <div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
		<script src="/LearningCommunity/resources/js/frontend/course/main.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/bootstrap-tagsinput.min.js"></script>	
	<script>
			CKEDITOR.replace('content');
		</script>
</body>
</html>