<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/nzblue/js/datu.css" media="screen" />
<script type="text/javascript" src="<%=basePath%>themes/nzblue/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>themes/nzblue/js/jquery.jslides.js"></script>
<div id="full-screen-slider">
	<ul id="slides">
		<li style="background: url('<%=basePath%>pic/001.jpg') no-repeat center top"><a href="javascript:;">1</a></li>
		<li style="background: url('<%=basePath%>pic/002.jpg') no-repeat center top"><a href="javascript:;">2</a></li>
		<li style="background: url('<%=basePath%>pic/003.jpg') no-repeat center top"><a href="javascript:;">3</a></li>
		<li style="background: url('<%=basePath%>pic/004.jpg') no-repeat center top"><a href="javascript:;">4</a></li>
	</ul>
</div>
