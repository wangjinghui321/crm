package com.zzuli.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.zzuli.crm.dao.CustomerDao;
import com.zzuli.crm.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/*@Override
	 *����
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
		
	}*/

	public CustomerDaoImpl() {
		super(Customer.class);
	}


	/*@Override
	//dao�д�����ͳ�Ƹ����ķ���
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
	//��ҳ��ѯ
	public List<Customer> findByPage(DetachedCriteria detachedCriteria,
			Integer begin, Integer pageSize) {
		// ���detachedCriteria�е�����
		detachedCriteria.setProjection(null);
		
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}*/

	/*@Override
	//ͨ��id��ѯ�ͻ�
	public Customer findById(Long cust_id) {
		
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}*/

	/*@Override
	 * ɾ��
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
		
	}

	@Override
	�޸�
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
		
	}*/
	
}
