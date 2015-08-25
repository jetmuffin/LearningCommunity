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
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li class="active">个人中心</li>
			</ol>
		</div>
	</div>
${userMsg}
	<div id="usercenter">
		<div class="container">
			<div class="sidebar l">
				<div class="profile-header media clearfix">
					<a href="" class="l user-avatar"><img
						src="/LearningCommunity/read/avatar/${user.userId}"
						class="avatar-128" alt="" /></a>
					<div class="media-body">
						<h4 class="media-heading">${user.userName}</h4>
						<p>性别: ${user.gender}</p>
						<p>城市: ${user.city}</p>
						<c:if test="${not empty loginUser }">
							<c:if test="${ loginUser.userId ne user.userId}">
								<a href="/LearningCommunity/user/addFriend/${user.userId }" class=" r btn btn-primary btn-large add-friend">加为好友</a>
							</c:if>
						</c:if>
						
					</div>
				</div>
				<div class="profile clearfix">
					<ul class="profile-rank">
						<li><strong>${user.rank}</strong><span class="text-muted">等级</span></li>
						<li><strong class="integral">${user.integral }</strong><span
							class="text-muted">积分</span></li>
					</ul>
				</div>
				<ul class="rep-rects clearfix" id="calendar">
				
				</ul>
				<div class="motto">
					<p>${user.motto}</p>
				</div>
				<div class="route">
					<strong>钻研方向</strong>
				</div>
			</div>
			<div class="usercenter-main r">
				<ul class="nav nav-pills">
					<li  <c:if test="${center_module eq 'course'}">class="active"</c:if> ><a href="/LearningCommunity/user/${user.userId}/course"><span>课程</span></a></li>
					<li <c:if test="${center_module eq 'ask'}">class="active"</c:if> ><a href="/LearningCommunity/user/${user.userId}/ask"><span>提问</span></a></li>
					<li <c:if test="${center_module eq 'answer'}">class="active"</c:if> ><a href="/LearningCommunity/user/${user.userId}/answer"><span>回答</span></a></li>
					<li <c:if test="${center_module eq 'blog'}">class="active"</c:if> ><a href="/LearningCommunity/user/${user.userId}/blog"><span>文章</span></a></li>
					<div class="clearfix"></div>
				</ul>
				<jsp:include page="${center_module}.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
		<script>
			var uid = ${user.userId};
			</script>
	<script src="/LearningCommunity/resources/js/frontend/user/center.js"></script>
</body>
</html>