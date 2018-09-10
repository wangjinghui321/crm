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
	//注入Dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria,
			Integer currPage, Integer pageSize) {
		//封装pageBean
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		//封装总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装LinkMan对象集合
		Integer begin = (currPage - 1)*pageSize;//分页查询，从第几页开始
		List<LinkMan> list = linkManDao.findPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//查询所用客户
	public List<Customer> findAll() {
		
		return linkManDao.findAll();
	}

	@Override
	//保存联系人
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
		
	}

	@Override
	//通过id查询联系人
	public LinkMan findById(Long lkm_id) {
		
		return linkManDao.findById(lkm_id);
	}

	@Override
	//修改联系人
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
		
	}

	@Override
	//删除联系人
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
		
	}
	
}
