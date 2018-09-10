package com.zzuli.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

	SaleVisit findById(String visit_id);

	void delete(SaleVisit saleVisit);

	void update(SaleVisit saleVisit);

}
