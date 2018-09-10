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
		//先把上边设置的查询条件清空
		detachedCriteria.setProjection(null);
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	@Override
	//查询所用客户
	public List<Customer> findAll() {
		
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

	@Override
	//保存联系人
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		
	}

	@Override
	//通过id查询联系人
	public LinkMan findById(Long lkm_id) {
		
		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
	}

	@Override
	//修改联系人
	public void update(LinkMan linkMan) {
	this.getHibernateTemplate().update(linkMan);
		
	}

	@Override
	//删除联系人
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
		
	}

}
