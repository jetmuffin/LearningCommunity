<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 用户管理</title>
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
					<li><a href="users"  class="active">用户列表</a></li>
					<li><a href="adduser">添加用户</a></li>
					<li><a href="login.html">Sign in form</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div class="templatemo-content-container">
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
					<table
						class="table table-striped table-bordered templatemo-user-table">
						<thead>
							<tr>
								<td><a href="" class="white-text templatemo-sort-by">#
										<span class="caret"></span>
								</a></td>
								<td><a href="" class="white-text templatemo-sort-by">First
										Name <span class="caret"></span>
								</a></td>
								<td><a href="" class="white-text templatemo-sort-by">Last
										Name <span class="caret"></span>
								</a></td>
								<td><a href="" class="white-text templatemo-sort-by">User
										Name <span class="caret"></span>
								</a></td>
								<td><a href="" class="white-text templatemo-sort-by">Email
										<span class="caret"></span>
								</a></td>
								<td>Edit</td>
								<td>Action</td>
								<td>Delete</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1.</td>
								<td>John</td>
								<td>Smith</td>
								<td>@jS</td>
								<td>js@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>2.</td>
								<td>Bill</td>
								<td>Jones</td>
								<td>@bJ</td>
								<td>bj@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>3.</td>
								<td>Mary</td>
								<td>James</td>
								<td>@mJ</td>
								<td>mj@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>4.</td>
								<td>Steve</td>
								<td>Bride</td>
								<td>@sB</td>
								<td>sb@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>5.</td>
								<td>Paul</td>
								<td>Richard</td>
								<td>@pR</td>
								<td>pr@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>6.</td>
								<td>Will</td>
								<td>Brad</td>
								<td>@wb</td>
								<td>wb@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>7.</td>
								<td>Steven</td>
								<td>Eric</td>
								<td>@sE</td>
								<td>se@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
							<tr>
								<td>8.</td>
								<td>Landi</td>
								<td>Susan</td>
								<td>@lS</td>
								<td>ls@company.com</td>
								<td><a href="" class="templatemo-edit-btn">Edit</a></td>
								<td><a href="" class="templatemo-link">Action</a></td>
								<td><a href="" class="templatemo-link">Delete</a></td>
							</tr>
						</tbody>
					</table>
				</div>
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