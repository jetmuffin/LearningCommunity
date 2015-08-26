<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="center-content">
                 <div class="main-title">
                 	<h1>
                    	<c:choose>
                    		<c:when test="${loginUser.userId eq user.userId }">我的评论</c:when>
                    		<c:otherwise>他的评论</c:otherwise>
                    	</c:choose>
                    </h1>
                </div>              
              <div class="course-list blog-list"  >
                    <div class="all-course-list">
                        <c:forEach var="comment" items="${comments}">
                                <div class="blog-item clearfix">
                            <div class="blog-title">
                                <a href="/LearningCommunity/blog/view/${comment.blog.blogId}">${comment.blog.title}</a>
                            </div>
                            <div class="blog-summary">
                                <p>${st.getSummary(comment.content, 200) }</p>
                            </div>
                            <div class="blog-footer clearfix">
                                <div class="blog-time l">${comment.time}</div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>                  
                </div>  
                </div>
