package com.zzuli.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;
import com.zzuli.crm.domain.PageBean;

/**
 * À¼ÏªÈËservice½Ó¿Ú
 * @author °¢»Ô
 *
 */
public interface LinkManService {

	PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize);

	List<Customer> findAll();

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
