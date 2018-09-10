package com.zzuli.crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.domain.User;

/**
 * 用户service接口
 * @author 阿辉
 *
 */
@Transactional
public interface UserService {

	void regist(User user);


	User login(User user);


	List<User> findAll();


}
