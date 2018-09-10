package com.zzuli.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;

/**
 * ��ϵ��dao�ӿ�
 * @author ����
 *
 */
public interface LinkManDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findPage(DetachedCriteria detachedCriteria, Integer begin,
			Integer pageSize);

	List<Customer> findAll();

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
