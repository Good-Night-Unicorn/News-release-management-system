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
import com.entity.Asks;
import com.service.AsksService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/asks" , produces = "text/plain;charset=utf-8")
public class AsksController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private AsksService asksService;

	// 准备添加数据
	@RequestMapping("createAsks.action")
	public String createAsks() {
		return "admin/addasks";
	}
	// 添加数据
	@RequestMapping("addAsks.action")
	public String addAsks(Asks asks) {
		asks.setAddtime(VeDate.getStringDateShort());
		this.asksService.insertAsks(asks);
		return "redirect:/asks/createAsks.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddAsks.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddAsks(Asks asks) {
		asks.setAddtime(VeDate.getStringDateShort());
		int x = this.asksService.insertAsks(asks);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteAsks.action")
	public String deleteAsks(String id) {
		this.asksService.deleteAsks(id);
		return "redirect:/asks/getAllAsks.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteAsks.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteAsks(String id) {
		int x = this.asksService.deleteAsks(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteAsksByIds.action")
	public String deleteAsksByIds() {
		String[] ids = this.getRequest().getParameterValues("asksid");
		if (ids != null) {
			for (String asksid : ids) {
				this.asksService.deleteAsks(asksid);
			}
		}
		return "redirect:/asks/getAllAsks.action";
	}

	// 更新数据
	@RequestMapping("updateAsks.action")
	public String updateAsks(Asks asks) {
		this.asksService.updateAsks(asks);
		return "redirect:/asks/getAllAsks.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateAsks.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateAsks(Asks asks) {
		int x = this.asksService.updateAsks(asks);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllAsks.action")
	public String getAllAsks(String number) {
		List<Asks> asksList = this.asksService.getAllAsks();
		PageHelper.getUserPage(asksList, "asks", "getAllAsks", 10, number, this.getRequest());
		return "admin/listasks";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Asks> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Asks> list = this.asksService.getAllAsks();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryAsksByCond.action")
	public String queryAsksByCond(String cond, String name, String number) {
		Asks asks = new Asks();
		if(cond != null){
			if ("question".equals(cond)) {
				asks.setQuestion(name);
			}
			if ("addtime".equals(cond)) {
				asks.setAddtime(name);
			}
			if ("memo".equals(cond)) {
				asks.setMemo(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.asksService.getAsksByLike(asks), "asks", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryasks";
	}

	// 按主键查询数据
	@RequestMapping("getAsksById.action")
	public String getAsksById(String id) {
		Asks asks = this.asksService.getAsksById(id);
		this.getRequest().setAttribute("asks", asks);
		return "admin/editasks";
	}


}
