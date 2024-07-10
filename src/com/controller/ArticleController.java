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
import com.entity.Article;
import com.service.ArticleService;
import com.entity.Banner;
import com.service.BannerService;
import com.util.PageHelper;
import com.util.VeDate;
//定义为控制器
@Controller
// 设置路径
@RequestMapping(value = "/article" , produces = "text/plain;charset=utf-8")
public class ArticleController extends BaseController {
	// @Autowired的作用是自动注入依赖的ServiceBean
	@Autowired
	private ArticleService articleService;
	@Autowired
	private BannerService bannerService;

	// 准备添加数据
	@RequestMapping("createArticle.action")
	public String createArticle() {
		List<Banner> bannerList = this.bannerService.getAllBanner();
		this.getRequest().setAttribute("bannerList", bannerList);
		return "admin/addarticle";
	}
	// 添加数据
	@RequestMapping("addArticle.action")
	public String addArticle(Article article) {
		article.setAddtime(VeDate.getStringDateShort());
		article.setHits("0");
		this.articleService.insertArticle(article);
		return "redirect:/article/createArticle.action";
	}

	// AJAX添加数据
	@RequestMapping("xaddArticle.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xaddArticle(Article article) {
		article.setAddtime(VeDate.getStringDateShort());
		article.setHits("0");
		int x = this.articleService.insertArticle(article);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}
	// 通过主键删除数据
	@RequestMapping("deleteArticle.action")
	public String deleteArticle(String id) {
		this.articleService.deleteArticle(id);
		return "redirect:/article/getAllArticle.action";
	}

	// AJAX通过主键删除数据
	@RequestMapping("xdeleteArticle.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xdeleteArticle(String id) {
		int x = this.articleService.deleteArticle(id);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 批量删除数据
	@RequestMapping("deleteArticleByIds.action")
	public String deleteArticleByIds() {
		String[] ids = this.getRequest().getParameterValues("articleid");
		if (ids != null) {
			for (String articleid : ids) {
				this.articleService.deleteArticle(articleid);
			}
		}
		return "redirect:/article/getAllArticle.action";
	}

	// 更新数据
	@RequestMapping("updateArticle.action")
	public String updateArticle(Article article) {
		this.articleService.updateArticle(article);
		return "redirect:/article/getAllArticle.action";
	}

	// AJAX更新数据
	@RequestMapping("xupdateArticle.action")
	@ResponseBody //将java对象转为json格式的数据
	public String xupdateArticle(Article article) {
		int x = this.articleService.updateArticle(article);
		JSONObject json = new JSONObject();
		json.put("result", x);
		//System.out.println(json.toString());
		return json.toString();
	}

	// 显示全部数据
	@RequestMapping("getAllArticle.action")
	public String getAllArticle(String number) {
		List<Article> articleList = this.articleService.getAllArticle();
		PageHelper.getUserPage(articleList, "article", "getAllArticle", 10, number, this.getRequest());
		return "admin/listarticle";
	}

	// AJAX显示全部数据
	@RequestMapping(value = "getDataList.action", produces = "application/json; charset=utf-8")
	@ResponseBody //将java对象转为json格式的数据
	public Map<String, Object> getDataList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Article> list = this.articleService.getAllArticle();
		// 返回的map中定义Layui的数据格式
		map.put("count", pager.getTotal());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryArticleByCond.action")
	public String queryArticleByCond(String cond, String name, String number) {
		Article article = new Article();
		if(cond != null){
			if ("title".equals(cond)) {
				article.setTitle(name);
			}
			if ("bannerid".equals(cond)) {
				article.setBannerid(name);
			}
			if ("image".equals(cond)) {
				article.setImage(name);
			}
			if ("istop".equals(cond)) {
				article.setIstop(name);
			}
			if ("isflv".equals(cond)) {
				article.setIsflv(name);
			}
			if ("contents".equals(cond)) {
				article.setContents(name);
			}
			if ("addtime".equals(cond)) {
				article.setAddtime(name);
			}
			if ("hits".equals(cond)) {
				article.setHits(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.articleService.getArticleByLike(article), "article", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryarticle";
	}

	// 按主键查询数据
	@RequestMapping("getArticleById.action")
	public String getArticleById(String id) {
		Article article = this.articleService.getArticleById(id);
		this.getRequest().setAttribute("article", article);
		List<Banner> bannerList = this.bannerService.getAllBanner();
		this.getRequest().setAttribute("bannerList", bannerList);
		return "admin/editarticle";
	}


}
