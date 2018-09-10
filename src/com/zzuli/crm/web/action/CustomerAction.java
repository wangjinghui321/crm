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

	// ע��service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// set�����������õ�ǰҳ��
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// set��������ÿҳ��ʾ�ļ�¼��
	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/*
	 * �ļ��ϴ�����������
	 * uploadҪ�ͱ��е�name����ֵһ��
	 */
	private String uploadFileName;//�ļ�����
	private File upload; //�ļ�����
	private String uploadContentType;//�ļ�������
	

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
	 * ��ת�����ҳ��
	 */
	public String saveUI() {
		return "saveUI";
	}

	/*
	 * ����ͻ���Ϣ
	 */
	public String save() throws IOException {
		//�ϴ�ͼƬ
		if(upload!=null){
			//�ļ��ϴ�
			//�����ļ��ϴ�·��
			String path="D:/javaee/upload";
			//����ļ���
			String uuidFileName=UploadUtils.getUuidFileNames(uploadFileName);
			
			//Ŀ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			//����Ŀ¼
			String url = path+realPath;
			File file=new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			//�ļ��ϴ�
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//����cust_image������ֵ
			customer.setCust_image(url+"/"+uuidFileName);
			
			
		}

		customerService.save(customer);
		return "saveSuccess";
	}

	/*
	 * ��ҳ��ѯ���пͻ���Ϣ
	 */
	public String findAll() {
		// ���ղ�������ҳ����
		// ���ʹ��DetachedCriteria����������ѯ--����ҳ��
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//��web����������
		if(customer.getCust_name()!=null){
			//����������
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictSource()!=null){
			if(customer.getBaseDictSource().getDict_id()!=null && !"".equals(customer.getBaseDictSource().getDict_id())){
				//������Դ��
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
			
		}
		if(customer.getBaseDictLevel()!=null){
			if(customer.getBaseDictLevel().getDict_id()!=null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				//���뼶����
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
			
		}
		if(customer.getBaseDictIndustry()!=null){
			if(customer.getBaseDictIndustry().getDict_id()!=null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				//������ҵ��
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
			
		}
		
		// ����ҵ����ѯ:
		PageBean<Customer> pageBean = customerService.findByPage(
				detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";

	}
	/*
	 * ɾ���ͻ��ķ���
	 */
	public String delete(){
		//�Ȳ�ѯ��ɾ��
		customer = customerService.findById(customer.getCust_id());
		//ɾ��ͼƬ
		if(customer.getCust_image()!=null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		//ɾ���ͻ�
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/*
	 * �༭�ͻ�
	 * ���Բ���
	 */
	public String edit(){
		//�Ȳ�ѯ���޸�
		customer = customerService.findById(customer.getCust_id());
		//��customer���ݵ�ҳ��
		//���ַ�����1���ֶ�ѹջ  2����Ϊ��ģ�������Ķ���Ĭ����ջ��
		//��һ�֣��������ݣ�<s:property value="cust_name"/> or <s:textfile name="cust_name">
		//�ڶ��֣��������ݣ�<s:property value="model.cust_name"/>
		//��һ�֣�
		//ActionContext.getContext().getValueStack().push(customer);
		
		return "editSuccess";
	}
	
	/*
	 * �޸Ŀͻ��ķ���
	 */
	public String update() throws IOException{
		//�ļ����Ƿ���ѡ��ѡ�˾�ɾ��ԭ�ļ����ϴ����ļ���ûѡ��������
		if(upload !=null){
			//ɾ���ļ�
			String cust_image = customer.getCust_image();
			if(cust_image != null || !"".equals(cust_image)){
				File file = new File(cust_image);
				if(file.exists()){
					file.delete();
				}
			}
			//�ϴ����ļ�
			String path="D:/javaee/upload";
			//����ļ���
			String uuidFileName=UploadUtils.getUuidFileNames(uploadFileName);
			
			//Ŀ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			//����Ŀ¼
			String url = path+realPath;
			File file=new File(url);
			if(!file.exists()){
				file.mkdirs();
			}
			//�ļ��ϴ�
			File dictFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			//����cust_image������ֵ
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	
	/**
	 * ��ѯ���ÿͻ�
	 * @throws IOException 
	 */
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		//�Ѳ���Ҫ��json����ȥ����ʹ��jsonConfig����
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictIndustry","baseDictLevel"});
				JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
				//System.out.println(jsonArray.toString());
				
				//��json��ӡ��ҳ����
				ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
