package com.zzuli.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.LinkManDao;
import com.zzuli.crm.domain.Customer;
import com.zzuli.crm.domain.LinkMan;
import com.zzuli.crm.domain.PageBean;
import com.zzuli.crm.service.LinkManService;
@Transactional
public class LinkManServiceImpl implements LinkManService {
	//ע��Dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize) {
		//��װpageBean
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//��װ��ǰҳ
		pageBean.setCurrPage(currPage);
		//��װÿҳ��ʾ�ļ�¼��
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��
		double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װLinkMan���󼯺�
		Integer begin = (currPage - 1)*pageSize;//��ҳ��ѯ���ӵڼ�ҳ��ʼ
		List<LinkMan> list = linkManDao.findPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//��ѯ���ÿͻ�
	public List<Customer> findAll() {
		
		return linkManDao.findAll();
	}

	@Override
	//������ϵ��
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
		
	}

	@Override
	//ͨ��id��ѯ��ϵ��
	public LinkMan findById(Long lkm_id) {
		
		return linkManDao.findById(lkm_id);
	}

	@Override
	//�޸���ϵ��
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
		
	}

	@Override
	//ɾ����ϵ��
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}
	
}
