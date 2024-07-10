package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 用户收藏表的实体类
public class Fav {
	private String favid = "F"+VeDate.getStringId(); // 生成主键编号
	private String usersid; // 用户
	private String articleid; // 新闻
	private String addtime; // 收藏日期
	private String username; // 映射数据
	private String title; // 映射数据
	private Users users;// 多对一映射类
	private Article article;// 多对一映射类

	public String getFavid() {
		return this.favid;
	}

	public void setFavid(String favid) {
		this.favid = favid;
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
		jsonString.put("favid", this.favid); // 主键编号
		jsonString.put("usersid", this.usersid); // 用户
		jsonString.put("articleid", this.articleid); // 新闻
		jsonString.put("addtime", this.addtime); // 收藏日期
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("Article", this.article); // 多对一映射类
		jsonString.put("username", this.username); // 映射数据
		jsonString.put("title", this.title); // 映射数据
		return jsonString.toString();
	}




}




