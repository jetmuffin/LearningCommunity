<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 问题详情</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="/LearningCommunity/question/questions">讨论</a></li>
				<li class="active">问题详情</li>
			</ol>
			<h1 class="h3 title">
				<a href="">${question.title}</a>
			</h1>
		</div>
	</div>
	<div id="question">
		<div class="container">
			<div class="question-main">
				<div class="qa-item">
					<div class="qa-header">
						<div class="qa-header-right r">#1</div>
						<a href="" class="qa-author"><img src="/LearningCommunity/read/avatar/${question.user.userId }"
							class="avatar-32" alt="">${question.user.userName }</a>
					</div>
					<div class="qa-content">
						<div class="qa-content-inner rich-text">${question.content }</div>
						<div class="qa-content-addon clearfix">
							<span class="qa-createtime l">${question.time}</span>
							<div class="taglist l">
								<c:forEach var="tag" items="${st.stringSplit(question.tag)}">
									<a href="" target="_blank" class="list-tag">${tag}</a>
								</c:forEach>
							</div>
							<span class="qa-view-num r">${question.readNums} 浏览</span> <span
								class="qa-total-comment r">${question.answerNums} 回答</span>
						</div>
					</div>
					<div class="qa-answer">
						<div class="qa-answer-title">
							<h2>${answers.list.size()}个回答</h2>
						</div>
						<c:forEach var="answer" items="${answers.list}" varStatus="status">
							<div class="qa-answer-item clearfix">
								<div class="qa-answer-header">
									<a href="" class="qa-author"><img src="default.png"
										class="avatar-32" alt="">${answer.user.userName}</a> <span
										class="answer-time">${answer.time} 回答</span>
									<div class="qa-answer-header-right r">#${status.count }</div>
								</div>
								<div class="qa-answer-content">
									<div class="qa-answer-content-inner  rich-text">${answer.content}</div>
									<div class="qa-answer-content-addon r">
										<a href="">回复</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="qa-write">
						<div class="qa-write-header">
							<h2>撰写答案</h2>
						</div>
						<div class="qa-write-content clearfix">
							<form action="/LearningCommunity/question//answer/${questionId}"
								method="post">
								<textarea name="content" id="content" rows="60" cols="60"
									name="content"></textarea>
								<button type="submit" class="btn btn-primary btn-lg r">提交回答</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="sidebar.jsp"></jsp:include>
		</div>
	</div>
	<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script src="/LearningCommunity/resources/js/frontend/course/main.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('content');
	</script>
</body>
</html>