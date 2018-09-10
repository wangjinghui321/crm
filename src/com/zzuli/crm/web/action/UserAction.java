package com.zzuli.crm.web.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzuli.crm.domain.User;
import com.zzuli.crm.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	// 模型驱动需要的对象
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 注入UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 注册功能
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}

	// 登录功能
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// 登录失败
			// 添加错误信息
			this.addActionError("用户名或密码错误");
			return LOGIN;
		} else {
			// 登录成功
			// ServletActionContext.getRequest().getSession().setAttribute("existUser",
			// existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}

	/**
	 * 查询所用用户
	 * 
	 * @throws IOException
	 */
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		// 把不想要的json数据去掉，使用jsonConfig对象
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "user_password" });
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		// System.out.println(jsonArray.toString());

		// 将json打印到页面上
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter()
				.println(jsonArray.toString());
		return NONE;
	}

}
