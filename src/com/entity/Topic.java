package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 用户评论表的实体类
public class Topic {
	private String topicid = "T"+VeDate.getStringId(); // 生成主键编号
	private String usersid; // 用户
	private String articleid; // 新闻
	private String contents; // 内容
	private String addtime; // 日期
	private String username; // 映射数据
	private String title; // 映射数据
	private Users users;// 多对一映射类
	private Article article;// 多对一映射类

	public String getTopicid() {
		return this.topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getArticleid() {
		return this.articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("topicid", this.topicid); // 主键编号
		jsonString.put("usersid", this.usersid); // 用户
		jsonString.put("articleid", this.articleid); // 新闻
		jsonString.put("contents", this.contents); // 内容
		jsonString.put("addtime", this.addtime); // 日期
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("Article", this.article); // 多对一映射类
		jsonString.put("username", this.username); // 映射数据
		jsonString.put("title", this.title); // 映射数据
		return jsonString.toString();
	}




}




