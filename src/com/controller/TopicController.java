package com.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.entity.Topic;
import com.service.TopicService;
import com.entity.Users;
import com.entity.Article;
import com.service.UsersService;
import com.service.ArticleService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/topic" , produces = "text/plain;charset=utf-8")
public class TopicController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private TopicService topicService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ArticleService articleService;

	// 准备添加数据
	@RequestMapping("createTopic.action")
	public String createTopic() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Article> articleList = this.articleService.getAllArticle();
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/addtopic";
	}
	// 添加数据
	@RequestMapping("addTopic.action")
	public String addTopic(Topic topic) {
		topic.setAddtime(VeDate.getStringDateShort());
		this.topicService.insertTopic(topic);
		return "redirect:/topic/createTopic.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddTopic.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddTopic(Topic topic) {
		topic.setAddtime(VeDate.getStringDateShort());
		int x = this.topicService.insertTopic(topic);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteTopic.action")
	public String deleteTopic(String id) {
		this.topicService.deleteTopic(id);
		return "redirect:/topic/getAllTopic.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteTopic.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteTopic(String id) {
		int x = this.topicService.deleteTopic(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteTopicByIds.action")
	public String deleteTopicByIds() {
		String[] ids = this.getRequest().getParameterValues("topicid");
		if (ids != null) {
			for (String topicid : ids) {
				this.topicService.deleteTopic(topicid);
			}
		}
		return "redirect:/topic/getAllTopic.action";
	}

	// 更新数据
	@RequestMapping("updateTopic.action")
	public String updateTopic(Topic topic) {
		this.topicService.updateTopic(topic);
		return "redirect:/topic/getAllTopic.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateTopic.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateTopic(Topic topic) {
		int x = this.topicService.updateTopic(topic);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllTopic.action")
	public String getAllTopic(String number) {
		List<Topic> topicList = this.topicService.getAllTopic();
		PageHelper.getUserPage(topicList, "topic", "getAllTopic", 10, number, this.getRequest());
		return "admin/listtopic";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Topic> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Topic> list = this.topicService.getAllTopic();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryTopicByCond.action")
	public String queryTopicByCond(String cond, String name, String number) {
		Topic topic = new Topic();
		if(cond != null){
			if ("usersid".equals(cond)) {
				topic.setUsersid(name);
			}
			if ("articleid".equals(cond)) {
				topic.setArticleid(name);
			}
			if ("contents".equals(cond)) {
				topic.setContents(name);
			}
			if ("addtime".equals(cond)) {
				topic.setAddtime(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.topicService.getTopicByLike(topic), "topic", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/querytopic";
	}

	// 按主键查询数据
	@RequestMapping("getTopicById.action")
	public String getTopicById(String id) {
		Topic topic = this.topicService.getTopicById(id);
		this.getRequest().setAttribute("topic", topic);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Article> articleList = this.articleService.getAllArticle();
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/edittopic";
	}


}
