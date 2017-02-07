<%@page import="rule.VerifySource"%>
<%@page import="util.MD5"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//修改
	//<li class="active">
	String active_home = "";
	String active_read = "";
	String active_mystacks = "";
	String active_borrow = "";
	String active_register = "";
	String active_login = "";
	
	String RequestURI = request.getRequestURI();
	//System.out.println(RequestURI);
	switch(RequestURI) {
		case "/WEB-INF/index.jsp":
			active_home = "active";
			break;
		case "/WEB-INF/mystacks.jsp":
			active_mystacks = "active";
			break;
		case "/WEB-INF/borrow.jsp":
			active_borrow = "active";
			break;
		case "/WEB-INF/register.jsp":
			active_register = "active";
			break;
		case "/WEB-INF/login.jsp":
			active_login = "active";
			break;
	}
	
	%>
	<!-- 
	String nickname = "";
	int userId = 0;
	String verify = "";
	
	boolean isLogin = false;
	if(request.getCookies()!=null) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies) {
			
			if(cookie.getName().equals("nickname")) {
				String[] sss = cookie.getValue().split("&");  //解密程序
				StringBuilder bs = new StringBuilder();
				for(String str:sss) {
					int x = Integer.valueOf(str);
					char c = (char)x;
					bs.append(c);
				}
				nickname = bs.toString();
			}
			
			if(cookie.getName().equals("user_id")) {
				try {
					userId = Integer.valueOf(cookie.getValue());
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(cookie.getName().equals("verify")) {
				verify = cookie.getValue();
			}
		}
	}
	
String str_verify_source = VerifySource.get(userId + "");    //"#vc" + user_id + "@*!6^xs";

if(verify.equals(MD5.code(str_verify_source))) {
	isLogin = true;
}

request.setAttribute("isLogin",isLogin);
request.setAttribute("nickname", nickname);
request.setAttribute("userId", userId);
request.setAttribute("verify", verify);
	//request.setAttribute("password", password);
-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style>
        body{
            padding-top: 0px;
        }
        .starter{
            padding: 40px 15px;
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">VCB</a>
        </div>
        
        <!-- 搜索框 -->
        <form action="/search" method="get" class="navbar-form navbar-left" role="search">  <!-- 更改  -->
	        <div class="form-group">
	        	<input type="hidden" name="type" value="bookname">
	        	<input type="text"  name="q" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">O</button>
      	</form>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="<%=active_home %>"><a href="/">首页</a></li>
                <c:if test="${request.isLogin}">
	                <li class="<%=active_read %>"><a href="/people/${request.userId}">我读</a></li>
	                <li class="<%=active_mystacks %>"><a href="/stacks">书库</a></li>
	                <li class="<%=active_borrow %>"><a href="/borrow">借书</a></li>
                </c:if>
             </ul>
             <ul class="nav navbar-nav navbar-right">
             <c:choose>
                 <c:when test="${!request.isLogin}">
	                <li class="<%=active_register %>"><a href="/register" >注册</a></li>
	                <li class="<%=active_login %>"><a href="/login" >登陆</a></li>
                </c:when>
              	<c:otherwise>
	            	<li class="dropdown">
	          		<a href="#" class="dropdown-toggle" data-toggle="dropdown">${request.nickname}<span class="caret"></span></a>
		          		<ul class="dropdown-menu" role="menu">
		            	<li><a href="/people/${request.userId}">我的主页</a></li>
		            	<li><a href="/message">消息</a></li>
		            	<li><a href="/settings">设置</a></li>
		            	<li class="divider"></li>
		            	<li><a href="/logout">退出</a></li>
		          		</ul>
	       	 		</li>
            	</c:otherwise>
            </c:choose>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>