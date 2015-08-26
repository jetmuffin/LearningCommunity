<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 class="h4 mt0 mb20">好友申请</h2>
<div class="notify-stream  notify-friend border-top">
	<c:forEach var="friend" items="${friends}">
		<section class="stream-item viewed">
		<a href="/LearningCommunity/user/${friend.userId }/course" class="user-thumb"><img src="/LearningCommunity/read/avatar/${friend.userId }" alt="" /></a>
		<a href="/LearningCommunity/user/${friend.userId }/course">${friend.userName }</a>
		<p>城市：${friend.city }</p>
		<p>座右铭：${friend.motto}</p>
		<p>积分：${friend.integral}</p>
		<a class="btn btn-default r" href="/LearningCommunity/user/validateFriend/${friend.userId}/0">拒绝申请</a>
		<a class="btn btn-primary r" href="/LearningCommunity/user/validateFriend/${friend.userId}/1">同意申请</a>
		<div class="clearfix"></div>
	</section>
	</c:forEach>
</div>