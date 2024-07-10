<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh_CN">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div id="box" style="width: 1200px;">
		<table class="table1200" border="0" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF"
			bgcolor="#FFFFFF" style="margin-bottom: 20px;">
			<tr>
				<td align="center" valign="top" bgcolor="#FFFFFF" class="bg_qc">
					<div
						style="font-size: 14px; background-color: #F5F5F5; height: 30px; width: 100%; line-height: 30px; text-align: left; font-family: 宋体;">
						&nbsp;&nbsp;您的位置：<a href="index/index.action">首页</a>&nbsp;&gt;&nbsp;意见反馈
					</div>
					<table height="30" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td></td>
						</tr>
					</table>
					<form action="index/addComplains.action" name="myform" method="post">
						<table width="66%" border="0" cellpadding="5" cellspacing="1" bgcolor="#E0EDB7">
							<tr>
								<td height="30" colspan="2" align="center" background="themes/nzblue/images/nzcms/list_bg.gif" class="white14B">意见反馈
								</td>
							</tr>
							<tr>
								<td height="50" align="right" bgcolor="#FFFFFF">标题：</td>
								<td bgcolor="#FFFFFF"><input type="text" name="title" style="width: 260px; height: 30px"
									required="required" id="title" placeholder="请输入标题" /></td>
							</tr>
							<tr>
								<td height="50" align="right" bgcolor="#FFFFFF">内容：</td>
								<td bgcolor="#FFFFFF"><textarea style="width: 99%; height: 130px" name="contents" id="contents"
										placeholder="请输入内容" required="required"></textarea></td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" colspan="2" align="center"><label> <input type="submit" name="Submit"
										value="提交" /> &nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" name="Submit2" value="重置" />
								</label></td>
							</tr>
						</table>
					</form>
					<table height="50" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

