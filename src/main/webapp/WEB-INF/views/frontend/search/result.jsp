<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
<jsp:useBean id="jt" class="com.lst.lc.utils.JspUtil" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 搜索</title>
<link rel="stylesheet"
	href="/LearningCommunity/resources/css/search.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div id="main" class="clearfix">
		<div class="container">
			<div class="result l">

				<c:if test="${param.type eq 'course'}">
					<div class="search-course">
						<h3 class="tip">
							为你搜索到“<strong>${param.key}</strong>”的课程
						</h3>
						<div class="result-list">
							<ul>
								<c:forEach var="course" items="${results.results}">
									<li class="course">
										<h2 class="title">
											<a href="/LearningCommunity/course/view/${course.courseId }">${course.title}</a>
										</h2>
										<div class="postil">
											<span>${course.createTime}</span><span>${course.studentNums }人学习</span>
										</div>
										<div class="content clearfix">
											<div class="thumbnail">
												<a href="/LearningCommunity/course/view/${course.courseId }"><img
													src="/LearningCommunity/read/photo/${course.courseId}"
													alt=""></a>
											</div>
											<div class="intro">
												<div class="desc">课程简介：${course.description }</div>
												<div class="chapter">
													章节内容：
													<c:forEach var="lesson" varStatus="status"
														items="${course.courseLessons}">第${status.count}章  ${lesson.title}</c:forEach>
												</div>
											</div>
										</div>
									</li>

								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
				<c:if test="${param.type eq 'question'}">
					<div class="search-question">
						<h3 class="tip">
							为你搜索到“<strong>${param.key}</strong>”的问答
						</h3>
						<div class="result-list">
							<ul>
								<c:forEach var="question" items="${results.results}">
									<li class="question">
										<div class="user-thumb l">
											<a href=""><img
												src="/LearningCommunity/read/avatar/${question.user.userId }"
												alt="" class="avatar-40"></a>
											<p class="user-name">${question.user.userName}</p>
										</div>
										<div class="question-box">
											<div class="title">
												<a
													href="/LearningCommunity/question/view/${question.questionId }">${question.title}</a>
											</div>
											<div class="summary">${question.content}</div>
											<div class="addon">${question.time }
												<c:forEach var="tag" items="${st.stringSplit(question.tag)}">
													<a href="" target="_blank" class="list-tag">${tag}</a>
												</c:forEach>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>

				<c:if test="${param.type eq 'blog'}">
					<div class="search-question">
						<h3 class="tip">
							为你搜索到“<strong>${param.key}</strong>”的文章
						</h3>
						<div class="result-list">
							<ul>
								<c:forEach var="blog" items="${results.results}">
									<li class="blog">

										<div class="blog-box">
											<div class="user-thumb l">
												<a href="/LearningCommnity/user/${blog.user.userId }/course"><img
													src="/LearningCommunity/read/avatar/${blog.user.userId }"
													alt="" class="avatar-40">
													<p class="user-name">${blog.user.userName}</p></a>
											</div>
											<div class="title">
												<a href="/LearningCommunity/blog/view/${blog.blogId }">${blog.title}</a>
											</div>
											<div class="summary">${st.getSummary(blog.content, 200) }</div>
											<div class="addon">
												${jt.format(blog.time)}
												<c:forEach var="tag" items="${st.stringSplit(blog.tag)}">
													<a href="" target="_blank" class="list-tag">${tag}</a>
												</c:forEach>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>

			</div>
		<div class="sidebar l">
			<ul class="nav">
				<li <c:if test="${param.type eq 'course'}">class="active"</c:if>><a
					href="/LearningCommunity/search/search?key=${param.key}&type=course">课程</a></li>
				<li <c:if test="${param.type eq 'question'}">class="active"</c:if>><a
					href="/LearningCommunity/search/search?key=${param.key}&type=question">问答</a></li>
				<li <c:if test="${param.type eq 'blog'}">class="active"</c:if>><a
					href="/LearningCommunity/search/search?key=${param.key}&type=blog">文章</a></li>
			</ul>

			<div class="popular-search">
				<dl>
					<dt>热门搜索</dt>
					<dd class="clearfix">
						<a title="java"
							href="/LearningCommunity/search/search?type=${param.type}&key=java">
							java </a> <a title="mysql"
							href="/LearningCommunity/search/search?type=${param.type}&key=mysql">
							mysql </a> <a title="php"
							href="/LearningCommunity/search/search?type=${param.type}&key=php">
							php </a> <a title="android"
							href="/LearningCommunity/search/search?type=${param.type}&key=android">
							android </a> <a title="bootstrap"
							href="/LearningCommunity/search/search?type=${param.type}&key=bootstrap">
							bootstrap </a> <a title="python"
							href="/LearningCommunity/search/search?type=${param.type}&key=python">
							python </a> <a title="linux"
							href="/LearningCommunity/search/search?type=${param.type}&key=linux">
							linux </a> <a title="C"
							href="/LearningCommunity/search/search?type=${param.type}&key=C">
							C </a> <a title="javascript"
							href="/LearningCommunity/search/search?type=${param.type}&key=javascript">
							javascript </a> <a title="ajax"
							href="/LearningCommunity/search/search?type=${param.type}&key=ajax">
							ajax </a> <a title="c++"
							href="/LearningCommunity/search/search?type=${param.type}&key=c%2B%2B">
							c++ </a> <a title="html"
							href="/LearningCommunity/search/search?type=${param.type}&key=html">
							html </a> <a title="ps"
							href="/LearningCommunity/search/search?type=${param.type}&key=ps">
							ps </a> <a title="html5"
							href="/LearningCommunity/search/search?type=${param.type}&key=html5">
							html5 </a> <a title="git"
							href="/LearningCommunity/search/search?type=${param.type}&key=git">
							git </a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>
	<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>