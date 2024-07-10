$(function() {
	$('#btnUpdate').click(function() {// 保存按钮点击事件
		// 获取页面数据并且转化成JSON格式的数据
		var data = { "articleid" : $("#articleid").val(),"title" : $("#title").val(),"bannerid" : $("#bannerid").val(),"image" : $("#image").val(),"istop" : $("#istop").val(),"isflv" : $("#isflv").val(),"contents" : $("#contents").val(),"addtime" : $("#addtime").val(),"hits" : $("#hits").val()};
		var loc = $('#basepath').val();// 获取项目根目录
		var url = loc + "article/xupdateArticle.action";// 获取更新数据Controller地址
		$.ajax({// 调用JQuery的Ajax方法
			type : "post",// 提交方式为Post
			url : url,// 提交路径
			dataType : "json",// 提交数据格式
			data : data,// 提交的数据
			success : function(json) {// 提交数据成功此json是返回值
				if (json.result == 0) {// 返回数据是0则更新失败
					alert('数据请求失败,请稍后再试！');// 弹出提示
				} else {// 返回数据不是0 则更新成功
					var index = parent.layer.getFrameIndex(window.name);// 获得frame索引
					parent.location.reload();// 父窗体重新加载
					parent.layer.close(index);// 关闭当前frame
				}
				console.log(json.result);// 浏览器控制台输出返回数据(测试用)
			},
			error : function() {// 提交数据失败 
				alert('ajax请求发生错误3');// 弹出提示
			}
		});
	});

	$('#btnClose').click(function() {// 关闭按钮点击事件
		var index = parent.layer.getFrameIndex(window.name);
		parent.location.reload();
		parent.layer.close(index);
	});
});


























