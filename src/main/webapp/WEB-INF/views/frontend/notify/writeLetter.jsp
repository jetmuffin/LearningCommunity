<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2 class="h4 mt0 mb20">撰写站内信</h2>
        <form action="" method="post">
          <input id="myTitle" type="text" name="title" required=""  class="tagClose input-lg"  name="title" placeholder="标题：一句话描述您的意见标题" value="">
        <input type="text" class="feedback-input"  name="name"   placeholder="联系方式：请输入您的联系邮箱"/>
          <textarea id="content" name="content" rows="120" cols="60"></textarea>
          <button type="submit" class="r btn btn-primary btn-lg" id="btn-ask">提交</button>
        </form>