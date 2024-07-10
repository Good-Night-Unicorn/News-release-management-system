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
import com.entity.Fav;
import com.service.FavService;
import com.entity.Users;
import com.entity.Article;
import com.service.UsersService;
import com.service.ArticleService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/fav" , produces = "text/plain;charset=utf-8")
public class FavController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private FavService favService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ArticleService articleService;

	// 准备添加数据
	@RequestMapping("createFav.action")
	public String createFav() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Article> articleList = this.articleService.getAllArticle();
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/addfav";
	}
	// 添加数据
	@RequestMapping("addFav.action")
	public String addFav(Fav fav) {
		fav.setAddtime(VeDate.getStringDateShort());
		this.favService.insertFav(fav);
		return "redirect:/fav/createFav.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddFav.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddFav(Fav fav) {
		fav.setAddtime(VeDate.getStringDateShort());
		int x = this.favService.insertFav(fav);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteFav.action")
	public String deleteFav(String id) {
		this.favService.deleteFav(id);
		return "redirect:/index/myFav.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteFav.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteFav(String id) {
		int x = this.favService.deleteFav(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteFavByIds.action")
	public String deleteFavByIds() {
		String[] ids = this.getRequest().getParameterValues("favid");
		if (ids != null) {
			for (String favid : ids) {
				this.favService.deleteFav(favid);
			}
		}
		return "redirect:/fav/getAllFav.action";
	}

	// 更新数据
	@RequestMapping("updateFav.action")
	public String updateFav(Fav fav) {
		this.favService.updateFav(fav);
		return "redirect:/fav/getAllFav.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateFav.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateFav(Fav fav) {
		int x = this.favService.updateFav(fav);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllFav.action")
	public String getAllFav(String number) {
		List<Fav> favList = this.favService.getAllFav();
		PageHelper.getUserPage(favList, "fav", "getAllFav", 10, number, this.getRequest());
		return "admin/listfav";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Fav> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Fav> list = this.favService.getAllFav();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryFavByCond.action")
	public String queryFavByCond(String cond, String name, String number) {
		Fav fav = new Fav();
		if(cond != null){
			if ("usersid".equals(cond)) {
				fav.setUsersid(name);
			}
			if ("articleid".equals(cond)) {
				fav.setArticleid(name);
			}
			if ("addtime".equals(cond)) {
				fav.setAddtime(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.favService.getFavByLike(fav), "fav", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryfav";
	}

	// 按主键查询数据
	@RequestMapping("getFavById.action")
	public String getFavById(String id) {
		Fav fav = this.favService.getFavById(id);
		this.getRequest().setAttribute("fav", fav);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Article> articleList = this.articleService.getAllArticle();
		this.getRequest().setAttribute("articleList", articleList);
		return "admin/editfav";
	}


}
