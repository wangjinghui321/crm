package com.zzuli.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zzuli.crm.dao.UserDao;
import com.zzuli.crm.domain.User;

/**
 * 用户dao接口实现类
 * 
 * @author 阿辉
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	// 保存用户数据到数据库
	@Override
	public void save(User user) {
		// hibernate模板
		getHibernateTemplate().save(user);

	}

	
	@Override
	public User get(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate()
				.find("from User where user_code=? and user_password=?",user.getUser_code(), user.getUser_password());
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<User> findAll() {
		
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

}
