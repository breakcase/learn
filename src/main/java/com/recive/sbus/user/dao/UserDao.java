package com.recive.sbus.user.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recive.sbus.user.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	final static String TABLENAME = "User";

	public List<User> findByUsernameAndPassword(String username, String password);

	public List<User> findByUsername(String username);
}
