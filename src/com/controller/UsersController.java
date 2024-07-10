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
import com.entity.Users;
import com.service.UsersService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/users" , produces = "text/plain;charset=utf-8")
public class UsersController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private UsersService usersService;

	// 准备添加数据
	@RequestMapping("createUsers.action")
	public String createUsers() {
		return "admin/addusers";
	}
	// 添加数据
	@RequestMapping("addUsers.action")
	public String addUsers(Users users) {
		users.setRegdate(VeDate.getStringDateShort());
		this.usersService.insertUsers(users);
		return "redirect:/users/createUsers.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddUsers.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddUsers(Users users) {
		users.setRegdate(VeDate.getStringDateShort());
		int x = this.usersService.insertUsers(users);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteUsers.action")
	public String deleteUsers(String id) {
		this.usersService.deleteUsers(id);
		return "redirect:/users/getAllUsers.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteUsers.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteUsers(String id) {
		int x = this.usersService.deleteUsers(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteUsersByIds.action")
	public String deleteUsersByIds() {
		String[] ids = this.getRequest().getParameterValues("usersid");
		if (ids != null) {
			for (String usersid : ids) {
				this.usersService.deleteUsers(usersid);
			}
		}
		return "redirect:/users/getAllUsers.action";
	}

	// 更新数据
	@RequestMapping("updateUsers.action")
	public String updateUsers(Users users) {
		this.usersService.updateUsers(users);
		return "redirect:/users/getAllUsers.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateUsers.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateUsers(Users users) {
		int x = this.usersService.updateUsers(users);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllUsers.action")
	public String getAllUsers(String number) {
		List<Users> usersList = this.usersService.getAllUsers();
		PageHelper.getUserPage(usersList, "users", "getAllUsers", 10, number, this.getRequest());
		return "admin/listusers";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Users> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Users> list = this.usersService.getAllUsers();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryUsersByCond.action")
	public String queryUsersByCond(String cond, String name, String number) {
		Users users = new Users();
		if(cond != null){
			if ("username".equals(cond)) {
				users.setUsername(name);
			}
			if ("password".equals(cond)) {
				users.setPassword(name);
			}
			if ("realname".equals(cond)) {
				users.setRealname(name);
			}
			if ("sex".equals(cond)) {
				users.setSex(name);
			}
			if ("birthday".equals(cond)) {
				users.setBirthday(name);
			}
			if ("contact".equals(cond)) {
				users.setContact(name);
			}
			if ("regdate".equals(cond)) {
				users.setRegdate(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.usersService.getUsersByLike(users), "users", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryusers";
	}

	// 按主键查询数据
	@RequestMapping("getUsersById.action")
	public String getUsersById(String id) {
		Users users = this.usersService.getUsersById(id);
		this.getRequest().setAttribute("users", users);
		return "admin/editusers";
	}


}
