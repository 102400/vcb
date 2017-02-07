<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cart</title>
<style type="text/css">
.check-all {

}
.status{

}
.delete{

}
html, body { height: 60%; margin: 0; padding: 0; }
#map { height: 100%; }
</style>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>

<div id="map"></div>

<h4>我的借阅箱</h4>
<hr>
<!-- 地图上显示 -->
<c:forEach items="${cartshow.ownerLocationIdMap}" var="i">
	<div>
	<!-- <form action="/order/create" method="post"> -->
	<table class="table table-hover table-condensed">
		<c:forEach items="${i.value}" var="j" varStatus="status">
				<c:if test="${status.first}">
					<div>
						<a href="/people/${j.owner.userId}">${j.owner.userNickname}</a>:
						[${j.ownerlocation.locationId}]${j.ownerlocation.name}&nbsp;纬度${j.ownerlocation.n}&nbsp;经度${j.ownerlocation.e}<br>
					</div>
					<tr>
						<th><input class="check-all" type="checkbox"  value="${j.ownerlocation.locationId}"/>全选</th>
						<th>bookName</th>
						<th>删除</th>
					</tr>
				</c:if>
					<tr class="${j.cart.itemId}">
						<th><input name="${j.ownerlocation.locationId}" type="checkbox" value="${j.cart.itemId}"/></th>
						<th><a href="/book/${j.book.bookId}">[${j.book.bookId}]${j.book.name}</a></th>
						<th><button type="button" class="delete btn btn-danger" value="${j.cart.itemId},in">删除</button></th>
					</tr>
		</c:forEach>
	</table>
	<c:forEach items="${i.value}" var="j" varStatus="status" end="${exitId}">
			<c:if test="${status.first}">
				<button type="button" class="status btn btn-default" value="${j.ownerlocation.locationId}">提交订单</button>
				<c:set var="exitId" value="0"></c:set>
			</c:if>
	</c:forEach>
	<!-- </form> -->
	</div>
	<hr>
</c:forEach>




cart.jsp

<script type="text/javascript">
$(function () {
	 $(".delete").click(function() {
	        var url = "/ajax/stacks/people/cart";
	        var val = $(this).val();
	        var html = $(this).html();

	        var itemId = val.split(",")[0];
	        var status = val.split(",")[1];


	        if(val==itemId + ",in") {
	            var sendData = {"status":status,"itemId":itemId};
	            $.post(url,sendData,function(backData,textStatus,ajax) {
	                var javaJSON = ajax.responseText;
	                var jsJSON = eval("(" + javaJSON + ")");
	                if(jsJSON=="success") {
	                    if(html=="删除") {
	                    	alert("ok");
	                    	//删除节点
	                        $("." + itemId).remove();
	                    }
	                }
	            });
	        }
	        else {
	            return;
	        }
		 
	 });
	 $(".check-all").click(function() {
		 var val = $(this).val();
		 if(this.checked) {
			 $("input[name=" + val + "]" ).attr("checked",true);
		 }
		 else {
			 $("input[name=" + val + "]" ).attr("checked",false);
		 }
	 });
	 $(".status").click(function() {
		 var url = "/ajax/order/create";
		 var val = $(this).val();
		 
		 var itemIds = [];
		 
		 $("input[name=" + val + "]:checked").each(function(a,b){
			 var val = $(b).attr("value");
			 itemIds.push(val);
		});
		 var params = $.param({'itemIds':itemIds},true);
		 
		 //var status = 1;  //创建订单
		 //var sendData = {"status":status + "," + params};
		 var sendData = params;
         $.post(url,sendData,function(backData,textStatus,ajax) {
             var javaJSON = ajax.responseText;
             var jsJSON = eval("(" + javaJSON + ")");
             if(jsJSON=="success") {
            	 //跳转订单页
            	 alert("订单创建成功");
            	 window.location.href="/order/borrow";
             }
             else if(jsJSON=="fail") {
            	 alert("订单创建失败");
             }
             else {
            	 alert("出现未知错误");
             }
         });
		 
	 });
});
</script>
<script type="text/javascript">
	var map;
	function initMap() {
		var shanghai = {lat: 31.2335, lng: 121.4716};
	  	var map = new google.maps.Map(document.getElementById('map'), {
		    center: shanghai,
		    zoom: 10
	  	});
	  	<c:forEach items="${cartshow.ownerLocationIdMap}" var="i">
		  	<c:forEach items="${i.value}" var="j" varStatus="status" end="${exitId}">
		  		<c:if test="${status.first}">
			  		var location = {lat: ${j.ownerlocation.n}, lng: ${j.ownerlocation.e}};
				  	var marker = new google.maps.Marker({
				  	    position: location,
				  	    map: map,
				  	    title: '${j.ownerlocation.name}'
				  	});
				  	<c:set var="exitId" value="0"></c:set>
				</c:if>
		  	</c:forEach>
		</c:forEach>
	}
</script>
<!-- src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap"> -->
<!-- src="https://ditu.google.cn/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap"> -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap">
</script>
</body>
</html>