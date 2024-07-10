package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.entity.Article;
import com.entity.Asks;
import com.entity.Banner;
import com.entity.Complains;
import com.entity.Fav;
import com.entity.Topic;
import com.entity.Users;
import com.service.ArticleService;
import com.service.AsksService;
import com.service.BannerService;
import com.service.ComplainsService;
import com.service.FavService;
import com.service.TopicService;
import com.service.UsersService;
import com.util.PageHelper;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private FavService favService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ComplainsService complainsService;
	@Autowired
	private AsksService asksService;

	// TODO Auto-generated method stub
	// 公共方法 提供公共查询数据
	private void front() {
		this.getRequest().setAttribute("title", "新闻发布管理系统");
		List<Banner> bannerList = this.bannerService.getAllBanner();
		this.getRequest().setAttribute("bannerList", bannerList);
	}

	// 首页显示
	@RequestMapping("index.action")
	public String index() {
		this.front();
		List<Banner> bannerList = this.bannerService.getAllBanner();
		List<Banner> frontList = new ArrayList<Banner>();
		for (Banner banner : bannerList) {
			List<Article> articleList = this.articleService.getArticleByBanner(banner.getBannerid());
			banner.setArticleList(articleList);
			frontList.add(banner);
		}
		this.getRequest().setAttribute("frontList", frontList);
		List<Article> topList = this.articleService.getTopArticle();
		List<Article> favList = this.articleService.getFlvArticle();
		this.getRequest().setAttribute("topList", topList);
		this.getRequest().setAttribute("favList", favList);
		return "users/index";
	}

	// 新闻公告
	@RequestMapping("article.action")
	public String article(String id, String number) {
		this.front();
		Article article = new Article();
		article.setBannerid(id);
		List<Article> articleList = this.articleService.getArticleByCond(article);
		PageHelper.getIndexPage(articleList, "article", "article", id, 10, number, this.getRequest());
		Banner banner = this.bannerService.getBannerById(id);
		this.getRequest().setAttribute("banner", banner);
		return "users/article";
	}

	// 阅读公告
	@RequestMapping("read.action")
	public String read(String id) {
		this.front();
		Article article = this.articleService.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleService.updateArticle(article);
		this.getRequest().setAttribute("article", article);
		Topic topic = new Topic();
		topic.setArticleid(id);
		List<Topic> topicList = this.topicService.getTopicByCond(topic);
		this.getRequest().setAttribute("topicList", topicList);
		this.getRequest().setAttribute("tnum", topicList.size());
		return "users/read";
	}

	// 准备登录
	@RequestMapping("preLogin.action")
	public String prelogin() {
		this.front();
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.action")
	public String login() {
		this.front();
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			this.getSession().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users users = usersList.get(0);
			if (password.equals(users.getPassword())) {
				this.getSession().setAttribute("userid", users.getUsersid());
				this.getSession().setAttribute("username", users.getUsername());
				this.getSession().setAttribute("users", users);
				return "redirect:/index/index.action";
			} else {
				this.getSession().setAttribute("message", "密码错误");
				return "redirect:/index/preLogin.action";
			}
		}
	}

	// 准备注册
	@RequestMapping("preReg.action")
	public String preReg() {
		this.front();
		List<Asks> asksList = this.asksService.getAllAsks();
		this.getRequest().setAttribute("asksList", asksList);
		return "users/register";
	}

	// 用户注册
	@RequestMapping("register.action")
	public String register(Users users) {
		this.front();
		Users u = new Users();
		u.setUsername(users.getUsername());
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			users.setRegdate(VeDate.getStringDateShort());
			this.usersService.insertUsers(users);
		} else {
			this.getSession().setAttribute("message", "用户名已存在");
			return "redirect:/index/preReg.action";
		}

		return "redirect:/index/preLogin.action";
	}

	// 退出登录
	@RequestMapping("exit.action")
	public String exit() {
		this.front();
		this.getSession().removeAttribute("userid");
		this.getSession().removeAttribute("username");
		this.getSession().removeAttribute("users");
		return "redirect:/index/index.action";
	}

	// 准备修改密码
	@RequestMapping("prePwd.action")
	public String prePwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		String password = this.getRequest().getParameter("password");
		String repassword = this.getRequest().getParameter("repassword");
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			this.usersService.updateUsers(users);
		} else {
			this.getSession().setAttribute("message", "旧密码错误");
			return "redirect:/index/prePwd.action";
		}
		this.getSession().setAttribute("message", "修改成功");
		return "redirect:/index/prePwd.action";
	}

	@RequestMapping("forget.action")
	public String forget() {
		this.front();
		return "users/forget";
	}

	@RequestMapping("step1.action")
	public String step1() {
		this.front();
		String username = this.getRequest().getParameter("username");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			this.getSession().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users x = usersList.get(0);
			String questions = x.getAsks().getQuestion();
			this.getRequest().setAttribute("questions", questions);
			this.getRequest().setAttribute("username", username);
			this.getRequest().setAttribute("userid", x.getUsersid());
		}
		return "users/step1";
	}

	@RequestMapping("step2.action")
	public String step2() {
		this.front();
		String userid = this.getRequest().getParameter("userid");
		String answer = this.getRequest().getParameter("answer");
		Users u = this.usersService.getUsersById(userid);
		if (u.getAnswer().equals(answer)) {
			this.getRequest().setAttribute("userid", userid);
			this.getRequest().setAttribute("username", u.getUsername());
		} else {
			this.getSession().setAttribute("message", "答案错误");
			return "redirect:/index/preLogin.action";
		}
		return "users/step2";
	}

	@RequestMapping("updatePwd.action")
	public String updatePwd() {
		this.front();
		String userid = this.getRequest().getParameter("userid");
		String password = this.getRequest().getParameter("repassword");
		Users u = this.usersService.getUsersById(userid);
		u.setPassword(password);
		this.usersService.updateUsers(u);
		this.getSession().setAttribute("message", "密码已重置");
		return "redirect:/index/preLogin.action";
	}

	@RequestMapping("usercenter.action")
	public String usercenter() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/usercenter";
	}

	@RequestMapping("userinfo.action")
	public String userinfo() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		this.getSession().setAttribute("users", this.usersService.getUsersById(userid));
		return "users/userinfo";
	}

	@RequestMapping("personal.action")
	public String personal(Users users) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.usersService.updateUsers(users);
		this.getSession().setAttribute("message", "修改成功");
		return "redirect:/index/userinfo.action";
	}

	@RequestMapping("preComplains.action")
	public String preComplains() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/addComplains";
	}

	@RequestMapping("addComplains.action")
	public String addComplains(Complains complains) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		complains.setAddtime(VeDate.getStringDateShort());
		complains.setStatus("未回复");
		complains.setUsersid(userid);
		this.complainsService.insertComplains(complains);
		return "redirect:/index/preComplains.action";
	}

	@RequestMapping("myComplains.action")
	public String myComplains(String number) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Complains complains = new Complains();
		complains.setUsersid(userid);
		List<Complains> complainsList = this.complainsService.getComplainsByCond(complains);
		PageHelper.getIndexPage(complainsList, "complains", "myComplains", null, 10, number, this.getRequest());
		return "users/myComplains";
	}

	@RequestMapping("addTopic.action")
	public String addTopic(Topic topic) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		topic.setAddtime(VeDate.getStringDateShort());
		topic.setUsersid(userid);
		this.topicService.insertTopic(topic);
		return "redirect:/index/read.action?id=" + topic.getArticleid();
	}

	@RequestMapping(value = "addfav.action", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String addfav(String id) {
		this.front();
		JSONObject json = new JSONObject();
		if (this.getSession().getAttribute("userid") == null) {
			json.put("status", "login");
			json.put("message", "请登录");
			System.out.println(json.toString());
			return json.toString();
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setArticleid(id);
		fav.setUsersid(userid);
		List<Fav> favList = this.favService.getFavByCond(fav);
		if (favList.size() == 0) {
			fav.setAddtime(VeDate.getStringDateShort());
			this.favService.insertFav(fav);
			json.put("status", "success");
			json.put("message", "收藏成功");
		} else {
			json.put("status", "fail");
			json.put("message", "收藏失败");
		}
		System.out.println(json.toString());
		return json.toString();
	}

	@RequestMapping("myFav.action")
	public String myFav(String number) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Fav fav = new Fav();
		fav.setUsersid(userid);
		List<Fav> favList = this.favService.getFavByCond(fav);
		PageHelper.getIndexPage(favList, "fav", "myFav", null, 10, number, this.getRequest());
		return "users/myFav";
	}

	@RequestMapping("deleteFav.action")
	public String deleteFav(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.favService.deleteFav(id);
		return "redirect:/index/myFav.action";
	}

	// TODO Auto-generated method stub
}
