<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search</title>
<style type="text/css">
.leftfloat {
	float:left;
}
.clearboth {
	clear:both;
}
.status {

}
html, body { height: 60%; margin: 0; padding: 0; }
#map { height: 100%; }
</style>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<%
boolean isBookName = false;
boolean isIsbn = false;
boolean isBookInfo = false;

String type = request.getParameter("type");
switch(type) {
	case "bookname":
		isBookName = true;
		break;
	case "isbn":
		isIsbn = true;
		break;
	case "bookinfo":
		isBookInfo = true;
		break;
}

String q = request.getParameter("q");
%>
<form action="/search" method="get" class="leftfloat">
	<%if(isBookName) { %>
	<button type="submit" name="type" value="bookname" class="btn btn-success" >bookname</button>
	<%}else { %>
	<button type="submit" name="type" value="bookname" class="btn btn-default" >bookname</button>
	<%} %>
	<input type="hidden" name="q" value="<%=q %>">
</form>
<form action="/search" method="get" class="leftfloat">
	<%if(isIsbn) { %>
	<button type="submit" name="type" value="isbn" class="btn btn-success" >isbn</button>
	<%}else { %>
	<button type="submit" name="type" value="isbn" class="btn btn-default" >isbn</button>
	<%} %>
	<input type="hidden" name="q" value="<%=q %>">
</form>
<form action="/search" method="get" class="leftfloat">
	<%if(isBookInfo) { %>
	<button type="submit" name="type" value="bookinfo" class="btn btn-success" >bookinfo</button>
	<%}else { %>
	<button type="submit" name="type" value="bookinfo" class="btn btn-default" >bookinfo</button>
	<%} %>
	<input type="hidden" name="q" value="<%=q %>">
</form>
<hr class="clearboth">
<c:if test="${type=='bookname'}">
<table class="table table-hover table-condensed">
	<tr>
		<th>bookId</th>
		<th>bookName</th>
		<th>bookInfo</th>
	</tr>
	<c:forEach items="${searchbooknameshow.bookList}" var="bookList">
		<tr>
			<th>${bookList.bookId}</th>
			<th><a href="/book/${bookList.bookId}">${bookList.name}</a></th>
			<th>${bookList.bookInfoId}</th>
		</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${type=='isbn'}">
<div>
<a href="/book/${searchisbnshow.book.bookId}">${searchisbnshow.book.name}</a>
[ISBN:${searchisbnshow.book.isbn}]
</div>
<div id="map"></div>
<table class="table table-hover table-condensed">
	<tr>
		<th>userNickName</th>
		<th>locationName</th>
		<th>bookName</th>
		<th>状态</th>
	</tr>
<c:forEach items="${searchisbnshow.stacksList}" varStatus="status">
	<tr>
		<th><a href="/people/${searchisbnshow.userList[status.index].userId}">${searchisbnshow.userList[status.index].userNickname}</a></th>
		<th>${searchisbnshow.locationList[status.index].name}</th>
		<th><a href="/book/${searchisbnshow.book.bookId}">${searchisbnshow.book.name}</a></th>
		<th>
			<c:if test="${searchisbnshow.userList[status.index].userId!=request.userId}"><!-- 不显示自己 -->
			<c:choose>
				<c:when test="${fn:contains(peoplestacksshow.cartItemId,searchisbnshow.stacksList[status.index].itemId)}">
					<button type="button" class="status btn btn-danger" id="${searchisbnshow.stacksList[status.index].itemId}" value="${searchisbnshow.stacksList[status.index].itemId},in" >已加入借阅箱</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="status btn btn-success" id="${searchisbnshow.stacksList[status.index].itemId}" value="${searchisbnshow.stacksList[status.index].itemId},out" >加入我的借阅箱</button>
				</c:otherwise>
			</c:choose>
			</c:if>
		</th>
	</tr>
</c:forEach>
</table>
<script type="text/javascript">
$(function () {
    $(".status").click(function(){
        var url = "/ajax/stacks/people/cart";
        var val = $(this).val();
        var html = $(this).html();

        var itemId = val.split(",")[0];
        var status = val.split(",")[1];
        var userId = "${request.userId}";
        var verify = "${request.verify}";

        //alert(status+","+itemId+","+userId+","+verify);

        if(val==itemId + ",out"||val==itemId + ",in") {
            var sendData = {"status":status,"itemId":itemId,"userId":userId,"verify":verify};
            $.post(url,sendData,function(backData,textStatus,ajax) {
                var javaJSON = ajax.responseText;
                var jsJSON = eval("(" + javaJSON + ")");
                if(jsJSON=="success") {
                    if(html=="已加入借阅箱") {
                        $("#" + itemId).val(itemId + ",out");
                        $("#" + itemId).html("加入我的借阅箱");
                        $("#" + itemId).attr("class","btn btn-success");
                    }
                    else if(html=="加入我的借阅箱") {
                    	$("#" + itemId).val(itemId + ",in");
                    	$("#" + itemId).html("已加入借阅箱");
                    	$("#" + itemId).attr("class","btn btn-danger");
                    }
                }
            });
        }
        else {
            return;
        }
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
	  	<c:forEach items="${searchisbnshow.locationList}" var="l">
			  		var location = {lat: ${l.n}, lng: ${l.e}};
				  	var marker = new google.maps.Marker({
				  	    position: location,
				  	    map: map,
				  	    title: '${l.name}'
				  	});
		</c:forEach>
	}
</script>
<!-- src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap"> -->
<!-- src="https://ditu.google.cn/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap"> -->
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap">
</script>
</c:if>
<c:if test="${type=='bookinfo'}">
${message}<br>
<table class="table table-hover table-condensed">
<c:forEach items="${searchbookinfoshow.bookIdMap}" var="bookmap">
	<tr>
		<th><a href="/book/${bookmap.value.bookId}">${bookmap.value.bookId}&nbsp;${bookmap.value.name}[ISBN:${bookmap.value.isbn}]</a></th>
	</tr>
</c:forEach>
</table>
<div id="map"></div>
<table class="table table-hover table-condensed">
	<tr>
		<th>userNickName</th>
		<th>locationName</th>
		<th>bookName</th>
		<th>状态</th>
	</tr>
<c:forEach items="${searchbookinfoshow.stacksList}" varStatus="status">
	<tr>
		<th><a href="/people/${searchbookinfoshow.userList[status.index].userId}">${searchbookinfoshow.userList[status.index].userNickname}</a></th>
		<th>${searchbookinfoshow.locationList[status.index].name}</th>
		<th><a href="/book/${searchbookinfoshow.bookList[status.index].bookId}">${searchbookinfoshow.bookList[status.index].bookId}&nbsp;${searchbookinfoshow.bookList[status.index].name}</a></th>
		<th>
		<c:if test="${searchbookinfoshow.userList[status.index].userId!=request.userId}"><!-- 不显示自己 -->
		<c:choose>
				<c:when test="${fn:contains(peoplestacksshow.cartItemId,searchbookinfoshow.stacksList[status.index].itemId)}">
					<button type="button" class="status btn btn-danger" id="${searchbookinfoshow.stacksList[status.index].itemId}" value="${searchbookinfoshow.stacksList[status.index].itemId},in" >已加入借阅箱</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="status btn btn-success" id="${searchbookinfoshow.stacksList[status.index].itemId}" value="${searchbookinfoshow.stacksList[status.index].itemId},out" >加入我的借阅箱</button>
				</c:otherwise>
		</c:choose>
		</c:if>
		</th>
	</tr>
</c:forEach>
	
</table>
<script type="text/javascript">
$(function () {
    $(".status").click(function(){
        var url = "/ajax/stacks/people/cart";
        var val = $(this).val();
        var html = $(this).html();

        var itemId = val.split(",")[0];
        var status = val.split(",")[1];
        var userId = "${request.userId}";
        var verify = "${request.verify}";

        //alert(status+","+itemId+","+userId+","+verify);

        if(val==itemId + ",out"||val==itemId + ",in") {
            var sendData = {"status":status,"itemId":itemId,"userId":userId,"verify":verify};
            $.post(url,sendData,function(backData,textStatus,ajax) {
                var javaJSON = ajax.responseText;
                var jsJSON = eval("(" + javaJSON + ")");
                if(jsJSON=="success") {
                    if(html=="已加入借阅箱") {
                        $("#" + itemId).val(itemId + ",out");
                        $("#" + itemId).html("加入我的借阅箱");
                        $("#" + itemId).attr("class","btn btn-success");
                    }
                    else if(html=="加入我的借阅箱") {
                    	$("#" + itemId).val(itemId + ",in");
                    	$("#" + itemId).html("已加入借阅箱");
                    	$("#" + itemId).attr("class","btn btn-danger");
                    }
                }
            });
        }
        else {
            return;
        }
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
	  	<c:forEach items="${searchbookinfoshow.locationIdMap}" var="l">
			  		var location = {lat: ${l.value.n}, lng: ${l.value.e}};
				  	var marker = new google.maps.Marker({
				  	    position: location,
				  	    map: map,
				  	    title: '${l.value.name}'
				  	});
		</c:forEach>
	}
</script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh8aME_ww-vFVileHeIB9vbLatITorqec&callback=initMap">
</script>
</c:if>
<hr>
search.jsp
</body>
</html>