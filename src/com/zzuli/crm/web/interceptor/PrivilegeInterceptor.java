package com.zzuli.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.zzuli.crm.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//�ж�session���Ƿ��е�¼����Ϣ
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){
			//���������Ϣ��ҳ����ת����¼ҳ��
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("�㻹û�е�¼�����¼");
			return actionSupport.LOGIN;
		}else{
			//����ִ����һ��������
			return invocation.invoke();
		}
		
	}

}
