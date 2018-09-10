package com.zzuli.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.UserDao;
import com.zzuli.crm.domain.User;
import com.zzuli.crm.service.UserService;
import com.zzuli.crm.utils.MD5Utils;

/**
 * �û�serviceʵ����
 * 
 * @author ����
 *
 */
@Transactional
public class UserServiceImpl implements UserService {
	// ע��UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// ע�Ṧ��
	@Override
	public void regist(User user) {
		// ��������м���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.save(user);
	}

	// ��¼����
	@Override
	public User login(User user) {
		// ��������м���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		// ����dao��
		User user2 = userDao.get(user);

		return user2;
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

}
