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
	
	//接收条件查询的结束日期
	private Date visit_end_time;
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	//注入service
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	/**
	 * 分页查询
	 * @return
	 */
	public String findAll(){
		//创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		//设置查询条件
		if(saleVisit.getVisit_time() != null){
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		
		//存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
	}
	
	/**
	 * 跳转到保存页面的方法
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * 保存拜访记录
	 */
	public String save(){
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * 删除拜访记录
	 */
	public String delete(){
		//先查询再删除
		 saleVisit= saleVisitService.findById(saleVisit.getVisit_id());
		 
		 saleVisitService.delete(saleVisit);
		return "deleteSuccess";
	}
	
	/**
	 * 跳转到修改页面
	 */
	public String edit(){
		 saleVisit= saleVisitService.findById(saleVisit.getVisit_id());
		return "editSuccess";
	}
	
	/**
	 * 修改拜访记录
	 */
	public String update(){
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}

}
