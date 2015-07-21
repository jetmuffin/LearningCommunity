<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 文章详情</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="clearfix"></div>
	<div id="subheader">
		<div class="container">
			<ol class="breadcrumb">
				<li><a href="/LearningCommunity/blog/blogs">文章</a></li>
				<li class="active">文章详情</li>
			</ol>
			<h1 class="h3 title">
				<a href="">${blog.title}</a>
			</h1>
		</div>
	</div>
	<div id="blog">
		<div class="container">
			<div class="blog-main">
				<div class="blog-content">
					<div class="blog-content-wrapper clearfix">
						<div class="blog-content-inner  rich-text">${blog.content}</div>
						<div class="blog-content-addon">
							<span class="l"><strong>${blog.readNums}</strong>&nbsp;阅读</span>
							<span class="l"><strong>${blog.commentNums }</strong>&nbsp;评论</span>
							<ul class="taglist l">
								<li><a href="" class="tag">${blog.tag}</a></li>
							</ul>
							<div class="share bdsharebuttonbox bdshare-button-style0-16 r"
								data-tag="top-share" data-bd-bind="1436963543170">
								<div class="d">分享文章到：</div>
								<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
								<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
								<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
								<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
								<a href="#" class="bds_more" data-cmd="more"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="blog-comment">
					<h2 class="h4 blog-comment-title">${comments.list.size()}条评论</h2>
					<c:forEach var="comment" items="${comments.list}"
						varStatus="status">
						<div class="blog-comment-item clearfix">
							<div class="blog-comment-header">
								<a href="" class="blog-comment-author"><img
									src="default.png" class="avatar-32" alt="">${comment.user.userName}</a>
								<span class="answer-time">${comment.time} 回答</span>
								<div class="blog-comment-header-right r">#${status.count
									+1}</div>
							</div>
							<div class="blog-comment-content">
								<div class="blog-comment-content-inner  rich-text">
									${comment.content}</div>
								<div class="blog-comment-content-addon r">
									<a href="">回复</a>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
				<div class="blog-write">
					<div class="blog-write-header">
						<h2>评论</h2>
					</div>
					<div class="blog-write-content clearfix">
						<form action="/LearningCommunity/blog/comment/${blog.blogId}"
							method="post">
							<textarea name="content" id="content" rows="60" cols="60"
								name="content"></textarea>
							<button type="submit" class="btn btn-primary btn-lg r">提交回答</button>
						</form>
					</div>
				</div>
			</div>
			<div class="blog-sidebar">
				<div class="sidebar-item">
					<h3 class="item-title">作者</h3>
					<ul class="blog-hot-author">
						<li class="author-item clearfix"><a href=""
							class="l user-avatar"><img
								src="/LearningCommunity/read/avatar/${blog.user.userId }" alt=""
								class="avatar-40"></a>
							<div class="author-info clearfix">
								<strong><a href="">${blog.user.userName }</a></strong>
								<p class="text-muted">${blog.user.rank }</p>
							</div>
							<p class="moto">${blog.user.motto }</p></li>
					</ul>
				</div>
				<div class="sidebar-item">
					<h3 class="item-title">作者的其他文章</h3>
					<ul class="blog-author-others">
						<c:forEach var="otherblog" items="${otherBlogs}">
							<li class="others-item clearfix"><a href="/LearningCommunity/blog/view/${otherblog.blogId }">${otherblog.title }</a></li>
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
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('content');
	</script>


</body>
</html>