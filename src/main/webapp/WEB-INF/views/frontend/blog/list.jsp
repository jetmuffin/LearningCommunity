<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 全部文章</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="/LearningCommunity/blog/blogs">文章</a></li>
				<li class="active">所有文章</li>
			</ol>
		</div>
	</div>

	<div id="blog">
		<div class="container">
			<div class="blog-main">
				<p class="main-title">
					今天，有什么心得感悟分享给大家呢？ <a id="goWrite"
						href="/LearningCommunity/blog/add" class="btn btn-primary btn-lg">撰写文章</a>
				</p>
				<div class="main-tab">
					<a href="" class="blog-tab active">最新</a> <a href=""
						class="blog-tab">最热</a>
				</div>
				<div class="main-list">
					<c:forEach var="blog" items="${page.list}">
						<div class="blog-item clearfix">
							<div class="blog-title">
								<a href="/LearningCommunity/blog/view/${blog.blogId}">${blog.title}</a>
							</div>
							<div class="blog-summary">
								<p>kingshard简介 kingshard是一个由Go开发高性能MySQL
									Proxy项目，kingshard在满足基本的读写分离的功能上，致力于简化MySQL分库分表操作；能够让DBA通过kingshard轻松平滑地实现MySQL数据库扩容。
									主要功能： {代...</p>
							</div>
							<div class="blog-footer clearfix">
								<div class="blog-author l">
									<a href=""><img
										src="/LearningCommunity/read/avatar/${blog.user.userId }"
										class="avatar-20" alt="">${blog.user.userName}</a>
								</div>
								<div class="blog-time l">${blog.time}</div>
								<div class="blog-tag-list l">
									<c:forEach var="tag" items="${st.stringSplit(blog.tag)}">
										<a href="" target="_blank" class="list-tag">${tag}</a>
									</c:forEach>
								</div>
								<div class="blog-addon r">
									<span>浏览 ${blog.readNums }</span> <span>评论 ${blog.commentNums }</span>
								</div>
							</div>
						</div>

					</c:forEach>

					<div class="text-center">
						<ul class="pagination">
							<li class="active"><a href="">上一页</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li><a href="">5</a></li>
							<li><a href="">下一页</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="blog-sidebar">
				<div class="sidebar-item">
					<h3 class="item-title">
						我的讨论<a>»</a>
					</h3>
					<div class="user-info clearfix">
						<div class="user-img  text-center clearfix">
							<a href="" class=""><img src="default.png" alt=""></a>
							<p class="username ">kdsksa</p>
						</div>
						<div class="user-statics l text-center statics-mid">
							<a href="">我的文章</a>
						</div>
						<div class="user-statics l text-center">
							<a href="">我的回复</a>
						</div>
					</div>
				</div>
				<div class="sidebar-item">
					<h3 class="item-title">
						热门作者<a>»</a>
					</h3>
					<ul class="blog-hot-author">
						<c:forEach var="user" items="${users}">
							<li class="author-item clearfix"><a
								href="/LearningCommunity/user/index/${user.userId }"
								class="l user-avatar"><img
									src="/LearningCommunity/read/avatar/${user.userId }" alt=""
									class="avatar-40"></a>
								<div class="author-info">
									<strong><a href="">${user.userName}</a></strong>
								</div></li>
						</c:forEach>
					</ul>
				</div>
				<div class="sidebar-item">
					<h3 class="item-title">
						热门标签<a>»</a>
					</h3>
					<ul class="taglist clearfix">
						<c:forEach var="tag" items="${tags}">
							<li><a class="tag" href="">${tag.tagName }</a></li>
						</c:forEach>
					</ul>
				</div>

			</div>
		</div>

	</div>
	<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script src="/LearningCommunity/resources/js/frontend/course/main.js"></script>
</body>
</html>