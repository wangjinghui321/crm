package com.zzuli.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzuli.crm.domain.BaseDict;
import com.zzuli.crm.service.BaseDictService;

public class BaseDiceAction extends ActionSupport implements ModelDriven<BaseDict> {
	private BaseDict baseDict = new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	//注入service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	//根据类型名称查询
	public String findByTypeCode() throws IOException{
		//System.out.println("执行了basedict中的方法");
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		//把list转为JSON
		/*
		 * jsonlib fastjson
		 * JSONConfig:  转json的配置对象
		 * JSONArray:	将数组和list集合转为json
		 * JSONObject:	将对象和map集合转为接送
		 */
		
		//把不想要的json数据去掉，使用jsonConfig对象
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		//System.out.println(jsonArray.toString());
		
		//将json打印到页面上
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}

}
