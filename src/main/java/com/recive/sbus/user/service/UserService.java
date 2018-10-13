package com.recive.sbus.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recive.sbus.user.dao.UserDao;
import com.recive.sbus.user.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean verifyLogin(User oUser) {

		List<User> userList = userDao.findByUsernameAndPassword(oUser.getUsername(), oUser.getPassword());
		return userList.size() > 0;
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		List<User> userList = userDao.findByUsername(username);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	public User getUser(long userid) {
		return userDao.findOne(userid);
	}

	public User updateUser(User oUser) {
		User user = userDao.save(oUser);
		return user;
	}
}
