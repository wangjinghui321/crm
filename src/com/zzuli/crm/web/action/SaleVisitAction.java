package com.zzuli.crm.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.domain.SaleVisit;
import com.zzuli.crm.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit  saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	private Integer currPage=1;
	private Integer pageSize=3;
	
	
	public void setCurrPage(Integer currPage) {
		if(currPage==null){
			currPage=1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize==null){
			pageSize=3;
		}
		this.pageSize = pageSize;
	}
	
	//����������ѯ�Ľ�������
	private Date visit_end_time;
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	//ע��service
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	/**
	 * ��ҳ��ѯ
	 * @return
	 */
	public String findAll(){
		//�������߲�ѯ����
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		//���ò�ѯ����
		if(saleVisit.getVisit_time() != null){
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		
		//����ֵջ
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
	}
	
	/**
	 * ��ת������ҳ��ķ���
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * ����ݷü�¼
	 */
	public String save(){
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * ɾ���ݷü�¼
	 */
	public String delete(){
		//�Ȳ�ѯ��ɾ��
		 saleVisit= saleVisitService.findById(saleVisit.getVisit_id());
		 
		 saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	
	/**
	 * ��ת���޸�ҳ��
	 */
	public String edit(){
		 saleVisit= saleVisitService.findById(saleVisit.getVisit_id());
		return "editSuccess";
	}
	
	/**
	 * �޸İݷü�¼
	 */
	public String update(){
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}

}
