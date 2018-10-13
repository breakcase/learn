package com.recive.sbus.login.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recive.sbus.login.dao.LoginDao;
import com.recive.sbus.user.domain.User;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public boolean verifyLogin(User oUser) {

		List<User> userList = loginDao.findByUsernameAndPassword(oUser.getUsername(), oUser.getPassword());
		return userList.size() > 0;
	}

	public boolean registUser(User oUser) {
		User user = loginDao.save(oUser);
		return user.getId() > 0;
	}

	public boolean changePwd(String username, String password) {
		return loginDao.changePwd(username, password) > 0;
	}

	public void updateLoginTimeAndCount(String username, int count) {
		loginDao.updateLoginTimeAndCount(username, new Date(), count);
	}

}
