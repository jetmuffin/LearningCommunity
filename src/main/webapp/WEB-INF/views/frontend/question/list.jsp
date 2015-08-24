<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
<jsp:useBean id="jt" class="com.lst.lc.utils.JspUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 全部讨论</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="">讨论</a></li>
				<li class="active">所有问题</li>
			</ol>
		</div>
	</div>
	<div id="question">
		<div class="container">
			<div class="question-main">
				<p class="main-title">
					今天，你在学习时遇到了什么问题呢？ <a id="goAsk"
						href="/LearningCommunity/question/ask"
						class="btn btn-primary btn-lg">我要提问</a>
				</p>
				<div class="main-tab">
					<a href="" class="question-tab active">最新</a> <a href=""
						class="question-tab">最热</a> <a href="" class="question-tab">未回答</a>
				</div>
				<div class="main-list">
					<c:forEach var="question" items="${page.list}">
						<div class="question-item clearfix">
							<a href="/LearningCommunity/question/view/${question.questionId}"
								target="_blank"
								class="replynumber  <c:if test="${question.answerNums gt 0}">hasanswer</c:if> <c:if test="${question.answerNums eq 0}">noanswernum</c:if>">
								<div class="ys l">
									<div class="number">
										<span>${question.answerNums }</span>
									</div>
									<div>回答</div>
								</div>
								<div class="browsenum l">
									<div class="number">
										<span>${question.readNums }</span>
									</div>
									<div>浏览</div>
								</div>
							</a>
							<div class="question-title">
								<a
									href="/LearningCommunity/question/view/${question.questionId}">${question.title}</a>
							</div>
							<div class="question-footer clearfix">
								<div class="question-author l">
									<a href="">${question.user.userName}</a>
								</div>
								<div class="question-time l">${jt.format(question.time)}</div>
								<div class="question-tag-list l">
									<c:forEach var="tag" items="${st.stringSplit(question.tag)}">
										<a href="" target="_blank" class="list-tag">${tag}</a>
									</c:forEach>
								</div>
							</div>
						</div>
					</c:forEach>

					<div class="text-center">
						<ul class="pagination">
							<c:choose>
								<c:when test="${not page.hasPre}">
									<li class="disabled"><a href="#">上一页</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="/LearningCommunity/question/questions?pageNum=${page.pageNow-1}">上一页</a></li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${page.totalPageCount lt 5}">
									<c:forEach var="item" varStatus="status" begin="1"
										end="${page.totalPageCount}">
										<li <c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
											href="/LearningCommunity/question/questions?pageNum=${status.index}">${status.index}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${page.pageNow lt 5}">
											<c:forEach var="item" varStatus="status" begin="1" end="5">
												<li <c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
													href="/LearningCommunity/question/questions?pageNum=${status.index}">${status.index}</a></li>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="item" varStatus="status"
												begin="${page.pageNow-2}" end="${page.pageNow+2}">
												<li <c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
													href="/LearningCommunity/question/questions?pageNum=${status.index}">${status.index}}</a></li>
											</c:forEach>
										</c:otherwise>
									</c:choose>

								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${not page.hasNext}">
									<li class="disabled"><a href="#">下一页</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="/LearningCommunity/question/questions?pageNum=${page.pageNow+1}">下一页</a></li>
								</c:otherwise>
							</c:choose>
						</ul>


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
</body>
</html>