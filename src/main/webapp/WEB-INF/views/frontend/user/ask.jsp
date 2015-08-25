<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <div class="center-content">
<div class="main-title" >
                    <h1>我的提问</h1>
                </div>
                <div class="course-list question-list" >
                    <div class="all-course-list">
                        <c:forEach var="question" items="${questions}">
                        <div class="question-item clearfix">
                            <a href="/LearningCommunity/question/view/${question.questionId}"
                                target="_blank"
                                class="replynumber  <c:if test="${question.answerNums gt 0}">hasanswer</c:if> <c:if test="${question.answerNums eq 0}">noanswernum</c:if>">
                                <div class="ys l">
                                    <div class="number">
                                        <span>${question.answerNums }</span>
                                    </div>
                                    <div>回答</div>
                                </div>
                                <div class="browsenum l">
                                    <div class="number">
                                        <span>${question.readNums }</span>
                                    </div>
                                    <div>浏览</div>
                                </div>
                            </a>
                            <div class="question-title">
                                <a
                                    href="/LearningCommunity/question/view/${question.questionId}">${question.title}</a>
                            </div>
                            <div class="question-footer clearfix">
                                <div class="question-time l">${question.time}</div>
                                <div class="question-tag-list l">
                                    <c:forEach var="tag" items="${st.stringSplit(question.tag)}">
                                        <a href="" target="_blank" class="list-tag">${tag}</a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>                  
                </div>  
              </div>