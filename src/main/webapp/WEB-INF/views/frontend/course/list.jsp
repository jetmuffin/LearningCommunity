<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左偏树 - 全部课程</title>
<link rel="stylesheet" href="/LearningCommunity/resources/css/main.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="clearfix"></div>
	<div id="main">
		<div class="container">
			<div class="course">
				<div class="course-nav-box">
					<div class="course-nav-hd">
						<h2>所有课程</h2>
					</div>
						<div class="course-nav-base-line">
				            <label class="hd l">方向：</label>
			            	<div class="bd" id="searchingType" type="radio">
			            		<ul>
			            			<li class="course-nav-item on"><a href="">全部</a></li>
			            			<c:forEach var="direction" items="${directions}">
			            				<li class="course-nav-item"><a href="">${direction.directionName}</a></li>
			            			</c:forEach>
			            		</ul>
				            </div>
				        </div>
						<div class="course-nav-base-line">
				            <label class="hd l">分类：</label>
			            	<div class="bd" id="searchingType" type="radio">
			            		<ul>
			            			<li class="course-nav-item on"><a href="?direction=${param.direction}&category=all&difficulty=${param.difficulty}">全部</a></li>
			            			<c:forEach var="category" items="${categories}">
			            				<li class="course-nav-item"><a href="?direction=${param.direction}&category=${category.categoryId}&difficulty=${param.difficulty}">${category.categoryName}</a></li>
			            			</c:forEach>
			            		</ul>
				            </div>
				        </div>		
						<div class="course-nav-base-line">
				            <label class="hd l">难度：</label>
			            	<div class="bd" id="searchingType" type="radio">
			            		<ul>
			            			<li class="course-nav-item on"><a href="">全部</a></li>
			            			<li class="course-nav-item"><a href="">初级</a></li>
			            			<li class="course-nav-item"><a href="">中级</a></li>
			            			<li class="course-nav-item"><a href="">高级</a></li>
			            		</ul>
				            </div>
				        </div>				        		        
				</div>
				<div class="course-tool-bar clearfix">
					<div class="tool-left l">
                        <a href="" class="sort-item active">最新</a>
                        <a href="" class="sort-item">最热</a>
                    </div>
				</div>		
				<div class="course-list">
					<div class="all-course-list">
						<div class="course-item">
							<div class="widget-course">
								<a href=""><img src="test.jpg" class="widget-course-banner" alt=""></a>
							<div class="widget-course-info">
            					<h2 class="h4 title"><a href="">2015 台湾大学黑客马拉松</a></h2>
            					<ul class="widget-course-meta">
					                <li>课程描述描述描述</li>
					                <li class="course-student-number">学生人数：200</li>
					            </ul>
                                <a href="" class="btn btn-primary btn-sm">立即学习</a>
                            </div>									
							</div>
			
						</div>
						<div class="course-item">
							<div class="widget-course">
								<a href=""><img src="test.jpg" class="widget-course-banner" alt=""></a>
							<div class="widget-course-info">
            					<h2 class="h4 title"><a href="">2015 台湾大学黑客马拉松</a></h2>
            					<ul class="widget-course-meta">
					                <li>课程描述描述描述</li>
					                <li class="course-student-number">学生人数：200</li>
					            </ul>
                                <a href="" class="btn btn-primary btn-sm">立即学习</a>
                            </div>	
							</div>
			
						</div>
						<div class="course-item">
							<div class="widget-course">
								<a href=""><img src="test.jpg" class="widget-course-banner" alt=""></a>
							<div class="widget-course-info">
            					<h2 class="h4 title"><a href="">2015 台湾大学黑客马拉松</a></h2>
            					<ul class="widget-course-meta">
					                <li>课程描述描述描述</li>
					                <li class="course-student-number">学生人数：200</li>
					            </ul>
                                <a href="" class="btn btn-primary btn-sm">立即学习</a>
                            </div>
							</div>
				
						</div>
						<div class="course-item">
							<div class="widget-course">
								<a href=""><img src="test.jpg" class="widget-course-banner" alt=""></a>
							<div class="widget-course-info">
            					<h2 class="h4 title"><a href="">2015 台湾大学黑客马拉松</a></h2>
            					<ul class="widget-course-meta">
					                <li>课程描述描述描述</li>
					                <li class="course-student-number">学生人数：200</li>
					            </ul>
                                <a href="" class="btn btn-primary btn-sm">立即学习</a>
                            </div>	
							</div>
			
						</div>
						<div class="course-item">
							<div class="widget-course">
								<a href=""><img src="test.jpg" class="widget-course-banner" alt=""></a>
							<div class="widget-course-info">
            					<h2 class="h4 title"><a href="">2015 台湾大学黑客马拉松</a></h2>
            					<ul class="widget-course-meta">
					                <li>课程描述描述描述</li>
					                <li class="course-student-number">学生人数：200</li>
					            </ul>
                                <a href="" class="btn btn-primary btn-sm">立即学习</a>
                            </div>	
							</div>
			
						</div> 			
					</div>					
				</div>		
			</div>
			<div class="clearfix"></div>
			<div class="text-center">
		        <ul class="pagination">
					<li class="active"><a href="" >上一页</a></li>
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
	<div class="clearfix"></div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>