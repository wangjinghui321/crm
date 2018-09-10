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
	
	//ע��service
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	//�����������Ʋ�ѯ
	public String findByTypeCode() throws IOException{
		//System.out.println("ִ����basedict�еķ���");
		List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		//��listתΪJSON
		/*
		 * jsonlib fastjson
		 * JSONConfig:  תjson�����ö���
		 * JSONArray:	�������list����תΪjson
		 * JSONObject:	�������map����תΪ����
		 */
		
		//�Ѳ���Ҫ��json����ȥ����ʹ��jsonConfig����
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		//System.out.println(jsonArray.toString());
		
		//��json��ӡ��ҳ����
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}

}
