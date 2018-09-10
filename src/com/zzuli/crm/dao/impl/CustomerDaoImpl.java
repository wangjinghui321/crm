package com.zzuli.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.zzuli.crm.dao.CustomerDao;
import com.zzuli.crm.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/*@Override
	 *保存
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
		
	}*/

	public CustomerDaoImpl() {
		super(Customer.class);
	}


	/*@Override
	//dao中带条件统计个数的方法
	public Integer findCount(DetachedCriteria detachedCriteria) {
		//select count(*) from ..... where xxxx;
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}*/

	/*@Override
	//分页查询
	public List<Customer> findByPage(DetachedCriteria detachedCriteria,
			Integer begin, Integer pageSize) {
		// 清空detachedCriteria中的条件
		detachedCriteria.setProjection(null);
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}*/

	/*@Override
	//通过id查询客户
	public Customer findById(Long cust_id) {
		
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}*/

	/*@Override
	 * 删除
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
		
	}

	@Override
	修改
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
		
	}*/
	
}
