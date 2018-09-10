package com.zzuli.crm.dao;

import java.util.List;

import com.zzuli.crm.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
