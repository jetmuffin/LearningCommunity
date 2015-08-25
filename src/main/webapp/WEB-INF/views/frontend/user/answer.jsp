<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <jsp:useBean id="st" class="com.lst.lc.utils.StringUtils" scope="page" />
     <div class="center-content">
<div class="main-title" >
                     	<c:choose>
                    		<c:when test="${loginUser.userId eq user.userId }">我的回答</c:when>
                    		<c:otherwise>他的回答</c:otherwise>
                    	</c:choose>
                </div>
                <div class="course-list question-list" >
                    <div class="all-course-list">
                        <c:forEach var="answer" items="${answers}">
                        <div class="question-item clearfix">
                            <a href="/LearningCommunity/question/view/${answer.question.questionId}"
                                target="_blank"
                                class="replynumber  <c:if test="${answer.question.answerNums gt 0}">hasanswer</c:if> <c:if test="${question.answerNums eq 0}">noanswernum</c:if>">
                                <div class="ys l">
                                    <div class="number">
                                        <span>${answer.question.answerNums }</span>
                                    </div>
                                    <div>回答</div>
                                </div>
                                <div class="browsenum l">
                                    <div class="number">
                                        <span>${answer.question.readNums }</span>
                                    </div>
                                    <div>浏览</div>
                                </div>
                            </a>
                            <div class="question-title">
                                <a
                                    href="/LearningCommunity/question/view/${answer.question.questionId}">${answer.question.title}</a>
                            </div>
                            <div class="question-content">
                            	<p>${st.getSummary(answer.content, 200) }</p>
                            </div>
                            <div class="question-footer clearfix">
                                <div class="question-time l">${answer.question.time}</div>
                                <div class="question-tag-list l">
                                    <c:forEach var="tag" items="${st.stringSplit(answer.question.tag)}">
                                        <a href="" target="_blank" class="list-tag">${tag}</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>                  
                </div>  
              </div>