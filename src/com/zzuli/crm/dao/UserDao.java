package com.zzuli.crm.dao;

import java.util.List;

import com.zzuli.crm.domain.User;

/**
 * �û�dao�ӿ�
 * @author ����
 *
 */
public interface UserDao {

	void save(User user);

	User get(User user);

	List<User> findAll();


}
