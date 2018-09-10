package com.zzuli.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zzuli.crm.dao.UserDao;
import com.zzuli.crm.domain.User;
import com.zzuli.crm.service.UserService;
import com.zzuli.crm.utils.MD5Utils;

/**
 * 用户service实现类
 * 
 * @author 阿辉
 *
 */
@Transactional
public class UserServiceImpl implements UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 注册功能
	@Override
	public void regist(User user) {
		// 对密码进行加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.save(user);
	}

	// 登录功能
	@Override
	public User login(User user) {
		// 对密码进行加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		
		// 调用dao层
		User user2 = userDao.get(user);

		return user2;
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

}
