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
	//模型驱动
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	//注入service
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//设置当前页数和每页显示的记录数
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
	 * 分页查询
	 */
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		
		//设置条件
		if(linkMan.getLkm_name()!=null){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null&&!"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		
		//把分页查询到的相关信息封装到PageBean中
		PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria,currPage,pageSize);
		
		//把pageBean压栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * 添加页面
	 */
	public String saveUI(){
		//查询所用客户，用在添加页面中
		List<Customer> list = linkManService.findAll();
		//将list保存到值栈中
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * 保存联系人
	 */
	public String save(){
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/**
	 * 跳转到编辑页面
	 */
	public String edit(){
		//查询所有客户用于选择
		//通过id查询某个联系人，回显到编辑页面上
		//查询所有客户
		List<Customer> list = linkManService.findAll();
		//查询联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		
		//把list放入值栈中
		ActionContext.getContext().getValueStack().set("list", list);
		//把联系人放入值栈
		ActionContext.getContext().getValueStack().push(linkMan);
		
		return "editSuccess";
	}
	
	/**
	 * 修改联系人
	 */
	public String update(){
		//修改了联系人
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * 删除联系人
	 */
	public String delete(){
		//先查询再删除
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
	

}
