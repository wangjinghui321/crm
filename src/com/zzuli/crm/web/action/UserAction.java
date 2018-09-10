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
	// ģ��������Ҫ�Ķ���
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// ע��UserService
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ע�Ṧ��
	public String regist() {
		userService.regist(user);
		return LOGIN;
	}

	// ��¼����
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			// ��¼ʧ��
			// ��Ӵ�����Ϣ
			this.addActionError("�û������������");
			return LOGIN;
		} else {
			// ��¼�ɹ�
			// ServletActionContext.getRequest().getSession().setAttribute("existUser",
			// existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}

	/**
	 * ��ѯ�����û�
	 * 
	 * @throws IOException
	 */
	public String findAllUser() throws IOException {
		List<User> list = userService.findAll();
		// �Ѳ���Ҫ��json����ȥ����ʹ��jsonConfig����
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "user_password" });
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		// System.out.println(jsonArray.toString());

		// ��json��ӡ��ҳ����
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter()
				.println(jsonArray.toString());
		return NONE;
	}

}
