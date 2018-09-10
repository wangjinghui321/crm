package com.zzuli.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zzuli.crm.dao.LinkManDao;
import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<LinkMan> findPage(DetachedCriteria detachedCriteria,
			Integer begin, Integer pageSize) {
		//�Ȱ��ϱ����õĲ�ѯ�������
		detachedCriteria.setProjection(null);
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	@Override
	//��ѯ���ÿͻ�
	public List<Customer> findAll() {
		
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	//������ϵ��
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		
	}

	@Override
	//ͨ��id��ѯ��ϵ��
	public LinkMan findById(Long lkm_id) {
		
		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
	}

	@Override
	//�޸���ϵ��
	public void update(LinkMan linkMan) {
	this.getHibernateTemplate().update(linkMan);
		
	}

	@Override
	//ɾ����ϵ��
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
		
	}

}
