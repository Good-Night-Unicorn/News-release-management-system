<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<!-- duilian-->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="end_dh_box">
	<tr>
		<td align="center">
			<p>
				版权所有 | <a href="<%=basePath%>admin/index.action"
					target="_blank"><font color="#FF6600">后台管理员入口</font> </a>
			</p>
			<p>&nbsp;</p>
		</td>
	</tr>
</table>

<%
	String message = (String) session.getAttribute("message");
	if (message == null) {
		message = "";
	}
	if (!message.trim().equals("")) {
		out.println("<script language='javascript'>");
		out.println("alert('" + message + "');");
		out.println("</script>");
	}
	session.removeAttribute("message");
%>