<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>

<div id="time">

</div>
<input type="text" id="text"/>
<span style="color:#F00" id="text_msg">vcb</span>
<hr>
index.jsp
<jsp:include page="include/foot.jsp"></jsp:include>
<script type="text/javascript">
function showTime() {
	var now = new Date();
	var hour = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	if(hour<10)
		hour = "0" + hour;
	if(minutes<10)
		minutes = "0" + minutes;
	if(seconds<10)
		seconds = "0" + seconds;
	document.getElementById("time").innerHTML = "" + hour + ":" + minutes + ":" + seconds;
	setTimeout("showTime()",1000);
}
function a() {
	var locale = new Date();
	var l_str = locale.toLocaleString();
	var gmt = new Date();
	var g_str = gmt.toGMTString();
	document.getElementById("time").innerHTML = "系统当前时间:" + l_str + "<br>" + "GMT:" + g_str;
	setTimeout("a()",1000);
	
	$(function () {
		$("#text").blur(function() {
	        //var val = $(this).val();
	        $("#text_msg").html("vcb");
	 	});
		$("#text").focus(function() {
	        //var val = $(this).val();
	        $("#text_msg").html("");
	 	});
	});
}
window.onload = a;
</script>
</body>
</html>