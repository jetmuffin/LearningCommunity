<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div id="header">
		<div class="container">
			<div id="logo">
				<img src="" alt="LOGO" width="140" height="60">
			</div>
			<div class="header-menu">
				<ul class="nav-item">
					<li><a href="/LearningCommunity/course/courses" class="active">课程</a></li>
					<li><a href="">计划</a></li>
					<li><a href="">分享</a></li>
					<li><a href="">社区</a></li>
				</ul>
			</div>

			<div id="login-area">
				<c:choose>
					<c:when test="${empty loginUser}">
						<ul class="header-unlogin">
							<li class="header-signin"><a href="javascript:showLoginBox()" id="js-signin-btn">登录</a></li>
							<li class="header-signup"><a href="javascript:showRegisterBox()" id="js-signup-btn">注册</a></li>
						</ul>					
					</c:when>
					<c:otherwise>
						<ul class="header-login">
							<li class="notification"><a href=""></a></li>
							<li><a href="" class="user-avatar"><img src="/LearningCommunity/resources/images/default.png" alt="" width="32" height="32"></a></li>
						</ul>					
					</c:otherwise>
				</c:choose>
			</div>
			<div id="search-area">
	            <form action="/index/search" name="search-form" method="get">
		            <input class="js-input-keyword search-input" placeholder="搜索课程、问答" type="text" autocomplete="off" name="words" value="">
		            <input type="button" class="btn_search js-btn-search">
		            <dl class="search-area-result" style="display: none;"></dl>
	            </form>
	        </div>
        </div>
	</div>