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
					<li><a href="/LearningCommunity/manage/course/courses"  class="active">课程列表</a></li>
					<li><a href="/LearningCommunity/manage/category/categories">分类列表</a></li>
					<li><a href="/LearningCommunity/manage/direction/directions">方向列表</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div class="templatemo-content-container">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
						class="table table-striped table-bordered templatemo-user-table course-table">
						<thead>

							<tr>
								<td><a href="" class="white-text templatemo-sort-by">#
										<span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=courseName" class="white-text templatemo-sort-by">课程名称 <span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=direction" class="white-text templatemo-sort-by">方向<span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=category" class="white-text templatemo-sort-by">类别 <span class="caret"></span>
								</a></td>
								<td><a href="?sortKey=enabled" class="white-text templatemo-sort-by">开放状态
										<span class="caret"></span>
								</a></td>
								<td>Edit</td>
								<td>View</td>
								<td>Delete</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course" items="${page.list}">
								<tr>
									<td><div class="course-thumb"><img src="/LearningCommunity/read/photo/${course.courseId }" alt="" /></div></td>
									<td>${course.title}</td>
									<td>${course.direction.directionName}</td>
									<td>${course.category.categoryName}</td>
									<td><c:if test="${course.enabled eq 1}"><div class="margin-right-15  templatemo-inline-block circle green-bg"></div>开放</c:if><c:if test="${course.enabled eq 0}"><div class="margin-right-15  templatemo-inline-block circle pink-bg"></div>未开放</c:if></td>
								<td class="course-td"><a href="edit/${course.courseId}"  class="templatemo-edit-btn">编辑</a></td>
								<td class="course-td"><a href="view/${course.courseId}"  class="templatemo-edit-btn">详情</a></td>
								<td class="course-td"><a href="delete/${course.courseId}" class="templatemo-link">删除</a></td>								
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div>
				<div class="text-right"><button onClick="location.href='add'"  class="templatemo-blue-button">添加新课程</button></div>
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