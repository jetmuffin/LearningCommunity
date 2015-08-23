<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>LST Admin</h1>
        </header>
        <div class="profile-photo-container">
          <img src="/LearningCommunity/resources/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">          
          <ul>
            <li><a href="/LearningCommunity/manage/index"  <c:if test="${module eq 'index'}">class="active"</c:if>  ><i class="fa fa-home fa-fw"></i>首页</a></li>
            <li><a href="/LearningCommunity/manage/users"  <c:if test="${module eq 'user'}">class="active"</c:if> ><i class="fa fa-bar-chart fa-fw"></i>用户管理</a></li>
            <li><a href="/LearningCommunity/manage/course/courses"  <c:if test="${module eq 'course'}">class="active"</c:if> ><i class="fa fa-database fa-fw"></i>课程管理</a></li>
              <li><a href="/LearningCommunity/index/index"  ><i class="fa fa-database fa-fw"></i>回到前台</a></li>
          </ul>  
        </nav>
      </div>