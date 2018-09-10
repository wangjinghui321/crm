package com.zzuli.crm.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zzuli.crm.dao.BaseDao;
/**
 * 通用的Dao实现类
 * @author 阿辉
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class<?> clazz;
	
	public BaseDaoImpl(Class<?> clazz) {
		this.clazz = clazz;
	}
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
		
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	@Override
	public List<T> findAll() {
		
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
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
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin,Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

}
