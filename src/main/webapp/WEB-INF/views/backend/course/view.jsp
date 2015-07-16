<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程详情</title>
</head>
<body>

	课程名称：${course.title}
	</br>
	章节列表：
	<br />
	<c:forEach var="lesson" items="${lessons}">
		<tr>
			<td>${lesson.title}</td>
			<td>${lesson.summary}</td>
			<td class="course-td"><a href="editlesson/${lesson.lessonId}"
				class="templatemo-edit-btn">编辑</a></td>
		</tr>
		<br/>
	</c:forEach>


</body>
</html>