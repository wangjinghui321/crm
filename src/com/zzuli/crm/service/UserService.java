package com.zzuli.crm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.domain.User;

/**
 * �û�service�ӿ�
 * @author ����
 *
 */
@Transactional
public interface UserService {

	void regist(User user);


	User login(User user);


	List<User> findAll();


}
