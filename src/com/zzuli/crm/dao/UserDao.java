package com.zzuli.crm.dao;

import java.util.List;

import com.zzuli.crm.domain.User;

/**
 * 用户dao接口
 * @author 阿辉
 *
 */
public interface UserDao {

	void save(User user);

	User get(User user);

	List<User> findAll();


}
