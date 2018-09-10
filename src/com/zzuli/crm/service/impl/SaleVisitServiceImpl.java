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
	//ע��Dao
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize) {
		//�õ�pageBean����
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//��װ��ǰҳ��
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ�ļ�¼��
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װlist����
		Integer begin = (currPage - 1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//����ݷü�¼
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
		
	}

	@Override
	//����id��ѯ
	public SaleVisit findById(String visit_id) {
		
		return saleVisitDao.findById(visit_id);
	}

	@Override
	//ɾ���ݷü�¼
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	//�޸İݷü�¼
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
}
