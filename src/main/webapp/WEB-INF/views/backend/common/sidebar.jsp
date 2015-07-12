<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <div class="square"></div>
          <h1>Visual Admin</h1>
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
            <li><a href="index"  <c:if test="${module eq 'index'}">class="active"</c:if>  ><i class="fa fa-home fa-fw"></i>首页</a></li>
            <li><a href="users"  <c:if test="${module eq 'user'}">class="active"</c:if> ><i class="fa fa-bar-chart fa-fw"></i>用户管理</a></li>
            <li><a href="courses"  <c:if test="${module eq 'course'}">class="active"</c:if> ><i class="fa fa-database fa-fw"></i>课程管理</a></li>
            <li><a href="maps.html"><i class="fa fa-map-marker fa-fw"></i>Maps</a></li>
            <li><a href="manage-users.html"><i class="fa fa-users fa-fw"></i>Manage Users</a></li>
            <li><a href="preferences.html"><i class="fa fa-sliders fa-fw"></i>Preferences</a></li>
            <li><a href="login.html"><i class="fa fa-eject fa-fw"></i>Sign Out</a></li>
          </ul>  
        </nav>
      </div>