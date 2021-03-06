<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<script type="text/javascript">
function changeimg(){
	document.getElementById("img").src="/images/captcha?" + new Date().getMilliseconds();
}
</script>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<%
boolean isLogin = (boolean)request.getAttribute("isLogin");
if(isLogin) {
	response.sendRedirect("/");
}
%>
<form onsubmit="return isFormOK()" action="/register" method="post" class="form-horizontal" role="form">
    <div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" id="nickname" name="nickname" class="form-control" placeholder="昵称(长度:1-16)" onblur="checkNickname()" />
	    	<span style="color:#F00" id="nickname_msg"></span>
	    </div>
	</div>
    <div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" id="username" name="username" class="form-control" placeholder="用户名(长度:6-16)" />
	    	<span style="color:#F00" id="username_msg"></span>
	    </div>
	</div>
	<div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" id="email" name="email" class="form-control" placeholder="邮箱(长度:5+)" />
	    	<span style="color:#F00" id="email_msg"></span>
	    </div>
	</div>
	<div class="form-group">
	    <div class="col-sm-10">
	    	<input type="password" name="password" class="form-control" placeholder="密码(长度:6-16)" />
	    </div>
  	</div>
  	 <div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" name="locationName" class="form-control" placeholder="默认地点名称" />
	    </div>
  	</div>
 	<div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" name="n" class="form-control" placeholder="默认地点纬度(-90<x<90)" />
	    </div>
  	</div>
 	<div class="form-group">
	    <div class="col-sm-10">
	    	<input type="text" name="e" class="form-control" placeholder="默认地点经度(-180<x<180)" />
	    </div>
  	</div>
  	<div class="form-group">
	    <div class="col-sm-10">
    		<img src="/images/captcha" style="cursor: pointer;" onclick="changeimg();" id="img"/>
    	 </div>
  	</div>
  	<div class="form-group">
	    <div class="col-sm-10">
    		<input type="text" name="captcha" class="form-control" placeholder="CAPTCHA" />
    	 </div>
  	</div>
    <div class="form-group">
    <div class="col-sm-10">
    	<button type="submit" class="btn btn-default">注册</button>
    	<span style="color:#F00" id="submit_msg"></span>
	</div>
	</div>
</form>

register.JSP

<script type="text/javascript">
var isUsernameOk = false;
var isEmailOk = false;
var isNicknameOk = false;

function isFormOK() {
	if(isUsernameOk&&isEmailOk) {
		return true;
	}
	else {
		alert("请检查表单");
		return false;
	}
}

//function $(id) {
	//return document.getElementById(id);
//}

function checkNickname() {
	//var nickname = $("nickname").value;
	var nickname = document.getElementById("nickname").value;
	var nickname_msg = document.getElementById("nickname_msg");
	if(nickname.length<1||nickname.length>16) {
		nickname_msg.innerHTML = "请检查长度";
	}
	else {
		nickname_msg.innerHTML = "";
		isNicknameOk = true;
	}
}

$(function () {
	$("#username").blur(function() {
        var url = "/ajax/register/check/username";
        var val = $(this).val();

        var sendData = {"username":val};
        $.post(url,sendData,function(backData,textStatus,ajax) {
            var javaJSON = ajax.responseText;
            var jsJSON = eval("(" + javaJSON + ")");
            if(jsJSON=="ok") {
            	$("#username_msg").html("可注册");
            	isUsernameOk = true;
            }
            else if(jsJSON=="illegal"){
            	$("#username_msg").html("不合规则");
            }
			else if(jsJSON=="exists"){
				$("#username_msg").html("username已存在");
			}
        });
	 
 	});
	
	 $("#email").blur(function() {
	        var url = "/ajax/register/check/useremail";
	        var val = $(this).val();

            var sendData = {"email":val};
            $.post(url,sendData,function(backData,textStatus,ajax) {
                var javaJSON = ajax.responseText;
                var jsJSON = eval("(" + javaJSON + ")");
                if(jsJSON=="ok") {
                	$("#email_msg").html("可注册");
                	isEmailOk = true;
                }
                else if(jsJSON=="illegal"){
                	$("#email_msg").html("不合规则");
                }
				else if(jsJSON=="exists"){
					$("#email_msg").html("email已存在");
				}
            });
		 
	 });
});
</script>
</body>
</html>