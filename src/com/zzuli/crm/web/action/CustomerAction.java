package com.zzuli.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.service.CustomerService;
import com.zzuli.crm.utils.UploadUtils;

public class CustomerAction extends ActionSupport implements
		ModelDriven<Customer> {
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	// 注入service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// set方法接收设置当前页数
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// set方法接收每页显示的记录数
	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/*
	 * 文件上传的三个属性
	 * upload要和表单中的name属性值一致
	 */
	private String uploadFileName;//文件名称
	private File upload; //文件本身
	private String uploadContentType;//文件的类型
	

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/*
	 * 跳转到添加页面
	 */
	public String saveUI() {
		return "saveUI";
	}

	/*
	 * 保存客户信息
	 */
	public String save() throws IOException {
		//上传图片
		if(upload!=null){
			//文件上传
			//设置文件上传路径
			String path="D:/javaee/upload";
			//随机文件名
			String uuidFileName=UploadUtils.getUuidFileNames(uploadFileName);
			
			//目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file=new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//设置cust_image的属性值
			customer.setCust_image(url+"/"+uuidFileName);
			
			
		}

		customerService.save(customer);
		return "saveSuccess";
	}

	/*
	 * 分页查询所有客户信息
	 */
	public String findAll() {
		// 接收参数：分页参数
		// 最好使用DetachedCriteria对象（条件查询--带分页）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//在web层设置条件
		if(customer.getCust_name()!=null){
			//输入名称了
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictSource()!=null){
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())){
				//输入来源了
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
			
		}
		if(customer.getBaseDictLevel()!=null){
			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				//输入级别了
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
			
		}
		if(customer.getBaseDictIndustry()!=null){
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				//输入行业了
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
			
		}
		
		// 调用业务层查询:
		PageBean<Customer> pageBean = customerService.findByPage(
				detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";

	}
	/*
	 * 删除客户的方法
	 */
	public String delete(){
		//先查询再删除
		customer = customerService.findById(customer.getCust_id());
		//删除图片
		if(customer.getCust_image()!=null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		//删除客户
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/*
	 * 编辑客户
	 * 回显操作
	 */
	public String edit(){
		//先查询再修改
		customer = customerService.findById(customer.getCust_id());
		//将customer传递到页面
		//两种方法：1、手动压栈  2、因为是模型驱动的对象，默认在栈顶
		//第一种：回显数据：<s:property value="cust_name"/> or <s:textfile name="cust_name">
		//第二种：回显数据：<s:property value="model.cust_name"/>
		//第一种：
		//ActionContext.getContext().getValueStack().push(customer);
		
		return "editSuccess";
	}
	
	/*
	 * 修改客户的方法
	 */
	public String update() throws IOException{
		//文件项是否已选：选了就删除原文件，上传新文件，没选不做操作
		if(upload !=null){
			//删除文件
			String cust_image = customer.getCust_image();
			if(cust_image != null || !"".equals(cust_image)){
				File file = new File(cust_image);
				if(file.exists()){
					file.delete();
				}
			}
			//上传新文件
			String path="D:/javaee/upload";
			//随机文件名
			String uuidFileName=UploadUtils.getUuidFileNames(uploadFileName);
			
			//目录分离
			String realPath = UploadUtils.getPath(uuidFileName);
			//创建目录
			String url = path+realPath;
			File file=new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			//文件上传
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//设置cust_image的属性值
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	
	/**
	 * 查询所用客户
	 * @throws IOException 
	 */
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		//把不想要的json数据去掉，使用jsonConfig对象
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictIndustry","baseDictLevel"});
				JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
				//System.out.println(jsonArray.toString());
				
				//将json打印到页面上
				ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
