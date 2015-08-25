<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="center-content">
                <div class="main-title">
                    <h1>我的课程</h1>
                </div>
                <div class="course-list">
                    <div class="all-course-list">
                        <c:forEach var="course" items="${courses}">
                            <div class="course-item">
                                <div class="widget-course">
                                    <a href="/LearningCommunity/course/view/${course.courseId}"><img src="/LearningCommunity/read/thumb/${course.courseId}?height=136" class="widget-course-banner" alt=""></a>
                                <div class="widget-course-info">
                                    <h2 class="h4 title"><a href="">${course.title}</a></h2>
                                    <ul class="widget-course-meta">
                                        <li class="course-desc">${course.description}</li>
                                    </ul>
                                </div>                                  
                                </div>
                            </div>                      
                        </c:forEach>
                    </div>                  
                </div>  
</div>