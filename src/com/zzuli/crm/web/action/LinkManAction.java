package com.zzuli.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	//ģ������
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	//ע��service
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//���õ�ǰҳ����ÿҳ��ʾ�ļ�¼��
	private Integer currPage = 1;
	private Integer pageSize = 3;
	public void setCurrPage(Integer currPage) {
		if(currPage==null){
			currPage = 1;
		}
		this.currPage = currPage;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize==null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * ��ҳ��ѯ
	 */
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		
		//��������
		if(linkMan.getLkm_name()!=null){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null&&!"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		
		//�ѷ�ҳ��ѯ���������Ϣ��װ��PageBean��
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currPage,pageSize);
		
		//��pageBeanѹջ
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ���ҳ��
	 */
	public String saveUI(){
		//��ѯ���ÿͻ����������ҳ����
		List<Customer> list = linkManService.findAll();
		//��list���浽ֵջ��
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * ������ϵ��
	 */
	public String save(){
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/**
	 * ��ת���༭ҳ��
	 */
	public String edit(){
		//��ѯ���пͻ�����ѡ��
		//ͨ��id��ѯĳ����ϵ�ˣ����Ե��༭ҳ����
		//��ѯ���пͻ�
		List<Customer> list = linkManService.findAll();
		//��ѯ��ϵ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		
		//��list����ֵջ��
		ActionContext.getContext().getValueStack().set("list", list);
		//����ϵ�˷���ֵջ
		ActionContext.getContext().getValueStack().push(linkMan);
		
		return "editSuccess";
	}
	
	/**
	 * �޸���ϵ��
	 */
	public String update(){
		//�޸�����ϵ��
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * ɾ����ϵ��
	 */
	public String delete(){
		//�Ȳ�ѯ��ɾ��
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	

}
