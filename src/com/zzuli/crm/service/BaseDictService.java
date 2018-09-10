package com.zzuli.crm.service;

import java.util.List;

import com.zzuli.crm.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
