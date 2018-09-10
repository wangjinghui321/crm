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
	//ע��dao
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
		//��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		//��װ��ǰ��ʾ�ļ�¼��
		pageBean.setPageSize(pageSize);
		//��װ�ܵļ�¼��
		//����Dao
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ�����ݼ���
		Integer begin = (currPage - 1)*pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	//ͨ��id��ѯ�ͻ�
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
	//��ѯ���ÿͻ�
	public List<Customer> findAll() {
		
		return customerDao.findAll();
	}
	
}
