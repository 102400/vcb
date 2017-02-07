<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orderlend</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<c:forEach items="${orderborrowshow.orderList}" var="ordervat">
	<div>
	<table class="table table-hover table-condensed">
		<c:forEach items="${ordervat.orderItemList}" varStatus="status">
			<c:if test="${status.first}">
				<div>
					订单号:${ordervat.order.orderId}&nbsp;&nbsp;
					地点:${ordervat.ownerLocationList[status.index].locationId}&nbsp;${ordervat.ownerLocationList[status.index].name}&nbsp;&nbsp;
					owner:<a href="/people/${ordervat.ownerList[status.index].userId}">${ordervat.ownerList[status.index].userNickname}</a>
					holder:<a href="/people/${ordervat.holderList[status.index].userId}">${ordervat.holderList[status.index].userNickname}</a>
				</div>
				<tr>
					<th>
						bookName
					</th>
				</tr>
			</c:if>
			<tr>
				<th>
					<a href="/book/${ordervat.bookList[status.index].bookId}">${ordervat.bookList[status.index].name}</a>
				</th>
			</tr>
			<c:if test="${status.last}">
				<div>
					<c:choose>
						<c:when test="${ordervat.order.state==-99}">
							订单状态:<span id="s${ordervat.order.orderId}">不同意借书</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="-99,${ordervat.order.orderId}" disabled="disabled">X</button>
						</c:when>
						<c:when test="${ordervat.order.state==0}">
							订单状态:<span id="s${ordervat.order.orderId}">未知错误</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="0,${ordervat.order.orderId}" disabled="disabled">X</button>
						</c:when>
						<c:when test="${ordervat.order.state==1}">
							订单状态:<span id="s${ordervat.order.orderId}">你的选择:</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="1,${ordervat.order.orderId}">同意借出</button>
							<button type="button" class="status btn btn-default" id="d${ordervat.order.orderId}" value="-1,${ordervat.order.orderId}">不同意借出</button>
						</c:when>
						<c:when test="${ordervat.order.state==2}">
							订单状态:<span id="s${ordervat.order.orderId}">等待对方拿书</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="2,${ordervat.order.orderId}" disabled="disabled">X</button>
						</c:when>
						<c:when test="${ordervat.order.state==3}">
							订单状态:<span id="s${ordervat.order.orderId}">已借给对方</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="3,${ordervat.order.orderId}">催还</button>
						</c:when>
						<c:when test="${ordervat.order.state==4}">
							订单状态:<span id="s${ordervat.order.orderId}">等待对方还书</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="4,${ordervat.order.orderId}" >确认收到书</button>
						</c:when>
						<c:when test="${ordervat.order.state==5}">
							订单状态:<span id="s${ordervat.order.orderId}">完成订单</span>
							<button type="button" class="status btn btn-default" id="${ordervat.order.orderId}" value="5,${ordervat.order.orderId}" disabled="disabled">X</button>
						</c:when>
					</c:choose>
				</div>
			</c:if>
		</c:forEach>
	</table>
	</div>
	<hr>
</c:forEach>



orderlend.JSP
<script type="text/javascript">
$(function () {
    $(".status").click(function(){
        var url = "/ajax/order/process";
        var val = $(this).val();
        var html = $(this).html();

        var status = val.split(",")[0];
        var orderId = val.split(",")[1];
        
        //alert(status+","+itemId+","+userId+","+verify);

        var sendData = {"status":status,"orderId":orderId,"u":"owner"};  //操作指令
        $.post(url,sendData,function(backData,textStatus,ajax) {
            var javaJSON = ajax.responseText;
            var jsJSON = eval("(" + javaJSON + ")");
         	var isSuccess = jsJSON.split(",")[0];
         	var nextStatus = jsJSON.split(",")[1];
            if(isSuccess=="success") {
                if(nextStatus=="2") {
                    $("#" + orderId).val(nextStatus + "," + orderId);
                    $("#" + orderId).html("X");
                    $("#" + orderId).attr("disabled","disabled");
                    $("#d" + orderId).html("X");
                    $("#d" + orderId).attr("disabled","disabled");
                    $("#d" + orderId).hide();
                    $("#s" + orderId).html("等待对方拿书");
                }
                else if(nextStatus=="4") {
                	$("#" + orderId).val(nextStatus + "," + orderId);
                    $("#" + orderId).html("确认收到书");
                    $("#" + orderId).removeAttr("disabled");
                    $("#s" + orderId).html("等待对方还书");
                }
                else if(nextStatus=="5") {
                	$("#" + orderId).val(nextStatus + "," + orderId);
                    $("#" + orderId).html("X");
                    $("#" + orderId).attr("disabled","disabled");
                    $("#s" + orderId).html("完成订单");
                }
                else if(nextStatus=="-99") {
                	$("#" + orderId).val(nextStatus + "," + orderId);
                    $("#" + orderId).html("X");
                    $("#" + orderId).attr("disabled","disabled");
                    $("#d" + orderId).html("X");
                    $("#d" + orderId).attr("disabled","disabled");
                    $("#d" + orderId).hide();
                    $("#s" + orderId).html("不同意借书");
                }
            }
        });
    });

});
</script>
</body>
</html>