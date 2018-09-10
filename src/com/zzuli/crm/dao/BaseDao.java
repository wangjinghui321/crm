package com.zzuli.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * ͨ�õ�Dao
 * @author ����
 *
 */
public interface BaseDao<T> {
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Serializable id);
	
	//��ѯ����
	public List<T> findAll();
	
	//ͳ�Ƹ���
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	//��ҳ��ѯ
	public List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}
