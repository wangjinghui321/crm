package com.zzuli.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zzuli.crm.dao.BaseDictDao;
import com.zzuli.crm.domain.BaseDict;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	//�����ֵ����ͱ����ѯ�ֵ�˳��
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) getHibernateTemplate().find("from BaseDict where dict_type_code=?", dict_type_code);
	}

}
