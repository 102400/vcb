<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<th>holderId</th>
		</tr>
		 <c:forEach var="i" begin="0" end="${mystacksshow.itemListSize}" step="1">
			<c:if test="${mystacksshow.itemList[i].ownerLocationId==location.locationId}">
				<tr>
					<th>
						<a href="/book/${mystacksshow.itemList[i].bookId}">${mystacksshow.itemList[i].bookId}
							<c:if test="${mystacksshow.itemList[i].bookId==mystacksshow.bookList[i].bookId}">
								${mystacksshow.bookList[i].name}
							</c:if>
						</a>
					</th>
					<th>
						<c:choose>
							<c:when test="${mystacksshow.itemList[i].isLoan}">
								<button type="button" class="btn btn-warning" >借出</button>
							</c:when>
							<c:when test="${mystacksshow.itemList[i].canBorrow}">
								<button type="button" class="status btn btn-success" id="${mystacksshow.itemList[i].itemId}" value="${mystacksshow.itemList[i].itemId},public" >公开</button>
							</c:when>
							<c:when test="${!mystacksshow.itemList[i].canBorrow}">
								<button type="button" class="status btn btn-default" id="${mystacksshow.itemList[i].itemId}" value="${mystacksshow.itemList[i].itemId},private" >私有</button>
							</c:when>
						</c:choose>
					</th>
					<th>
						<a href="/people/${mystacksshow.itemList[i].holderId}">${mystacksshow.itemList[i].holderId}</a>
					</th>
				</tr>
			</c:if>
		</c:forEach>
		</table>
</c:forEach>
mystacks.JSP

<script type="text/javascript">
$(function () {
    $(".status").click(function(){
        var url = "/ajax/stacks/my/status";
        var val = $(this).val();
        var html = $(this).html();

        var itemId = val.split(",")[0];
        var status = val.split(",")[1];
        var userId = "${request.userId}";
        var verify = "${request.verify}";

        //alert(status+","+itemId+","+userId+","+verify);

        if(val==itemId + ",public"||val==itemId + ",private") {
            var sendData = {"status":status,"itemId":itemId,"userId":userId,"verify":verify};
            $.post(url,sendData,function(backData,textStatus,ajax) {
                var javaJSON = ajax.responseText;
                var jsJSON = eval("(" + javaJSON + ")");
                if(jsJSON=="success") {
                    if(html=="私有") {
                        $("#" + itemId).val(itemId + ",public");
                        $("#" + itemId).html("公开");
                        $("#" + itemId).attr("class","btn btn-success");
                    }
                    else if(html=="公开") {
                    	$("#" + itemId).val(itemId + ",private");
                    	$("#" + itemId).html("私有");
                    	$("#" + itemId).attr("class","btn btn-default");
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