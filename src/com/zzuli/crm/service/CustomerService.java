package com.zzuli.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria,Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();
	
	
}
