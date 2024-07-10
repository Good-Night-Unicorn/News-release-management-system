<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="themes/nzblue/css/css.css" rel="stylesheet" type="text/css">
<link href="themes/nzblue/css/css_2.css" rel="stylesheet" type="text/css">
<table width="100%" height="200" border="0" align="center" cellpadding="0" cellspacing="0"
	style="background-color: #1C5CA6; background-image: url(themes/nzblue/images/nzgov_06.jpg); background-attachment: scroll; background-repeat: no-repeat; background-position: center center;">
	<tr>
		<td align="right" valign="middle"><table border="0" cellpadding="0" cellspacing="0" style="margin-right: 20px;">

			</table></td>
	</tr>
</table>
<!--下拉这里开始 -->
<script type="text/javascript">
	var timeout = 200;
	var closetimer = 0;
	var ddmenuitem = 0;
	// open hidden layer
	function mopen(id) {
		// cancel close timer
		mcancelclosetime();
		// close old layer
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
		// get new layer and show it
		ddmenuitem = document.getElementById(id);
		ddmenuitem.style.visibility = 'visible';
	}
	// close showed layer
	function mclose() {
		if (ddmenuitem)
			ddmenuitem.style.visibility = 'hidden';
	}
	// go close timer
	function mclosetime() {
		closetimer = window.setTimeout(mclose, timeout);
	}
	// cancel close timer
	function mcancelclosetime() {
		if (closetimer) {
			window.clearTimeout(closetimer);
			closetimer = null;
		}
	}
	// close layer when click-out
	document.onclick = mclose;
</script>
<div id="top_dh_box">

	<div id="top_dh_01">
		<div id="sddm">
			<li id="sddmdiv2"><a href="index/index.action">本站首页</a></li>
			<c:forEach items="${bannerList}" var="banner">
				<li><table width="120" border="0" cellpadding="0" cellspacing="0" class="dh_bj01">
						<tr>
							<td height="55" align="center"><a href="index/article.action?id=${banner.bannerid}">${banner.bannername }</a></td>
						</tr>
					</table></li>
				<!--导航 -->
			</c:forEach>
			<c:if test="${sessionScope.userid != null }">
				<li><table width="120" border="0" cellpadding="0" cellspacing="0" class="dh_bj01">
						<tr>
							<td height="55" align="center"><a href="javascript:;" onmouseover="mopen('m663')" onmouseout="mclosetime()">用户菜单</a></td>
						</tr>
					</table>
					<div id="m663" onMouseOver="mcancelclosetime()" onMouseOut="mclosetime()">
						<table border="0" cellpadding="0" cellspacing="0" class="top_dh_02">
							<tr>
								<td align=left><table width="100" border="0" cellpadding="0" cellspacing="0" class="xux" onMouseOver="this.bgColor='#F5F5F5';"
										onMouseOut="this.bgColor='#FFFFFF';" bgcolor="#ffffff">
										<tr>
											<td height="40"><a href="index/prePwd.action"><img src="themes/nzblue/images/biao.gif" border="0">&nbsp;修改密码</a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align=left><table width="100" border="0" cellpadding="0" cellspacing="0" class="xux" onMouseOver="this.bgColor='#F5F5F5';"
										onMouseOut="this.bgColor='#FFFFFF';" bgcolor="#ffffff">
										<tr>
											<td height="40"><a href="index/userinfo.action"><img src="themes/nzblue/images/biao.gif" border="0">&nbsp;个人信息</a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align=left><table width="100" border="0" cellpadding="0" cellspacing="0" class="xux" onMouseOver="this.bgColor='#F5F5F5';"
										onMouseOut="this.bgColor='#FFFFFF';" bgcolor="#ffffff">
										<tr>
											<td height="40"><a href="index/myFav.action"><img src="themes/nzblue/images/biao.gif" border="0">&nbsp;我的收藏</a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align=left><table width="100" border="0" cellpadding="0" cellspacing="0" class="xux" onMouseOver="this.bgColor='#F5F5F5';"
										onMouseOut="this.bgColor='#FFFFFF';" bgcolor="#ffffff">
										<tr>
											<td height="40"><a href="index/preComplains.action"><img src="themes/nzblue/images/biao.gif" border="0">&nbsp;意见反馈</a></td>
										</tr>
									</table></td>
							</tr>
							<tr>
								<td align=left><table width="100" border="0" cellpadding="0" cellspacing="0" class="xux" onMouseOver="this.bgColor='#F5F5F5';"
										onMouseOut="this.bgColor='#FFFFFF';" bgcolor="#ffffff">
										<tr>
											<td height="40"><a href="index/myComplains.action"><img src="themes/nzblue/images/biao.gif" border="0">&nbsp;我的意见反馈</a></td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div></li>
				<!--导航 -->
				<li><table width="120" border="0" cellpadding="0" cellspacing="0" class="dh_bj01">
						<tr>
							<td height="55" align="center"><a href="index/exit.action">退出登录</a></td>
						</tr>
					</table></li>
				<!--导航 -->
			</c:if>
			<c:if test="${sessionScope.userid == null }">
				<li><table width="120" border="0" cellpadding="0" cellspacing="0" class="dh_bj01">
						<tr>
							<td height="55" align="center"><a href="index/preReg.action">用户注册</a></td>
						</tr>
					</table></li>
				<li><table width="120" border="0" cellpadding="0" cellspacing="0" class="dh_bj01">
						<tr>
							<td height="55" align="center"><a href="index/preLogin.action">用户登录</a></td>
						</tr>
					</table></li>
			</c:if>
		</div>
	</div>
</div>