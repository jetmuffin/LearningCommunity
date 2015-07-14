<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 课程管理</title>
<link href="/LearningCommunity/resources/font/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/templatemo-style.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="../common/sidebar.jsp"></jsp:include>
	<!-- Main content -->
	<div class="templatemo-content col-1 light-gray-bg">
		<div class="templatemo-top-nav-container">
			<div class="row">
				<nav class="templatemo-top-nav col-lg-12 col-md-12">
				<ul class="text-uppercase">
					<li><a href="/LearningCommunity/manage/course/courses?pageNum=1&pageSize=5">课程列表</a></li>
					<li><a href="/LearningCommunity/manage/category/categories"   class="active">分类列表</a></li>
					<li><a href="/LearningCommunity/manage/direction/directions">方向列表</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div class="templatemo-content-container">
				<c:if test="${not empty categoryMsg}">
		<div class="alert alert-info templatemo-content-widget" role="alert">
			<i class="fa fa-times"></i>
			<p>${categoryMsg}</p>
		</div>
		</c:if>
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
						class="table table-striped table-bordered templatemo-user-table">
						<thead>
							<tr>
								<td><a href="" class="white-text templatemo-sort-by">#
										<span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=category" class="white-text templatemo-sort-by">分类名
										 <span class="caret"></span>
								</a></td>	
								<td><a href="?sortKey=direction" class="white-text templatemo-sort-by">方向名
										 <span class="caret"></span></a></td>
								<td><a href="?sortKey=admin" class="white-text templatemo-sort-by">创建人
										 <span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=time" class="white-text templatemo-sort-by">创建时间
										<span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=enabled" class="white-text templatemo-sort-by">是否启用
										<span class="caret"></span>
								</a></td>	
								<td>编辑</td>				
									<td>删除</td>																								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="category" items="${categories}">
								<tr>
									<td>${category.categoryId}</td>
									<td>${category.categoryName}</td>
									<td>${category.direction.directionName }</td>
									<td>${category.admin.name}</td>
									<td>${category.time}</td>
									<td><c:if test="${category.enabled eq 1}"><div class="margin-right-15  templatemo-inline-block circle green-bg"></div>启用</c:if><c:if test="${direction.enabled eq 0}"><div class="margin-right-15  templatemo-inline-block circle pink-bg"></div>未启用</c:if></td>
								<td><a href="edit/${category.categoryId}" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="delete/${category.categoryId}" class="templatemo-link">Delete</a></td>									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="text-right"><button onClick="location.href='add'"  class="templatemo-blue-button">添加新分类</button></div>
			</div>
		</div>
		          <div class="pagination-wrap">
            <ul class="pagination">
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li class="active"><a href="#">3 <span class="sr-only">(current)</span></a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#" aria-label="Next">
                  <span aria-hidden="true"><i class="fa fa-play"></i></span>
                </a>
              </li>
            </ul>
          </div>          
          <jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
		<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/templatemo-script.js"></script>
</body>
</html>