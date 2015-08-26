<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
 <jsp:useBean id="jt" class="com.lst.lc.utils.JspUtil" scope="page" />
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="center-content">
                 <div class="main-title">
                 	<h1>
                    	<c:choose>
                    		<c:when test="${loginUser.userId eq user.userId }">我的文章</c:when>
                    		<c:otherwise>他的文章</c:otherwise>
                    	</c:choose>
                    </h1>
                </div>              
              <div class="course-list blog-list"  >
                    <div class="all-course-list">
                        <c:forEach var="blog" items="${blogs}">
                                <div class="blog-item clearfix">
                            <div class="blog-title">
                                <a href="/LearningCommunity/blog/view/${blog.blogId}">${blog.title}</a>
                            </div>
                            <div class="blog-summary">
                                <p>${st.getSummary(blog.content, 200) }</p>
                            </div>
                            <div class="blog-footer clearfix">
                                <div class="blog-author l">
                                    <a href=""><img
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
                                    <span>浏览 ${blog.readNums }</span> <span>评论 ${blog.commentNums }</span>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>                  
                </div>  
                </div>
