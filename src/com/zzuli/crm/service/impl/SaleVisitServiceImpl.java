package com.zzuli.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.SaleVisitDao;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.domain.SaleVisit;
import com.zzuli.crm.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	//注入Dao
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize) {
		//得到pageBean对象
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		//封装总记录数
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装list集合
		Integer begin = (currPage - 1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//保存拜访记录
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
		
	}

	@Override
	//根据id查询
	public SaleVisit findById(String visit_id) {
		
		return saleVisitDao.findById(visit_id);
	}

	@Override
	//删除拜访记录
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	//修改拜访记录
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
}
