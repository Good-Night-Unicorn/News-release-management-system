package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 提示问题表的实体类
public class Asks {
	private String asksid = "A"+VeDate.getStringId(); // 生成主键编号
	private String question; // 问题
	private String addtime; // 创建日期
	private String memo; // 备注
	public String getAsksid() {
		return this.asksid;
	}

	public void setAsksid(String asksid) {
		this.asksid = asksid;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("asksid", this.asksid); // 主键编号
		jsonString.put("question", this.question); // 问题
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		return jsonString.toString();
	}




}




