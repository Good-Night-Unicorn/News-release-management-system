<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">

		<dl id="menu-admin">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>管理员信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="admin/createAdmin.action" data-title="新增管理员" href="javascript:void(0)">新增管理员信息</a></li>
					<li><a data-href="admin/getAllAdmin.action" data-title="管理员列表" href="javascript:void(0)">管理员信息列表</a></li>
					<li><a data-href="admin/queryAdminByCond.action" data-title="管理员查询" href="javascript:void(0)">管理员信息查询</a></li>
					<li><a data-href="admin/prePwd.action" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu-member">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>网站用户信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="users/getAllUsers.action" data-title="网站用户列表" href="javascript:void(0)">网站用户信息列表</a></li>
					<li><a data-href="users/queryUsersByCond.action" data-title="网站用户查询" href="javascript:void(0)">网站用户信息查询</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>新闻分类信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="banner/createBanner.action" data-title="新增新闻分类" href="javascript:void(0)">新增新闻分类信息</a></li>
					<li><a data-href="banner/getAllBanner.action" data-title="新闻分类列表" href="javascript:void(0)">新闻分类信息列表</a></li>
					<li><a data-href="banner/queryBannerByCond.action" data-title="新闻分类查询" href="javascript:void(0)">新闻分类信息查询</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>新闻内容信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="article/createArticle.action" data-title="新增新闻内容" href="javascript:void(0)">新增新闻内容信息</a></li>
					<li><a data-href="article/getAllArticle.action" data-title="新闻内容列表" href="javascript:void(0)">新闻内容信息列表</a></li>
					<li><a data-href="article/queryArticleByCond.action" data-title="新闻内容查询" href="javascript:void(0)">新闻内容信息查询</a></li>
				</ul>
			</dd>
		</dl>

		<dl id="menu">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>用户评论信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="topic/getAllTopic.action" data-title="用户评论列表" href="javascript:void(0)">用户评论信息列表</a></li>
					<li><a data-href="topic/queryTopicByCond.action" data-title="用户评论查询" href="javascript:void(0)">用户评论信息查询</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>提示问题信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="asks/createAsks.action" data-title="新增提示问题" href="javascript:void(0)">新增提示问题信息</a></li>
					<li><a data-href="asks/getAllAsks.action" data-title="提示问题列表" href="javascript:void(0)">提示问题信息列表</a></li>
					<li><a data-href="asks/queryAsksByCond.action" data-title="提示问题查询" href="javascript:void(0)">提示问题信息查询</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu">
			<dt>
				<i class="Hui-iconfont">&#xe62d;</i>意见反馈信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
			</dt>
			<dd>
				<ul>
					<li><a data-href="complains/getAllComplains.action" data-title="意见反馈列表" href="javascript:void(0)">意见反馈信息列表</a></li>
					<li><a data-href="complains/queryComplainsByCond.action" data-title="意见反馈查询" href="javascript:void(0)">意见反馈信息查询</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>


