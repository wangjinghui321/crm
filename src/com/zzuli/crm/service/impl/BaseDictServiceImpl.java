package com.zzuli.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.BaseDictDao;
import com.zzuli.crm.domain.BaseDict;
import com.zzuli.crm.service.BaseDictService;

@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	//×¢ÈëDao
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
