<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="question-sidebar">
	<div class="sidebar-item">
		<h3 class="item-title">
			我的讨论<a>»</a>
		</h3>
		<div class="user-info clearfix">
			<div class="user-img l text-center">
				<a href=""><img src="default.png" alt=""></a>
				<p class="username">${user.userName}</p>
			</div>
			<div class="user-statics l text-center statics-mid">
				<a href="">提问</a>
				<p>${questionSide.questionNums }</p>
			</div>
			<div class="user-statics l text-center">
				<a href="">回答</a>
				<p>${questionSide.answerNums }</p>
			</div>
		</div>
	</div>
	<div class="sidebar-item">
		<h3 class="item-title">
			热门标签<a>»</a>
		</h3>
		<ul class="taglist clearfix">
			<c:forEach var="tag" items="${questionSide.tags}">
				<li class="tag l"><a href="">${tag.tagName}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="sidebar-item">
		<h3 class="item-title">
			热门问题<a>»</a>
		</h3>
		<ul class="question-hot">
			<c:forEach var="question" items="${questionSide.questions}">
				<li class="question-item"><a href="/LearningCommunity/question/view/${question.questionId}">${question.title }</a><small
					class="text-muted">${question.answerNums } 回答</small></li>
			</c:forEach>
		</ul>
	</div>
</div>