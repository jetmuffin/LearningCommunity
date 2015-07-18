$(function(){

});

function loginInputFocus(input){
	$(input).parent().parent().addClass("active");
}

function loginInputBlur(input){
	$(input).parent().parent().removeClass("active");
}

function showLoginBox(){
	login_box_template = '<div id="login-modal"><div style="width:100%;height:100%;position:fixed;z-index:2000;top:0;left:0;overflow:hidden" onclick="hideLoginBox()"><div style="height:100%;opacity:.4;background:#000"></div></div><div class="modal"><div class="modal-header clearfix"><h2 class="l">登录</h2><div class="register r">没有账号？ <a href="javascript:hideLoginBox();showRegisterBox();"><strong>立即注册</strong></a></div></div><div class="modal-body"><form id="loginForm" ><div class="control-group"><div class="user-id"><input type="text" placeholder="请输入邮箱/用户名"  name="email" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"></div></div><div class="control-group"><div class="user-pw"><input type="password"  name="password" placeholder="请输入密码" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"></div></div><div class="errorMsg" "></div><div class="control-group"><div class="r"><button type="button" onclick="userLogin()" class="btn btn-primary btn-login">登录</button></div></div></form></div><div class="modal-footer"><div class="line-area"><span>或</span></div><div class="outside-login"><h4>其他帐号</h4><ul class="outside-list clearfix"><li title="微博登录" class="l"><a href="/users/auth/weibo" class="icon-outside-weibo"></a></li><li title="QQ登录" class="l"><a href="/users/auth/qq" class="icon-outside-qq"></a></li></ul></div></div></div></div>';
	$("body").append(login_box_template);
}

function hideLoginBox(){
	$("#login-modal").remove();
}

function showRegisterBox(){
	register_box_template = '<div id="register-modal"> <div style="width: 100%; height: 100%; position: fixed; z-index: 2000; top: 0px; left: 0px; overflow: hidden;" onclick="javascript:hideRegisterBox()"><div style="height: 100%; opacity: 0.4; background: rgb(0, 0, 0);"></div></div> <div class="modal"><div class="modal-header clearfix"><h2 class="l">注册</h2><div class="register r">已有账号？<a href="javascript:hideRegisterBox();showLoginBox();"><strong>立即登录</strong></a></div></div><div class="modal-body"><form id="registerForm"><div class="control-group"><div class="user-id"><input type="text" placeholder="请输入邮箱/用户名" name="email" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)" /></div></div><div class="control-group"><div class="user-pw"><input type="password" placeholder="请输入密码" name="password" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"></div></div><div class="control-group"><div class="user-pw"><input type="password" placeholder="请再次输入密码" name="passwordAgain" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"></div></div><div class="control-group"><div class="user-nkn"><input type="type" placeholder="请再次输入用户昵称"  name="userName" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"></div></div>	<div class="control-group"><div class="user-vldtcd"><input type="type" placeholder="请输入验证码" name="captcha" onfocus="loginInputFocus(this)" onblur="loginInputBlur(this)"><a href="javascript:changeCaptcha()" class="verify-img-wrap js-verify-refresh"><img id="captcha-img" class="verify-img" src="/LearningCommunity/read/captcha"></a><span class="verify-extra r"> 看不清<br> <a href="javascript:changeCaptcha()" class="js-verify-refresh">换一张</a> </span></div></div>	<div class="errorMsg"></div><div class="control-group"><div class="r"><button type="button" class="btn btn-primary btn-register" onclick="javascript:userRegister()">注册</button></div></div>	</form></div><div class="modal-footer"><div class="line-area"> <span>或</span></div><div class="outside-login"> <h4>联合登录</h4> <ul class="outside-list clearfix"><li title="微博登录" class="l"> <a href="/users/auth/weibo" class="icon-outside-weibo"></a></li> <li title="QQ登录" class="l"><a href="/users/auth/qq" class="icon-outside-qq"></a></li></ul></div></div></div></div>';
	$("body").append(register_box_template);
}

function hideRegisterBox(){
	$("#register-modal").remove();
}

function collapseChapter(div){
	var d = $(div).next('ul');
	d.slideToggle('300');
	collapseChaterButton();
}

function collapseChaterButton(){
	var btn = $('#collapse-btn');
	if(btn.html() == '+'){
		btn.html('-');
	}else if(btn.html() == '-')
		btn.html('+');
}

function changeCaptcha(){
	var r = Math.random()*10;
	$('#captcha-img').attr("src","/LearningCommunity/read/captcha?r="+r);
}

function userLogin(){
        $.ajax({
            type: "post",
            url: "/LearningCommunity/user/login",     
            data: $("#loginForm").serialize(),    
            success: function(data) {
            	if(data.status == 1){
            		location.reload();
            	}else{
            		$(".errorMsg").html(data.message);
            	}
            }
        })
}

function userRegister(){
    $.ajax({
        type: "post",
        url: "/LearningCommunity/user/register",     
        data: $("#registerForm").serialize(),    
        success: function(data) {
        	if(data.status == 1){
        		location.reload();
        	}else{
        		$(".errorMsg").html(data.message);
        	}
        },
        error: function(data) {
        	console.log(data);
        }
    })	
}