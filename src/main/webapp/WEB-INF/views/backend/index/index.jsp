<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理 - 首页</title>
<link href="/LearningCommunity/resources/font/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/LearningCommunity/resources/css/templatemo-style.css"
	rel="stylesheet">
<style>
.status-item {
    float: left;
    width: 20%;
    text-align: center;
    margin-top: 10px;
}
.status-num {
    font-size: 30px;
    color: #26B396;
    font-weight: bold;
}
.status-key {
    font-size: 18px;
}
.chart-item {
    width: 45%;
    float: left;
    text-align: center;
    margin-top: 20px;
}
.clearfix{
        clear:both;
}
</style>
</head>
<body>
	<jsp:include page="../common/sidebar.jsp"></jsp:include>
	<!-- Main content -->
	<div class="templatemo-content col-1 light-gray-bg">
		<div class="templatemo-top-nav-container">
			<div class="row">
				<nav class="templatemo-top-nav col-lg-12 col-md-12">
				<ul class="text-uppercase">
					<li><a href="" class="active">Admin panel</a></li>
					<li><a href="">Dashboard</a></li>
					<li><a href="">Overview</a></li>
					<li><a href="/LearningCommunity/manage/logout">Sign in form</a></li>
				</ul>
				</nav>
			</div>
		</div>
		<!-- Content -->
		<div style="padding:20px;min-height:600px;">
		            <div class="templatemo-content-widget white-bg col-2">
              <i class="fa fa-times"></i>
              <div class="square"></div>
              <h2 class="templatemo-inline-block">欢迎您，管理员！</h2><hr>
              <h3>系统状态</h3>
                            <div class="status-item">
                        <div class="status-num">${courseCount}</div>
                        <div class="status-key">课程</div>
              </div>
                    <div class="status-item">
                        <div class="status-num">${userNum}</div>
                        <div class="status-key">用户</div>
              </div>
              <div class="status-item">
                        <div class="status-num">${blogNum}</div>
                        <div class="status-key">文章</div>
              </div>
              <div class="status-item">
                        <div class="status-num">${questionNum}</div>
                        <div class="status-key">问题</div>
              </div>    
              <div class="clearfix"></div>         
              <div class="chart-item">
              <div id="canvas-holder-1">
                        <h4>用户发表文章情况</h4>
                        <canvas id="chart-area-1" width="250" height="250"/>
                </div>
              </div> 
              <div class="chart-item">
              <div id="canvas-holder-2">
                        <h4>用户讨论问题情况</h4>
                        <canvas id="chart-area-2" width="250" height="250"/>
                </div>
              </div>
              <div class="clearfix"></div>
            </div>
			</div>
			
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</div>
		<!-- Cotent end -->
	</div>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript"
		src="/LearningCommunity/resources/js/templatemo-script.js"></script>
        <script type="text/javascript" src="/LearningCommunity/resources/js/Chart.min.js"></script>
	<!-- Templatemo Script -->
	<script>
	var data_1 = [
                   {
                           value: ${blogUserCount},
                           color: "#FDB45C",
                           highlight: "#FFC870",
                           label: "发表文章用户"
                   },
                   {
                           value: ${userNum-blogUserCount},
                           color: "#949FB1",
                           highlight: "#A8B3C5",
                           label: "未发表文章用户"
                   }
           ];
	var data_2 = [
                  {
                          value: ${questionUserCount},
                          color:"#F7464A",
                          highlight: "#FF5A5E",
                          label: "参与讨论用户"
                  },
                  {
                          value: ${userNum-questionUserCount},
                          color: "#46BFBD",
                          highlight: "#5AD3D1",
                          label: "未参与讨论用户"
                  }
          ];
	window.onload = function(){
        var ctx = document.getElementById("chart-area-1").getContext('2d');
        window.myPie = new Chart(ctx).Pie(data_1);
        var ctx = document.getElementById("chart-area-2").getContext('2d');
        window.myPie_2 = new Chart(ctx).Pie(data_2);
        };
    
	</script>
</body>
</html>