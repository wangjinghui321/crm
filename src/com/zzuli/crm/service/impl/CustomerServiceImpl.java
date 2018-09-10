package com.zzuli.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.CustomerDao;
import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	//注入dao
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
		
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装当前显示的记录数
		pageBean.setPageSize(pageSize);
		//封装总的记录数
		//调用Dao
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据集合
		Integer begin = (currPage - 1)*pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//通过id查询客户
	public Customer findById(Long cust_id) {
		
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
		
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}

	@Override
	//查询所用客户
	public List<Customer> findAll() {
		
		return customerDao.findAll();
	}
	
}
