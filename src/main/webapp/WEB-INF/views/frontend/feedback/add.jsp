<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>反馈</title>
</head>
<body>
	<form action="add" method="POST">
		标题： 
		<input type="text" name="title" ><br />
		反馈内容:
		<textarea name="content" id="content" rows="20" cols="60">
            </textarea>
		<script>
			CKEDITOR.replace('content');
		</script>
		<button type="submit">提交</button>
	</form>
</body>
</html>