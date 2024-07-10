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
import com.entity.Banner;
import com.service.BannerService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/banner" , produces = "text/plain;charset=utf-8")
public class BannerController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private BannerService bannerService;

	// 准备添加数据
	@RequestMapping("createBanner.action")
	public String createBanner() {
		return "admin/addbanner";
	}
	// 添加数据
	@RequestMapping("addBanner.action")
	public String addBanner(Banner banner) {
		banner.setAddtime(VeDate.getStringDateShort());
		this.bannerService.insertBanner(banner);
		return "redirect:/banner/createBanner.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddBanner.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddBanner(Banner banner) {
		banner.setAddtime(VeDate.getStringDateShort());
		int x = this.bannerService.insertBanner(banner);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteBanner.action")
	public String deleteBanner(String id) {
		this.bannerService.deleteBanner(id);
		return "redirect:/banner/getAllBanner.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteBanner.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteBanner(String id) {
		int x = this.bannerService.deleteBanner(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteBannerByIds.action")
	public String deleteBannerByIds() {
		String[] ids = this.getRequest().getParameterValues("bannerid");
		if (ids != null) {
			for (String bannerid : ids) {
				this.bannerService.deleteBanner(bannerid);
			}
		}
		return "redirect:/banner/getAllBanner.action";
	}

	// 更新数据
	@RequestMapping("updateBanner.action")
	public String updateBanner(Banner banner) {
		this.bannerService.updateBanner(banner);
		return "redirect:/banner/getAllBanner.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateBanner.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateBanner(Banner banner) {
		int x = this.bannerService.updateBanner(banner);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllBanner.action")
	public String getAllBanner(String number) {
		List<Banner> bannerList = this.bannerService.getAllBanner();
		PageHelper.getUserPage(bannerList, "banner", "getAllBanner", 10, number, this.getRequest());
		return "admin/listbanner";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Banner> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Banner> list = this.bannerService.getAllBanner();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryBannerByCond.action")
	public String queryBannerByCond(String cond, String name, String number) {
		Banner banner = new Banner();
		if(cond != null){
			if ("bannername".equals(cond)) {
				banner.setBannername(name);
			}
			if ("addtime".equals(cond)) {
				banner.setAddtime(name);
			}
			if ("memo".equals(cond)) {
				banner.setMemo(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.bannerService.getBannerByLike(banner), "banner", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/querybanner";
	}

	// 按主键查询数据
	@RequestMapping("getBannerById.action")
	public String getBannerById(String id) {
		Banner banner = this.bannerService.getBannerById(id);
		this.getRequest().setAttribute("banner", banner);
		return "admin/editbanner";
	}


}
