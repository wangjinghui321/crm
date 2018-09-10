package com.zzuli.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;

/**
 * 联系人dao接口
 * @author 阿辉
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
