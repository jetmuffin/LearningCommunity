<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 class="h4 mt0 mb20">撰写站内信</h2>
<div class="letter-form">
        <form action="" method="post">
          <select class="form-control " name="uid" id='friend_select'>
          	<option value="14">Jeff</option>
          		<c:forEach var="friend" items="${friends}">
          			<option value="${friend.userId}">${friend.userName}</option>
          		</c:forEach>
          </select>
          <textarea id="content" name="content" rows="120" cols="60"></textarea>
          <button type="submit" class="r btn btn-primary btn-lg" id="btn-ask">提交</button>
        </form>
        </div>
        	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/chosen/chosen.jquery.js"></script>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/bootstrap-tagsinput.min.js"></script>	
	<script>
	$(document).ready(function(){
			CKEDITOR.replace('content');
			var chosen = $("#friend_select");
			chosen.chosen();
	});
		</script>