<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html lang="zh_CN">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
<link href="themes/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div id="box" style="width: 1200px;">
		<table class="table1200" border="0" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#FFFFFF"
			style="margin-bottom: 20px;">
			<tr>
				<td align="center" valign="top" bgcolor="#FFFFFF" class="bg_qc">
					<div
						style="font-size: 14px; background-color: #F5F5F5; height: 30px; width: 100%; line-height: 30px; text-align: left; font-family: 宋体;">
						&nbsp;&nbsp;您的位置：<a href="index/index.action">首页</a>&nbsp;&gt;&nbsp;<a href="index/article.action?id=${article.bannerid }">${article.bannername }</a>&nbsp;&gt;&nbsp;${article.title }
					</div>
					<div
						style="font-size: 36px; line-height: 50px; color: #000000; text-align: center; width: 90%; padding-top: 25px; padding-bottom: 25px; font-weight: bold; font-family: 微软雅黑;">
						${article.title }</div>
					<div
						style="margin-top: 20px; font-size: 14px; color: #333333; background-color: #F5F5F5; height: 30px; width: 100%; margin-bottom: 20px; line-height: 30px; text-align: center;">
						发稿作者：管理员 &nbsp;&nbsp;‖&nbsp;&nbsp;发布时间：${article.addtime }&nbsp;&nbsp;‖&nbsp;&nbsp;查看${article.hits }次&nbsp;&nbsp;‖&nbsp;&nbsp;<input
							name="button" type="button" onclick="" value="收藏新闻" style="height: 30px" id="addfav" />
					</div> <img src="${article.image }" alt=" " border="0" class="bg_qc" style="width: 260px; height: 260px;" />

					<div
						style="text-align: left; margin: auto; width: 96%; line-height: 30px; font-size: 16px; color: #333333; fixed; word-break: break-all; fixed: word-break:                                       
	break-all;">${article.contents }</div>
					<div style="text-align: center; width: 90%; margin-top: 20px; margin-bottom: 20px;"></div>
				</td>
			</tr>
		</table>
		<div id="ECS_COMMENT">
			<div class="box">
				<div class="box_1">
					<h3>
						<span class="text">用户评论</span>(共<font class="f1">${tnum }</font>条评论)
					</h3>
					<div class="boxCenterList clearfix" style="height: 1%;">
						<ul class="comments">
							<c:forEach items="${topicList}" var="topic">
								<li class="word"><font class="f2">${topic.username } </font> <font class="f3">(${topic.addtime }) </font> <br />
									<p>${topic.contents }</p>
									<hr /></li>
							</c:forEach>
						</ul>
						<div class="blank5"></div>
					</div>
				</div>
			</div>
			<div class="blank5"></div>

		</div>
		<c:if test="${sessionScope.userid != null }">
			<form action="index/addTopic.action" name="myform" method="post">
				<table class="table1200" border="0" cellpadding="5" cellspacing="1" bgcolor="#E0EDB7">
					<tr>
						<td height="30" colspan="2" align="center" background="themes/nzblue/images/nzcms/list_bg.gif" class="white14B">新闻评论</td>
					</tr>
					<tr>
						<td height="50" align="right" bgcolor="#FFFFFF">内容：</td>
						<td bgcolor="#FFFFFF"><textarea style="width: 99%; height: 130px" name="contents" id="contents" placeholder="请输入内容"
								required="required"></textarea></td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" colspan="2" align="center"><label> <input type="hidden" name="articleid" id="articleid"
								value="${article.articleid }"> <input type="submit" name="Submit" value="提交" /> &nbsp;&nbsp;&nbsp;&nbsp; <input
								type="reset" name="Submit2" value="重置" />
						</label></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>
	<input type="hidden" id="articleid" value="${article.articleid}">
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$("#addfav").click(function() {
			var articleid = $('#articleid').val();
			var data = {
				id : articleid,
			};
			$.ajax({
				type : "post",
				url : 'index/addfav.action',
				data : data,
				dataType : "json",
				success : function(json) {
					var status = json.status;
					var message = json.message;
					if (status == 'login') {
						location.href = 'index/preLogin.action';
					} else {
						alert(message);
					}
				}
			});
		})
	</script>
</body>


</html>

