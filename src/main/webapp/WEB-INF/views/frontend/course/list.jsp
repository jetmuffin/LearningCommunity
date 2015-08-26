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
								<c:forEach var="directionTag"
									items="${courseMenu.directionTags}">
									<li
										class="course-nav-item <c:if test="${directionTag.isActive}">on</c:if>"><a
										href="?directionId=${directionTag.id}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type=${param.sort}">${directionTag.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="course-nav-base-line">
						<label class="hd l">分类：</label>
						<div class="bd" id="searchingType" type="radio">
							<ul>
								<c:forEach var="categoryTag" items="${courseMenu.categoryTags}">
									<li
										class="course-nav-item <c:if test="${categoryTag.isActive}">on</c:if>"><a
										href="?directionId=${param.directionId}&categoryId=${categoryTag.id}&difficulty=${param.difficulty}&type=${param.sort}">${categoryTag.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="course-nav-base-line">
						<label class="hd l">难度：</label>
						<div class="bd" id="searchingType" type="radio">
							<ul>
								<c:forEach var="difficultyTag"
									items="${courseMenu.difficultyTags}">
									<li
										class="course-nav-item <c:if test="${difficultyTag.isActive}">on</c:if>"><a
										href="?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${difficultyTag.id}&type=${param.sort}">${difficultyTag.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="course-tool-bar clearfix">
					<div class="tool-left l">
						<a
							href="?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}"
							class="sort-item" id="sortByTime">最新</a> <a
							href="?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type=heat"
							class="sort-item" id="sortByHeat">最热</a>
					</div>
				</div>
				<div class="course-list">
					<div class="all-course-list">
						<c:forEach var="course" items="${courseMenu.page.list}">
							<div class="course-item">
								<div class="widget-course">
									<a href="/LearningCommunity/course/view/${course.courseId}"><img
										src="/LearningCommunity/read/thumb/${course.courseId }?height=236"
										class="widget-course-banner" alt=""></a>
									<div class="widget-course-info">
										<h2 class="h4 title">
											<a href="">${course.title}</a>
										</h2>
										<ul class="widget-course-meta">
											<li class="course-desc">${course.description}</li>
											<li class="course-student-number">学生人数：${course.studentNums}</li>
										</ul>
										<a href="/LearningCommunity/course/learn/${course.courseId}'" id="join-right-now" class="btn btn-primary btn-sm">立即学习</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="text-center">
            <ul class="pagination">
                            <c:choose>
                                <c:when test="${not courseMenu.page.hasPre}">
                                    <li class="disabled"><a href="#">上一页</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a
                                        href="/LearningCommunity/course/courses?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type${param.type}&pageNum=${courseMenu.page.pageNow-1}">上一页</a></li>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${courseMenu.page.totalPageCount lt 5}">
                                    <c:forEach var="item" varStatus="status" begin="1"
                                        end="${courseMenu.page.totalPageCount}">
                                        <li <c:if test="${courseMenu.page.pageNow eq status.index}">class="active"</c:if>><a
                                            href="/LearningCommunity/course/courses?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type${param.type}&pageNum=${status.index}">${status.index}</a></li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${courseMenu.page.pageNow lt 5}">
                                            <c:forEach var="item" varStatus="status" begin="1" end="5">
                                                <li <c:if test="${courseMenu.page.pageNow eq status.index}">class="active"</c:if>><a
                                                    href="/LearningCommunity/course/courses?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type${param.type}&pageNum=${status.index}">${status.index}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="item" varStatus="status"
                                                begin="${courseMenu.page.pageNow-2}" end="${courseMenu.page.pageNow+2}">
                                                <li <c:if test="${courseMenu.page.pageNow eq status.index}">class="active"</c:if>><a
                                                    href="/LearningCommunity/course/courses?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type${param.type}&pageNum=${status.index}">${status.index}}</a></li>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>

                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${not courseMenu.page.hasNext}">
                                    <li class="disabled"><a href="#">下一页</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a
                                        href="/LearningCommunity/course/courses?directionId=${param.directionId}&categoryId=${param.categoryId}&difficulty=${param.difficulty}&type${param.type}&pageNum=${courseMenu.page.pageNow+1}">下一页</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>


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