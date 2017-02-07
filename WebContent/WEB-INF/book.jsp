<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>book</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<h3>${bookshow.book.name}</h3>
<img><!-- 封面 -->
作者:
<c:forEach items="${bookshow.authorList}" var="author">
 	<a href="/celebrity/${author.celebrityId}">${author.nameZh}&nbsp;${author.nameEn}</a>&nbsp;&nbsp;
</c:forEach>
<br/>
出版社:${bookshow.publisher.name}<br/><!-- 链接出版社页面 -->
译者:
<c:forEach items="${bookshow.translatorList}" var="translator">
 	<a href="/celebrity/${translator.celebrityId}">${translator.nameZh}&nbsp;${translator.nameEn}</a>&nbsp;&nbsp;
</c:forEach>
<br/><!-- 译者 -->
语言:${bookshow.language.languageName}<br/><!-- 链接语言页面 -->
出版日期:${bookshow.book.publicationYear}-${bookshow.book.publicationMonth}<br/><!-- 链接出版日期页面 -->
页数:${bookshow.book.pages}<br/>
定价:${bookshow.book.price}<br/>
bookInfo:${bookshow.bookInfo.name}<br/><!-- 链接bookinfo页面 -->
ISBN:${bookshow.book.isbn}<br/>

<%--
作者:<br/>
出版社:${bookshow.publisherName}<br/><!-- 链接出版社页面 -->
译者:<br/>
语言:${bookshow.languageName}<br/><!-- 链接语言页面 -->
出版日期:${bookshow.book.publicationYear}-${bookshow.book.publicationMonth}<br/><!-- 链接出版日期页面 -->
页数:${bookshow.book.pages}<br/>
定价:${bookshow.book.price}<br/>
info:${bookshow.bookInfoName}<br/><!-- 链接bookinfo页面 -->
ISBN:${bookshow.book.isbn}<br/>
--%>
<!-- 
 ${box.endIndex}
 <c:forEach var="i" begin="0" end= "${box.endIndex}" step="1">
 	${box.a[i]}<br/>
 	${box.b[i]}<br/>
 	${box.c[i]}<br/>
 </c:forEach>
 -->
 
 <!-- 
 AJXA  想读 在读 读过 删除
  -->
<!-- 显示已拥有和(未)拥有 -->
<div>
<c:choose>
	<c:when test="${request.own}">
		<button type="button" class="btn btn-success" id="own"  value="true">已拥有</button>
	</c:when>
	<c:otherwise>
		<button type="button" class="btn btn-default" id="own"  value="false">拥有</button>
	</c:otherwise>
</c:choose>
</div>
<div><!-- 缺失功能:显示除默认地点外其他地点是否拥有 --></div>
<div>
<form action="/search" method="get">
<button type="submit" name="type" value="isbn" class="btn btn-default">通过ISBN借书</button>
<input type="hidden" name="q" value="${bookshow.book.isbn}">
</form>
<form action="/search" method="get">
<button type="submit" name="type" value="bookinfo" class="btn btn-default">通过bookInfo借书</button>
<input type="hidden" name="q" value="${bookshow.book.bookInfoId}">
</form>
</div>
<hr>
<div>
功能未完成
<button type="button" class="btn btn-default" id="wish">想读</button>
<button type="button" class="btn btn-default" id="do">在读</button>
<button type="button" class="btn btn-default" id="collect">读过</button>
<button type="button" class="btn btn-default" id="delect">删除</button>
</div>
<br>
book.jsp
<script type="text/javascript">
var bookId = ${bookshow.book.bookId};
var userId = ${request.userId};
var verify = "${request.verify}";
$(function () {
		//获取文本框中输入值

    $("#own").click(function(){
    	var url = "/ajax/book/own";
    	var val = $(this).val();
    	var html = $(this).html();
    	
        if(val=="true"||val=="false") {  //已拥有
        	var sendData = {"own":val,"bookId":bookId,"userId":userId,"verify":verify};
        	$.post(url,sendData,function(backData,textStatus,ajax) {
					var javaJSON = ajax.responseText;
					var jsJSON = eval("(" + javaJSON + ")");
					if(jsJSON=="success") {
	                    if(html=="拥有") {
	                    	$("#own").val("true");
	                    	$("#own").html("已拥有");
	                    	$("#own").attr("class","btn btn-success");
	                    }
	                    else if(html=="已拥有") {
	                    	$("#own").val("false");
	                    	$("#own").html("拥有");
	                    	$("#own").attr("class","btn btn-default");
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
</body>
</html>