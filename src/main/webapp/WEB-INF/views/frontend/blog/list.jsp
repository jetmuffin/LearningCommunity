<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
<jsp:useBean id="jt" class="com.lst.lc.utils.JspUtil" scope="page" />
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
					<a href="/LearningCommunity/blog/blogs?type=0" class="blog-tab active">最新</a> <a href="/LearningCommunity/blog/blogs?type=1"
						class="blog-tab">最热</a>
				</div>
				<div class="main-list">
					<c:forEach var="blog" items="${page.list}">
						<div class="blog-item clearfix">
							<div class="blog-title">
								<a href="/LearningCommunity/blog/view/${blog.blogId}">${blog.title}</a>
							</div>
							<div class="blog-summary">
								<p>${st.getSummary(blog.content, 200) }</p>
							</div>
							<div class="blog-footer clearfix">
								<div class="blog-author l">
									<a href="/LearningCommunity/user/${blog.user.userId}/course"><img
										src="/LearningCommunity/read/avatar/${blog.user.userId }"
										class="avatar-20" alt="">${blog.user.userName}</a>
								</div>
								<div class="blog-time l">${jt.format(blog.time)}</div>
								<div class="blog-tag-list l">
									<c:forEach var="tag" items="${st.stringSplit(blog.tag)}">
										<a href="" target="_blank" class="list-tag">${tag}</a>
									</c:forEach>
								</div>
								<div class="blog-addon r">
									<span>浏览 ${blog.readNums }</span> <span>评论
										${blog.commentNums }</span>
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
										href="/LearningCommunity/blog/blogs?pageNum=${page.pageNow-1}">上一页</a></li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${page.totalPageCount lt 5}">
									<c:forEach var="item" varStatus="status" begin="1"
										end="${page.totalPageCount}">
										<li
											<c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
											href="/LearningCommunity/blog/blogs?pageNum=${status.index}">${status.index}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${page.pageNow lt 5}">
											<c:forEach var="item" varStatus="status" begin="1" end="5">
												<li
													<c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
													href="/LearningCommunity/blog/blogs?pageNum=${status.index}">${status.index}</a></li>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="item" varStatus="status"
												begin="${page.pageNow-2}" end="${page.pageNow+2}">
												<li
													<c:if test="${page.pageNow eq status.index}">class="active"</c:if>><a
													href="/LearningCommunity/blog/blogs?pageNum=${status.index}">${status.index}}</a></li>
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
										href="/LearningCommunity/blog/blogs?pageNum=${page.pageNow+1}">下一页</a></li>
								</c:otherwise>
							</c:choose>
						</ul>

					</div>
				</div>
			</div>
			<div class="blog-sidebar">

				<c:if test="${ not empty loginUser}">
					<div class="sidebar-item">
						<h3 class="item-title">
							我的文章<a>»</a>
						</h3>
						<div class="user-info clearfix">
							<div class="user-img  text-center clearfix">
								<a href="" class=""><img
									src="/LearningCommunity/read/avatar/${loginUser.userId }"
									alt=""></a>
								<p class="username ">${loginUser.userName}</p>
							</div>
							<div class="user-statics l text-center statics-mid">
								<a href="/LearningCommunity/user/${loginUser.userId}/blog">我的主题</a>
							</div>
							<div class="user-statics l text-center">
								<a href="/LearningCommunity/user/${loginUser.userId}/comment">我的回复</a>
							</div>
						</div>
					</div>
				</c:if>

				<div class="sidebar-item">
					<h3 class="item-title">
						热门作者<a>»</a>
					</h3>
					<ul class="blog-hot-author">
						<c:forEach var="user" items="${users}">
							<li class="author-item clearfix"><a
								href="/LearningCommunity/user/${user.userId}/course"
								class="l user-avatar"><img
									src="/LearningCommunity/read/avatar/${user.userId }" alt=""
									class="avatar-40"></a>
								<div class="author-info">
									<strong><a href="/LearningCommunity/user/${user.userId}/course">${user.userName}</a></strong>
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