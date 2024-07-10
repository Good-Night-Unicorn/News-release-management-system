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
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/pwd.js" charset="utf-8"></script>
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
						&nbsp;&nbsp;您的位置：<a href="index/index.action">首页</a>&nbsp;&gt;&nbsp;运输订单
					</div>
					<table height="30" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td></td>
						</tr>
					</table>
					<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left">
								<div class="z" id="z">
									<table width="99%" border="0" cellpadding="5" cellspacing="1" bgcolor="#E0EDB7">
										<tr>
											<td height="30" colspan="13" align="left" background="themes/nzblue/images/nzcms/list_bg.gif"
												class="white14B">运输订单</td>
										</tr>
										<tr>
											<td align="center" bgcolor="#FFFFFF">货运单号</td>
											<td align="center" bgcolor="#FFFFFF">发货人</td>
											<td align="center" bgcolor="#FFFFFF">物资类型</td>
											<td align="center" bgcolor="#FFFFFF">重量</td>
											<td align="center" bgcolor="#FFFFFF">出发地</td>
											<td align="center" bgcolor="#FFFFFF">目的地</td>
											<td align="center" bgcolor="#FFFFFF">运费</td>
											<td align="center" bgcolor="#FFFFFF">状态</td>
											<td align="center" bgcolor="#FFFFFF">承运人</td>
											<td align="center" bgcolor="#FFFFFF">车辆</td>
											<td align="center" bgcolor="#FFFFFF">日期</td>
											<td align="center" bgcolor="#FFFFFF">备注</td>
											<td align="center" bgcolor="#FFFFFF">操作</td>
										</tr>
										<c:forEach items="${ordersList}" var="orders">
											<tr align="center" bgcolor="#FFFFFF">
												<td>${orders.ordercode}</td>
												<td>${orders.realname}</td>
												<td>${orders.catename}</td>
												<td>${orders.zhongliang}</td>
												<td>${orders.thestart}</td>
												<td>${orders.theend}</td>
												<td>${orders.total}</td>
												<td>${orders.status}</td>
												<td>${orders.sendername}</td>
												<td>${orders.carsno}</td>
												<td>${orders.addtime}</td>
												<td>${orders.memo}</td>
												<td><c:if test="${orders.status eq '通过审核'}">
														<a href="index/preSend.action?id=${orders.ordersid}">接受订单</a>
													</c:if> </td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<table height="20" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>${html }</td>
						</tr>
					</table>
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

