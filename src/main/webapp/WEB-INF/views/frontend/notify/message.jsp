<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2 class="h4 mt0 mb20">消息通知</h2>

<div class="notify-stream border-top">

	<c:forEach var="letter" items="${letters}">
	<h3 class="time">${letter.id.time } <small class="r">来自 <a href="">${letter.userByFromUid.userName}</a></small></h3>

	<section class="stream-item item-message viewed">
${letter.content }
	</section>
</c:forEach>
</div>