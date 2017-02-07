<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mystacks</title>
<style type="text/css">
.status {

}
html, body { height: 60%; margin: 0; padding: 0; }
#map { height: 100%; }
</style>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>

<div id="map"></div>

<c:forEach items="${mystacksshow.locationList}" var="location">
	<!-- 显示defaultLocation -->
	<h3>${location.name}</h3>纬度${location.n} 经度${location.e}<!-- ${location.locationId}<br/> -->
	<table class="table table-hover table-condensed">
		<tr>
			<th>bookName</th>
			<th>状态</th>
		</tr>
		 <c:forEach var="i" begin="0" end="${mystacksshow.itemListSize}" step="1">
			<c:if test="${mystacksshow.itemList[i].ownerLocationId==location.locationId}">
				<tr>
						<c:choose>
							<c:when test="${mystacksshow.itemList[i].isLoan}">
								<th>
									<a href="/book/${mystacksshow.itemList[i].bookId}">${mystacksshow.itemList[i].bookId}
										<c:if test="${mystacksshow.itemList[i].bookId==mystacksshow.bookList[i].bookId}">
											${mystacksshow.bookList[i].name}
										</c:if>
									</a>
								</th>
								<th>
									<button type="button" class="btn btn-warning" >借出</button>
								</th>
							</c:when>
							<c:when test="${mystacksshow.itemList[i].canBorrow}">
								<th>
									<a href="/book/${mystacksshow.itemList[i].bookId}">${mystacksshow.itemList[i].bookId}
										<c:if test="${mystacksshow.itemList[i].bookId==mystacksshow.bookList[i].bookId}">
											${mystacksshow.bookList[i].name}
										</c:if>
									</a>
								</th>
								<!-- jstl bug?  访问userId为1时,失效?
								<c:forEach items="${peoplestacksshow.cartItemId}" var="qqq">${qqq}@</c:forEach>
								<div>!!!${mystacksshow.itemList[i].itemId}<div>
								 -->
								<th>
									<c:choose>
										<c:when test="${fn:contains(peoplestacksshow.cartItemId,mystacksshow.itemList[i].itemId)}">
											<button type="button" class="status btn btn-danger" id="${mystacksshow.itemList[i].itemId}" value="${mystacksshow.itemList[i].itemId},in" >已加入借阅箱</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="status btn btn-success" id="${mystacksshow.itemList[i].itemId}" value="${mystacksshow.itemList[i].itemId},out" >加入我的借阅箱</button>
										</c:otherwise>
									</c:choose>
								</th>
							</c:when>
						</c:choose>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<hr>
</c:forEach>
mystacks.JSP
<script type="text/javascript">
$(function () {
    $(".status").click(function(){
        var url = "/ajax/stacks/people/cart";
        var val = $(this).val();
        var html = $(this).html();

        var itemId = val.split(",")[0];
        var status = val.split(",")[1];
        //var userId = "${request.userId}";
        //var verify = "${request.verify}";

        //alert(status+","+itemId+","+userId+","+verify);

        if(val==itemId + ",out"||val==itemId + ",in") {
        	//var sendData = {"status":status,"itemId":itemId,"userId":userId,"verify":verify};
            var sendData = {"status":status,"itemId":itemId};
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
	  	
	  	<c:forEach items="${mystacksshow.locationList}" var="location">
	  		var location = {lat: ${location.n}, lng: ${location.e}};
		  	var marker = new google.maps.Marker({
		  	    position: location,
		  	    map: map,
		  	    title: '${location.name}'
		  	});
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